package com.ashokit.dao;

public class UserDao {
	
	public String getLoginStatus(String username,String password) {
		 if(username != null && password != null) {
			 if("Mahesh".equals(username) && "Mahesh".equals(password)) {
				 return "Login Success";
			 }else {
				 return "Login Failure";
			 }
		 }else {
			 throw new RuntimeException("Provide Inputs Correctly....");
		 }
	 }
	
	public void sendEmailNotifications(int count) {
		System.out.println("Email Sent For Today Count::::" + count);
	}
}
