package games.moorhuhn.views;

import java.io.File;

import games.moorhuhn.basics.Environment;
import games.moorhuhn.basics.Sound;
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
	
	public Target(PApplet p, int newXPos, int newYPos) {
		  parent = p;
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
		 if (Moorhuhn.getMyGameController().myMenu.inMenu == false)
		 {
			 state = 0;
		 }
	 }
	 
	 public void sound()
	 {
		 if(state <= 1)
		 {
			 Sound.playSound(new File(Environment.getActualPath()+"\\bin\\games\\moorhuhn\\sounds\\sndShotgun.wav"));
		 }
	 }
}
