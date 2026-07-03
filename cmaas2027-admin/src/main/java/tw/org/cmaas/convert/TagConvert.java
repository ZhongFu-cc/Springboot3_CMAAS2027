package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddTagDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutTagDTO;
import tw.org.cmaas.pojo.entity.Tag;

@Mapper(componentModel = "spring")
public interface TagConvert {

	Tag addDTOToEntity(AddTagDTO addTagDTO);
	
	Tag putDTOToEntity(PutTagDTO updateTagDTO);
	
}
