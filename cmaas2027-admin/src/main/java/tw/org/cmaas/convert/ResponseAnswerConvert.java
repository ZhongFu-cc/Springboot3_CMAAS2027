package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddResponseAnswerDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutResponseAnswerDTO;
import tw.org.cmaas.pojo.entity.ResponseAnswer;

@Mapper(componentModel = "spring")
public interface ResponseAnswerConvert {

    // 宣告默認映射 , 告訴 MapStruct 如何把 CommonStatusEnum → Integer
//    default Integer commonStatusEnumMapToInteger(CommonStatusEnum status) {
//        return status == null ? null : status.getValue();
//    }
	
	ResponseAnswer addDTOToEntity(AddResponseAnswerDTO responseAnswerDTO);
	
	ResponseAnswer putDTOToEntity(PutResponseAnswerDTO putResponseAnswerDTO);
	
	PutResponseAnswerDTO entityToPutDTO(ResponseAnswer responseAnswer);
	
	
}
