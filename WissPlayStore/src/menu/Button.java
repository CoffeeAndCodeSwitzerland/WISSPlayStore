package menu;

import processing.core.PApplet;

/**
 * 
 * @author MaPf06
 *
 * GUI element Button
 */

public class Button extends GUI{
	
	String text = "No Text";
	int textSize;
	int textColor = 0;
	
	Button(PApplet core, int xPos,int yPos, int buttonWidth, int buttonHeight) {
		super(core, xPos, yPos);
		myWidth = buttonWidth;
		myHeight = buttonHeight;
	}
	
	//Draws a Rectangle with text in it
	public void drawButton() {
		p.fill(myColor[0], myColor[1], myColor[2]);
		p.rect(xPos, yPos, myWidth, myHeight, myWidth/4);
		p.fill(textColor);
		p.textSize(textSize);
		p.textAlign(p.CENTER);
		p.text(text,xPos+ myWidth/2, yPos + myHeight/2);
	}
	
	public boolean checkOnButton(int x, int y) {
		if (x < xPos+ myWidth && x > xPos && y < yPos+ myHeight && y > yPos) return true;
		else return false;
	}
	
}
