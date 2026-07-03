package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddScheduleEmailRecordDTO;
import tw.org.cmaas.pojo.entity.ScheduleEmailRecord;

@Mapper(componentModel = "spring")
public interface ScheduleEmailRecordConvert {

	ScheduleEmailRecord addDTOToEntity(AddScheduleEmailRecordDTO addScheduleEmailRecordDTO);

	ScheduleEmailRecord copyEntity(ScheduleEmailRecord scheduleEmailRecord);
	
}
