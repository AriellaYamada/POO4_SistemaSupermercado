package Structure;

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
		this.date = Def.CalendarToString(new GregorianCalendar());
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

	public float getTotalPrice() {
		Float total = 0f;
		for (CartItem i : products) total += i.getTotalPrice();
		return total;
	}

	public int getYear(){
		String[] splited = date.split("/");
		return Integer.parseInt(splited[2]);
	}
	public int getMonth(){
		String[] splited = date.split("/");
		return Integer.parseInt(splited[1]);
	}

	public int getDay(){
		String[] splited = date.split("/");
		return Integer.parseInt(splited[0]);
	}

	public String getTotalPriceAsStr() { return String.format("%.2f", getTotalPrice()); }
}
