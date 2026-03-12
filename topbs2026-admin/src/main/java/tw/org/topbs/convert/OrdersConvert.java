package tw.org.topbs.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.addEntityDTO.AddOrdersDTO;
import tw.org.topbs.pojo.DTO.putEntityDTO.PutOrdersDTO;
import tw.org.topbs.pojo.VO.OrdersVO;
import tw.org.topbs.pojo.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrdersConvert {

	Orders addDTOToEntity(AddOrdersDTO addOrdersDTO);

	Orders putDTOToEntity(PutOrdersDTO putOrdersDTO);
	
	OrdersVO entityToVO(Orders orders);
	
	List<OrdersVO> entityListToVOList(List<Orders> ordersList);
	
}
