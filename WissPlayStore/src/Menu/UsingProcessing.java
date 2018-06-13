package Menu;

import processing.core.PApplet;

public class UsingProcessing extends PApplet {
	
	String[] gameName = {"Tetris","Moorhuhn","OCD"};
	//Button[] button = new Button[3];
	int fontsize;
	int size = 0;
	Button b = new Button(this, 100,100,100,100);
	
	public static void main(String[] args) {
		PApplet.main("Menu.UsingProcessing");
	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		surface.setResizable(true);
		resize();
		/*
		for (int i = 0; i < button.length; i++) {
			button[i] = new Button(this, 0,0,100,100);
			button[i].changeColor(100,200,100);
		}*/
	}
	
	public void draw() {
		background(0);
		b.changeColor(100);
		b.drawButton();
		//for (int i = 0; i < button.length; i++) button[i].drawButton();
	}
	
	public void resize() {
		fontsize = height/5;
		if (height/3 > width/5) size = width/5;
		else size = height/3;
	}
	
}
