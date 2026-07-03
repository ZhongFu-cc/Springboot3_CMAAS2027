package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddEmailTemplateDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutEmailTemplateDTO;
import tw.org.cmaas.pojo.entity.EmailTemplate;

@Mapper(componentModel = "spring")
public interface EmailTemplateConvert {

	EmailTemplate insertDTOToEntity(AddEmailTemplateDTO addArticleDTO);

	EmailTemplate updateDTOToEntity(PutEmailTemplateDTO updateArticleDTO);
	
}
