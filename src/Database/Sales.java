package Database;

import Server.*;
import java.util.*;
import java.util.stream.Stream;

public class Sales {

	private static Sales salesDB;
	private List<Sale> sales;
	private Stream<Sale> filtered;

	public Sales() {
		sales = new LinkedList<Sale>();
		filtered = sales.stream();
	}

	//Singleton
	public static Sales getInstance() {
		if (salesDB == null){
			salesDB = new Sales();
		}
		return salesDB;
	}

	public List<Sale> ListAll() { return sales; }

	public void Register(String clientId, String product, int quantity, String date) {
		Sale new_sale = new Sale(clientId, product, quantity, date);
		sales.add(new_sale);
	}
}
