/**
 * managing the two players 
 * change after a action the player
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	19.03.2018
 */

package games.schiffliversaenken.functions;

import games.schiffliversaenken.online.client;
import games.schiffliversaenken.online.server;
import games.schiffliversaenken.view.GameView;

public class GameControler {

	private PlayField player1Fields;
	private PlayField player2Fields;
	int myWidth;
	int myHeight;
	String activePlayer;
	server server;
	client client;
	String toSend;
	String friendsIP = "192.168.0.101";
	boolean isServer = false;
	boolean isClient = false;
	String winner;
	String threadOutput;

	int placeShips = 6;
	boolean restartGame = false;
	boolean EnemyReady = false;

	int tick = 0;
	/**
	 * initialize both player
	 * 
	 * @param width
	 * @param height
	 */
	public GameControler(int width, int height) {
		myWidth = width;
		myHeight = height;
		player1Fields = new PlayField();
		player2Fields = new PlayField();
		player1Fields.Player = "Spiler 1";
		player2Fields.Player = "Gegner";
		player1Fields.setSchiffli = true;
		activePlayer = "Spiler 1";
		player1Fields.startSettingNextShip(4);
		player1Fields.setetShip();
		player1Fields.itsTurn = false;
	}

	public PlayField getPlayerFields(int player) {
		if (player == 1)
			return player1Fields;
		return player2Fields;
	}

	/**
	 * create the ships for both player
	 */
	public void draw() {
		if (!player1Fields.isPlacingAShip()) {
			if (placeShips > 6) {
				player1Fields.startSettingNextShip(4);
				placeShips--;
			} else if (placeShips > 3) {
				player1Fields.startSettingNextShip(3);
				placeShips--;
			} else if (placeShips > 1) {
				player1Fields.startSettingNextShip(2);
				placeShips--;
			}
			player1Fields.setetShip();
		}
		if (tick <= 0&&(isServer||isClient)) {
			if (isServer) {
				threadOutput = server.txt;
				server.txt = "nothing";}
			if (isClient) {
				threadOutput = client.txt;
				client.txt = "nothing";}
			if(!threadOutput.equals("nothing")) {
				System.out.println(threadOutput);
			}
			String commandFromThread[] = threadOutput.split("/");
			switch (commandFromThread[0]) {
			case "rdy":
				EnemyReady = true;
				break;
			case "shot":
				gotShotten(commandFromThread[1],commandFromThread[2]);
				break;
			case "hit":
				player2Fields.getHited(Integer.parseInt(commandFromThread[1]),Integer.parseInt(commandFromThread[2]));
				player2Fields.getID(Integer.parseInt(commandFromThread[1]),Integer.parseInt(commandFromThread[2]),Integer.parseInt(commandFromThread[3]));
				break;
			case "destroy":
				player2Fields.toDestroy(Integer.parseInt(commandFromThread[1]));
				break;
			}
			tick=0;
		}
		tick--;
	}

	private void gotShotten(String column, String row) {
		player1Fields.schiessen(Integer.parseInt(column), Integer.parseInt(row));
		if (player1Fields.getState(Integer.parseInt(column), Integer.parseInt(row))==5) {
			if(isClient) {
				client.sendLine("hit/"+column+"/"+row+"/"+player1Fields.getDestroyed(Integer.parseInt(column), Integer.parseInt(row)));
			}else if(isServer) {
				server.sendLine("hit/"+column+"/"+row+"/"+player1Fields.getDestroyed(Integer.parseInt(column), Integer.parseInt(row)));
			}
		}else if (player1Fields.getState(Integer.parseInt(column), Integer.parseInt(row))==6) {
			if(isClient) {
				client.sendLine("hit/"+column+"/"+row+"/"+player1Fields.getDestroyed(Integer.parseInt(column), Integer.parseInt(row)));
				client.sendLine("destroy/"+player1Fields.getDestroyed(Integer.parseInt(column), Integer.parseInt(row)));
			}else if(isServer) {
				server.sendLine("hit/"+column+"/"+row+"/"+player1Fields.getDestroyed(Integer.parseInt(column), Integer.parseInt(row)));
				server.sendLine("destroy/"+player1Fields.getDestroyed(Integer.parseInt(column), Integer.parseInt(row)));
			}
		}
		player1Fields.itsTurn = true;
	}

	/**
	 * check button
	 * 
	 * @param x
	 * @param y
	 * @param myVar
	 * @param myGameView
	 */
	public void buttonClicked(float x, float y, Variables myVar, GameView myGameView) {
		int column = 11;
		int row = 11;
		// sync view with array: Row
		
		if (((y - (myVar.tBorder)) / (myVar.fSize)) >= 0) {
			row = (int) ((y - (myVar.tBorder)) / (myVar.fSize));
		}
		if(player1Fields.setSchiffli == true) {
			if(((x-(myVar.lBorder))/(myVar.fSize))>=0) {
				column = (int)((x-(myVar.lBorder))/(myVar.fSize));
			}
		}else if(player1Fields.setSchiffli == false){
			if(((x-(myVar.rBorder))/(myVar.fSize))>=0) {
				column = (int)((x-(myVar.rBorder))/(myVar.fSize));
			}
		}
		if ((column < 10 && column + 1 > 0) && (row < 10 && row + 1 > 0)) { // Klicken ist inerhalb des Spielfeldes
			if (player1Fields.setSchiffli == true) {
				player1Fields.tryToSetetShip(column, row);
			}
		}
		if (player1Fields.setSchiffli == false && EnemyReady == true) {
			if ((column < 10 && column + 1 > 0) && (row < 10 && row + 1 > 0)) { // Klicken ist inerhalb des Spielfeldes
				if (player1Fields.itsTurn == true) {
					if ((player2Fields.getFeld(column, row).myZustand == 0) || (player2Fields.getFeld(column, row).myZustand == 4)) {
						player2Fields.schiessen(column, row);
						if(isServer) {
							server.sendLine("shot/" + column + "/" + row);
							player1Fields.itsTurn = false;
						} else if(isClient) {
							client.sendLine("shot/" + column + "/" + row);
							player1Fields.itsTurn = false;
						}
					}
				}
			}
		}
		if ((player1Fields.isPlacingAShip() == false)
				&& myGameView.getShape().checkHitboxButton(x, y, (float) (myWidth * 0.45), (float) (myHeight * 0.1))) {
			myGameView.setBackgroundLight();
			for (int r = 0; r <= 9; r++) {
				for (int c = 0; c <= 9; c++) {
					player1Fields.getFeld(c, r).changeColorSetShip();
				}
			}
			player1Fields.setSchiffli = false;
			if (isServer) {
				server.sendLine("rdy");
			} else if(isClient){
				client.sendLine("rdy");
			}

		}
	}

