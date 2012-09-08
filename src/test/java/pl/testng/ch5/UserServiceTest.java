package pl.testng.ch5;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import org.testng.annotations.Test;


public class UserServiceTest {

	
	@Test
	public void shouldAssignDOC() {
	
		UserServiceImpl userServiceImpl= new UserServiceImpl();
		
		userServiceImpl.setUserDAO(mock(UserDAO.class));
		userServiceImpl.setSecurityService(mock(SecurityService.class));
		
		assertNotNull(userServiceImpl.getUserDAO());
		assertNotNull(userServiceImpl.getSecurityService());
		
	}
	@Test
	public void shouldUpdatePassword() throws Exception {
		
		UserServiceImpl userServiceImpl= new UserServiceImpl();
		
		UserDAO userDAO = mock(UserDAO.class);
		userServiceImpl.setUserDAO(userDAO);
		
		SecurityService securityService = mock(SecurityService.class);
		when(securityService.md5("password")).thenReturn("md5pass");
		userServiceImpl.setSecurityService(securityService);
		
		User user = new User("password");
		userServiceImpl.assignPassword(user);
		
		verify(securityService).md5("password");
		verify(userDAO).updateUser(user);
		assertEquals(user.getPassword(), "md5pass");
	}
	
	
}
