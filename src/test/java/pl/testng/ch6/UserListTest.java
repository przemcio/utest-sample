package pl.testng.ch6;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pl.testng.ch5.User;
import static org.fest.assertions.Assertions.*;


public class UserListTest {

	private UserList userList;
	
	@BeforeMethod
	public void setUp() {
		
	userList = new UserList();		
	}
	
	@Test()
	public void shouldReturnZeroUsers(){
		
		
		assertThat(userList.getUsers()).isEmpty();
	}
	@Test
	public void shouldReturnOneUser() {
		
		User user1 = new User("Name 1");
		
		userList.addUser(user1);
		
		assertThat(userList.getUsers()).contains(user1);
		
		
	}
	@Test
	public void shouldReturnTwoUser(){
		User user1 = new User("Name 1");
		User user2 = new User("Name 2");
		
		userList.addUser(user1);
		userList.addUser(user2);
		
		assertThat(userList.getUsers()).contains(user1,user2);
		
	}
}