	/*
	 * if(((y-(myVar.tBorder))/(myVar.fSize))>=0) { row =
	 * (int)((y-(myVar.tBorder))/(myVar.fSize)); } if(player2Fields.setSchiffli ==
	 * true || player1Fields.itsTurn == true) {
	 * if(((x-(myVar.rBorder))/(myVar.fSize))>=0) { column =
	 * (int)((x-(myVar.rBorder))/(myVar.fSize)); } }else
	 * if(player1Fields.setSchiffli == true || player2Fields.itsTurn == true){
	 * if(((x-(myVar.lBorder))/(myVar.fSize))>=0) { column =
	 * (int)((x-(myVar.lBorder))/(myVar.fSize)); } } if ( (column < 10 && column+1 >
	 * 0) && (row < 10 && row+1 > 0)){ //Klicken ist inerhalb des Spielfeldes if
	 * (player2Fields.setSchiffli == true) { player2Fields.tryToSetetShip(column,
	 * row); }else { player1Fields.tryToSetetShip(column, row); } } /** set ships
	 * for both player
	 *//*
		 * if( (player2Fields.isPlacingAShip() == false) &&
		 * myGameView.getShape().checkHitboxButton(x,y,(float)(myWidth*0.45),(float)(
		 * myHeight*0.1))) { for (int r = 0; r <= 9; r++){ for (int c = 0; c<=9;c++){
		 * player2Fields.getFeld(c,r).changeColorSetShip(); player2Fields.setSchiffli =
		 * false; player1Fields.setSchiffli = true; activePlayer = "Spiler 1"; } } } if(
		 * (player1Fields.isPlacingAShip() == false) &&
		 * myGameView.getShape().checkHitboxButton(x,y,(float)(myWidth*0.45),(float)(
		 * myHeight*0.1))) { myGameView.setBackgroundLight(); for (int r = 0; r <= 9;
		 * r++){ for (int c = 0; c<=9;c++){
		 * player1Fields.getFeld(c,r).changeColorSetShip(); player1Fields.setSchiffli =
		 * false; player2Fields.itsTurn = true; activePlayer = "Spiler 2"; }
		 * 
		 * }
		 * 
		 * } /** change between both player
		 *//*
			 * if(player1Fields.setSchiffli==false && player2Fields.setSchiffli==false) { if
			 * ((column < 10 && column+1 > 0) && (row < 10 && row+1 > 0)){ //Klicken ist
			 * inerhalb des Spielfeldes if(player2Fields.itsTurn == true) {
			 * if((player1Fields.getFeld(column, row).myZustand ==
			 * 0)||(player1Fields.getFeld(column, row).myZustand == 4)) {
			 * player1Fields.schiessen(column, row);
			 * 
			 * player2Fields.itsTurn = false; player1Fields.itsTurn = true; activePlayer =
			 * "Spiler 1"; } }else if(player1Fields.itsTurn == true){
			 * if((player2Fields.getFeld(column, row).myZustand ==
			 * 0)||(player2Fields.getFeld(column, row).myZustand == 4)) {
			 * player2Fields.schiessen(column, row); player1Fields.itsTurn = false;
			 * player2Fields.itsTurn = true; activePlayer = "Spiler 2"; } } } } }
			 * 
			 * public boolean winningPlayer() { if(player1Fields.anzahlVersunkeneSchiffli ==
			 * 6||player2Fields.anzahlVersunkeneSchiffli == 6) {
			 * if(player1Fields.anzahlVersunkeneSchiffli == 6) { activePlayer =
			 * "Dr Gwinner isch Spiler 1"; } if(player2Fields.anzahlVersunkeneSchiffli == 6)
			 * { activePlayer = "Dr Gwinner isch Spiler 2"; } restartGame = true; return
			 * true; }else { return false; }
			 */
	public boolean winningPlayer() {
		if (player1Fields.anzahlVersunkeneSchiffli == 6 || winner == "Player 2") {
			if (player1Fields.anzahlVersunkeneSchiffli == 6) {
				activePlayer = "Dr Gwinner isch Spiler 1";
			}
			if (winner == "Player 2") {
				activePlayer = "Dr Gwinner isch Spiler 2";
			}
			restartGame = true;
			return true;
		} else {
			return false;
		}
	}

	public void startServer() {
		server = new server();
		server.start();
		isServer = true;
		System.out.println("SERVER ON");
	}

	public void startClient() {
		client = new client(friendsIP,80);
		client.start();
		isClient = true;
		System.out.println("CLIENT ON");
		player1Fields.itsTurn = true;
	}
	
	public void stoppingThreads() {
		client.serverConnected = false;
		server.isRunning = false;
	}
}
