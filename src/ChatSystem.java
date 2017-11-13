import java.util.ArrayList;
public class ChatSystem {
	boolean locked;
	ArrayList<Chatroom> chatrooms;
	public ChatSystem(){
		chatrooms = new ArrayList<Chatroom>();
		locked = false;
	}
	public void addRoom(String chatname){
		while(locked){
			
		}
		chatrooms.add(new Chatroom(chatname));
		locked=false;
		return;
	}
	public boolean chatExists(String chatname){
		while(locked){
			
		}
		locked=true;
		int size=chatrooms.size();
		for(int i=0;i==size;i++){
			if(chatname.equals(chatrooms.get(i).name)){
				locked=false;
				return true;
			}
		}
		locked=false;
		return false;
	}
	
	public int chatIndex(String chatname){
		while(locked){
			
		}
		locked=true;
		int size=chatrooms.size();
		for(int i=0;i<size;i++){
			if(chatname.equals(chatrooms.get(i).name)){
				locked=false;
				return i;
			}
		}
		locked=false;
		return -1;
	}

}
