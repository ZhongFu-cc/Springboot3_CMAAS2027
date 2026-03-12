package tw.org.topbs.convert;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.addEntityDTO.AddScheduleEmailRecordDTO;
import tw.org.topbs.pojo.entity.ScheduleEmailRecord;

@Mapper(componentModel = "spring")
public interface ScheduleEmailRecordConvert {

	ScheduleEmailRecord addDTOToEntity(AddScheduleEmailRecordDTO addScheduleEmailRecordDTO);

	ScheduleEmailRecord copyEntity(ScheduleEmailRecord scheduleEmailRecord);
	
}
