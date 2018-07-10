package games.schiffliversaenken.online;

import games.schiffliversaenken.sockets.MySocket;

public class server extends MySocket{
	public boolean isRunning =false;
	public String txt = "nothing";
	
	public server(){
		myLog("server is running...");
		name = "server";
		openServer();
		isRunning = true;
		myLog("server is on");
	}
	
	public void run() {
			while (isRunning) {
				txt = getLine();
			}
			myLog("Server stopped");
    	}
	}
