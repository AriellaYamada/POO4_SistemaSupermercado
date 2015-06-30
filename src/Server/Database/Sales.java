package Server.Database;

import Structure.Cart;
import Structure.CartItem;
import Structure.Sale;
import Structure.User;

import java.util.LinkedList;
import java.util.List;

public class Sales implements ListRegister{

	private static Sales salesDB;
	private static List<Sale> sales;

	private Sales() { sales = new LinkedList<>(); }

	//Singleton
	public static Sales getInstance() {
		if (salesDB == null){
			salesDB = new Sales();
		}
		return salesDB;
	}

	public static List<Sale> getSales() {
		return sales;
	}

	public int Register(String... value){
		User user = Users.SearchUser(value[1]);
		List<CartItem> products = new LinkedList<>();

		for (int i = 2; i < value.length; i+=3){
			products.add(new CartItem(value[i], value[i+1], value[i+2]));
		}

		Sale sale = new Sale(value[0], user, products);
		sales.add(sale);
		return 0;
	}

	public static void Register(User user, Cart cart) {
		Sale sale = new Sale(user, cart.ListAll());
		sales.add(sale);

		SalesDatabase.getInstance().WriteFile(sale);
		cart.Finalize();
		ProductsDatabase.getInstance().WriteFile();
	}
}
