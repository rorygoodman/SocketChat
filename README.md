# SocketChat
  Chat Server written in Java
  Only been tested on windows machines connected to the scss network.

# How to Run

  Run compile.sh to compile the project

  Run run.sh followed by the port no. 

  eg: ./run.sh 1234
  
# Client Input
 ## Joining
  Joining a chat room is initiated by a client by sending the following message to a chat server.

		  JOIN_CHATROOM: [chatroom name]
		  CLIENT_IP: [IP Address of client if UDP | 0 if TCP]
		  PORT: [port number of client if UDP | 0 if TCP]
		  CLIENT_NAME: [string Handle to identifier client user]
 ## Leaving
  A client leaves a chat room by sending the following message to the chat server:

		  LEAVE_CHATROOM: [ROOM_REF]
		  JOIN_ID: [integer previously provided by server on join]
		  CLIENT_NAME: [string Handle to identifier client user]
 ## Disconnecting
   To terminate the client/server connection, a client will send the following message to the server, which responds by      terminating the connection.

		  DISCONNECT: [IP address of client if UDP | 0 if TCP]
		  PORT: [port number of client it UDP | 0 id TCP]
		  CLIENT_NAME: [string handle to identify client user]
 ## Sending Messages
  To send a chat message the client sends the following:

		  CHAT: [ROOM_REF]
		  JOIN_ID: [integer identifying client to server]
		  CLIENT_NAME: [string identifying client user]
		  MESSAGE: [string terminated with '\n\n']
      
  The server then sends the following to every client in the chatroom
  
      CHAT: [ROOM_REF]
		  CLIENT_NAME: [string identifying client user]
		  MESSAGE: [string terminated with '\n\n']
      
  

