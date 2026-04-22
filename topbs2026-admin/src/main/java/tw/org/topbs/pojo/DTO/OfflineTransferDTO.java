package tw.org.topbs.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OfflineTransferDTO {

	@NotNull
	@Schema(description = "會員ID")
	private Long memberId;
	
	@NotBlank
	@Schema(description = "匯款帳號-後五碼  台灣會員使用")
	private String remitAccountLast5;
	
}
