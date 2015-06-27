package Client;

import java.util.List;
import java.util.stream.Stream;
import Structure.Product;

public class Cart {

	private static Cart cart;

	private List<Product> products;
	private Stream<Product> filtered;

	//Singleton
	public static Cart getInstance() {
		if (cart == null)
			cart = new Cart();
		return cart;
	}

	public boolean CheckCart(Product product) {
		filtered = products.stream();
		filtered = filtered.filter(p -> p.getName().equals(product.getName()));
		return (filtered.count() == 0);
	}

	public void Add(Product product) {
		Product newProduct = new Product(product.getName(), product.getPrice(), product.getExpiration(),
				product.getProvider(), product.getQuantity());
		products.add(newProduct);
	}
}
