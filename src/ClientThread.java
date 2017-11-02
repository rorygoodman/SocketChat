
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
    	String chatroom,alias,clientIP,port;
        try (
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
        	System.out.println("howdy");
        	//BufferedReader readChat = new BufferedReader("chat.txt");
        	PrintWriter chatWriter = new PrintWriter("chat.txt", "UTF-8");
            String inputLine, outputLine;
            while (alive) {
            	inputLine=in.readLine();
            	if(inputLine!=null){
            		if(inputLine.contains("HELO")){
            			
            			outputLine= "HELO"+inputLine.substring("HELO".length())+"\nIP:134.226.50.34\nPort:1234\nStudentID:14316993";
            			System.out.println(outputLine);
            			outStream.write(outputLine.getBytes());
            			System.out.println("helo");
            		}
            		else if(inputLine.contains("KILL_SERVICE")){
            			alive=false;
            			socket.close();
            			System.exit(0);
            		}
            		else if(inputLine.contains("JOIN_CHATROOM")){
            			chatroom = inputLine.substring(inputLine.indexOf("JOIN_CHATROOM: ")+15, inputLine.length());
            			inputLine=in.readLine();
            			clientIP=inputLine.substring("CLIENT_IP: ".length());
            			in.readLine();
            			inputLine=in.readLine();
            			alias = inputLine.substring("CLIENT_NAME: ".length());
            			System.out.println(chatroom);
            			outputLine="JOINED_CHATROOM: "+chatroom+"\n"+
      						  "SERVER_IP: 134.226.50.34\n"+
    						  "PORT: 1234\n"+
    						  "ROOM_REF: 0\n"+
    						  "JOIN_ID: 0\n\n";
            			outStream.write(outputLine.getBytes());
            			System.out.println("boop");
            		}
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

