package tw.org.cmaas.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddSettingDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutSettingDTO;
import tw.org.cmaas.pojo.VO.SettingVO;
import tw.org.cmaas.pojo.entity.Setting;

@Mapper(componentModel = "spring")
public interface SettingConvert {

	Setting addDTOToEntity(AddSettingDTO addSettingDTO);

	Setting putDTOToEntity(PutSettingDTO putSettingDTO);
	
	SettingVO entityToVO(Setting setting);
	
	List<SettingVO> entityListToVOList(List<Setting> settingList);
	
}
