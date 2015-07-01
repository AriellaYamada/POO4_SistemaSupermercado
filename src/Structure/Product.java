package Structure;

import Client.Connection;
import Def.Split;

public class Product {

	private final String name;
	private double price;
	private String expiration;
	private String provider;
	private int amount_real;
	private int amount_virtual;

	public Product (String name, double price, String date, String provider, int amount) {
		this.name = name;
		this.price = price;
		this.expiration = date;
		this.provider = provider;
		amount_real = amount_virtual = amount;
	}

	public String getName() { return name; }
	public double getPrice() { return price; }
	public String getProvider() { return provider; }
	public int getAmount_real() { return amount_real; }
	public int getAmount_virtual() { return amount_virtual; }
	public String getExpiration() { return expiration; }

	public String getPriceAsStr() { return String.format("%.2f", price); }
	public String getAmountRealAsStr() { return Integer.toString(amount_real); }
	public String getAmountVirtualAsStr() { return Integer.toString(amount_virtual); }

	public void setPrice(String value) { price = Double.parseDouble(value); }
	public void setPrice(Double value) { price = value; }
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
		amount_virtual += amount;
		return amount_real;
	}

	public synchronized void refreshStock(int amount) { setAmount(amount); }

	//Solicita ao servidor a reserva do produto
	public boolean RequestReservation(int qtd) {
		String signal = "reserve" + Split.regSep + name + Split.fieldSep + Integer.toString(qtd);
		Connection.getInstance().SendSignal(signal);
		return Connection.getInstance().ReceiveSignal().equals("ok");
	}

	//Reserva o produto
	public synchronized boolean Reserve(int qtd) {
		if (amount_virtual >= qtd) {
			amount_virtual -= qtd;
			return true;
		}
		return false;
	}

	//Realiza a venda do prouduto
	public synchronized void Sell(int qtd){
		amount_real -= qtd;
	}

	//Cancela a reserva
	public synchronized void CancelReservation(int qtd) {
		amount_virtual += qtd;
	}

	public void selfRefresh() {
		Connection.getInstance().SendSignal("selfrefresh" + Split.regSep + this.name);
		String answer = Connection.getInstance().ReceiveSignal();
		amount_real = amount_virtual = Integer.parseInt(answer);
	}

	public void selfRefreshCart() {
		Connection.getInstance().SendSignal("selfrefreshcart" + Split.regSep + this.name);
		String answer = Connection.getInstance().ReceiveSignal();
		String[] values = answer.split(Split.fieldSep);
		amount_virtual = Integer.parseInt(values[0]);
		amount_real = Integer.parseInt(values[1]);
	}
}
