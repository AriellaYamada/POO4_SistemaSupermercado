package Server;

import Server.Database.*;

public class ServerMain {
	public static void main (String[] args){
		UsersDatabase.getInstance().ReadFile(Users.getInstance());
		ProductsDatabase.getInstance().ReadFile(Products.getInstance());
		//SalesDatabase.getInstance().ReadFile(Sales.getInstance());
		Server server = new Server(Integer.parseInt(args[0]));
		Thread t = new Thread(server);
		t.run();
	}
}
