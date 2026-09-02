package tw.org.cmaas.strategy.project;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tw.org.cmaas.config.RegistrationFeeConfig;
import tw.org.cmaas.enums.MemberCategoryEnum;
import tw.org.cmaas.enums.MembershipDuesEnum;
import tw.org.cmaas.enums.RegistrationPhaseEnum;
import tw.org.cmaas.exception.RegistrationInfoException;
import tw.org.cmaas.helper.TagAssignmentHelper;
import tw.org.cmaas.pojo.DTO.EmailBodyContent;
import tw.org.cmaas.pojo.entity.Member;
import tw.org.cmaas.service.AsyncService;
import tw.org.cmaas.service.MemberTagService;
import tw.org.cmaas.service.NotificationService;
import tw.org.cmaas.service.OrdersService;
import tw.org.cmaas.service.SettingService;
import tw.org.cmaas.service.TagService;
import tw.org.cmaas.utils.CountryUtil;

@Component
@RequiredArgsConstructor
public class PostpaidModeStrategy implements ProjectModeStrategy {

	@Value("${project.name}")
	private String PROJECT_NAME;

	@Value("${project.banner-url}")
	private String BANNER_PHOTO_URL;

	@Value("${project.group-size}")
	private int GROUP_SIZE;

	private final RegistrationFeeConfig registrationFeeConfig;
	private final TagAssignmentHelper tagAssignmentHelper;
	private final MemberTagService memberTagService;
	private final TagService tagService;
	private final OrdersService ordersService;
	private final SettingService settingService;
	private final NotificationService notificationService;
	private final AsyncService asyncService;

	// 臨時創建Workshop處理
	private static final Map<String, BigDecimal> WORKSHOP_FEE_MAP = Map.of("WSA001", BigDecimal.valueOf(5000), "WSA002",
			BigDecimal.valueOf(5000), "WSB001", BigDecimal.valueOf(5000), "WSB002", BigDecimal.valueOf(5000));

	// 主會議的workshop代號
	private static final String MAIN_CONFERENCE_CODE = "MAIN";

	// 常年會費，不分會員身份都是同一個價錢
	private static final BigDecimal ANNUAL_DUES_FEE = BigDecimal.valueOf(1000);

	// 申請中醫師教育學分的費用
	private static final BigDecimal CME_FEE = BigDecimal.valueOf(800);

