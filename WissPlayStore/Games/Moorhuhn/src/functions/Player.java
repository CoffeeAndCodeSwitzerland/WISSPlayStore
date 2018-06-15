package Moorhuhn.src.functions;

import java.io.File;

import Moorhuhn.src.basics.Environment;
import Moorhuhn.src.basics.Sound;
import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;
import Moorhuhn.src.shapes.Huhn;
import Moorhuhn.src.views.Shot;

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
	
	public Player(PApplet p){
		parent = p;
		shotGun = new Gun(parent);
	}	  
	  
	    public void gotShot()
	    {
	    	/*Startet mit Maustaste das Spiel neu(Nur für Mobile Version)
	    	if (Moorhuhn.getMyGameController().myMenu.inMenu == true)
	    	{
	    		Moorhuhn.getMyGameController().myMenu.restart();
	    	}*/
	      //Prüft ob der Spieler noch Schüsse hat
	      if (Moorhuhn.getMyGameController().Shots.size() > 0)
	      {
	        shotGun.gunSettings();
	        Moorhuhn.getMyGameController().Shots.remove(Moorhuhn.getMyGameController().Shots.size() - 1); //Zieht dem Spieler ein Schuss ab
	        //Sound Shotgun
	        Sound.playSound(new File("src/sounds/sndShotgun.wav"));
	        shotHuhn();	//Prüft ob ein Huhn oder ein Flieger getroffen wurde
	        Moorhuhn.getMyGameController().myPlane.shotPlane(); //Prüft ob der Flieger getroffen wird
	        Moorhuhn.getMyGameController().myPlane.myBanner.isBannerShot(); //Prüft ob der Banner getroffen wird
	        Moorhuhn.getMyGameController().myZepelin.shotZepelin(); //Prüft ob der Zepelin getroffen wird
	        shotGun.scopeBlack(); //Prüft welches Scope verwendet werden muss
	        if (Moorhuhn.getMyGameController().Shots.size() == 0)
	        {
	            //Wechselt die Scope Farbe
	            shotGun.scopeRed();
	            noAmo = true;
	        }
	      }
	      else
	      {
	        //Prüft ob der Spieler unten rechts klickt um nachzuladen
	        if (parent.mouseX >= parent.width-250 && parent.mouseY >= parent.height-150)
	          {
	            reload();
	            noAmo = false;
	            Sound.playSound(new File(Environment.getActualPath()+"\\bin\\sounds\\sndReload.wav"));
	          }
	          else
	          {
	        	  doReload();
	        	  Sound.playSound(new File(Environment.getActualPath()+"\\bin\\sounds\\sndEmptyGun.wav"));
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
	    	  if (Moorhuhn.getMyGameController().myMenu.inMenu == false)
	    	  {
			      //Wechselt die Scope Farbe
			      shotGun.scopeBlack();
			      //Gibt dem Spieler 8 Schüsse
			      for(int i = 1; i <= 8; i++)
			       {
			    	  Moorhuhn.getMyGameController().Shots.add(new Shot(parent,parent.width-i*30,parent.height-100));
			       }
	    	  }
	    }
	    
	    
	    //Handle the imgShots
	    public void myKeyPressed()
	    {
	      switch(parent.key) 
	      {
	        case 32: Moorhuhn.getMyGameController().myMenu.restart(); //32 entspricht Leertaste
	      }
	    }
	    
	    /**
	     * Prüft ob ein Huhn getroffen wurde
	     */
	    void shotHuhn()
	    {
	       for (Huhn h : Moorhuhn.getMyGameController().chickens) 
	        {    
	    	   h.shotHuhn();
	        }
	    }   
	    
	  public void showHitmarker()
	  {
		  Moorhuhn.getMyGameController().myHitmarker.useHitmarker = 1;
	  }
}
