package Database;

import Server.*;

import java.io.IOException;

import static java.lang.System.out;

public class ProductsDatabase extends Database {


	public void WriteFile() {
		OpenWriter(false);
		final String SEPARATOR = ",";
		final String ENDLINE = "\n";
		final String HEADER = "name, price, expiration, provider, quantity";

		try {
			fw.append(HEADER);
			fw.append(ENDLINE);
			fw.flush();

			for (Product p : Products.getInstance().ListAll()) {

				fw.append(p.getName());
				fw.append(SEPARATOR);

				fw.append(Float.valueOf(p.getPrice()).toString());
				fw.append(SEPARATOR);

				fw.append(p.CalendarToStr(p.getExpiration()));
				fw.append(SEPARATOR);

				fw.append(p.getProvider());
				fw.append(SEPARATOR);

				fw.append(Integer.valueOf(p.getQuantity()).toString());
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
				Products.getInstance().Register(splited[0], Float.parseFloat(splited[1]), splited[2], splited[3], Integer.parseInt(splited[4]));
			}
		} catch (IOException e) {
			out.println("Erro na leitura do arquivo.");
			e.printStackTrace();
		}
	}
}
