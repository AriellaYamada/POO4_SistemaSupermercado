package Server;

import Def.Split;
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
		String[] cmd = Split.splitReg(line);

		switch (cmd[0]){
			case "newuser": //Cadastro de novo usuario
				return newuser(cmd[1]);

			case "login":   //Efetuar login
				return login(cmd[1]);

			case "getname": //Busca o nome do usuario logado
				return Users.GetUserName(cmd[1]);

			case "listall": //Busca todos os produtos cadastrados no sistema
				return Products.AllProducts();

			case "reserve": //Solicita a reserva de um produto
				return reserve(cmd[1]);

			case "dereserve": //Cancela a reserva de um produto
				return dereserve(cmd[1]);

			case "listcart": //Lista todos os itens no carrinho do cliente
				line = cart.ListAllAsStr();
				break;

			case "sell": //Solicita a compra de um produto
				return sell();

			case "clearcart" : //Limpeza do carrinho
				cart.ClearCart();
				break;

			case "selfrefresh": //Atualizacao da lista de produtos
				line = Products.searchProduct(cmd[1]).getAmountVirtualAsStr();
				break;

			case "logout": //Saida do sistema
				return logout();

			default:
				break;
		}
		return line;
	}

	//Realiza o cadastro do cliente
	private String newuser(String cmd) {
		String answer;
		String[] args = Split.splitField(cmd);
		int response = Users.Register(args[0], args[1], args[2], args[3], args[4], args[5]);

		//Verifica se for possivel realizar o cadastro
		if (response == 0) {
			//Resposta enviada para a aplicacao de cliente
			answer = "ok";
			//Atualiza o arquivo de registros de clientes
			UsersDatabase.getInstance().WriteFile();
		}
		else {
			//Mensagem de erro enviada ao cliente, caso o cadastro nao seja possivel
			answer = "fail" + Split.regSep + "f_id" + Split.fieldSep + "Este login ja esta sendo utilizado";
		}

		return answer;
	}

	//Verifica se o login pode ser realizado
	private String login(String cmd) {
		String answer;
		String[] args = Split.splitField(cmd);
		int response = Users.Login(args[0], args[1]);

		if (response == 0) {   //Se o login foi efetuado corretamente
			user = Users.SearchUser(args[0]);
			answer = "ok";
		}

		else if (response == 1) //Caso o usuario nao seja encontrado
			answer = "fail"+ Split.regSep +"f_userlogin"+ Split.fieldSep +"Usuario nao encontrado";

		else    //Caso a senha digitada for incorreta
			answer = "fail"+ Split.regSep +"f_userpassword"+ Split.fieldSep +"Senha incorreta";

		return answer;
	}

	//Reserva o produto solicitado
	private String reserve(String cmd) {
		CartItem item;
		String[] args = Split.splitField(cmd);
		boolean errorFlag;

		//Verifica se o produto ja esta no carrinho
		if (cart.CheckCart(args[0])) {
			//Adiciona caso nao esteja
			item = new CartItem(Products.searchProduct(args[0]));
			errorFlag = item.AddToCart(cart);
		} else {
			//Atualiza a quantidade caso ja esteja
			item = cart.searchItem(args[0]);
			errorFlag = item.RefreshQuantity(Integer.parseInt(args[1]));
		}

		if(errorFlag) return "ok";
		return "fail";
	}

	//Cancela a reserva de um produto
	private String dereserve(String cmd) {
		CartItem item;
		String[] args = Split.splitField(cmd);

		//Encontra o produto no carrinho
		item = cart.searchItem(args[0]);
		//Remove a quantidade desejava da reserva
		item.RemoveFromCart(Integer.parseInt(args[1]));

		//Verifica se ainda há algum produto daquele tipo cadastrado
		if (item.getReservedQtd() == 0)
			cart.RemoveProduct(item);

		return "ok";
	}

	//Cadastra a venda
	private String sell() {
		Sales.Register(user, cart);

		return "ok";
	}

	//Realiza a finalizacao da thread do cliente
	private String logout() {
		connected = false;

		return "ok";
	}
}
