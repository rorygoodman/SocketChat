import java.util.ArrayList;
import java.net.*;
import java.util.HashMap;
public class Chatroom {
	String name;
	ArrayList<Client> clients;
	ArrayList messages;
	boolean locked;
	int numMessages;
	int numJoins;
	public Chatroom(String name){
		locked=false;
		numJoins=0;
		this.name=name;
		this.clients = new ArrayList<Client>();
		messages=new ArrayList<String>();
		numMessages = 0;
	}
	public void addClient(String clientIP,String clientName,Socket socket){
		while(locked){
			
		}
		locked=true;
		clients.add(new Client(clientIP,true,clientName,numJoins,socket));
		numJoins++;
		locked=false;
		return;
	}
	public void removeClient(String clientName){
		while(locked){
			
		}
		locked=true;
		int clientIndex=-1;
		for(int i=0;i<clients.size();i++){
			if(clients.get(i).name.equals(clientName)){
				clientIndex=i;
			}
		}
		clients.remove(clientIndex);
		if(clientIndex == -1){
			System.out.println("NOT REMOVED");
		}
		locked=false;
		return;
	}
	public Client getClient(String name){
		while(locked){
			
		}
		locked=true;
		int size = clients.size();
		for(int i=0;i<size;i++){
			if(name.equals(clients.get(i).name)){
				locked=false;
				return clients.get(i);
			}
		}
		locked=false;
		return null;
	}
	                                                                  
}
