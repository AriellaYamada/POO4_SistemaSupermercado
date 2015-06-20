package Server;

public class ServerMain {
	public static void main (String[] args){
		Server server = new Server(Integer.parseInt(args[0]));
		Thread t = new Thread(server);
		t.run();
	}
}
