package tw.org.cmaas.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tw.org.cmaas.pojo.DTO.SendEmailDTO;
import tw.org.cmaas.pojo.entity.ScheduleEmailTask;

@Mapper(componentModel = "spring")
public interface ScheduleEmailTaskConvert {

	// DTO 名稱不同的屬性名轉換
	@Mapping(source = "scheduleTime", target = "startTime")
	ScheduleEmailTask DTOToEntity(SendEmailDTO sendEmailDTO);

	ScheduleEmailTask copyEntity(ScheduleEmailTask scheduleEmailTask);

}
