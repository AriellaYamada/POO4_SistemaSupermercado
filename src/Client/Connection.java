package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Connection {

	public Socket sock;
	public PrintWriter signalOut;
	public Scanner signalIn;

	public Connection () {
		try {
			ConnectServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void ConnectServer () throws IOException {

		Scanner scan = new Scanner(in);
		out.println("Digite o IP: ");
		String ip = scan.nextLine();
		out.println("Digite a porta: ");
		int port = Integer.parseInt(scan.nextLine());
		//Conectar com servidor
		sock = new Socket(ip, port);
		//"Porta" de saida de mensagens para o servidor
		signalOut = new PrintWriter(sock.getOutputStream(), true);
		//"Porta" de entrada de mensagens do servidor
		signalIn = new Scanner(sock.getInputStream());

	}

	//Método que encerra a conexão com o servidor
	public void CloseConnectionClient () throws IOException {
		sock.close();
	}

	//Método que envia mensagem para o servidor
	public void SendSignal(String response) {
		signalOut.println(response);
		System.out.println(response);
	}

	//Método que recebe mensagem do servidor
	public String ReceiveSignal() {

		return signalIn.nextLine();
	}
}
