package pl.testng.ch6;

import java.util.ArrayList;
import java.util.List;

import pl.testng.ch5.User;

public class UserList {

	private List<User> userList = new ArrayList<User>();

	public List<User> getUsers() {
		return userList;

	}

	public void addUser(User user) {
		userList.add(user);

	}

}
