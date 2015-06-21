package Server;

import Database.UsersDatabase;

public class ServerMain {
	public static void main (String[] args){
		UsersDatabase.getInstance().ReadFile();
		Server server = new Server(Integer.parseInt(args[0]));
		Thread t = new Thread(server);
		t.run();
	}
}
