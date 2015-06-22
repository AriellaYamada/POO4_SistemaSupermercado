package Client;

public class Cart {

	private static Cart cart;

	//Singleton
	public static Cart getInstance() {
		if (cart == null)
			cart = new Cart();
		return cart;
	}
}
