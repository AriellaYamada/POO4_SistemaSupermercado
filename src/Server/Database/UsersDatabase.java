package Server.Database;

import Structure.User;

import java.io.IOException;

import static java.lang.System.out;

public class UsersDatabase extends Database {

	private static UsersDatabase usersDB;

	public static UsersDatabase getInstance() {
		if(usersDB == null)
			usersDB = new UsersDatabase();
		return usersDB;
	}

	public UsersDatabase() { OpenFile("users.csv"); }

	public void WriteFile() {
		final String HEADER = "name,address,telephone,email,id,password";
		WriteFile("name","address","telephone","email","id","password");

		for (User u : Users.getInstance().ListAll()){
			WriteFile(u.getName(),
					u.getAddress(),
					u.getTel(),
					u.getEmail(),
					u.getId(),
					u.getPassword()
			);
		}
	}
}
