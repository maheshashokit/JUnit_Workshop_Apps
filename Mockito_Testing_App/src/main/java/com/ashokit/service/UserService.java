package com.ashokit.service;

import com.ashokit.dao.UserDao;

public class UserService {
	
	//Requires the UserDao Object
	private UserDao userDao;
	
	//Injecting the UserDao Object Into UserService
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//Service Method used to validate the Login Credentials
	public String validateLogin(String username, String password) {
		//calling the UserDAO Class Method.
		return userDao.getLoginStatus(username, password);
	}
	
	//Service Method used for send email's
	public void sendEmails() {
		userDao.sendEmailNotifications(05);
	}

}
