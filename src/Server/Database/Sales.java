package Server.Database;

import Structure.Sale;

import java.util.LinkedList;
import java.util.List;

public class Sales {

	private static Sales salesDB;
	private static List<Sale> sales;

	private Sales() { sales = new LinkedList<>(); }

	//Singleton
	public static Sales getInstance() {
		if (salesDB == null){
			salesDB = new Sales();
		}
		return salesDB;
	}

	public static List<Sale> getSales() { return sales; }

	public static void AddSale(Sale sale) {
		sales.add(sale);
		SalesDatabase.getInstance().WriteFile(sale);
	}
}
