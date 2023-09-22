package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.UserService;

public class TestLoginUser {
	
	@Test
	
	public void TestuserLoginWithValidInput() {
		UserService userService = new UserService();
		 
		
		assertDoesNotThrow(() -> { 
			UserEntity user  = userService.Login("thamimtommy123@gmail.com");
			if(!BCrypt.checkpw("Alagu@1234", user.getPassword())) {
				throw new ValidationException("Incorrect Password");
			}
			System.out.println(user);
			
					}
		);
		
	}
	
@Test
	
	public void TestuserLoginWithInValidEmail() {
		UserService userService = new UserService();
		 
		Exception exception = assertThrows(RuntimeException.class, () -> {
			UserEntity user = userService.Login("umar@gmail.com");
			System.out.println(user);
		});

		String expectedMessage = "email doesn't exits";
		String receivedMessage = exception.getMessage();
		System.out.println(receivedMessage);
		assertTrue(expectedMessage.equals(receivedMessage));
		;
		
	}

@Test

public void TestuserLoginWithInValidEmailAsNull() {
	UserService userService = new UserService();
	 
	Exception exception = assertThrows(ValidationException.class, () -> {
		UserEntity user  = userService.Login(null);
		System.out.println(user);
	});

	String expectedMessage = "Email cannot be null or empty";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	;
	
}

@Test

public void TestuserLoginWithInValidEmailAsEmpty() {
	UserService userService = new UserService();
	 
	Exception exception = assertThrows(ValidationException.class, () -> {
		UserEntity user  = userService.Login("");
		System.out.println(user);
	});

	String expectedMessage = "Email cannot be null or empty";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	;
	
}

@Test

public void TestuserLoginWithInValidEmailFormat() {
	UserService userService = new UserService();
	 
	Exception exception = assertThrows(ValidationException.class, () -> {
		UserEntity user  = userService.Login("thioauoiurewnm");
		System.out.println(user);
	});

	String expectedMessage = "Email doesn't match the pattern";
	String receivedMessage = exception.getMessage();
	System.out.println(receivedMessage);
	assertTrue(expectedMessage.equals(receivedMessage));
	;
	
}
	

}
