package Structure;

import Server.cmdProcess;
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


	public void refreshStock(int qtd) {
		quantity = qtd;
	}

	public void addToCart() {
		if (Cart.getInstance().CheckCart(this))
			Cart.getInstance().Add(this);
		else
			quantity++;
	}
}
