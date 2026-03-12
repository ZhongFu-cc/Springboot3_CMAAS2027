package tw.org.topbs.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.addEntityDTO.AddCheckinRecordDTO;
import tw.org.topbs.pojo.DTO.putEntityDTO.PutCheckinRecordDTO;
import tw.org.topbs.pojo.VO.CheckinRecordVO;
import tw.org.topbs.pojo.entity.CheckinRecord;
import tw.org.topbs.pojo.excelPojo.AttendeesExcel;
import tw.org.topbs.pojo.excelPojo.CheckinRecordExcel;

@Mapper(componentModel = "spring")
public interface CheckinRecordConvert {

	CheckinRecord addDTOToEntity(AddCheckinRecordDTO addCheckinRecordDTO);

	CheckinRecord putDTOToEntity(PutCheckinRecordDTO putCheckinRecordDTO);

	CheckinRecordVO entityToVO(CheckinRecord checkinRecord);

	List<CheckinRecordVO> entityListToVOList(List<CheckinRecord> checkinRecordList);

	CheckinRecordExcel attendeesExcelToCheckinRecordExcel(AttendeesExcel attendeesExcel);
	
}
