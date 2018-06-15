package Menu;

import processing.core.PApplet;

/**
 * @author MaPf06
 *
 *Class used for GUI Elements.
 */

public abstract class GUI extends Processing{

	int xPos;
	int yPos;
	int myWidth;
	int myHeight;
	int myColor[] = {100,100,100};
	
	GUI(PApplet core, int newXPos, int newYPos) {
		super(core);
		xPos = newXPos;
		yPos = newYPos;
	}
	
	//Changs the size
	public void resize(int newWidth, int newHeight) {
		myWidth = newWidth;
		myHeight = newHeight;
	}
	
	//Changes the Position
	public void repos(int newXPos, int newYPos ) {
		xPos = newXPos;
		yPos = newYPos;
	}
	
	//Changes the color to an grey
	public void changeColor(int color) {
		myColor[0] = color;
		myColor[1] = color;
		myColor[2] = color;
	}
	
	//Changes the color to the one specified with Red, Green, Blue
	public void changeColor(int red, int green, int blue) {
		myColor[0] = red;
		myColor[1] = green;
		myColor[2] = blue;
	}
	
}
