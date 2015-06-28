package Structure;

public class CartItem {

	private Product product;
	private int reservedQtd;
	private float curPrice;

	public CartItem(Product product) {
		this.product = product;
		curPrice = product.getPrice();
	}

	public Product getProduct() { return product; }
	public String getProductName() { return product.getName(); }
	public int getReservedQtd() { return reservedQtd; }
	public String getReservedQtdAsStr() { return Integer.toString(reservedQtd); }
	public float getPrice() { return curPrice; }
	public String getPriceAsStr() { return Float.toString(curPrice); }
	public float getTotalPrice() { return reservedQtd * curPrice; }
	public String getTotalPriceAsStr() { return Float.toString(getTotalPrice()); }

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