package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.model.Orders;
import in.fssa.doboo.service.OrderService;

public class TestGetAllOrdersByUserId {
	@Test
	public void testGetAllOrdersByUserId() {
	
		OrderService orderService = new OrderService();		
		assertDoesNotThrow(() -> {
			List<Orders> orders = orderService.getOrdersByUserId(33);
			for(Orders order : orders) {
				System.out.println(order);			
			}
			
		});
	}

}
