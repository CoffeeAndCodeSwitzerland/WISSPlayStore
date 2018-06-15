package Moorhuhn.src.views;

import java.io.File;

import Moorhuhn.src.basics.Environment;
import Moorhuhn.src.basics.Sound;
import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Diese Klasse bestimmt die Zeit für welche der Hitmarker angezeigt werden soll und stellt diesen dar.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Hitmarker {
	
	  PApplet parent;
	  int xPos = 0;
	  int yPos = 0;
	  int scale = 50;
	  int myWidth = scale;
	  int myHeight = scale;
	  int counterHitmarker;
	  public int useHitmarker = 0;
	
	public Hitmarker(PApplet p){
		parent = p;
	}
	  void myDraw()
	  {
	    if (counterHitmarker < 10)
	    {
	    		parent.image(Moorhuhn.getMyImage().imgWhiteHitmarker, xPos, yPos, myWidth, myHeight); 
	    }
	  }
	  
	  public void showHitmarker()
	  {
	    if (useHitmarker > 0)
	    {
	      useHitmarker++;
	      setPos();
	      myDraw();
	      counterHitmarker++;
	      delHitmarker();
	    }
	  }
	  
	  
	  void setPos()
	  {
	    if (useHitmarker < 3)
	    {
	      xPos = parent.mouseX-35;
	      yPos = parent.mouseY-35;
	      //Spielt Ton ab
	      Sound.playSound(new File(Environment.getActualPath()+"\\bin\\sounds\\sndHitmarker.wav"));
	    }
	  }
	  
	  void delHitmarker()
	  {
	   if (counterHitmarker > 10)
	    {
	      useHitmarker = 0;
	      counterHitmarker = 0;
	      //Setzt Ton zurück
	    }
	  }
}
