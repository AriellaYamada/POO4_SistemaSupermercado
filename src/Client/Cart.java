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

	public synchronized boolean CheckCart(Product product) {
		filtered = products.stream();
		filtered = filtered.filter(p -> p.getName().equals(product.getName()));
		return (filtered.count() == 0);
	}

	public void Add(Product product, int qtd) {
		Product newProduct = new Product(product.getName(), product.getPrice(), product.getExpiration(),
				product.getProvider(), qtd);
		products.add(newProduct);
	}

	//Incrementa de 1 em 1 a quantidade do produto no carrinho
	public void RefreshQuantity(Product product, int qtd) {
		filtered = products.stream()
				.filter(p -> p.getName().equals(product.getName()));
		filtered.collect(Collectors.toList()).get(0).updateAmount(qtd);
	}

	public void Remove(Product product) {
		filtered = products.stream()
				.filter(p -> p.getName().equals(product.getName()));
		Product p = filtered.collect(Collectors.toList()).get(0);
		if (p.updateAmount(-1) == 0){
			products.remove(p);
		}
	}

	public List<Product> ListAll() { return products;}

	public String AllProducts() {
		String list = "";
		for (Product p : products) {
			list = p.getName() + p.getPriceAsStr() + p.getExpirationAsStr() + p.getProvider() + p.getQuantityAsStr();
		}
		return list;
	}

	public void ClearCart () {
		products.clear();
	}
}
