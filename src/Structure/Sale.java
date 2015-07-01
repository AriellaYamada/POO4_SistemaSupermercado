package Structure;

import Server.Database.Sales;

import java.util.GregorianCalendar;
import java.util.List;

public class Sale {

	private final User user;
	private final List<CartItem> products;
	private final String date;

	public Sale(String date, User user, List<CartItem> products) {
		this.user = user;
		this.products = products;
		this.date = date;
	}

	public Sale(User user, List<CartItem> products) {
		this.date = Sales.CalendarToString(new GregorianCalendar());
		this.user = user;
		this.products = products;
	}


	public User getUser() { return user; }
	public String getUserId() { return user.getId(); }
	public List<CartItem> getProducts() { return products; }
	public String getDate() { return date; }

	public int getNumberItems() {
		int nItems = 0;
		for (CartItem i : products) nItems += i.getReservedQtd();
		return nItems;
	}

	//Calcula o preco total da compra
	public double getTotalPrice() {
		double total = 0f;
		for (CartItem i : products) total += i.getTotalPrice();
		return total;
	}

	//Busca o ano da compra
	public int getYear(){
		String[] splited = date.split("/");
		return Integer.parseInt(splited[2]);
	}

	//Busca o mês da compra
	public int getMonth(){
		String[] splited = date.split("/");
		return Integer.parseInt(splited[1]);
	}

	//Busca o dia da compra
	public int getDay(){
		String[] splited = date.split("/");
		return Integer.parseInt(splited[0]);
	}

	//Retorna o preço total da compra como string
	public String getTotalPriceAsStr() { return String.format("%.2f", getTotalPrice()); }
}
