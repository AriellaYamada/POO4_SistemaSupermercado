package Structure;

import Def.Split;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cart {

	private List<CartItem> products;
	private Stream<CartItem> filtered;

	public Cart() {
		products = new LinkedList<>();
	}

	public boolean CheckCart(String name) {
		filtered = products.stream()
				.filter(p -> p.getProduct().getName().equals(name));
		return (filtered.count() == 0);
	}

	public CartItem searchItem(String name) {
		filtered = products.stream()
				.filter(i -> i.getProduct().getName().equals(name));
		return filtered.collect(Collectors.toList()).get(0);
	}

	public void AddProduct(CartItem product) {
		products.add(product);
	}

	public void RemoveProduct(CartItem product) {
		products.remove(product);
	}

	public List<CartItem> ListAll() { return products; }

	public String ListAllAsStr() {
		String response = "";
		for (CartItem i : products) {
			response += i.getProduct().getName() + Split.fieldSep
					+ i.getProduct().getPrice() + Split.fieldSep
					+ i.getProduct().getExpiration() + Split.fieldSep
					+ i.getProduct().getProvider() + Split.fieldSep
					+ i.getReservedQtd() + Split.regSep;
		}
		return response;
	}

	public void Finalize() {
		for(CartItem item : products) {
			item.getProduct().Sell(item.getReservedQtd());
		}
		products = new LinkedList<>();
	}

	public void ClearCart(){
		for(CartItem i : products) {
			i.RemoveFromCart(i.getReservedQtd());
		}
		products.clear();
	}
}