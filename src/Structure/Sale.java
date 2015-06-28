package Structure;

import java.util.GregorianCalendar;
import java.util.List;

public class Sale {

	private User user;
	private List<CartItem> products;
	private String date;

	public Sale(User user, List<CartItem> products) {
		this.user = user;
		this.products = products;
		this.date = Def.CalendarToString(new GregorianCalendar());
	}

	public User getUser() { return user; }
	public List<CartItem> getProducts() { return products; }
	public String getDate() { return date; }
}