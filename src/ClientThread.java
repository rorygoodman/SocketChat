
import java.net.*;
import java.io.*;
public class ClientThread extends Thread {
    private Socket socket = null;
    public ClientThread(Socket socket) {
        super("main");
        this.socket = socket;
    }   
    public void run() {
    	boolean alive=true;
    	String chatroom,alias,clientIP,port;
    	chatroom=null;
    	alias=null;
        try (
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
        	
        	FileReader fr = new FileReader("chat.txt"); 
        	BufferedReader readChat = new BufferedReader(fr);
        	PrintWriter chatWriter = new PrintWriter("chat.txt", "UTF-8");
            String inputLine, outputLine,message;
            inputLine=null;
            while (alive) { 	
            	inputLine=in.readLine();
            	System.out.println(inputLine);
            	if(inputLine!=null){
            		if(inputLine.contains("HELO")){
       			
            			outputLine= "HELO"+inputLine.substring("HELO".length())+"\nIP: 134.226.50.42 \nPort:1234\nStudentID:14316993";
            			outStream.write(outputLine.getBytes());
            		}
            		else if(inputLine.contains("KILL_SERVICE")){
            			alive=false;
            			socket.close();
            			System.exit(0);
            		}
            		else if(inputLine.contains("JOIN_CHATROOM")){
            			chatroom = inputLine.substring(inputLine.indexOf("JOIN_CHATROOM: ")+15, inputLine.length());
            			inputLine=in.readLine();
            			System.out.println(inputLine);
            			clientIP=inputLine.substring("CLIENT_IP: ".length());
            			in.readLine();	
            			inputLine=in.readLine();
            			System.out.println(inputLine);
            			
            			alias = inputLine.substring("CLIENT_NAME: ".length());
            			System.out.println(alias);
            			outputLine="JOINED_CHATROOM: "+chatroom+"\n"+
      						  "SERVER_IP: 134.226.50.34\n"+
    						  "PORT: 1234\n"+
    						  "ROOM_REF: 0\n"+
    						  "JOIN_ID: 0\n";
            			outStream.write(outputLine.getBytes());
            			outputLine="CHAT:0\n"+
            			"CLIENT_NAME: "+alias+"\n"+
            					"MESSAGE: "+alias+" JOINED\n\n";
            			outStream.write(outputLine.getBytes());
            		}
            		else if(inputLine.contains("CHAT:")){
            			inputLine=in.readLine();//TODO proper implementation
            			inputLine=in.readLine();//TODO
            			message=inputLine;
            			while(!inputLine.contains("\n\n")){
            				inputLine=in.readLine();
            				System.out.println(inputLine);
            				message+=inputLine;
            			}
            			outputLine="CHAT: "+chatroom+"\n"+
            			"CLIENT_NAME: "+alias+"\n"+
            			"MESSAGE: "+message;
            			System.out.println("gets here");
            			outStream.write(outputLine.getBytes());
            			
            		}
            		else if(inputLine.contains("LEAVE_CHATROOM: ")){
            			//if(inputLine.substring(inputLine.indexOf("LEAVE_CHATROOM: ")).equals("0")){
            				chatroom=null;
            				outputLine="LEFT_CHATROOM: 0\n"+
            				"JOIN_ID: 0\n";
            				outStream.write(outputLine.getBytes());
            				outputLine="CHAT: 0\n"+
                        	"CLIENT_NAME: "+alias+"\n"+
                        	"MESSAGE: "+alias+" LEFT\n\n";
                        	outStream.write(outputLine.getBytes());
            			//}
            		}
            		
            		else{
            			
            		}
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

