package Database;

import Server.*;

import java.io.IOException;

import static java.lang.System.out;

public class SalesDatabase extends Database {


	public void WriteFile() {
		OpenWriter(false);
		final String SEPARATOR = ",";
		final String ENDLINE = "\n";
		final String HEADER = "clientID, product, quantity";

		try {
			fw.append(HEADER);
			fw.append(ENDLINE);
			fw.flush();

			for (Sale s : Sales.getInstance().ListAll()) {

				fw.append(s.getClientId());
				fw.append(SEPARATOR);

				fw.append(s.getProduct());
				fw.append(SEPARATOR);

				fw.append(Integer.valueOf(s.getQuantity()).toString());
				fw.append(ENDLINE);

				fw.flush();
			}
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
				Sales.getInstance().Register(splited[0], splited[1], Integer.parseInt(splited[2]));
			}
		} catch (IOException e) {
			out.println("Erro na leitura do arquivo.");
			e.printStackTrace();
		}
	}
}
