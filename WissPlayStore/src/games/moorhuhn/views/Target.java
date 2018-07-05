package games.moorhuhn.views;

import games.moorhuhn.controllers.Moorhuhn;
import processing.core.PApplet;

public class Target {
	PApplet parent;
	int holeCounter = 0;
	int xPos = 0;
	int yPos = 0;
	int myWidth = 25;
	int myHeight = 25;
	int state = 0;
	
	Moorhuhn moorhuhn;
	
	public Target(PApplet p, int newXPos, int newYPos, Moorhuhn moorHuhn) {
		  parent = p;
		  moorhuhn = moorHuhn;
		  newXPos = (int)parent.random(200)+parent.width/4-100;
		  newYPos = (int)parent.random(200)+parent.height/2-100;
		  xPos = newXPos;
		  yPos = newYPos;
		}
	
	
	 public void myDraw()
	 {
		 if (state >= 1)
		 {
			 parent.image(Moorhuhn.getMyImage().imgHole, xPos, yPos); 
			 sound();
		 }
		 if (moorhuhn.getMyGameController().myMenu.inMenu == false)
		 {
			 state = 0;
		 }
	 }
	 
	 public void sound()
	 {
		 if(state <= 1)
		 {
			 moorhuhn.music.playSound("sndShotgun");
		 }
	 }
}
