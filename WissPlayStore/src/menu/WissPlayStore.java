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
 * @author MaPf06
 * [Ljava.lang.String;@1540e19d
 * 
 */

public class WissPlayStore extends PApplet {
	
	String[] gameName = {"Tetris","Moorhuhn","Schiffliversänkä","TextAdventure N/A"};
	Button[] button;
	String applicationName;
	
	public static void main(String[] args) {
		if (args.length == 0) {
			PApplet.main("menu.WissPlayStore");
		} else {
			String arg = args[0];
			switch (arg) {
			case "Tetris":
				TetrisStart.myMain();
				break;
			case "Moorhuhn":
				Moorhuhn.myMain();
				break;
			case "Schiffliversänkä":
				Schiffliversenken.myMain();
				break;
			case "SUVSE":
				break;
			}
		}
	}
	//Sets up the window
	public void settings() {
		size(800,800);
	}
	//Sets up the buttons
	public void setup() {
		try {
			setApplicationName();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = 800;
		int size = (width-(10*gameName.length)+10)/gameName.length;
		button = new Button[gameName.length];
		for (int i = 0; i < button.length; i++) {
			button[i] = new Button(this, (size+10)*i,10,size,size);
			button[i].changeColor(150);
			button[i].text = gameName[i];
			button[i].textSize = size/10;
		}
	}
	
	//Draws the GUI
	public void draw() {
		background(75);
		for (int i = 0; i < button.length; i++) {
			if (button[i].checkOnButton(mouseX, mouseY)) button[i].changeColor(255);
			else button[i].changeColor(150);
			button[i].drawButton();
		}
	}

	//When the mouse gets clicked it will start the game
	public void mouseClicked() {
		try {
			startGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Starts the game
	//This application will be startet with the game name as args
	public void startGame() throws IOException {
		String gameName = null;
		for (int i = 0; i < button.length; i++) {
			if (button[i].checkOnButton(mouseX,mouseY)) {
				gameName = button[i].text;
				i = button.length;
			}
		}
		if (gameName != null) {
			Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /c start java -jar "+applicationName+" "+gameName);
		}
	}
	
	private void setApplicationName() throws UnsupportedEncodingException {
		String path = WissPlayStore.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		System.out.println(decodedPath);
		String[] pathArray = decodedPath.split("/");
		applicationName = pathArray[pathArray.length-1];
		System.out.println(applicationName);
	}


	
}