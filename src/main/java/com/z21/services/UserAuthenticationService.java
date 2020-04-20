package com.z21.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.z21.be.models.User;
import com.z21.dao.UserAuthenticationDao;

public class UserAuthenticationService {
	
	
 	private UserAuthenticationDao userAuthenticationDao;
	
	public User authenticate(String userName, String pass){		
		return userAuthenticationDao.authenticate(userName, pass);		
	}
	
	public User registerNewUser(User user) {	
		return userAuthenticationDao.registerNewUser(user);
	}
	
	public User getUserInfo(String userName) {
		return null;
	}

	public UserAuthenticationDao getUserAuthenticationDao() {
		return userAuthenticationDao;
	}

	public void setUserAuthenticationDao(UserAuthenticationDao userAuthenticationDao) {
		this.userAuthenticationDao = userAuthenticationDao;
	}
	
	

}
