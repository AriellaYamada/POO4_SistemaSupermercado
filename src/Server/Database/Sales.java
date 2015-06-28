package Server.Database;

import Structure.Def;
import Structure.Sale;

import java.util.LinkedList;
import java.util.List;

public class Sales {

	private static Sales salesDB;
	private List<Sale> sales;

	private Sales() { sales = new LinkedList<>(); }

	//Singleton
	public static Sales getInstance() {
		if (salesDB == null){
			salesDB = new Sales();
		}
		return salesDB;
	}

	public void AddSale(Sale sale) {
		sales.add(sale);
	}
}
