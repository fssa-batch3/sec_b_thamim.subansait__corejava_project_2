package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ServiceException;

import in.fssa.doboo.model.Orders;
import in.fssa.doboo.service.OrderService;



public class TestCreateOrder {
	@Test
	public void testCreateOrderWithValidInput() {
	
		OrderService orderService = new OrderService();
		Orders order = new Orders();
		order.setTrackId(3);
		order.setUserId(33);		
		assertDoesNotThrow(() -> {
			orderService.createOrder(order);
		});
	}

	@Test

	public void testCreateOrderWithInValidInput() {
		OrderService orderService = new OrderService();
		Orders order = new Orders();
		order.setTrackId(3);
		order.setUserId(100);	
		Exception exception = assertThrows(ServiceException.class, () -> {
			orderService.createOrder(order);
		});
		String expectedMessage = "Id doesn't exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test

	public void testCreateOrderWithInValidInput2() {
		OrderService orderService = new OrderService();
		Orders order = new Orders();
		order.setTrackId(100);
		order.setUserId(33);	
		Exception exception = assertThrows(ServiceException.class, () -> {
			orderService.createOrder(order);
		});
		String expectedMessage = "track not found";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}
