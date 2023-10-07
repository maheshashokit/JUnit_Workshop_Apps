package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import com.ashokit.dao.UserDao;
import com.ashokit.entity.User;

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
	
	public List<User> getAllUsers(List<String> userIds) {
		List<User> userList = new ArrayList<User>();
		for(String userId : userIds){
			//getting the user object from userDao
			User user = userDao.getUserById(userId);
			//adding user object to userList
			userList.add(user);
		}
		return userList;
	}
	
	public User updateUser(User user) {
        // Business logic for updating user
        if (user == null || user.getFirstName() == null) {
            throw new IllegalArgumentException("Invalid user data");
        }
        // Actual update logic using the repository
        return userDao.saveUser(user);
    }
}
