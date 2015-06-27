package Structure;

import java.util.GregorianCalendar;
import Client.Cart;

public class Product {

	private String name;
	private Float price;
	private GregorianCalendar expiration;
	private String provider;
	private int quantity;

	public Product (String name, float price, String date, String provider, int quantity) {
		this.name = name;
		this.price = price;
		this.expiration = Def.StringToCalendar(date);
		this.provider = provider;
		this.quantity = quantity;
	}

	public Product (String name, float price, GregorianCalendar date, String provider, int quantity) {
		this.name = name;
		this.price = price;
		this.expiration = date;
		this.provider = provider;
		this.quantity = quantity;
	}

	public String getName() { return name; }
	public float getPrice() { return price; }
	public GregorianCalendar getExpiration() { return expiration; }
	public String getProvider() { return provider; }
	public int getQuantity() { return quantity; }

	public String getPriceAsStr() { return Float.toString(price); }
	public String getExpirationAsStr() { return Def.CalendarToString(expiration); }
	public String getQuantityAsStr() { return Integer.toString(quantity); }

	private synchronized int setAmount (int amount){
		if (amount < 0) return -1;

		quantity = amount;
		return quantity;
	}

	public synchronized int updateAmount (int amount){
		if ((quantity + amount) < 0) return -1;

		quantity += amount;
		return quantity;
	}

	public void refreshStock(int amount) {
		setAmount(amount);
	}

	public void addToCart() {
		if (Cart.getInstance().CheckCart(this))
			Cart.getInstance().Add(this);
		else
			quantity++;
	}

}
