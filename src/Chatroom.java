import java.util.ArrayList;
import java.util.HashMap;
public class Chatroom {
	String name;
	ArrayList<Client> clients;
	HashMap messages;
	int numMessages;
	public Chatroom(String name){
		this.name=name;
		this.clients = new ArrayList<Client>();
		messages=new HashMap();
		numMessages = 0;
	}
	public void addClient(Client newClient){
		clients.add(newClient);
	}
	                                                                  
}
