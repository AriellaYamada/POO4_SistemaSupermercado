package Server.Database;

import Structure.User;

public class UsersDatabase extends Database {

	private static UsersDatabase usersDB;

	public static UsersDatabase getInstance() {
		if(usersDB == null)
			usersDB = new UsersDatabase();
		return usersDB;
	}

	private UsersDatabase() {
		HEADER = "name,address,telephone,email,id,password";
		OpenFile("users.csv");
	}

	public void WriteFile() {
		WriteFile(HEADER);

		for (User u : Users.ListAll()){
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
