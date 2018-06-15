package Menu;

import processing.core.PApplet;

public class UsingProcessing extends PApplet {
	
	String[] gameName = {"Tetris","Moorhuhn","OCD"};
	Button[] button;
	int fontsize;
	int size = 0;
	
	public static void main(String[] args) {
		PApplet.main("Menu.UsingProcessing");
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
		}
	}
	
	public void draw() {
		resize();
		for (int i = 0; i < button.length; i++) button[i].drawButton();
	}
	
	public void resize() {
		for (int i = 0; i < button.length; i++) {
			//button[i].resize(width/3, height/5);
			//button[i].resize(100,100);
		}
	}
	
}
