package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddInvitedSpeakerDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutInvitedSpeakerDTO;
import tw.org.cmaas.pojo.entity.InvitedSpeaker;

@Mapper(componentModel = "spring")
public interface InvitedSpeakerConvert {

	InvitedSpeaker addDTOToEntity(AddInvitedSpeakerDTO addInvitedSpeakerDTO);

	InvitedSpeaker putDTOToEntity(PutInvitedSpeakerDTO putInvitedSpeakerDTO);
	
	
}
