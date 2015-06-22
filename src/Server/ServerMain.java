package Server;

import Database.Products;
import Database.ProductsDatabase;
import Database.SalesDatabase;
import Database.UsersDatabase;

public class ServerMain {
	public static void main (String[] args){
		UsersDatabase.getInstance().ReadFile();
		ProductsDatabase.getInstance().ReadFile();
		SalesDatabase.getInstance().ReadFile();
		Server server = new Server(Integer.parseInt(args[0]));
		Thread t = new Thread(server);
		t.run();
	}
}
