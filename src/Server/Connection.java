package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

public class Connection implements Runnable{
	private Socket sock = null;
	public Scanner scan = null;
	public PrintWriter pw = null;

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
		while (scan.hasNextLine()){
			String line = scan.nextLine();
			String result = cmdProcess.process(line);
			pw.println(result);
		}
	}
}
