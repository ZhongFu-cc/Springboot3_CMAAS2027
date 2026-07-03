package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddPublishFileDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutPublishFileDTO;
import tw.org.cmaas.pojo.entity.PublishFile;

@Mapper(componentModel = "spring")
public interface PublishFileConvert {

	PublishFile addDTOToEntity(AddPublishFileDTO addPublishFileDTO);

	PublishFile putDTOToEntity(PutPublishFileDTO putPublishFileDTO);

}