	@Override
	public void handleRegistration(Member member) {
		// 1.拿到配置設定,知道處於哪個註冊階段
		RegistrationPhaseEnum registrationPhaseEnum = settingService.getRegistrationPhaseEnum();

		// 2.透過Country 拿到國籍 , 只分國內國外,
		String country = CountryUtil.getTaiwanOrForeign(member.getCountry());

		// 3.拿到身分
		MemberCategoryEnum memberCategoryEnum = MemberCategoryEnum.fromValue(member.getCategory());

		// 4.解析報名的場次,主會議也放在workshopCodes內
		String workshopCodes = member.getWorkshopCodes();

		List<String> workshopList = (workshopCodes == null || workshopCodes.isBlank()) ? List.of()
				: Arrays.stream(workshopCodes.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();

		boolean joinMainConference = workshopList.contains(MAIN_CONFERENCE_CODE);

		// 5.主會議報名費,透過階段、國籍、身分,從 project.registration-fee 得到金額,有參加主會議才收
		BigDecimal mainConferenceFee = joinMainConference
				? registrationFeeConfig.getFee(registrationPhaseEnum.getValue(), country,
						memberCategoryEnum.getConfigKey())
				: BigDecimal.ZERO;

		// 臨時新增 Workshop的報名費計算
		// 6. workshop fee 加總,主會議的金額不從這裡算
		BigDecimal workshopFee = BigDecimal.ZERO;

		for (String ws : workshopList) {

			if (MAIN_CONFERENCE_CODE.equals(ws)) {
				continue;
			}

			BigDecimal fee = WORKSHOP_FEE_MAP.get(ws);
			if (fee != null) {
				workshopFee = workshopFee.add(fee);
			} else {
				throw new RegistrationInfoException("不合規的workshop資訊");
			}
		}

		// 6-1. 常年會費，選填，只有選擇「繳交常年會費$1000元」的人才需要於這次報名一併收取
		BigDecimal annualDuesFee = BigDecimal.ZERO;
		String membershipDuesStatus = member.getMembershipDuesStatus();

		if (membershipDuesStatus != null && !membershipDuesStatus.isBlank()) {

			MembershipDuesEnum membershipDuesEnum;
			try {
				membershipDuesEnum = MembershipDuesEnum.fromValue(membershipDuesStatus);
			} catch (IllegalArgumentException e) {
				throw new RegistrationInfoException("不合規的常年會費繳交資訊");
			}

			if (MembershipDuesEnum.PAY_ON_REGISTRATION == membershipDuesEnum) {
				annualDuesFee = ANNUAL_DUES_FEE;
			}
		}

		// 6-2. 中醫師教育學分，只有參加主會議才能申請，且必須填寫中醫師證號
		BigDecimal cmeFee = BigDecimal.ZERO;

		if (Integer.valueOf(1).equals(member.getApplyForCME())) {

			if (!joinMainConference) {
				throw new RegistrationInfoException("未參加主會議, 無法申請中醫師教育學分");
			}

			String professionalNumber = member.getProfessionalNumber();
			if (professionalNumber == null || professionalNumber.isBlank()) {
				throw new RegistrationInfoException("申請中醫師教育學分時, 必須填寫中醫師證號");
			}

			cmeFee = CME_FEE;
		}

		// 7. final fee (主會議報名費 + 工作坊報名 + 常年會費 + 中醫師教育學分)
		BigDecimal totalFee = mainConferenceFee.add(workshopFee).add(annualDuesFee).add(cmeFee);

		// 5.如果註冊費金額為0 , 創建免費註冊費訂單 , 會自動為繳費完畢的情況
		if (totalFee.compareTo(BigDecimal.ZERO) == 0) {
			ordersService.createFreeRegistrationOrder(member);
		} else {
			// 創建付費註冊費訂單
			ordersService.createRegistrationOrder(totalFee, member);
			// 獲取當下「未付款」的Member群體的Index，賦予「未繳費」標籤
			tagAssignmentHelper.assignTag(member.getMemberId(), ordersService::getNotPaidRegistrationOrderGroupIndex,
					tagService::getOrCreateNotPaidGroupTag, memberTagService::addMemberTag);
		}

		// 6.創建註冊成功通知信件內容
		EmailBodyContent registrationSuccessContent = notificationService.generateRegistrationSuccessContent(member,
				BANNER_PHOTO_URL);

		// 7.異步寄送信件
		asyncService.sendCommonEmail(member.getEmail(), PROJECT_NAME + " Registration Successful",
				registrationSuccessContent.getHtmlContent(), registrationSuccessContent.getPlainTextContent());

	}

	@Override
	public void handleGroupRegistration(Member member, boolean isMaster, BigDecimal totalFee) {
		if (isMaster) {
			// Master 負責付錢
			ordersService.createGroupRegistrationOrder(totalFee, member);
			// 獲取當下「未付款」的Member群體的Index，賦予「未繳費」標籤
			tagAssignmentHelper.assignTag(member.getMemberId(), ordersService::getNotPaidRegistrationOrderGroupIndex,
					tagService::getOrCreateNotPaidGroupTag, memberTagService::addMemberTag);
		} else {
			// Slave 不付錢，0元訂單，未付款
			ordersService.createFreeGroupRegistrationOrder(member);
			// 獲取當下「未付款」的Member群體的Index，賦予「未繳費」標籤
			tagAssignmentHelper.assignTag(member.getMemberId(), ordersService::getNotPaidRegistrationOrderGroupIndex,
					tagService::getOrCreateNotPaidGroupTag, memberTagService::addMemberTag);
		}

		// 2.產生系統團體報名通知信
		EmailBodyContent groupRegistrationSuccessContent = notificationService
				.generateGroupRegistrationSuccessContent(member, BANNER_PHOTO_URL);

		// 3.寄信個別通知會員，團體報名成功
		asyncService.sendCommonEmail(member.getEmail(), PROJECT_NAME + " GROUP Registration Successful",
				groupRegistrationSuccessContent.getHtmlContent(),
				groupRegistrationSuccessContent.getPlainTextContent());

	}

	@Override
	public void handlePaperSubmission(Long memberId) {
		// 「後付費」 模式,不用去攔截他投稿，但是注意最終是否能發表則是看有沒有繳註冊費

	}

}
