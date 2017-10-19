import java.io.IOException;
import java.net.*;
public class main {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			ServerSocket socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
