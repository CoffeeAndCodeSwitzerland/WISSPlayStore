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

public class UsingProcessing extends PApplet {
	
	String[] gameName = {"Tetris","Moorhuhn","Schiffliversänkä"};
	Button[] button;
	int fontsize;
	int size = 0;
	

	public static void main(String[] args) {
		PApplet.main("menu.UsingProcessing");
	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		button = new Button[gameName.length];
		surface.setResizable(true);
		resize();
		for (int i = 0; i < button.length; i++) {
			button[i] = new Button(this, 110*i,10,100,100);
			button[i].changeColor(0,200,0);
			button[i].text = gameName[i];
		}
	}
	
	public void draw() {
		resize();
		for (int i = 0; i < button.length; i++) {
			if (button[i].checkOnButton(mouseX, mouseY)) button[i].changeColor(0,255,0);
			else button[i].changeColor(55,55,255);
			button[i].drawButton();
		}
	}
	
	public void resize() {
		//Todo buttons should adapt to the changes in size
	}
	
	public void mouseClicked() {
		thread("startGame");
	}
	
	public void startGame() {
		if (button[0].checkOnButton(mouseX, mouseY)) {
			TetrisStart.myMain();
		}
		
		if (button[1].checkOnButton(mouseX, mouseY)) {
			Moorhuhn.myMain();
		}
		
		if (button[2].checkOnButton(mouseX, mouseY)) {
			Schiffliversenken.myMain();
		}
	}
}