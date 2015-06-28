package Server.Database;

import Structure.CartItem;
import Structure.Def;
import Structure.Sale;

public class SalesDatabase extends Database {

	private static SalesDatabase salesDB;

	//Singleton
	public static SalesDatabase getInstance() {
		if (salesDB == null)
			salesDB = new SalesDatabase();
		return salesDB;
	}

	private SalesDatabase() { OpenFile("sales.csv"); }

	public void WriteFile() {

		WriteFile("date","user","product","amount","price");

		for (Sale sale : Sales.getInstance().getSales()) {
			String products = "";
			for (CartItem i : sale.getProducts()) {
				products += i.getProduct().getName() + Def.comma + i.getReservedQtdAsStr() + Def.comma + i.getTotalAmountAsStr() + Def.comma;
			}
			WriteFile(sale.getDate(), sale.getUser().getId(), products);
		}
		CloseFile();
	}
}
