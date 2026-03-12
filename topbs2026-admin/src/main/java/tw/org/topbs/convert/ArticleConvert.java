package tw.org.topbs.convert;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.addEntityDTO.AddArticleDTO;
import tw.org.topbs.pojo.DTO.putEntityDTO.PutArticleDTO;
import tw.org.topbs.pojo.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleConvert {

	Article addDTOToEntity(AddArticleDTO insertArticleDTO);

	Article putDTOToEntity(PutArticleDTO updateArticleDTO);
	
	Article copyEntity(Article article);
	
}
