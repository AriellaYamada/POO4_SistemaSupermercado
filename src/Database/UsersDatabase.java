package Database;

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
		OpenWriter(false);
		final String SEPARATOR = ",";
		final String ENDLINE = "\n";
		final String HEADER = "name,address,telephone,email,id,password";

		try {
			fw.append(HEADER);
			fw.append(ENDLINE);
			fw.flush();

			for (User u : Users.getInstance().ListAll()) {

				fw.append(u.getName());
				fw.append(SEPARATOR);

				fw.append(u.getAddress());
				fw.append(SEPARATOR);

				fw.append(u.getTel());
				fw.append(SEPARATOR);

				fw.append(u.getEmail());
				fw.append(SEPARATOR);

				fw.append(u.getId());
				fw.append(SEPARATOR);

				fw.append(u.getPassword());
				fw.append(ENDLINE);

				fw.flush();
			}
			CloseFile();
		} catch (IOException e){
			out.println("Erro na escrita do arquivo.");
			e.printStackTrace();
		}
	}

	public void ReadFile() {
		OpenReader();

		String line;
		String splitSign = ",";
		try {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(splitSign);
				Users.getInstance().Register(splited[0], splited[1], splited[2], splited[3], splited[4], splited[5]);
			}
		} catch (IOException e) {
			out.println("Erro na leitura do arquivo.");
			e.printStackTrace();
		}
	}
}
