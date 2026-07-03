package tw.org.cmaas.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddOrdersItemDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutOrdersItemDTO;
import tw.org.cmaas.pojo.VO.OrdersItemVO;
import tw.org.cmaas.pojo.entity.OrdersItem;

@Mapper(componentModel = "spring")
public interface OrdersItemConvert {

	OrdersItem addDTOToEntity(AddOrdersItemDTO addOrdersItemDTO);

	OrdersItem putDTOToEntity(PutOrdersItemDTO putOrdersItemDTO);
	
	OrdersItemVO entityToVO(OrdersItem ordersItem);
	
	List<OrdersItemVO> entityListToVOList(List<OrdersItem> ordersItemList);
	
}
