package Client;

public class ClientMain {

	public static void main (String[] args) {

		Client c = new Client();

		c.SendSignal();
		c.ReceiveSignal();
		//c.ReceiveSignal();
	}
}
