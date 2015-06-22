package Client;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cart {

	private static Cart cart;

	private List<OnCart> products;
	private Stream<OnCart> filtered;

	//Singleton
	public static Cart getInstance() {
		if (cart == null)
			cart = new Cart();
		return cart;
	}

	public boolean CheckCart(String product) {
		filtered = products.stream();
		filtered = filtered.filter(p -> p.getName().equals(product));
		return (filtered.count() == 0);
	}

	public void AddToCart(String name, float price) {
		if(CheckCart(name)) {
			OnCart NewItem = new OnCart(name, price);
			products.add(NewItem);
		} else {
			AddOne(name);
		}
	}

	public void AddOne(String product) {
		filtered = products.stream();
		filtered = filtered.filter(p -> p.getName().equals(product));
		filtered.collect(Collectors.toList()).get(0).AddOne();
	}

	public void RefreshQtd(String name, int newQtd) {
	}
}
