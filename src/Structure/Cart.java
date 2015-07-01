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

	//Verifica se um produto ja foi adicionado ao carrinho
	public boolean CheckCart(String name) {
		filtered = products.stream()
				.filter(p -> p.getProduct().getName().equals(name));
		return (filtered.count() == 0);
	}

	//Busca um item do carrinho a partir do nome
	public CartItem searchItem(String name) {
		filtered = products.stream()
				.filter(i -> i.getProduct().getName().equals(name));
		return filtered.collect(Collectors.toList()).get(0);
	}

	//Adiciona um produto ao carrinho
	public void AddProduct(CartItem product) {
		products.add(product);
	}

	//Remove um produto do carrinho
	public void RemoveProduct(CartItem product) {
		products.remove(product);
	}

	//Lista todos os produtos que estao no carrinho
	public List<CartItem> ListAll() { return products; }

	//Lista todos os produtos armazenados no carrinho como uma unica string para envio para a aplicacao do cliente
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

	//Finalizacao da compra
	public void Finalize() {
		for(CartItem item : products) {
			item.getProduct().Sell(item.getReservedQtd());
		}
		products = new LinkedList<>();
	}

	//Limpa o carrinho de compras
	public void ClearCart(){
		for(CartItem i : products) {
			i.RemoveFromCart(i.getReservedQtd());
		}
		products.clear();
	}
}