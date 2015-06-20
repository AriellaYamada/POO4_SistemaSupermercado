package Database;

import Server.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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

	public int Register(String name, float price, String expiration, String provider, int quantity) {

		if(checkProduct(name) == true) {
			Product new_product = new Product(name, price, expiration, provider, quantity);
			products.add(new_product);
			return 0;
		}
		return 1;
	}

	public boolean checkProduct(String name) {
		filtered.filter(p -> p.getName().equals(name));
		if (filtered.count() == 0)
			return true;
		return false;
	}

	public Product searchProduct(String productName) {

		filtered.filter(p -> p.getName().equals(productName));
		List<Product> collector = filtered.collect(Collectors.toList());

		return collector.get(0);
	}
}
