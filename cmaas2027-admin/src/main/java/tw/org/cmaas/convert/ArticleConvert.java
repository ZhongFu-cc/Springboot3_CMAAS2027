package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddArticleDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutArticleDTO;
import tw.org.cmaas.pojo.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleConvert {

	Article addDTOToEntity(AddArticleDTO insertArticleDTO);

	Article putDTOToEntity(PutArticleDTO updateArticleDTO);
	
	Article copyEntity(Article article);
	
}
