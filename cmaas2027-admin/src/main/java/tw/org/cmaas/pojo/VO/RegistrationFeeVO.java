package tw.org.cmaas.pojo.VO;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegistrationFeeVO {

	@Schema(description = "費用明細")
	private List<RegistrationFeeItemVO> items;

	@Schema(description = "總金額(台幣)")
	private BigDecimal totalAmount;

}
