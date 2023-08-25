package in.fssa.doboo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.UserService;

public class TestUpdateUser {

	@Test
	public void testUpdateUserWithValidInput() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		assertDoesNotThrow(() -> {
			userService.updateUser(4, newUser);
		});
	}

	// Invalid Input
	@Test
	public void testUpdateUserWithInvaidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, null);
		});
		String expectedMessage = "Invalid user input";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Password null check
	@Test
	public void testUserWithPasswordNull() {
		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);
		});

		String expectedMessage = "Password cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	// Password is Empty
	@Test
	public void testUserWithPasswordEmpty() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		newUser.setPassword("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

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
		newUser.setEmail("thamim@gmail.com");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		newUser.setName(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);
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
		newUser.setEmail("thamim@gmail.com");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		newUser.setName("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

		});

		String expectedMessage = "Name cannot be null or empty";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Name Pattern check
	@Test
	public void testUserWithNameInValidPattern() {

		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();
		
		
		newUser.setName("Inba2309029301");
		newUser.setEmail("thamim@gmail.com");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

		});
		String expectedMessage = "Name doesn't match the pattern";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}
	
	// Password Pattern check
	@Test
	public void testUserWithPasswordInValidPattern() {

		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("inba123");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);

		});
		String expectedMessage = "Password doesn't match the pattern";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));

	}

	// Id is Invalid
	@Test
	public void testUserWithInvalidId() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(0, newUser);
		});

		String expectedMessage = "Id can't be less than or equal to zero";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	// Id check
	@Test
	public void testUserWithCheckIdPresent() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setArtistName("thamimtommy");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(100, newUser);
		});

		String expectedMessage = "Id doesn't exist";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	// Artist name with null
	@Test 
	public void testUserWithInvalidArtistName() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		newUser.setArtistName(null);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);
		});

		String expectedMessage = "Aritst Name cannot be null";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	// artist name greater than 24 chars
	@Test 
	public void testUserWithInvalidArtistName2() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		
		
		newUser.setArtistName("euiwueiuoqwiueioquweiuqiowueioqwueoiquwioeuqwioeuiowqueoiwqueioqwueiuqwueiu82719878uoiuiowueoiqw");
		newUser.setEmail("thamim@gmail.com");
		newUser.setName("praveen");
		newUser.setDob("2003-11-08");
		newUser.setRole("seller");
		newUser.setPassword("Inba123098");
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(4, newUser);
		});

		String expectedMessage = "Aritst Name is out of characters";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
	}
}