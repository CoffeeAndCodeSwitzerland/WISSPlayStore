package menu;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * 
 * @author MaPf06
 *
 *         GUI element Button
 */

public class Button extends GUI {

	String text = "No Text";
	private int textSize;
	private int textColor = 0;

	Button(PApplet core, int xPos, int yPos, int buttonWidth, int buttonHeight) {
		super(core, xPos, yPos);
		myWidth = buttonWidth;
		myHeight = buttonHeight;
	}

	// Draws a Rectangle with text in it
	public void drawButton() {
		p.fill(myColor[0], myColor[1], myColor[2]);
		p.rect(xPos, yPos, myWidth, myHeight, myWidth / 4);
		p.fill(textColor);
		p.textSize(textSize);
		p.textAlign(PConstants.CENTER);
		p.text(text, xPos + myWidth / 2, yPos + myHeight / 2);
	}

	public boolean checkOnButton(int x, int y) {
		if (x < xPos + myWidth && x > xPos && y < yPos + myHeight && y > yPos)
			return true;
		else
			return false;
	}
	
	public void changeTextSize(int newSize) {
		textSize = newSize;
		if (textSize <= 0) textSize = 1;
	}

}
