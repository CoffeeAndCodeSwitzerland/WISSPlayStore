package Moorhuhn.src.views;

import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Diese Klasse zeichnet die Schüsse.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Shot {
	PApplet parent;
	
	  int xPos = 0;
	  int yPos = 0;
	  int xSpeed = 0;
	  int ySpeed = 0;
	  int myWidth = 30;
	  int myHeight = 100;
	  
	public Shot(PApplet p, int newXPos, int newYPos)
	{
		parent = p;
	    xPos = newXPos+xSpeed;
	    yPos = newYPos+ySpeed;
	}
	  
	   public void myDraw () 
	   {
	     parent.image(Moorhuhn.getMyImage().imgShot, xPos, yPos);
	   }
}
