package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {

	private static Connection connection;

	private Socket sock;
	private PrintWriter signalOut;
	private Scanner signalIn;

	public static Connection getInstance() {
		if (connection == null)
			connection = new Connection();
		return connection;
	}

	public void Connect (String ip, int port) throws IOException{
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
		//System.out.println(response);
	}

	//Método que recebe mensagem do servidor
	public String ReceiveSignal() {
		return signalIn.nextLine();
	}
}
