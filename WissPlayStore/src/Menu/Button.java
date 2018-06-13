package Menu;

import processing.core.PApplet;

public class Button extends GUI{
	
	Button(PApplet core, int xPos,int yPos, int buttonWidth, int buttonHeight) {
		super(core, xPos, yPos, buttonWidth, buttonHeight);
	}
	
	public void drawButton() {
		p.fill(myColor[0], myColor[1], myColor[2]);
		p.fill(255);
		System.out.println("Should wörk"+ Integer.toString(myColor[1]) + " " + Integer.toString(myHeight));
		p.rect(xPos, yPos, myWidth, myHeight);
	}
	
}
