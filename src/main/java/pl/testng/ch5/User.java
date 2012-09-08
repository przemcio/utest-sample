package pl.testng.ch5;

public class User {

	private String password;
	public User(String password) {
		this.password = password;
	}

	public Object getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

}
