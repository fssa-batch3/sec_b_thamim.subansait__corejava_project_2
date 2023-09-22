package in.fssa.doboo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.UserService;
import in.fssa.doboo.util.PasswordEncryptUtil;

public class ConvetPasswordTest {
	@Test
	public void testConvetPassword() {
		
		System.out.println(PasswordEncryptUtil.encrypt("Inba123098")); 
	}

}
