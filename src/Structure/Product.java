package Structure;

import Client.Connection;

public class Product {

	private String name;
	private Float price;
	private String expiration;
	private String provider;
	private int amount_real;
	private int amount_virtual;

	public Product (String name, float price, String date, String provider, int amount) {
		this.name = name;
		this.price = price;
		this.expiration = date;
		this.provider = provider;
		amount_real = amount_virtual = amount;
	}

	public String getName() { return name; }
	public float getPrice() { return price; }
	public String getProvider() { return provider; }
	public int getAmount_real() { return amount_real; }
	public int getAmount_virtual() { return amount_virtual; }
	public String getExpiration() { return expiration; }

	public String getPriceAsStr() { return Float.toString(price); }
	public String getAmountRealAsStr() { return Integer.toString(amount_real); }
	public String getAmountVirtualAsStr() { return Integer.toString(amount_virtual); }

	public void setName(String value) { name = value; }
	public void setPrice(String value) { price = Float.parseFloat(value); }
	public void setPrice(Float value) { price = value; }
	public void setExpiration(String value) { expiration = value; }
	public void setProvider(String value) { provider = value; }

	private synchronized int setAmount (int amount){
		if (amount < 0) return -1;

		amount_real = amount;
		return amount_real;
	}

	public synchronized int updateAmount (int amount){
		if ((amount_real + amount) < 0) return -1;

		amount_real += amount;
		return amount_real;
	}

	public synchronized void refreshStock(int amount) {
		setAmount(amount);
	}

	public boolean RequestReservation(int qtd) {
		String signal = "reserve" + Def.regSep + name + Def.fieldSep + Integer.toString(qtd);
		Connection.getInstance().SendSignal(signal);
		return Connection.getInstance().ReceiveSignal().equals("ok");
	}

	public synchronized boolean Reserve(int qtd) {
		if (amount_virtual >= qtd) {
			amount_virtual -= qtd;
			return true;
		}
		return false;
	}

	public synchronized void Sell(int qtd){
		amount_real -= qtd;
	}

	public synchronized void CancelReservation(int qtd) {
		amount_virtual += qtd;
	}
}
