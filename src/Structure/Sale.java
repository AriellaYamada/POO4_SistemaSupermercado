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

	public String getTotalPriceAsStr() { return Float.toString(getTotalPrice()); }
}
