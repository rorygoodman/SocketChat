


 


 
import java.net.*;
import java.io.*;
 
public class main {
    public static void main(String[] args) throws IOException {
 
    if (args.length != 1) {
        System.err.println("Usage: <port number>");
        System.exit(1);
    }
        int portNumber = Integer.parseInt(args[0]);
        System.out.println(portNumber);
      
        boolean listening = true;
        File chat = new File("chat.txt"); 
        chat.createNewFile();
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
            	System.out.println("hi");
                new ClientThread(serverSocket.accept()).start();
                System.out.print("connection made");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
            
        }
    }
}

