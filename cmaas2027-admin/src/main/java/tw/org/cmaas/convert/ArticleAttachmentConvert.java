package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddArticleAttachmentDTO;
import tw.org.cmaas.pojo.entity.ArticleAttachment;

@Mapper(componentModel = "spring")
public interface ArticleAttachmentConvert {
	ArticleAttachment addDTOToEntity(AddArticleAttachmentDTO addArticleAttachmentDTO);
}
