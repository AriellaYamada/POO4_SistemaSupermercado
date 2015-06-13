package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

public class Conection implements Runnable{
	private Socket sock = null;
	public Scanner scan = null;
	public PrintWriter pw = null;

	Conection(Socket s){
		try {
			sock = s;
			scan = new Scanner(sock.getInputStream());
			pw = new PrintWriter(sock.getOutputStream(), true);
			out.println("+ Nova conexao: " + sock.getInetAddress().getHostAddress());
		} catch (IOException e) {
			out.println("Erro ao criar conexão");
		}
	}

	public void run(){
		while (scan.hasNextLine()){
			String line = scan.nextLine();
			String result = Database.process(line);
			pw.println(result);
		}
	}
}
