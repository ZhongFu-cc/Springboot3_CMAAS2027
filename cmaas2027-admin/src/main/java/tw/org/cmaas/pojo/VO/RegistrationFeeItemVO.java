package tw.org.cmaas.pojo.VO;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationFeeItemVO {

	@Schema(description = "項目代號,例如 MAIN、WSA001、ANNUAL_DUES、CME")
	private String code;

	@Schema(description = "項目名稱,給使用者看的")
	private String name;

	@Schema(description = "項目金額(台幣)")
	private BigDecimal amount;

}
