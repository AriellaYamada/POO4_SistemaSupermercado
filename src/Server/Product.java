package Server;

import javax.xml.soap.SAAJResult;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Product {

	private String name;
	private Float price;
	private GregorianCalendar expiration;
	private String provider;
	private int quantity;

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

	public static String CalendarToStr (GregorianCalendar date) {
		return date.get(Calendar.DAY_OF_MONTH) + "/"
				+(date.get(Calendar.MONTH)+1) + "/"
				+date.get(Calendar.YEAR);
	}

}
