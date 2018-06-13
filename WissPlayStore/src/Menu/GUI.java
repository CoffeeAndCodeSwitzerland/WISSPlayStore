package Menu;

import processing.core.PApplet;

public class GUI extends Processing{

	int xPos;
	int yPos;
	int myWidth;
	int myHeight;
	int myColor[] = {100,100,100};
	
	GUI(PApplet core, int newXPos,int newYPos, int newWidth, int newHeight) {
		super(core);
		xPos = newXPos;
		yPos = newYPos;
		int myWidht = newWidth;
		int myHeight = newHeight;
	}
	
	public void resize(int newWidth, int newHeight) {
		myWidth = newWidth;
		myHeight = newHeight;
	}
	
	public void repos(int newXPos, int newYPos ) {
		xPos = newXPos;
		yPos = newYPos;
	}
	
	public void changeColor(int color) {
		int[] myColor = {color,color,color};
	}
	
	public void changeColor(int red, int green, int blue) {
		int[] myColor = {red,green,blue};
	}
	
}
