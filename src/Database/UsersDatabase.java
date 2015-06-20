package Database;

import java.io.IOException;

import static java.lang.System.out;

public class UsersDatabase extends Database {


	public void WriteFile() {
		OpenWriter(false);
		final String SEPARATOR = ",";
		final String ENDLINE = "\n";
		final String HEADER = "name,adress, telephone, email, id, password";

		try {
			fw.append(HEADER);
			fw.append(ENDLINE);
			fw.flush();
			

			while(//lista não vazia) {
				fw.append(Users.getInstance().getName());
				fw.append(SEPARATOR);
				fw.append(Users.getInstance().getAdress());
				fw.append(SEPARATOR);
				fw.append(Users.getInstance().getTel());
				fw.append(SEPARATOR);
				fw.append(Users.getInstance().getName());
				fw.append(SEPARATOR);
			}


			fw.flush();

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
