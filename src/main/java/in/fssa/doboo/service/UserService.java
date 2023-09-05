package in.fssa.doboo.service;

import java.util.Set;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.StringUtil;
import in.fssa.doboo.dao.UserDAO;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.validator.UserValidator;

public class UserService {

	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAO(); // Initialize userDao instance in the constructor
	}

	/**
	 * 
	 * @return
	 */
	public Set<UserEntity> getAllUsers() {

		Set<UserEntity> userList = null;
		try {
			userList = userDAO.findAll();
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return userList;
	}
	public UserEntity Login(String email) throws ValidationException,PersistanceException {
		UserValidator.validateEmail(email);
		UserEntity user = null;
		try {
			user = new UserEntity(); 
			user = userDAO.logIn(email);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		return user;
	}
	/**
	 * 
	 * @param newUser
	 * @throws Exception
	 */
	
	/*
	 * User object 
	 * 1. user name
	 * 2. password
	 * 3. date of birth
	 * 4. email
	 * 5. artist name
	 * 6. role
	 *  
	 * 
	 * */
	public void createUser(UserEntity newUser) throws ServiceException,ValidationException{
		try {
			UserValidator.validate(newUser);

			UserDAO userDAO = new UserDAO();
			userDAO.emailExists(newUser.getEmail());
			userDAO.create(newUser);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	/**
	 * 
	 * @param id
	 * @param updateUser
	 * @throws ValidationException
	 */

	public void updateUser(int id, UserEntity updateUser) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.validate(updateUser);
		UserValidator.checkIdExists(id);
		UserValidator.validateArtist(updateUser.getArtistName());
//
//		if (updateUser.getName() != null) {
//			UserValidator.validateName(updateUser.getName());
//		}
//		if (updateUser.getPassword() != null) {
//			UserValidator.validatePassword(updateUser.getPassword());
//		}

		UserDAO userDAO = new UserDAO();

		try {
			userDAO.update(id, updateUser);
		} catch (PersistanceException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void deleteUser(int id) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.checkIdExists(id);
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.delete(id);
		} catch (PersistanceException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 */
	public UserEntity findByUserId(int id) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.checkIdExists(id);
		UserDAO userDao = new UserDAO();
		try {
			return userDao.findById(id);
		} catch (PersistanceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws ValidationException
	 */

	public UserEntity findByEmail(String email) throws ValidationException {
		UserValidator.validateEmail(email);
		UserValidator.checkEmailExists(email);
		UserDAO userDao = new UserDAO();
		return userDao.findByEmailId(email);
	}
/**
 * 
 * @param artistName
 * @return
 * @throws ValidationException
 */
	public UserEntity findByArtistName(String artistName) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(artistName,"artistName");
		UserDAO userDao = new UserDAO();
		return userDao.findByArtistName(artistName);
	}

}
