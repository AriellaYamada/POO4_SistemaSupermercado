package Server.Database;

import Structure.Cart;
import Structure.CartItem;
import Structure.Sale;
import Structure.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

	//Lista de todas as vendas efetuadas
	public static List<Sale> getSales() {
		return sales;
	}

	//Converte a data em string
	public static String CalendarToString (GregorianCalendar date) {
		return date.get(Calendar.DAY_OF_MONTH) + "/"
				+(date.get(Calendar.MONTH)+1) + "/"
				+date.get(Calendar.YEAR);
	}

	//Cadastra uma venda a partir da leitura do .csv (apenas strings)
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

	//Cadastra uma venda pela interface
	public static void Register(User user, Cart cart) {
		Sale sale = new Sale(user, cart.ListAll());
		sales.add(sale);

		//Atualiza os arquivos .csv
		SalesDatabase.getInstance().WriteFile(sale);
		cart.Finalize();
		ProductsDatabase.getInstance().WriteFile();
	}
}
