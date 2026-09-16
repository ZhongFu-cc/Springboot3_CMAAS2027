package tw.org.cmaas.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tw.org.cmaas.config.RegistrationFeeConfig;
import tw.org.cmaas.constants.WorkshopConstants;
import tw.org.cmaas.enums.MemberCategoryEnum;
import tw.org.cmaas.enums.MembershipDuesEnum;
import tw.org.cmaas.enums.RegistrationPhaseEnum;
import tw.org.cmaas.exception.RegistrationInfoException;
import tw.org.cmaas.pojo.BO.RegistrationFeeBO;
import tw.org.cmaas.pojo.VO.RegistrationFeeItemVO;
import tw.org.cmaas.pojo.VO.RegistrationFeeVO;
import tw.org.cmaas.service.SettingService;
import tw.org.cmaas.utils.CountryUtil;

/**
 * 個人報名費用計算<br>
 * 正式註冊(產生訂單) 與 註冊前的費用預覽 共用同一套規則
 *
 */
@Component
@RequiredArgsConstructor
public class RegistrationFeeCalculator {

	/** 常年會費項目代號 */
	public static final String ANNUAL_DUES_CODE = "ANNUAL_DUES";

	/** 中醫師教育學分項目代號 */
	public static final String CME_CODE = "CME";

	// 臨時創建Workshop處理,工作坊費用
	private static final Map<String, BigDecimal> WORKSHOP_FEE_MAP = Map.of("WSA001", BigDecimal.valueOf(5000), "WSA002",
			BigDecimal.valueOf(5000), "WSB001", BigDecimal.valueOf(5000), "WSB002", BigDecimal.valueOf(5000));

	// 常年會費，不分會員身份都是同一個價錢
	private static final BigDecimal ANNUAL_DUES_FEE = BigDecimal.valueOf(1000);

	// 申請中醫師教育學分的費用
	private static final BigDecimal CME_FEE = BigDecimal.valueOf(800);

	private final RegistrationFeeConfig registrationFeeConfig;
	private final SettingService settingService;

	/**
	 * 依報名條件計算費用明細及總額
	 *
	 * @param registrationFeeBO
	 * @return
	 */
	public RegistrationFeeVO calculate(RegistrationFeeBO registrationFeeBO) {

		List<RegistrationFeeItemVO> items = new ArrayList<>();

		// 1.拿到身分
		MemberCategoryEnum memberCategoryEnum = MemberCategoryEnum.fromValue(registrationFeeBO.getCategory());

		// 2.報名場次,主會議也放在workshopCodes內
		List<String> workshopList = registrationFeeBO.getWorkshopCodes() == null ? List.of()
				: registrationFeeBO.getWorkshopCodes();

		boolean joinMainConference = workshopList.contains(WorkshopConstants.MAIN_CONFERENCE_CODE);

		// 3.主會議報名費,透過階段、國籍、身分,從 project.registration-fee 得到金額,有參加主會議才收
		if (joinMainConference) {
			RegistrationPhaseEnum registrationPhaseEnum = settingService.getRegistrationPhaseEnum();
			String country = CountryUtil.getTaiwanOrForeign(registrationFeeBO.getCountry());

			BigDecimal mainConferenceFee = registrationFeeConfig.getFee(registrationPhaseEnum.getValue(), country,
					memberCategoryEnum.getConfigKey());

			items.add(new RegistrationFeeItemVO(WorkshopConstants.MAIN_CONFERENCE_CODE,
					WorkshopConstants.WORKSHOP_NAME_MAP.get(WorkshopConstants.MAIN_CONFERENCE_CODE),
					mainConferenceFee));
		}

		// 4.工作坊報名費,主會議的金額不從這裡算
		for (String ws : workshopList) {

			if (WorkshopConstants.MAIN_CONFERENCE_CODE.equals(ws)) {
				continue;
			}

			BigDecimal fee = WORKSHOP_FEE_MAP.get(ws);
			if (fee == null) {
				throw new RegistrationInfoException("不合規的workshop資訊");
			}

			items.add(new RegistrationFeeItemVO(ws, WorkshopConstants.WORKSHOP_NAME_MAP.getOrDefault(ws, ws), fee));
		}

		// 5.常年會費,選填,只有選擇「本次報名繳交」的人才需要於這次報名一併收取
		String membershipDuesStatus = registrationFeeBO.getMembershipDuesStatus();

		if (membershipDuesStatus != null && !membershipDuesStatus.isBlank()) {

			MembershipDuesEnum membershipDuesEnum;
			try {
				membershipDuesEnum = MembershipDuesEnum.fromValue(membershipDuesStatus);
			} catch (IllegalArgumentException e) {
				throw new RegistrationInfoException("不合規的常年會費繳交資訊");
			}

			if (MembershipDuesEnum.PAY_ON_REGISTRATION == membershipDuesEnum) {
				items.add(new RegistrationFeeItemVO(ANNUAL_DUES_CODE, "常年會費", ANNUAL_DUES_FEE));
			}
		}

		// 6.中醫師教育學分,只有參加主會議才能申請,且必須填寫中醫師證號
		if (Integer.valueOf(1).equals(registrationFeeBO.getApplyForCME())) {

			if (!joinMainConference) {
				throw new RegistrationInfoException("未參加主會議, 無法申請中醫師教育學分");
			}

			String professionalNumber = registrationFeeBO.getProfessionalNumber();
			if (professionalNumber == null || professionalNumber.isBlank()) {
				throw new RegistrationInfoException("申請中醫師教育學分時, 必須填寫中醫師證號");
			}

			items.add(new RegistrationFeeItemVO(CME_CODE, "中醫師教育學分", CME_FEE));
		}

		// 7.加總
		BigDecimal totalAmount = items.stream().map(RegistrationFeeItemVO::getAmount).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		RegistrationFeeVO registrationFeeVO = new RegistrationFeeVO();
		registrationFeeVO.setItems(items);
		registrationFeeVO.setTotalAmount(totalAmount);

		return registrationFeeVO;
	}

	/**
	 * 將資料庫內以逗號分隔的workshopCodes 轉成List
	 *
	 * @param workshopCodes 例如 "WSA001,WSA002,MAIN"
	 * @return
	 */
	public static List<String> parseWorkshopCodes(String workshopCodes) {
		if (workshopCodes == null || workshopCodes.isBlank()) {
			return List.of();
		}
		return Arrays.stream(workshopCodes.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();
	}

}
