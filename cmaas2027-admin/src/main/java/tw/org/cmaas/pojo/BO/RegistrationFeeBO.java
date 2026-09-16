package tw.org.cmaas.pojo.BO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 報名費用計算的輸入條件<br>
 * 正式註冊(Member) 與 費用預覽(DTO) 都轉成這個物件後,交給 RegistrationFeeCalculator 計算
 *
 */
@Data
@Builder
public class RegistrationFeeBO {

	/** 國家,會再轉成 taiwan / foreign */
	private String country;

	/** 會員身份,對應 MemberCategoryEnum */
	private Integer category;

	/** 報名場次代號,主會議(MAIN) 與 工作坊 都放在這 */
	private List<String> workshopCodes;

	/** 是否繳交常年會費,對應 MembershipDuesEnum,選填 */
	private String membershipDuesStatus;

	/** 是否申請中醫師教育學分, 1為申請, 0為不申請 */
	private Integer applyForCME;

	/** 中醫師證號,申請教育學分時必填 */
	private String professionalNumber;

}
