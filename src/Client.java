import java.net.*;
public class Client {
	String IP;
	boolean connected;
	String name;
	int joinID;
	Socket socket;
	public Client(String IP, boolean connected,String name,int joinID,Socket socket){
		this.IP=IP;
		this.connected=connected;
		this.name=name;
		this.joinID=joinID;
		this.socket=socket;
	}
}
