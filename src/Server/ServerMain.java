package Server;

import Server.Database.*;

public class ServerMain {

	public static void main (String[] args){
		//Leitura de todos os registros armazenados no banco de dados de cliente, produtos e vendas
		UsersDatabase.getInstance().ReadFile(Users.getInstance());
		ProductsDatabase.getInstance().ReadFile(Products.getInstance());
		SalesDatabase.getInstance().ReadFile(Sales.getInstance());
		//Inicializa o servidor
		Server server = new Server(Integer.parseInt(args[0]));
		Thread t = new Thread(server);
		t.start();
	}
}
