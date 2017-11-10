
public class Client {
	int IP;
	String currentChat;
	boolean connected;
	String name;
	
	public Client(int IP, boolean connected,String name,String currentChat){
		this.IP=IP;
		this.connected=connected;
		this.name=name;
		this.currentChat=currentChat;
	}
}
