package Server;

import Server.Database.Products;
import Server.Database.Users;
import Server.Database.UsersDatabase;
import Structure.Cart;
import Structure.CartItem;
import Structure.Def;
import Structure.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

public class Connection implements Runnable{
	private Socket sock = null;
	public Scanner scan = null;
	public PrintWriter pw = null;

	private User user = null;
	private Cart cart = null;

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

		while (scan.hasNextLine()){
			String line = scan.nextLine();
			String result = process(line);
			pw.println(result);
		}
	}

	//Processa o comando recebido do cliente
	public String process (String line){
		String[] cmd = Def.splitReg(line);
		String[] args;
		int response = -2;
		switch (cmd[0]){
			//Cadastro de novo usuario
			case "newuser":
				args = Def.splitField(cmd[1]);
				response = Users.getInstance().Register(args[0], args[1], args[2], args[3], args[4], args[5]);
				UsersDatabase.getInstance().WriteFile();
				if (response == 0)
					line = "ok";
				else
					line = "fail"+ Def.regSep +"f_id"+ Def.fieldSep +"Este login ja esta sendo utilizado";
				break;
			//Efetuar login
			case "login":
				args = Def.splitField(cmd[1]);
				response = Users.getInstance().Login(args[0], args[1]);
				//Se o login foi efetuado corretamente
				if (response == 0)
					line = "ok";
					//Caso o usuario nao seja encontrado
				else if (response == 1)
					line = "fail"+ Def.regSep +"f_userlogin"+ Def.fieldSep +"Usuario nao encontrado";
					//Caso a senha digitada for incorreta
				else
					line = "fail"+ Def.regSep +"f_userpassword"+ Def.fieldSep +"Senha incorreta";
				break;
			//Busca o nome do usuario logado
			case "getname":
				line = Users.getInstance().GetUserName(cmd[1]);
				break;
			//Busca todos os produtos cadastrados no sistema
			case "listall":
				line = Products.getInstance().AllProducts();
				break;
			//Solicita a reserva de um produto
			case "reserve":
				CartItem item;
				args = Def.splitField(cmd[1]);
				if (cart.CheckCart(args[0])) {
					item = new CartItem(Products.getInstance().searchProduct(args[0]));
				} else {
					item = cart.searchItem(args[0]);
				}
				if(item.AddToCart(cart, Integer.parseInt(args[1])))
					line = "ok";
				else
					line = "fail";

				break;
			case "dereserve":
				args = Def.splitField(cmd[1]);
				//Encontra o produto no carrinho
				item = cart.searchItem(args[0]);
				//Remove a quantidade desejava da reserva
				item.RemoveFromCart(Integer.parseInt(args[1]));
				//Verifica se ainda há algum produto daquele tipo cadastrado
				if (item.getReservedQtd() == 0)
					cart.RemoveProduct(item);
				line = "ok";
				break;
			case "listcart":
				line = cart.ListAllAsStr();
				break;
			case "sell":

				break;
			default:
				break;
		}
		return line;
	}
}
