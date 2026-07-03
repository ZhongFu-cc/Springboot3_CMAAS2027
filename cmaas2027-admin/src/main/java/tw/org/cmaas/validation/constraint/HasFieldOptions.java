package tw.org.cmaas.validation.constraint;

import tw.org.cmaas.enums.FormFieldTypeEnum;
import tw.org.cmaas.pojo.DTO.FormFieldOptionDTO;

public interface HasFieldOptions {

	public FormFieldTypeEnum getFieldType();
	
	public FormFieldOptionDTO getOptions();
	
}
