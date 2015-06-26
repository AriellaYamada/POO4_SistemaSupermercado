package Client;

import java.util.List;
import java.util.stream.Collectors;
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
		filtered = filtered.filter(p -> p.equals(product));
		return (filtered.count() == 0);
	}

	public void AddToCart(Product selProduct) {
		if(CheckCart(selProduct)) {
			products.add(selProduct);
		} else {

		}
	}

	public void AddOne(String product) {

	}

	public void RefreshQtd(String name, int newQtd) {
	}
}
