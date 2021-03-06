package Structure;

import Server.Database.Products;

public class CartItem {

	private Product product;
	private int reservedQtd;
	private double curPrice;

	public CartItem(Product product) {
		this.product = product;
		curPrice = product.getPrice();
	}

	public CartItem(String name, String amount, String price) {
		product = Products.searchProduct(name);
		reservedQtd = Integer.parseInt(amount);
		curPrice = Double.parseDouble(price);
	}

	public Product getProduct() { return product; }
	public String getProductName() { return product.getName(); }
	public int getReservedQtd() { return reservedQtd; }
	public String getReservedQtdAsStr() { return Integer.toString(reservedQtd); }
	public double getPrice() { return curPrice; }
	public String getPriceAsStr() { return String.format("%.2f", curPrice); }
	public double getTotalPrice() { return reservedQtd * curPrice; }
	public String getTotalPriceAsStr() { return String.format("%.2f", getTotalPrice()); }

	//Adiciona o produto ao carrinho
	public boolean AddToCart(Cart cart) {
		if(product.Reserve(1)) {
			reservedQtd ++;
			cart.AddProduct(this);
			return true;
		}
		return false;
	}

	//Atualiza a quantidade solicitada
	public boolean RefreshQuantity(int qtd) {
		if (product.Reserve(qtd)) {
			reservedQtd += qtd;
			return true;
		}
		return false;
	}

	//Remove do carrinho
	public void RemoveFromCart(int qtd) {
		product.CancelReservation(qtd);
		reservedQtd -= qtd;
	}
}