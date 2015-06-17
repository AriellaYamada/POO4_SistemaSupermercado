package Server;

public class ServerMain {
	public static void main (String[] args){
		Server server = new Server(12345);
		Thread t = new Thread(server);
		t.run();
	}
}
