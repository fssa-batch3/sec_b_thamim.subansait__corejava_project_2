package in.fssa.doboo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.UserEntity;

import in.fssa.doboo.service.*;
import in.fssa.doboo.util.RandomValue;
public class TestCreateUser {
	// Valid Input
	RandomValue value = new RandomValue();
	@Order(2)
	@Test
		public void testCreateUserWithValidInput() {
			UserService userService = new UserService();
			UserEntity newUser = new UserEntity();
//			String randomString = value.generateRandomString(8);
			newUser.setEmail("thamimtommy123"+"@" +"gmail.com");
			newUser.setName("thamim");
			newUser.setArtistName("senson");
			newUser.setDob("2004-11-08");
			newUser.setRole("seller");
			newUser.setPassword("Alagu@1234");
			assertDoesNotThrow(() -> {
				userService.createUser(newUser);
			});
		}
	
	// Invalid Input
	
	@Test

	public void testCreateUserWithInvaidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "Invalid user input";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Email null check
	@Test

	public void testUserWithEmailNull() {
		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();
		newUser.setEmail(null);
		newUser.setName("Inba");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Email is Empty
	@Test

		public void testUserWithEmailEmpty() {
			UserService userService = new UserService();
			UserEntity newUser = new UserEntity();
			newUser.setEmail("");
			newUser.setName("Inba");
			newUser.setArtistName("thamimtommy");
			newUser.setDob("2003-11-08");
			newUser.setRole("seller");
			newUser.setPassword("Inba123098");
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.createUser(newUser);
			});
			String expectedMessage = "Email cannot be null or empty";
			String receivedMessage = exception.getMessage();
			assertTrue(expectedMessage.equals(receivedMessage));
		}
	
	// Password null check
	@Test

		public void testUserWithPasswordNull() {
			UserService userService = new UserService();
			UserEntity newUser = new UserEntity();
			newUser.setEmail("inba@gmail.com");
			newUser.setName("Inba");
			newUser.setArtistName("thamimtommy");
			newUser.setDob("2003-11-08");
			newUser.setRole("seller");
			newUser.setPassword(null);
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.createUser(newUser);
			});
			String expectedMessage = "Password cannot be null or empty";
			String receivedMessage = exception.getMessage();
			assertTrue(expectedMessage.equals(receivedMessage));
		}
	
	// Password empty check
		@Test

			public void testUserWithPasswordEmpty() {
				UserService userService = new UserService();
				UserEntity newUser = new UserEntity();
				newUser.setEmail("inba@gmail.com");
				newUser.setName("Inba");
				newUser.setArtistName("thamimtommy");
				newUser.setDob("2003-11-08");
				newUser.setRole("seller");
				newUser.setPassword("");
				Exception exception = assertThrows(ValidationException.class, () -> {
					userService.createUser(newUser);
				});
				String expectedMessage = "Password cannot be null or empty";
				String receivedMessage = exception.getMessage();
				assertTrue(expectedMessage.equals(receivedMessage));
			}
		
		// Name null check
		
		@Test

			public void testUserWithNameNull() {
				UserService userService = new UserService();
				UserEntity newUser = new UserEntity();
				newUser.setEmail("inba@gmail.com");
				newUser.setName(null);
				newUser.setArtistName("thamimtommy");
				newUser.setDob("2003-11-08");
				newUser.setRole("seller");
				newUser.setPassword("Inba12345");
				Exception exception = assertThrows(ValidationException.class, () -> {
					userService.createUser(newUser);
				});
				String expectedMessage = "Name cannot be null or empty";
				String receivedMessage = exception.getMessage();
				assertTrue(expectedMessage.equals(receivedMessage));
			}
		
		// Name is empty
		
		@Test

			public void testUserWithNameEmpty() {
				UserService userService = new UserService();
				UserEntity newUser = new UserEntity();
				newUser.setEmail("inba@gmail.com");
				newUser.setName("");
				newUser.setArtistName("thamimtommy");
				newUser.setDob("2003-11-08");
				newUser.setRole("seller");
				newUser.setPassword("Inba12345");
				Exception exception = assertThrows(ValidationException.class, () -> {
					userService.createUser(newUser);
				});
				String expectedMessage = "Name cannot be null or empty";
				String receivedMessage = exception.getMessage();
				assertTrue(expectedMessage.equals(receivedMessage));
			}
		
		// Email Pattern check
		@Test

			public void testUserWithEmailInValidPattern() {
				UserService userService = new UserService();
				UserEntity newUser = new UserEntity();
				newUser.setEmail("@.com");
				newUser.setName("Akil");
				newUser.setArtistName("thamimtommy");
				newUser.setDob("2003-11-08");
				newUser.setRole("seller");
				newUser.setPassword("Inba12345");
				Exception exception = assertThrows(ValidationException.class, () -> {
					userService.createUser(newUser);
				});
				String expectedMessage = "Email doesn't match the pattern";
				String receivedMessage = exception.getMessage();
				assertTrue(expectedMessage.equals(receivedMessage));
			}
		
		// Password Pattern check
		
		@Test

			public void testUserWithPasswordInValidPattern() {
				UserService userService = new UserService();
				UserEntity newUser = new UserEntity();
				newUser.setEmail("inba@gmail.com");
				newUser.setName("Akil");
				newUser.setArtistName("thamimtommy");
				newUser.setDob("2003-11-08");
				newUser.setRole("seller");
				newUser.setPassword("inba8973");
				Exception exception = assertThrows(ValidationException.class, () -> {
					userService.createUser(newUser);
				});
				String expectedMessage = "Password doesn't match the pattern";
				String receivedMessage = exception.getMessage();
				assertTrue(expectedMessage.equals(receivedMessage));
			}
		
		// User already exist
		
		@Test
			public void testCreateUserEmailCheck() {
				UserService userService = new UserService();
				UserEntity newUser = new UserEntity();
				newUser.setEmail("kumar@gmail.com");
				newUser.setName("Akil");
				newUser.setArtistName("thamimtommy");
				newUser.setDob("2003-11-08");
				newUser.setRole("seller");
				newUser.setPassword("Inba8973");
				Exception exception = assertThrows(ServiceException.class, () -> {
					userService.createUser(newUser);
				});
				String expectedMessage = "Email already exists";
				String receivedMessage = exception.getMessage();
				System.out.println(receivedMessage);
				assertTrue(expectedMessage.equals(receivedMessage));
			}
		
		
}
