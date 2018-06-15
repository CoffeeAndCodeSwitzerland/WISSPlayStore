package Moorhuhn.src.views;

import java.io.File;

import Moorhuhn.src.basics.Environment;
import Moorhuhn.src.basics.Sound;
import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Diese Klasse zeichnet eine Explosion.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Explosion {
	
	  PApplet parent;
	  public int xPos = 0;
	  public int yPos = 0;
	  public int myWidth = 932;
	  public int myHeight = 632;
	  int sndCounter = 0;
	  PImage gifExplosion;
	
	public Explosion(PApplet p){
		parent = p;
		gifExplosion = Moorhuhn.getMyImage().gifExplosion;
	}
	
	  
	  public void myDraw()
	  {
		sndCounter++;
	    parent.image(gifExplosion, xPos, yPos, myWidth, myHeight); 
	    if(sndCounter == 1) {
	    	Sound.playSound(new File(Environment.getActualPath()+"\\bin\\sounds\\sndExplosion.wav"));  	
	    }
	    	    else if(sndCounter > 10)
	    {
	    	sndCounter = 0;
	    }
	  }
	  
}
