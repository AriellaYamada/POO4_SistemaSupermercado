package Server.Database;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Structure.User;

public class Users implements ListRegister {

	private static Users usersDB;
	private static List<User> users;
	private static Stream<User> filtered;

	private Users () { users = new LinkedList<>(); }

	//Singleton
	public static Users getInstance() {
		if (usersDB == null){
			usersDB = new Users();
		}
		return usersDB;
	}

	//Lista de todos os usuarios cadastrados no sistema
	public static List<User> ListAll() { return users; }

	//Cadastro de cliente a partir da interface
	public static int Register (String name, String address, String tel, String email, String id, String password) {
		if (checkId(id)) {
			User new_user = new User(name, address, tel, email, id, password);
			users.add(new_user);
			return 0;
		}
		return 1;
	}

	//Cadastro de usuarios a partir da leitura do .csv
	public int Register (String... value) {
		if (checkId(value[4])) {
			User new_user = new User(value[0], value[1], value[2], value[3], value[4], value[5]);
			users.add(new_user);
			return 0;
		}
		return 1;
	}

	//Verifica se o id ja foi utilizado em algum cadastro
	public static boolean checkId (String id) {
		filtered = users.stream();
		filtered = filtered.filter(u -> u.getId().equals(id));
		return (filtered.count() == 0);
	}

	//Verifica o login e senha digitados no login
	public static int Login (String id, String password) {
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

	//Realiza uma busca de nome de usuario a partir do id
	public static String GetUserName(String id) {
		filtered = users.stream()
				.filter(u -> u.getId().equals(id));
		return filtered.collect(Collectors.toList()).get(0).getName();
	}

	//Realiza a busca de um usuario a partir do seu id
	public static User SearchUser(String userid) {
		filtered = users.stream().filter(u -> u.getId().equals(userid));
		return filtered.collect(Collectors.toList()).get(0);
	}
}
