package tw.org.cmaas.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddCheckinRecordDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutCheckinRecordDTO;
import tw.org.cmaas.pojo.VO.CheckinRecordVO;
import tw.org.cmaas.pojo.entity.CheckinRecord;
import tw.org.cmaas.pojo.excelPojo.AttendeesExcel;
import tw.org.cmaas.pojo.excelPojo.CheckinRecordExcel;

@Mapper(componentModel = "spring")
public interface CheckinRecordConvert {

	CheckinRecord addDTOToEntity(AddCheckinRecordDTO addCheckinRecordDTO);

	CheckinRecord putDTOToEntity(PutCheckinRecordDTO putCheckinRecordDTO);

	CheckinRecordVO entityToVO(CheckinRecord checkinRecord);

	List<CheckinRecordVO> entityListToVOList(List<CheckinRecord> checkinRecordList);

	CheckinRecordExcel attendeesExcelToCheckinRecordExcel(AttendeesExcel attendeesExcel);
	
}
