package Client;

public class OnCart {

	private String name;
	private Float price;
	private int quantity;

	public OnCart(String name, float price) {
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}

	public String getName() { return name; }
	public Float getPrice() {return price; }
	public int getQuantity() { return quantity; }

	public void AddOne() { quantity++; }
}
