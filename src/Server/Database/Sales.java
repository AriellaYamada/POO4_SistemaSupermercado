package Server.Database;

import Structure.Def;
import Structure.Sale;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Sales {

	private static Sales salesDB;
	private final List<Sale> sales;
	private final Stream<Sale> filtered;

	private Sales() {
		sales = new LinkedList<>();
		filtered = sales.stream();
	}


	//Singleton
	public static Sales getInstance() {
		if (salesDB == null){
			salesDB = new Sales();
		}
		return salesDB;
	}

	public List<Sale> getSales() { return sales; }

	public void AddSale(Sale sale) {
		sales.add(sale);
	}
}
