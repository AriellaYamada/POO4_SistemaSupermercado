package Structure;

public class Product {

	private String name;
	private Float price;
	private String expiration;
	private String provider;
	private int quantity;

	public Product (String name, float price, String date, String provider, int quantity) {
		this.name = name;
		this.price = price;
		this.expiration = date;
		this.provider = provider;
		this.quantity = quantity;
	}

	public String getName() { return name; }
	public float getPrice() { return price; }
	public String getProvider() { return provider; }
	public int getQuantity() { return quantity; }
	public String getExpiration() { return expiration; }

	public String getPriceAsStr() { return Float.toString(price); }
	public String getQuantityAsStr() { return Integer.toString(quantity); }

	public void setName(String value) { name = value; }
	public void setPrice(String value) { price = Float.parseFloat(value); }
	public void setPrice(Float value) { price = value; }
	public void setExpiration(String value) { expiration = value; }
	public void setProvider(String value) { provider = value; }


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

	public synchronized void refreshStock(int amount) {
		setAmount(amount);
	}
}
