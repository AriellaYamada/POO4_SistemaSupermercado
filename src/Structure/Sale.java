package Structure;

import java.util.GregorianCalendar;

public class Sale {

	private final String clientId;
	private final String product;
	private final int quantity;
	private final GregorianCalendar date;

	public Sale (String clientId, String product, int quantity, String date) {
		this.clientId = clientId;
		this.product = product;
		this.quantity = quantity;
		this.date = Def.StringToCalendar(date);
	}

	public String getClientId() { return clientId; }
	public String getProduct() { return product; }
	public int getQuantity() { return quantity; }
	public GregorianCalendar getDate() { return date; }
}
