package tw.org.topbs.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.addEntityDTO.AddOrdersItemDTO;
import tw.org.topbs.pojo.DTO.putEntityDTO.PutOrdersItemDTO;
import tw.org.topbs.pojo.VO.OrdersItemVO;
import tw.org.topbs.pojo.entity.OrdersItem;

@Mapper(componentModel = "spring")
public interface OrdersItemConvert {

	OrdersItem addDTOToEntity(AddOrdersItemDTO addOrdersItemDTO);

	OrdersItem putDTOToEntity(PutOrdersItemDTO putOrdersItemDTO);
	
	OrdersItemVO entityToVO(OrdersItem ordersItem);
	
	List<OrdersItemVO> entityListToVOList(List<OrdersItem> ordersItemList);
	
}
