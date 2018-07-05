package menu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import games.moorhuhn.controllers.Moorhuhn;
import games.schiffliversaenken.schiffeversenken.Schiffliversenken;
import games.tetris.controls.TetrisStart;
import processing.core.PApplet;

/**
 * 
 * @author MC2BP
 * 
 */

public class WissPlayStore extends PApplet {

	
	//To add a game button just add the name of it in this array
	//To make the game work you have to add it to the switch
	String[] games = {"Tetris","Moorhuhn","Schiffliversänkä","TextAdventure"};
	

	Button[][] button;
	Button showInfo;
	String applicationName;
	int arraySize;
	static String user = "No user";
	static int learnpoints = 0;
	// Size of the window
	int windowSize = 800;

	/*
	 * args are used to start a game or to give the application the username and the
	 * learpoints for the username and points they have to be written like this:
	 * username;learnpoints Example MyUser;100
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			PApplet.main("menu.WissPlayStore");
		} else if (args.length == 1) {
			String arg[] = args[0].split(";");
			if (arg.length == 1) {
				switch (arg[0]) {
					//Here you can start the game
					case "Tetris":
						TetrisStart.myMain();
						break;
					case "Moorhuhn":
						Moorhuhn.myMain();
						break;
					case "Schiffliversänkä":
						Schiffliversenken.myMain();
						break;
					case "TextAdventure":
						break;
				// Here you can start the game
				}

			} else if (arg.length == 2) {
				// Starts the game launcher
				user = arg[0];
				learnpoints = Integer.parseInt(arg[1]);
				PApplet.main("menu.WissPlayStore");
			}
		}
	}

	// Sets up the window
	public void settings() {
		size(windowSize, windowSize + 200);
	}

	// Sets up the buttons
	public void setup() {
		try {
			setApplicationName();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arraySize = (int) Math.ceil(Math.sqrt(games.length));
		int size = (windowSize - (10 * arraySize) + 10) / arraySize;
		button = new Button[arraySize][arraySize];
		for (int i = 0; i < arraySize; i++) {
			for (int j = 0; j < arraySize; j++) {
				if (i * arraySize + j < games.length) {
					button[i][j] = new Button(this, (size + 10) * j, (size + 10) * i, size, size);
					button[i][j].changeColor(150);
					button[i][j].text = games[i * arraySize + j];
					button[i][j].textSize = size / 10;
				}
			}
		}
		showInfo = new Button(this, 0, windowSize, windowSize, 200);
		showInfo.textSize = windowSize / 20;
		showInfo.changeColor(50, 100, 200);
		setTextInfo();
	}

	// Sets the text thats shows the username and the learnpoints
	private void setTextInfo() {
		showInfo.text = user + " Lernpunkte: " + Integer.toString(learnpoints);
	}

	// Draws the GUI
	public void draw() {
		background(75);
		// Draws the buttons
		for (int i = 0; i < arraySize; i++) {
			for (int j = 0; j < arraySize; j++) {
				if (i * arraySize + j < games.length) {
					// Changes the color
					if (button[i][j].checkOnButton(mouseX, mouseY))
						button[i][j].changeColor(255);
					else
						button[i][j].changeColor(150);
					button[i][j].drawButton();
				}
			}
		}
		// Draws the infos
		showInfo.drawButton();
	}

	// When the mouse gets clicked it will start the game
	public void mouseClicked() {
		try {
			startGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Starts the game
	// This application will be startet with the game name as args
	public void startGame() throws IOException {
		String gameName = null;
		// Tests if a button is pressed
		for (int i = 0; i < arraySize; i++) {
			for (int j = 0; j < arraySize; j++) {
				if (i * arraySize + j < games.length) {
					if (button[i][j].checkOnButton(mouseX, mouseY)) {
						gameName = button[i][j].text;
						i = button.length;
					}
				}
			}
		}

		if (gameName != null && learnpoints > 0) {
			learnpoints--;
			setTextInfo();
			// Starts a cmd which starts this application with args for the game
			Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /c start java -jar " + applicationName + " " + gameName);
		}
	}

	// Gives back the name of the running jar file
	private void setApplicationName() throws UnsupportedEncodingException {
		String path = WissPlayStore.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		System.out.println(decodedPath);
		String[] pathArray = decodedPath.split("/");
		applicationName = pathArray[pathArray.length - 1];
		System.out.println(applicationName);
	}

}