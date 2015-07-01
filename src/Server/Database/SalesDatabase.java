package Server.Database;

import Structure.CartItem;
import Structure.Sale;

import java.util.ArrayList;

public class SalesDatabase extends Database {

	private static SalesDatabase salesDB;

	//Singleton
	public static SalesDatabase getInstance() {
		if (salesDB == null)
			salesDB = new SalesDatabase();
		return salesDB;
	}

	private SalesDatabase() {
		HEADER = "date,user,product,amount,price";
		OpenFile("sales.csv");
	}

	public void WriteFile(){
		Sales.getSales().forEach(this::WriteFile);
	}

	//Escreve no fim do arquivo .csv a venda efetuada
	public void WriteFile(Sale sale) {
		ArrayList<String> list = new ArrayList<>();

		// Insert each sale data into list
		list.add(sale.getDate());
		list.add(sale.getUser().getId());

		for (CartItem i : sale.getProducts()) {
			list.add(i.getProduct().getName());
			list.add(i.getReservedQtdAsStr());
			list.add(Double.toString(i.getPrice()));
		}

		// Convert List to Array
		String[] array = new String[list.size()];
		array = list.toArray(array);

		WriteFile(true, array);
	}
}
