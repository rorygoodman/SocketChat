
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class ClientThread extends Thread {
    private Socket socket = null;
    public ClientThread(Socket socket) {
        super("main");
        this.socket = socket;
    }   
    public void run() {
    	boolean alive=true;
    	String chatroom,alias,ip,port;
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
        	System.out.println("howdy");
        	Scanner scan = new Scanner("chat.txt");
        	PrintWriter chatWriter = new PrintWriter("chat.txt", "UTF-8");
        	while(scan.nextLine()!=null){
        			
        	}
            String inputLine, outputLine;
            while (alive) {
            	inputLine=in.readLine();
            	if(inputLine!=null){
            		//if(inputLine.substring("JOIN_CHATROOM:"))
            	}
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

