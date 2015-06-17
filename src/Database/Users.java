package Database;

import java.util.List;
import Server.User;

public class Users {

	private static Users usersDB;
	private List<User> users;

	//Singleton
	public static Users getInstance() {
		if (usersDB == null){
			usersDB = new Users();
		}
		return usersDB;
	}

	public void Register (String name, String address, String tel, String email, String id, String password) {
		User new_user = new User(name, address, tel, email, id, password);
		users.add(new_user);
	}
}
