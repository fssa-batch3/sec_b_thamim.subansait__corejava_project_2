package in.fssa.doboo.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptUtil {
	public static String encrypt(String password) {
		return  BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
