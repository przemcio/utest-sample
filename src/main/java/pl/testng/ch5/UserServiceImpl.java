package pl.testng.ch5;

public class UserServiceImpl {

	private UserDAO userDAO;
	private SecurityService securityService;
	
	public UserDAO getUserDAO(){
		return userDAO;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void assignPassword(User user) throws Exception {
		String passwordMd5 = securityService.md5(user.getPassword());
		user.setPassword(passwordMd5);
		userDAO.updateUser(user);
	}
}
