package Structure;

import Server.Database.Products;

public class CartItem {

	private Product product;
	private int reservedQtd;
	private float curPrice;

	public CartItem(Product product) {
		this.product = product;
		curPrice = product.getPrice();
	}

	public CartItem(String name, String amount, String price) {
		product = Products.searchProduct(name);
		reservedQtd = Integer.parseInt(amount);
		curPrice = Float.parseFloat(price);
	}

	public Product getProduct() { return product; }
	public String getProductName() { return product.getName(); }
	public int getReservedQtd() { return reservedQtd; }
	public String getReservedQtdAsStr() { return Integer.toString(reservedQtd); }
	public float getPrice() { return curPrice; }
	public String getPriceAsStr() { return String.format("%.2f", curPrice); }
	public float getTotalPrice() { return reservedQtd * curPrice; }
	public String getTotalPriceAsStr() { return String.format("%.2f", getTotalPrice()); }

	public boolean AddToCart(Cart cart) {
		if(product.Reserve(1)) {
			reservedQtd ++;
			cart.AddProduct(this);
			return true;
		}
		return false;
	}

	public boolean RefreshQuantity(int qtd) {
		if (product.Reserve(qtd)) {
			reservedQtd += qtd;
			return true;
		}
		return false;
	}

	public void RemoveFromCart(int qtd) {
		product.CancelReservation(qtd);
		reservedQtd -= qtd;
	}
}