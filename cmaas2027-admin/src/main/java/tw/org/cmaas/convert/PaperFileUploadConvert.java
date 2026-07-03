package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddPaperFileUploadDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutPaperFileUploadDTO;
import tw.org.cmaas.pojo.entity.PaperFileUpload;

@Mapper(componentModel = "spring")
public interface PaperFileUploadConvert {

	PaperFileUpload addDTOToEntity(AddPaperFileUploadDTO addPaperFileUploadDTO);

	PaperFileUpload putDTOToEntity(PutPaperFileUploadDTO putPaperFileUploadDTO);
	
	
	
}
