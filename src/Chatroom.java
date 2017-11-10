import java.util.ArrayList;
public class Chatroom {
	String name;
	ArrayList<Client> clients;
	public Chatroom(String name){
		this.name=name;
		this.clients = new ArrayList<Client>();
	}
	public void addClient(Client newClient){
		clients.add(newClient);
	}
	                                                                  
}
