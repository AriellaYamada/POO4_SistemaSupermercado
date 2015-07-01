package Server.Database;

import Structure.User;

public class UsersDatabase extends Database {

	private static UsersDatabase usersDB;

	//Singleton
	public static UsersDatabase getInstance() {
		if(usersDB == null)
			usersDB = new UsersDatabase();
		return usersDB;
	}

	//Abre o arquivo .csv
	private UsersDatabase() {
		HEADER = "name,address,telephone,email,id,password";
		OpenFile("users.csv");
	}

	//Escrita no arquivo dos clientes cadastrados
	public void WriteFile() {
		WriteFile(false,HEADER);

		for (User u : Users.ListAll()){
			WriteFile(u.getName(),
					u.getAddress(),
					u.getTel(),
					u.getEmail(),
					u.getId(),
					Integer.toString(u.getPassword())
			);
		}
	}
}
