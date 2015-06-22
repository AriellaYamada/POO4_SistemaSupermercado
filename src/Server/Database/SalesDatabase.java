package Server.Database;

import Server.*;
import Structure.Sale;

import java.io.IOException;

import static java.lang.System.out;

public class SalesDatabase extends Database {

	private static SalesDatabase salesDB;

	//Singleton
	public static SalesDatabase getInstance() {
		if(salesDB == null)
			salesDB = new SalesDatabase();
		return salesDB;
	}

	public SalesDatabase() { OpenFile("sales.csv"); }

	public void WriteFile() {
		OpenWriter(false);
		final String SEPARATOR = ",";
		final String ENDLINE = "\n";
		final String HEADER = "clientID,product,quantity,date";

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
				fw.append(SEPARATOR);

				fw.append(cmdProcess.CalendarToString(s.getDate()));
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
				Sales.getInstance().Register(splited[0], splited[1], Integer.parseInt(splited[2]), splited[3]);
			}
		} catch (IOException e) {
			out.println("Erro na leitura do arquivo.");
			e.printStackTrace();
		}
	}
}
