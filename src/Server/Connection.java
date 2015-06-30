package Server;

import Server.Database.*;
import Structure.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

public class Connection implements Runnable{
	private Socket sock = null;
	public Scanner scan = null;
	public PrintWriter pw = null;

	private User user;
	private Cart cart = null;
	private boolean connected;

	//Estabelece conexao com o cliente
	Connection(Socket s){
		try {
			sock = s;
			scan = new Scanner(sock.getInputStream());
			pw = new PrintWriter(sock.getOutputStream(), true);
			out.println("+ Nova conexao: " + sock.getInetAddress().getHostAddress());
		} catch (IOException e) {
			out.println("Erro ao criar conexão");
		}
	}

	//Thread que espera comando do cliente
	public void run(){
		cart = new Cart();
		connected = true;

		while (scan.hasNextLine() && connected){
			String line = scan.nextLine();
			String result = process(line);
			pw.println(result);
		}

		cart.ClearCart();
	}

	//Processa o comando recebido do cliente
	public String process (String line){
		String[] cmd = Def.splitReg(line);

		switch (cmd[0]){
			case "newuser": //Cadastro de novo usuario
				return newuser(cmd[1]);

			case "login":   //Efetuar login
				return login(cmd[1]);

			case "getname": //Busca o nome do usuario logado
				return Users.GetUserName(cmd[1]);

			case "listall": //Busca todos os produtos cadastrados no sistema
				return Products.AllProducts();

			//Solicita a reserva de um produto
			case "reserve":
				return reserve(cmd[1]);

			case "dereserve":
				return dereserve(cmd[1]);

			case "listcart":
				line = cart.ListAllAsStr();
				break;

			case "sell":
				return sell();

			case "clearcart" :
				cart.ClearCart();
				break;

			case "selfrefresh":
				line = Products.searchProduct(cmd[1]).getAmountVirtualAsStr();
				break;

			case "logout":
				return logout();

			default:
				break;
		}
		return line;
	}

	private String newuser(String cmd) {
		String answer;
		String[] args = Def.splitField(cmd);
		int response = Users.Register(args[0], args[1], args[2], args[3], args[4], args[5]);

		if (response == 0) {
			answer = "ok";
			UsersDatabase.getInstance().WriteFile();
		}
		else {
			answer = "fail" + Def.regSep + "f_id" + Def.fieldSep + "Este login ja esta sendo utilizado";
		}

		return answer;
	}

	private String login(String cmd) {
		String answer;
		String[] args = Def.splitField(cmd);
		int response = Users.Login(args[0], args[1]);

		if (response == 0) {   //Se o login foi efetuado corretamente
			user = Users.SearchUser(args[0]);
			answer = "ok";
		}

		else if (response == 1) //Caso o usuario nao seja encontrado
			answer = "fail"+ Def.regSep +"f_userlogin"+ Def.fieldSep +"Usuario nao encontrado";

		else    //Caso a senha digitada for incorreta
			answer = "fail"+ Def.regSep +"f_userpassword"+ Def.fieldSep +"Senha incorreta";

		return answer;
	}

	private String reserve(String cmd) {
		CartItem item;
		String[] args = Def.splitField(cmd);
		boolean errorFlag;

		if (cart.CheckCart(args[0])) {
			item = new CartItem(Products.searchProduct(args[0]));
			errorFlag = item.AddToCart(cart);
		} else {
			item = cart.searchItem(args[0]);
			errorFlag = item.RefreshQuantity(Integer.parseInt(args[1]));
		}

		if(errorFlag) return "ok";
		return "fail";
	}

	private String dereserve(String cmd) {
		CartItem item;
		String[] args = Def.splitField(cmd);

		//Encontra o produto no carrinho
		item = cart.searchItem(args[0]);
		//Remove a quantidade desejava da reserva
		item.RemoveFromCart(Integer.parseInt(args[1]));

		//Verifica se ainda há algum produto daquele tipo cadastrado
		if (item.getReservedQtd() == 0)
			cart.RemoveProduct(item);

		return "ok";
	}

	private String sell() {
		Sales.Register(user, cart);

		return "ok";
	}

	private String logout() {
		connected = false;

		return "ok";
	}
}
