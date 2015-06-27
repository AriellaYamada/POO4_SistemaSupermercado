package Server.Database;

import Structure.Def;
import Structure.Sale;

public class SalesDatabase extends Database {

	private static SalesDatabase salesDB;

	//Singleton
	public static SalesDatabase getInstance() {
		if(salesDB == null)
			salesDB = new SalesDatabase();
		return salesDB;
	}

	private SalesDatabase() { OpenFile("sales.csv"); }

	public void WriteFile() {
		WriteFile("clientID","product","quantity","date");

		for (Sale s : Sales.getInstance().ListAll()){
			WriteFile(s.getClientId(),
					s.getProduct(),
					Integer.valueOf(s.getQuantity()).toString(),
					Def.CalendarToString(s.getDate())
			);
		}
	}
}
