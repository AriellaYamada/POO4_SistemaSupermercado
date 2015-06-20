package Database;

import Server.Product;
import Server.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Products {

	private static Products productsDB;
	private List<Product> products;
	private Stream<Product> filtered;

	public Products () {
		products = new LinkedList<Product>();
		filtered = products.stream();
	}

	//Singleton
	public static Products getInstance() {
		if (productsDB == null){
			productsDB = new Products();
		}
		return productsDB;
	}

	/*public void Register(String name, float price, ) {

	}*/

}
