
import java.net.*;
import java.io.*;
import java.util.*;
public class ClientThread extends Thread {
    private Socket socket = null;
    private static ChatSystem system = new ChatSystem();
    public ClientThread(Socket socket) {
        super("main");
        this.socket = socket;
       // this.system=system;
        
    }   
    public void run() {
    	boolean alive=true;
    	HashMap<Integer,Integer> chatrooms = new HashMap<Integer,Integer>();
    	String chatroom,alias,clientIP,port;
    	chatroom=null;
    	alias=null;
        try (
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine,message;
            int chatIndex;
            inputLine=null;
            outputLine=null;
            while (alive) { 	
            	inputLine=in.readLine();
            	System.out.println(inputLine);
            	
            	if(inputLine!=null){
            		if(inputLine.contains("HELO")){
       			
            			outputLine= "HELO"+inputLine.substring("HELO".length())+"\nIP: 134.226.50.34 \nPort:1234\nStudentID:14316993";
            			outStream.write(outputLine.getBytes());
            		}
            		else if(inputLine.contains("KILL_SERVICE")){
            			alive=false;
            			socket.close();
            			System.exit(0);
            		}
            		else if(inputLine.contains("JOIN_CHATROOM")){
            			chatroom = inputLine.substring(inputLine.indexOf("JOIN_CHATROOM: ")+15, inputLine.length());
            			chatIndex = system.chatIndex(chatroom);
            			if(chatIndex==-1){
            				system.addRoom(chatroom);
            				chatIndex = system.chatIndex(chatroom);
            			}
            			inputLine=in.readLine();
            			System.out.println(inputLine);
            			clientIP=inputLine.substring("CLIENT_IP: ".length());
            			in.readLine();	
            			inputLine=in.readLine();
            			System.out.println(inputLine);
            			alias = inputLine.substring("CLIENT_NAME: ".length());
            			system.chatrooms.get(chatIndex).addClient(clientIP,alias,socket);
            			chatrooms.put(chatIndex,system.chatrooms.get(chatIndex).messages.size()-1);
            			outputLine="JOINED_CHATROOM: "+chatroom+"\n"+
      						  "SERVER_IP: 134.226.50.34\n"+
    						  "PORT: 1234\n"+
    						  "ROOM_REF: "+chatIndex+"\n"+
    						  "JOIN_ID: "+system.chatrooms.get(chatIndex).getClient(alias).joinID+"\n";
            			outStream.write(outputLine.getBytes());
            			outputLine="CHAT:"+chatIndex+"\n"+
            			"CLIENT_NAME: "+alias+"\n"+
            					"MESSAGE: "+alias+" JOINED\n\n";
            			sendMessage(outputLine,chatIndex);
            		}
            		else if(inputLine.contains("CHAT:")){
            			int chatToMessage=Integer.parseInt(inputLine.substring("CHAT: ".length()));
            			inputLine=in.readLine();
            			int joinID = Integer.parseInt(inputLine.substring("JOIN_ID: ".length()));//TODO check if client in chat
            			inputLine=in.readLine();
            			String clientNameToMessage = inputLine.substring("CLIENT_NAME: ".length());
            			inputLine=in.readLine();
            			String theMessage = inputLine.substring("MESSAGE: ".length());
            			outputLine="CHAT: "+chatToMessage+"\n"+
            			"CLIENT_NAME: "+clientNameToMessage+"\n"+
            			"MESSAGE: "+theMessage+"\n\n";
            			System.out.println("gets here");
            			sendMessage(outputLine,chatToMessage);
            		}
            		else if(inputLine.contains("LEAVE_CHATROOM: ")){
            				int leaveRef=Integer.parseInt(inputLine.substring("LEAVE_CHATROOM: ".length()));
            				int joinID = Integer.parseInt(in.readLine().substring("JOIN_ID: ".length()));
            				if(chatrooms.containsKey(leaveRef)){
            					chatrooms.remove(leaveRef);
                				outputLine="LEFT_CHATROOM: "+leaveRef+"\n"+
                				"JOIN_ID: "+joinID+" \n";
                				System.out.println(system.chatrooms.get(leaveRef).getClient(alias).joinID);
                				outStream.write(outputLine.getBytes());
                				outputLine="CHAT: "+leaveRef+"\n"+
                            	"CLIENT_NAME: "+alias+"\n"+
                            	"MESSAGE: "+alias+" LEFT\n\n";
                				sendMessage(outputLine,leaveRef);
                            	system.chatrooms.get(leaveRef).removeClient(alias);
                            	
                            	
            				}
            				
            		}
            		else if(inputLine.contains("DISCONNECT: ")){
            			System.out.println("ERROR");
            			outStream.write("DISCONNECTED".getBytes());
            			socket.close();
            		}
            		else{
            			
            		}	
        		   }
         
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void sendMessage(String message,int roomRef){
    	for(int i=0;i<system.chatrooms.get(roomRef).clients.size();i++){
    		try{
    			DataOutputStream outStream = new DataOutputStream(system.chatrooms.get(roomRef).clients.get(i).socket.getOutputStream());
    			outStream.write(message.getBytes());
    			System.out.println("sent" +message+" to "+ system.chatrooms.get(roomRef).clients.get(i).name);
    		}
    		catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	 
    }

	   
   
}

