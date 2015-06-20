package Server;

public class Sale {

	private String clientId;
	private String product;
	private int quantity;

	public Sale (String clientId, String product, int quantity) {
		this.clientId = clientId;
		this.product = product;
		this.quantity = quantity;
	}

	public String getClientId() { return clientId; }
	public String getProduct() { return product; }
	public int getQuantity() { return quantity; }
}
