package Server.Database;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Structure.User;

public class Users implements ListRegister {

	private static Users usersDB;
	private final List<User> users;
	private Stream<User> filtered;

	private Users() { users = new LinkedList<>(); }

	//Singleton
	public static Users getInstance() {
		if (usersDB == null){
			usersDB = new Users();
		}
		return usersDB;
	}

	public List<User> ListAll() { return users; }

	public int Register (String name, String address, String tel, String email, String id, String password) {
		if (checkId(id)) {
			User new_user = new User(name, address, tel, email, id, password);
			users.add(new_user);
			return 0;
		}
		return 1;
	}

	public int Register (String... value) {
		if (checkId(value[4])) {
			User new_user = new User(value[0], value[1], value[2], value[3], value[4], value[5]);
			users.add(new_user);
			return 0;
		}
		return 1;
	}

	private boolean checkId (String id) {
		filtered = users.stream();
		filtered = filtered.filter(u -> u.getId().equals(id));
		return (filtered.count() == 0);
	}

	public int Login (String id, String password) {
		filtered = users.stream();
		filtered = filtered.filter(u -> u.getId().equals(id));
		//Verifica se o usuario existe
		if (filtered.count() == 0)
			return 1;
		filtered = users.stream()
				.filter(u -> u.getId().equals(id));
		//Verifica se a senha digitada é a correta
		if (filtered.collect(Collectors.toList()).get(0).getPassword().equals(password))
			return 0;
		return 2;
	}

	public String GetUserName(String id) {
		filtered = users.stream()
				.filter(u -> u.getId().equals(id));
		return filtered.collect(Collectors.toList()).get(0).getName();
	}
}
