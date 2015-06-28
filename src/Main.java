import Server.Database.Products;
import Server.Database.ProductsDatabase;
import Server.Database.Sales;
import Server.PDFCreator;
import Structure.User;

import java.io.IOException;

import static java.lang.System.out;

public class Main {
	public static void main (String[] args) throws IOException {
		out.println("Como você deseja conectar?");

		ProductsDatabase.getInstance().ReadFile(Products.getInstance());
/*
		Screennn scr = new Screennn();
		Stage stg = new Stage();
		scr.start(stg);

		String s = new Scanner(System.in).nextLine();
		if (s.equals("server")){
			ServerMain.main(null);
		}
		else {
			ClientMain.main(null);
		}*/

		/*User user1 = new User("Xossé", "rua x", "12345678", "emeio@emeio", "xosse", "datsenha");
		User user2 = new User("Maria", "rua y", "23456789", "maria@emeio", "maryjane", "senha123");

		Sales sales = Sales.getInstance();

		

		PDFCreator p = new PDFCreator();
		p.CreatePDF("2015fevereiro", sales.ListAll());*/
	}
}
