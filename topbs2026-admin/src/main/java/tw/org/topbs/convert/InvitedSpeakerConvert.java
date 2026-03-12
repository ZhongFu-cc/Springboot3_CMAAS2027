package tw.org.topbs.convert;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.addEntityDTO.AddInvitedSpeakerDTO;
import tw.org.topbs.pojo.DTO.putEntityDTO.PutInvitedSpeakerDTO;
import tw.org.topbs.pojo.entity.InvitedSpeaker;

@Mapper(componentModel = "spring")
public interface InvitedSpeakerConvert {

	InvitedSpeaker addDTOToEntity(AddInvitedSpeakerDTO addInvitedSpeakerDTO);

	InvitedSpeaker putDTOToEntity(PutInvitedSpeakerDTO putInvitedSpeakerDTO);
	
	
}
