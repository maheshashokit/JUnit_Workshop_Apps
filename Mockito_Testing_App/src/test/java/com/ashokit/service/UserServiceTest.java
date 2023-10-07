package com.ashokit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ashokit.dao.UserDao;
import com.ashokit.entity.User;

public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
    private UserDao userDao;
    
    @BeforeEach
    public void setUpEachTest() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    @DisplayName("Test Case For Validating User Login Credentials With Success...")
    public void validatingLoginCredentials_Success() {
    	
    	Assertions.assertNotNull(userDao);
    	
    	//Stubbing (or) Setting the Behavior of DAO Method.
    	Mockito.when(userDao.getLoginStatus(anyString(), anyString()))
    		   .thenReturn("Login Success");
        
    	/*doReturn("Login Success").when(userDao).getLoginStatus(anyString(), anyString());*/
    	
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
    	/*Mockito.when(userDao.getLoginStatus(anyString(), anyString()))
    		     .thenReturn("Login Failure");*/
    	
    	doReturn("Login Failure").when(userDao).getLoginStatus(anyString(), anyString());

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
    @DisplayName("Test Case For Void Method Using Interactions......")
    public void validatingVoidMethod() {
    	Assertions.assertNotNull(userDao);
    	//Stubbing (or) Setting the Behavior of DAO Method for void methods
    	doNothing().when(userDao).sendEmailNotifications(anyInt());
    	userService.sendEmails();
    	//Behavioral verification
    	verify(userDao,times(1)).sendEmailNotifications(anyInt());
    }
    
    
    @Test
    @DisplayName("Test Case For Demonstrating the Stubbing......")
	public void test_Get_All_Users() {
		List<String> userIds = new ArrayList<>();
		userIds.add("1234");
		userIds.add("1235");
		
		User user1 = new User("1234","Mahesh","Kumar");
		User user2 = new User("1235", "Suresh","Kumar");
		
		when(userDao.getUserById("1234")).thenReturn(user1);
		when(userDao.getUserById("1235")).thenReturn(user2);
		
		//doReturn(user1).when(userDao).getUserById("1234");
        //doReturn(user2).when(userDao).getUserById("1235");
		
		List<User> users = userService.getAllUsers(userIds);
		
		assertEquals(2, users.size());
	}
    
    @Test
    public void testUpdateUser() {
        // Create a real UserRepository (not a mock)
        UserDao realUserDao = new UserDao(); 

        // Create a spy on the real repository
        UserDao spyUserRepository = Mockito.spy(realUserDao);

        // Create a UserService instance using the spy
        UserService userService = new UserService(spyUserRepository);

        // Create a user for testing
        User userToUpdate = new User("Mahesh","Kumar","Male");

        // Call the updateUser method
        User updatedUser = userService.updateUser(userToUpdate);

        // Verify that the save method was called with the correct user
        verify(spyUserRepository, times(1)).saveUser(userToUpdate);

        // Perform assertions on the result, if needed
        assertEquals(userToUpdate.getFirstName(), updatedUser.getFirstName());
    }
	
}
