import Server.Database.Products;
import Server.Database.ProductsDatabase;
import Server.Database.Sales;
import Server.PDFCreator;
import Structure.*;

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

		/*
		// Teste para criação do PDF
		User user1 = new User("Xossé", "rua x", "12345678", "emeio@emeio", "xosse", "datsenha");
		User user2 = new User("Maria", "rua y", "23456789", "maria@emeio", "maryjane", "senha123");


		Product p1 = new Product("batata", 0.2f, "02/02/2015", "deus", 4);
		CartItem ci1 = new CartItem(p1);
		Product p2 = new Product("limão", 0.5f, "02/02/2015", "deus", 40);
		CartItem ci2 = new CartItem(p2);
		Product p3 = new Product("chocolate", 4.0f, "02/02/2015", "nestle", 50);
		CartItem ci3 = new CartItem(p3);
		Product p4 = new Product("agua", 2.3f, "02/02/2015", "crystal", 20);
		CartItem ci4 = new CartItem(p4);

		Cart c1 = new Cart();
		Cart c2 = new Cart();

		ci1.AddToCart(c1);
		ci2.AddToCart(c1);
		ci3.AddToCart(c2);
		ci4.AddToCart(c2);

		Sales sales = Sales.getInstance();

		sales.Register(user1, c1.ListAll());
		sales.Register(user2, c2.ListAll());

		PDFCreator p = new PDFCreator();
		p.CreatePDF("2015fevereiro", sales.getSales());
		*/
	}
}
