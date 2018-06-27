package menu;


import games.moorhuhn.controllers.Moorhuhn;
import games.schiffliversaenken.schiffeversenken.Schiffliversenken;
import games.tetris.controls.TetrisStart;
import processing.core.PApplet;

/**
 * 
 * @author MaPf06
 *
 */

public class WissPlayStore extends PApplet {
	
	String[] gameName = {"Tetris","Moorhuhn","Schiffliversänkä","TextAdventure N/A"};
	Button[] button;
	int fontsize;
	int size = 0;
	

	public static void main(String[] args) {
		PApplet.main("menu.WissPlayStore");
	}
	
	public void settings() {
		size(800,800);
	}
	
	public void setup() {
		width = 800;
		size = (width-(10*gameName.length)+10)/gameName.length;
		button = new Button[gameName.length];
		for (int i = 0; i < button.length; i++) {
			button[i] = new Button(this, (size+10)*i,10,size,size);
			button[i].changeColor(150);
			button[i].text = gameName[i];
			button[i].textSize = size/10;
		}
	}
	
	public void draw() {
		background(75);
		for (int i = 0; i < button.length; i++) {
			if (button[i].checkOnButton(mouseX, mouseY)) button[i].changeColor(255);
			else button[i].changeColor(150);
			button[i].drawButton();
		}
	}

	//W
	public void mouseClicked() {
		startGame();
	}
	
	public void startGame() {
		if (button[0].checkOnButton(mouseX, mouseY)) {
			thread("startTetris");
		}
		
		if (button[1].checkOnButton(mouseX, mouseY)) {
			thread("startMoorhuhn");
		}
		
		if (button[2].checkOnButton(mouseX, mouseY)) {
			thread("startSchiffliversenken");
		}
	}
	
	
	public void startTetris() {
		TetrisStart.myMain();
	}
	public void startMoorhuhn() {
		Moorhuhn.myMain();
	}
	public void startSchiffliversenken() {
		Schiffliversenken.myMain();
	}
}