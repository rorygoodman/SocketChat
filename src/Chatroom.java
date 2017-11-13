import java.util.ArrayList;
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
	public void addClient(String clientIP,String clientName){
		while(locked){
			
		}
		locked=true;
		clients.add(new Client(clientIP,true,clientName,numJoins));
		numJoins++;
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
	public ArrayList<String> getMessages(int lastSeen){
		return (ArrayList<String>)messages.subList(lastSeen, messages.size());
		
	}
	public void sendMessage(String message){
		while(locked){
			
		}
		locked=true;
		messages.add(message);
		locked=false;
		return;
	}
	                                                                  
}
