package in.fssa.doboo.validator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.ConnectionUtil;
import in.fssa.doboo.util.StringUtil;

public class UserValidator {
	/**
	 * 
	 */
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,24}$";
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	public static void validate(UserEntity user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("Invalid user input");
		}
		
		// this is for age below 16 years method
		
		String date = user.getDob();
		System.out.println(date);
		DateTimeFormatter formatDate =DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate formattedDate = LocalDate.parse(date,formatDate);
		
		LocalDate.parse(date,
                 DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
		
		LocalDate currentDay = LocalDate.now();
		
		long diff = ChronoUnit.DAYS.between(formattedDate, currentDay);
		int yearDiff = (int) diff/365; 
		
		if(yearDiff < 16) {
			throw new ValidationException("age must be 16");
		}
		
		validateName(user.getName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
//		validateArtist(user.getArtistName());
		
		
	}
		
	// validate userName;
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	
		public static void validateName(String name) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(name, "Name");
			
			if (!Pattern.matches(NAME_PATTERN, name)) {
				throw new ValidationException("Name doesn't match the pattern");
			}
		
		}
		
	 // validate artistName;
		/**
		 * 
		 * @param artistName
		 * @throws ValidationException
		 */
	
		public static void validateArtist(String artistName) throws ValidationException {
			if(artistName == null)
			{
				throw new ValidationException("Aritst Name cannot be null");
			}
			StringUtil.rejectIfInvalidString(artistName, "Name");
			
			if (artistName.length()>=24) {
				throw new ValidationException("Aritst Name is out of characters");
			}
		
		}
		
	// validate userEmail;
		/**
		 * 
		 * @param email
		 * @throws ValidationException
		 */
		
		public static void validateEmail(String email) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(email, "Email");
			
			if (!Pattern.matches(EMAIL_PATTERN, email)) {
				throw new ValidationException("Email doesn't match the pattern");
			}
		
		}
		
	// validate userPassword;
		/**
		 * 
		 * @param password
		 * @throws ValidationException
		 */
		
		public static void validatePassword(String password) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(password, "Password");
			
			if (!Pattern.matches(PASSWORD_PATTERN, password)) {
				throw new ValidationException("Password doesn't match the pattern");
			}
		
		
		}
		
	
		// check userId is already exists ;
		/**
		 * 
		 * @param id
		 * @throws ValidationException
		 */
		
		
		public static void checkIdExists(int id) throws ValidationException {
			Connection conn = null;
			PreparedStatement pre = null;
			ResultSet rs = null;

			try {
				String query = "Select * From users Where is_active = 1 AND id = ?";
				conn = ConnectionUtil.getConnection();
				pre = conn.prepareStatement(query);
				pre.setInt(1, id);
				rs = pre.executeQuery();
				if (!rs.next()) {
					throw new ValidationException("Id doesn't exist");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException();
			} finally {
				ConnectionUtil.close(conn, pre, rs);
			}
		}
		
		// check email is already in database 
		/**
		 * 
		 * @param email
		 * @throws ValidationException
		 */
		
		public static void checkEmailExists(String email) throws ValidationException {
			Connection conn = null;
			PreparedStatement pre = null;
			ResultSet rs = null;

			try {
				String query = "Select * From users Where is_active = 1 AND email = ?";
				conn = ConnectionUtil.getConnection();
				pre = conn.prepareStatement(query);
				pre.setString(1, email);
				rs = pre.executeQuery();
				if (!rs.next()) {
					throw new ValidationException("Email doesn't exist");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException();
			} finally {
				ConnectionUtil.close(conn, pre, rs);
			}
		}
		
		public static void validateId(int id) throws ValidationException{
			if(id <= 0) {
				throw new ValidationException("Id can't be less than or equal to zero");
			}
		}	
		
	}
