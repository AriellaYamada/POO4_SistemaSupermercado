package Structure;

import Client.Cart;

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

	public synchronized void Reserve() {
		quantity--;
	}

	public synchronized boolean AddToCart(int qtd) {
		if (quantity>0) {
			if (Cart.getInstance().CheckCart(this))
				Cart.getInstance().Add(this, qtd);
			else
				Cart.getInstance().RefreshQuantity(this, qtd);
			return true;
		}
		return false;
	}

	public synchronized boolean Reserve(int qtd) {
		return (updateAmount(-qtd) > 0);
	}

	public synchronized void Dereserve(int qtd) {
		updateAmount(qtd);
	}
}
