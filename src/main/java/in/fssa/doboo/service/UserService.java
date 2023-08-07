package in.fssa.doboo.service;

import java.util.Set;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.dao.*;

public class UserService {
	
	public Set<UserEntity> getAll() {
		
		UserDAO userDao = new UserDAO();

		Set<UserEntity> userList = userDao.findAll();
//		for (User user : userList) {
//			System.out.println(user);
//		}
		return userList;

	}
}
