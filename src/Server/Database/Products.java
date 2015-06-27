package Server.Database;

import Structure.Def;
import Structure.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Products implements ListRegister {

	private static Products productsDB;
	private List<Product> products;
	private Stream<Product> filtered;

	public Products () {
		products = new LinkedList<>();
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

		if(checkProduct(name)) {
			Product new_product = new Product(name, price, expiration, provider, quantity);
			products.add(new_product);
			return 0;
		}
		return 1;
	}

	public int Register(String... value) {

		if(checkProduct(value[0])) {
			Product new_product = new Product(value[0], Float.parseFloat(value[1]),
					value[2], value[3], Integer.parseInt(value[4]));

			products.add(new_product);
			return 0;
		}
		return 1;
	}

	public boolean checkProduct(String name) {
		filtered = products.stream();
		filtered = filtered.filter(p -> p.getName().equals(name));
		return (filtered.count() == 0);
	}

	public Product searchProduct(String productName) {

		filtered = products.stream();
		filtered = filtered.filter(p -> p.getName().equals(productName));
		List<Product> collector = filtered.collect(Collectors.toList());

		return collector.get(0);
	}

	public String AllProducts(){
		String response = "";
		for(Product p : products) {
			response += p.getName() + Def.fieldSep
					+ Float.valueOf(p.getPrice()).toString() + Def.fieldSep
					+ Integer.valueOf(p.getQuantity()).toString() + Def.regSep;
		}
		return response;
	}
}
