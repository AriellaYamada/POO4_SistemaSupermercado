package Server;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Product {

	private String name;
	private Float price;
	private GregorianCalendar expiration;
	private String provider;
	private int quantity;

	public Product (String name, float price, String date, String provider, int quantity) {
		this.name = name;
		this.price = price;
		this.expiration = StrToCalendar(date);
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

	public static GregorianCalendar StrToCalendar (String date) {
		String[] split_date = date.split("/");

		return new GregorianCalendar(Integer.parseInt(split_date[2]),
				Integer.parseInt(split_date[1])-1,
				Integer.parseInt(split_date[0]));
	}
}
