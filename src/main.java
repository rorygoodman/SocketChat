


 
import java.net.*;
import java.io.*;
 
public class main {
    public static void main(String[] args) throws IOException {
 
    if (args.length != 1) {
        System.err.println("No port no. entered");
        System.exit(1);
    }
    int portNumber = Integer.parseInt(args[0]);
    boolean listening = true;
     
    try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
        while (listening) {
            new ClientThread(serverSocket.accept()).start();
        }
    } catch (IOException e) {
        System.err.println("Could not listen on port " + portNumber);
        System.exit(-1);
    }
    }
}
