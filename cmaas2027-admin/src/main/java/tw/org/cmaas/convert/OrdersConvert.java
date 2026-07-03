package tw.org.cmaas.convert;

import java.util.List;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.addEntityDTO.AddOrdersDTO;
import tw.org.cmaas.pojo.DTO.putEntityDTO.PutOrdersDTO;
import tw.org.cmaas.pojo.VO.OrdersVO;
import tw.org.cmaas.pojo.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrdersConvert {

	Orders addDTOToEntity(AddOrdersDTO addOrdersDTO);

	Orders putDTOToEntity(PutOrdersDTO putOrdersDTO);
	
	OrdersVO entityToVO(Orders orders);
	
	List<OrdersVO> entityListToVOList(List<Orders> ordersList);
	
}
