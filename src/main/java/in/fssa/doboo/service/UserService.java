package in.fssa.doboo.service;

import java.util.Set;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.StringUtil;
import in.fssa.doboo.dao.UserDAO;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.validator.UserValidator;

public class UserService {

	private UserDAO userDao;

	public UserService() {
		this.userDao = new UserDAO(); // Initialize userDao instance in the constructor
	}

	/**
	 * 
	 * @return
	 */
	public Set<UserEntity> getAll() {

		Set<UserEntity> userList = userDao.findAll();

		return userList;
	}
	/**
	 * 
	 * @param newUser
	 * @throws Exception
	 */
	public void create(UserEntity newUser) throws Exception {
		UserValidator.validate(newUser);

		
		UserDAO userDAO = new UserDAO();
		userDAO.emailExists(newUser.getEmail());
		userDAO.create(newUser);
	}
	/**
	 * 
	 * @param id
	 * @param updateUser
	 * @throws ValidationException
	 */

	public void update(int id, UserEntity updateUser) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.validate(updateUser);
		UserValidator.checkIdExists(id);
		UserValidator.validateArtist(updateUser.getArtistName());

		if (updateUser.getName() != null) {
			UserValidator.validateName(updateUser.getName());
		}
		if (updateUser.getPassword() != null) {
			UserValidator.validatePassword(updateUser.getPassword());
		}

		UserDAO userDAO = new UserDAO();

		userDAO.update(id, updateUser);
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void delete(int id) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.checkIdExists(id);
		UserDAO userDAO = new UserDAO();
		userDAO.delete(id);
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 */
	public UserEntity findById(int id) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.checkIdExists(id);
		UserDAO userDao = new UserDAO();
		return userDao.findById(id);
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
