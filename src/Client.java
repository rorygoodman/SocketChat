
public class Client {
	String IP;
	boolean connected;
	String name;
	int joinID;
	public Client(String IP, boolean connected,String name,int joinID){
		this.IP=IP;
		this.connected=connected;
		this.name=name;
		this.joinID=joinID;
	}
}
