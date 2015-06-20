package Database;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Server.User;

public class Users {

	private static Users usersDB;
	private List<User> users;
	private Stream<User> filtered;

	public Users () {
		users = new LinkedList<User>();
		filtered = users.stream();
	}

	//Singleton
	public static Users getInstance() {
		if (usersDB == null){
			usersDB = new Users();
		}
		return usersDB;
	}

	public List<User> ListAll() { return users; }
	
	public int Register (String name, String address, String tel, String email, String id, String password) {
		if (CheckId(id) == true) {
			User new_user = new User(name, address, tel, email, id, password);
			users.add(new_user);

			return 0;
		}
		return 1;
	}

	public boolean CheckId (String id) {

		filtered.filter(u -> u.getId().equals(id));
		if (filtered.count() == 0)
			return true;
		return false;
	}

	public int Login (String id, String password) {
		filtered.filter(u -> u.getId().equals(id));
		if (filtered.count() == 0) {
			return 1;
		}
		filtered.findFirst();
		if (filtered.collect(Collectors.toList()).get(0).getPassword().equals(password))
			return 0;
		return 2;
	}
}
