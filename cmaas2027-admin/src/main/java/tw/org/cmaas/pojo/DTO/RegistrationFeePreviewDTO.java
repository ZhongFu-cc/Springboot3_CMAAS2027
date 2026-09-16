package tw.org.cmaas.pojo.DTO;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 註冊前的費用預覽,欄位與 AddMemberDTO 中會影響金額的欄位一致
 * 
 */
@Data
public class RegistrationFeePreviewDTO {

	@NotBlank
	@Schema(description = "國家")
	private String country;

	@NotNull
	@Schema(description = "用於分類會員資格, 1為 Member，2為 Permanent-Member，3為 Non-Member，4為 MVP，5為 Speaker，6為 Moderator，7為 Staff")
	private Integer category;

	@Schema(description = "報名場次代號,例如:MAIN,WSA001,WSA002,WSB001,WSB002")
	private List<String> workshopCodes;

	@Schema(description = "是否繳交常年會費(選填), 可選值:「本次報名繳交」、「已繳交116年會費」")
	private String membershipDuesStatus;

	@NotNull
	@Schema(description = "是否申請中醫師教育學分, 1為申請, 0為不申請")
	private Integer applyForCME;

	@Schema(description = "專業證照號碼(中醫師證號),申請教育學分時必填")
	private String professionalNumber;

}
