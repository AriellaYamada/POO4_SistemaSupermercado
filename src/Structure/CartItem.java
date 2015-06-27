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
	public int getReservedQtd() { return reservedQtd; }

	public boolean AddToCart(Cart cart, int qtd) {
		if(product.Reserve(qtd)) {
			reservedQtd += qtd;
			cart.AddProduct(this);
			return true;
		}
		return false;
	}

	public void RemoveFromCart(int qtd) {
		product.CancelReservation(qtd);
		reservedQtd -= qtd;
	}
}