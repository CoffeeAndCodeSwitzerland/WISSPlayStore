package games.moorhuhn.functions;

import java.io.File;

import games.moorhuhn.basics.Environment;
import games.moorhuhn.basics.Sound;
import games.moorhuhn.controllers.Moorhuhn;
import games.moorhuhn.shapes.Huhn;
import games.moorhuhn.views.Shot;
import processing.core.PApplet;

/**
 * Diese Klasse verarbeitet die ganzen eingaben vom Spieler.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Player {
	
	PApplet parent;
	public Gun shotGun;
	public boolean noAmo = false;
	
	Moorhuhn moorhuhn;
	
	public Player(PApplet p, Moorhuhn moorHuhn){
		parent = p;
		moorhuhn = moorHuhn;
		shotGun = new Gun(parent, moorhuhn);
	}	  
	  
	    public void gotShot()
	    {
	    	/*Startet mit Maustaste das Spiel neu(Nur f�r Mobile Version)
	    	if (Moorhuhn.getMyGameController().myMenu.inMenu == true)
	    	{
	    		Moorhuhn.getMyGameController().myMenu.restart();
	    	}*/
	      //Pr�ft ob der Spieler noch Sch�sse hat
	      if (moorhuhn.getMyGameController().Shots.size() > 0)
	      {
	        shotGun.gunSettings();
	        moorhuhn.getMyGameController().Shots.remove(moorhuhn.getMyGameController().Shots.size() - 1); //Zieht dem Spieler ein Schuss ab
	        //Sound Shotgun
	        moorhuhn.music.playSound("sndShotgun");
	        shotHuhn();	//Pr�ft ob ein Huhn oder ein Flieger getroffen wurde
	        moorhuhn.getMyGameController().myPlane.shotPlane(); //Pr�ft ob der Flieger getroffen wird
	        moorhuhn.getMyGameController().myPlane.myBanner.isBannerShot(); //Pr�ft ob der Banner getroffen wird
	        moorhuhn.getMyGameController().myZepelin.shotZepelin(); //Pr�ft ob der Zepelin getroffen wird
	        shotGun.scopeBlack(); //Pr�ft welches Scope verwendet werden muss
	        if (moorhuhn.getMyGameController().Shots.size() == 0)
	        {
	            //Wechselt die Scope Farbe
	            shotGun.scopeRed();
	            noAmo = true;
	        }
	      }
	      else
	      {
	        //Pr�ft ob der Spieler unten rechts klickt um nachzuladen
	        if (parent.mouseX >= parent.width-250 && parent.mouseY >= parent.height-150)
	          {
	            reload();
	            noAmo = false;
	            moorhuhn.music.playSound("sndReload");
	          }
	          else
	          {
	        	  doReload();
	        	  moorhuhn.music.playSound("sndEmptyGun");
	          }
	      }
	    }
	    
	    public void doReload()
	    {
	    	if (noAmo == true)
	    	{
	            //Gib dem Spieler zu deuten dass er nachladen muss
	        	parent.fill(255,255,255);
	        	parent.textSize(50);
	        	parent.text("RELOAD", parent.width-210,parent.height-20);
	    	}
	    }   
	    
	    public void reload()
	    {
	    	  if (moorhuhn.getMyGameController().myMenu.inMenu == false)
	    	  {
			      //Wechselt die Scope Farbe
			      shotGun.scopeBlack();
			      //Gibt dem Spieler 8 Sch�sse
			      for(int i = 1; i <= 8; i++)
			       {
			    	  moorhuhn.getMyGameController().Shots.add(new Shot(parent,parent.width-i*30,parent.height-100));
			       }
	    	  }
	    }
	    
	    
	    //Handle the imgShots
	    public void myKeyPressed()
	    {
	      switch(parent.key) 
	      {
	        case 32: moorhuhn.getMyGameController().myMenu.restart(); //32 entspricht Leertaste
	      }
	    }
	    
	    /**
	     * Pr�ft ob ein Huhn getroffen wurde
	     */
	    void shotHuhn()
	    {
	       for (Huhn h : moorhuhn.getMyGameController().chickens) 
	        {    
	    	   h.shotHuhn();
	        }
	    }   
	    
	  public void showHitmarker()
	  {
		  moorhuhn.getMyGameController().myHitmarker.useHitmarker = 1;
	  }
}
