import java.io.IOException;
import java.net.*;
import java.io.*;
public class main {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
