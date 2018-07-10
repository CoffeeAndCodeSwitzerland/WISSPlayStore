package games.schiffliversaenken.online;

import games.schiffliversaenken.sockets.MySocket;

public class client extends MySocket {	
	public boolean serverConnected = false;
	public String txt = "nothing";
	
	public client(String ip, int port) {
		myLog("Connecting to server:"+ip+":"+port+"...");
		name = "client";
		setConnection(ip, port);
		openServerConnection();
		sendHostRequest();
		serverConnected = true;
		myLog("Connected to server:"+ip+":"+port);
	}

	public void run() {
		while (serverConnected) {
			txt = getLine();
		}
	}
}
