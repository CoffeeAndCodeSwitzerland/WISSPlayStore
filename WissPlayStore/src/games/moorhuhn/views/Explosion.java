package games.moorhuhn.views;

import games.moorhuhn.controllers.Moorhuhn;
import games.moorhuhn.controllers.MusicHandler;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Diese Klasse zeichnet eine Explosion.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Explosion{
	
	  PApplet parent;
	  public int xPos = 0;
	  public int yPos = 0;
	  public int myWidth = 932;
	  public int myHeight = 632;
	  int sndCounter = 0;
	  PImage gifExplosion;
	  MusicHandler music;
	  
	public Explosion(PApplet p, MusicHandler musicHandler){
		parent = p;
		music = musicHandler;
		gifExplosion = Moorhuhn.getMyImage().gifExplosion;
	}

	  
	  public void myDraw()
	  {
		sndCounter++;
	    parent.image(gifExplosion, xPos, yPos, myWidth, myHeight); 
	    if(sndCounter == 1) {
	    	music.playSound("sndExplosion");  	
	    }
	    	    else if(sndCounter > 10)
	    {
	    	sndCounter = 0;
	    }
	  }
	  
}
