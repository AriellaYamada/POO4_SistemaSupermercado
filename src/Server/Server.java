package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

import static java.lang.System.out;

public class Server implements Runnable {
	private ServerSocket ss = null;
	public Socket output = null;
	public Scanner scan = null;
	public PrintWriter pw = null;
	private boolean acceptConnections;

	Server(int port) {
		try {
			ss = new ServerSocket(port);
			//showPossibleIPs();
			System.out.println("* Porta " + port + " aberta!\n");
			acceptConnections = true;
		} catch (IOException e) {
			out.println("Erro ao criar o Socket");
			e.printStackTrace();
		}
	}

	private void showPossibleIPs(){
		try {
			out.println("May the 4th be with you.\n\nSeus possiveis IP's:");
			Enumeration e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements())
			{
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements())
				{
					InetAddress i = (InetAddress) ee.nextElement();
					System.out.println("\t> " +i.getHostAddress());
				}
			}
			out.println("* Provavel IP: " + Inet4Address.getLocalHost());
		} catch (IOException e){
			out.println("Erro ao exibir os seus IP's.\nContinuando com o programa...");
		}
	}

	public Socket waitConnection(){
		Socket conect = null;
		try {
			conect = ss.accept();
		} catch (IOException e){
			out.println("Erro ao criar conex�o.");
		}
		return conect;
	}

	public void run (){
		Socket s = null;
		while (acceptConnections){
			s = waitConnection();
			if (s != null){
				Conection c = new Conection(s);
				Thread t = new Thread(c);
				t.run();
			}
		}
	}
}
