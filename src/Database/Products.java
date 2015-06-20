package Database;

import Server.Product;
import Server.User;

import java.util.GregorianCalendar;
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

	public List<Product> ListAll() { return products; }

	public void Register(String name, float price, String expiration, String provider, int quantity) {

		Product new_product = new Product(name, price, expiration, provider, quantity);
		products.add(new_product);
	}
}
