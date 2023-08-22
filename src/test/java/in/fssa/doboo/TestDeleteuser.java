package in.fssa.doboo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.service.UserService;

public class TestDeleteuser {
//	Delete User with valid Input
	@Test
	public void testDeleteUserWithValidInput() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.delete(1);
		});
	}

	// Id is Invalid
	@Test
	public void testDeleteUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(0);
		});

		String expectedMessage = "Id can't be less than or equal to zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserCheckIdPresent() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(100);
		});

		String expectedMessage = "Id doesn't exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}