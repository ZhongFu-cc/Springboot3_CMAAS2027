package tw.org.cmaas.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import tw.org.cmaas.enums.MemberCategoryEnum;
import tw.org.cmaas.enums.OrderStatusEnum;
import tw.org.cmaas.pojo.BO.MemberExcelRaw;
import tw.org.cmaas.pojo.DTO.AddGroupMemberDTO;
import tw.org.cmaas.pojo.DTO.AddMemberForAdminDTO;
import tw.org.cmaas.pojo.DTO.addEntityDTO.AddMemberDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutMemberDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutMemberForAdminDTO;
import tw.org.cmaas.pojo.VO.MemberOrderVO;
import tw.org.cmaas.pojo.VO.MemberTagVO;
import tw.org.cmaas.pojo.VO.MemberVO;
import tw.org.cmaas.pojo.entity.Member;
import tw.org.cmaas.pojo.excelPojo.MemberExcel;

@Mapper(componentModel = "spring")
public interface MemberConvert {

	@Mapping(source = "workshopCodes", target = "workshopCodes", qualifiedByName = "convertListToString")
	Member addDTOToEntity(AddMemberDTO addMemberDTO);

	Member addGroupDTOToEntity(AddGroupMemberDTO addGroupMemberDTO);

	Member forAdminAddDTOToEntity(AddMemberForAdminDTO addMemberForAdminDTO);

	Member putDTOToEntity(PutMemberDTO putMemberDTO);

	Member putForAdminDTOToEntity(PutMemberForAdminDTO putMemberForAdminDTO);

	MemberVO entityToVO(Member member);

	List<MemberVO> entityListToVOList(List<Member> memberList);

	MemberTagVO entityToMemberTagVO(Member member);

	MemberOrderVO entityToMemberOrderVO(Member member);

	//實體類先轉成BO，這個BO之後要setStatus 手動塞入訂單狀態的
	MemberExcelRaw entityToExcelRaw(Member member);

	// BO對象轉成真正的Excel 對象
	@Mapping(target = "status", source = "status", qualifiedByName = "convertStatus")
	@Mapping(target = "category", source = "category", qualifiedByName = "convertCategory")
	@Mapping(target = "applyForCME", source = "applyForCME", qualifiedByName = "convertApplyForCME")
	MemberExcel memberExcelRawToExcel(MemberExcelRaw memberExcelRaw);

	@Named("convertStatus")
	default String convertStatus(Integer status) {
		return OrderStatusEnum.fromValue(status).getLabelZh();
	}

	@Named("convertCategory")
	default String convertCategory(Integer category) {
		return MemberCategoryEnum.fromValue(category).getLabelZh();
	}

	@Named("convertApplyForCME")
	default String convertApplyForCME(Integer applyForCME) {
		return Integer.valueOf(1).equals(applyForCME) ? "是" : "否";
	}

	@Named("convertListToString")
	default String convertListToString(List<String> value) {

		if (value == null || value.isEmpty()) {
			return null;
		}

		return value.stream()
				.filter(v -> v != null && !v.trim().isEmpty())
				.map(String::trim)
				.collect(Collectors.joining(","));
	}

}
