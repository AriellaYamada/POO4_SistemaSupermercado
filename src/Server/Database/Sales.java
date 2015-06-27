package Server.Database;

import Structure.Sale;

import java.util.*;
import java.util.stream.Stream;

public class Sales implements ListRegister{

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

	public List<Sale> ListAll() { return sales; }

	public void Register(String clientId, String product, int quantity, String date) {
		Sale new_sale = new Sale(clientId, product, quantity, date);
		sales.add(new_sale);
	}

	public int Register(String... value) {
		Sale new_sale = new Sale(value[0], value[1], Integer.parseInt(value[2]), value[3]);
		sales.add(new_sale);
		return 0;
	}
}
