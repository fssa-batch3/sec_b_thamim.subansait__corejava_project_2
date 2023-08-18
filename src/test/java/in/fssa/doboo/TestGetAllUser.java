package in.fssa.doboo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.UserService;

public class TestGetAllUser {
	
	@Test
	public void testGetAllUsers() {

		UserService userService = new UserService();
		assertDoesNotThrow(() -> {
			Set<UserEntity> users = userService.getAll();
			for(UserEntity u : users) {
				System.out.println(u);			
			}

		});
	}
	
	@Test
	public void testFindUserWithValidInput() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			System.out.println(userService.findById(2));
		});
	}

	// Id is Invalid
	@Test
	public void testFindUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findById(0);
		});

		String expectedMessage = "Id can't be less than or equal to zero";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserWithCheckIdPresent() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findById(10);
		});

		String expectedMessage = "Id doesn't exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}