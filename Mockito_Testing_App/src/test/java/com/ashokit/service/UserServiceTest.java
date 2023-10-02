package com.ashokit.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ashokit.dao.UserDao;

public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
    private UserDao userDao;
    
    @BeforeEach
    public void setUpEachTest() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    public void clearDownEachTest() {
    	Mockito.reset(this);
    }
    
    @Test
    @DisplayName("Test Case For Validating User Login Credentials With Success...")
    public void validatingLoginCredentials_Success() {
    	
    	Mockito.mock(UserDao.class);
    	Assertions.assertNotNull(userDao);
    	
    	//Stubbing (or) Setting the Behavior of DAO Method.
    	Mockito.when(userDao.getLoginStatus(anyString(), anyString()))
    		   .thenReturn("Login Success");
    	
    	//original Service method calling
    	String loginStatus = userService.validateLogin("Mahesh", "Mahesh");
    	
    	//checking output through Assertions
    	Assertions.assertNotNull(loginStatus);
    	Assertions.assertEquals("Login Success", loginStatus);
    }
    
    @Test
    @DisplayName("Test Case For Validating User Login Credentials For Failure Scenario")
    public void validatingLoginCredentials_Failure() {
    	Assertions.assertNotNull(userDao);
    	
    	//Stubbing (or) Setting the Behavior of DAO Method.
    	Mockito.when(userDao.getLoginStatus(anyString(), anyString()))
    		   .thenReturn("Login Failure");

    	//calling original business method
    	String loginStatus = userService.validateLogin("Mahesh123", "Mahesh123");
    	
    	//Comparing the Results through Assertions
    	Assertions.assertNotNull(loginStatus);
    	Assertions.assertEquals("Login Failure", loginStatus);
    }
    
    @Test
    @DisplayName("Test Case For Validating User Login Credentials With Exception Scenario...")
    public void validatingLoginCredentials_Exception() {
    	Assertions.assertNotNull(userDao);
    	
    	//Stubbing (or) Setting the Behavior of DAO Method.
    	Mockito.when(userDao.getLoginStatus(null,null))
    		   .thenThrow(new RuntimeException("Provide Inputs Correctly...."));
    	
    	Assertions.assertThrows(RuntimeException.class, () ->{
    		userService.validateLogin(null, null);
    	});
    }
    
    @Test
    @DisplayName("Test Case For Void Method......")
    public void validatingVoidMethod() {
    	Assertions.assertNotNull(userDao);
    	userService.sendEmails();
    	//Stubbing (or) Setting the Behavior of DAO Method for void methods
    	verify(userDao,times(1)).sendEmailNotifications(anyInt());
    }
}
