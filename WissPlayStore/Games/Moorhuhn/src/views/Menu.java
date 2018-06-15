package Moorhuhn.src.views;

import java.io.File;
//import java.io.File;
import java.util.ArrayList;

import Moorhuhn.src.basics.Environment;
import Moorhuhn.src.basics.Sound;
import Moorhuhn.src.controllers.GameController;
//import basics.Sound;
import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;
import processing.core.PImage;
//import shapes.Huhn;

/**
 * Diese Klasse bestimmt die Zeit wie lange das Spiel geht. Die Klasse stellt das Menu dar und
 * stellt jegliche Parameter auf ihren Uhrsprünglichen Wert zurück.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Menu {
	PApplet parent;
	int xPos = 0;
	int yPos = 0;
	int counter = 0;
	int endTime = 7200;
	int myWidth = 0;
	int myHeight = 0;
	int holeCounter = 0;
	int sekCounter; //Counter welcher die Sekunden abzählt, 1sek = 60sekCounter / 60FPS
	int i = 0;
	int drawHolesCounter = 0; 
	public boolean inMenu = false; //Bestimmt ob man im Menu ist oder nicht
	PImage imgMenuHuhn;
	
	public ArrayList<Target>targets = new ArrayList<Target>();
	
	Sound mySound = new Sound();
	
	public Menu(PApplet p){ //Constructor
		parent = p;
		xPos = parent.width/2;
		yPos = parent.height/2;
		imgMenuHuhn = Moorhuhn.getMyImage().imgMenuHuhn;
		createObjects();
	}	
	
	public void createObjects()
	{
	    //Targets / füllt die ArrayList mit Löcher
	    for(int i = 0; i <= 8; i++)
	    {
	      targets.add(new Target(parent,parent.width-i*30,parent.height-100));//targets.add(new Target(parent,(parent.width/2)-i*30,parent.height/2));
	    }
	}
	
	
	 //Setzt die Zeit welche man Speielen kann (7200 = 2min / 60FPS)
	 public void timer()
	  {
	    
	    //Prüft ob die Zeit abgelaufen ist
	    if (Moorhuhn.getMyGameController().myClock.minutes < 0 && Moorhuhn.getMyGameController().myClock.tenSeconds > 0 && Moorhuhn.getMyGameController().myClock.seconds > 0)
	    {
	    	parent.textSize(100); //Bestimmt die Textgrösse
	    	parent.text("Time Over", parent.width/2, parent.height/2); //Gibt dem Spieler zu deuten dass die Zeit vorbei ist
	    	parent.textSize(20); //Bestimmt die Textgrösse
	    	parent.fill(255,255,255);
	    	parent.text("For restart press SPACE", parent.width/2, parent.height/2+50); //Zeigt dem Spieler wie er das Spiel neustarten kann
	    	inMenu = true; //Sagt dass das Menu gebraucht wird
	    	end(); //In der end Methode wird alles ausgeschaltet
	    	createObjects();
	    	endAnimation();
	    	drawHoles();
	    }
	  }
	 
	 public void endAnimation()
	 {
		 if (i < 8) 
		 {
			 if (holeCounter == 10)
			 {
				 i++;
				 holeCounter = 0;
			 }
			 holeCounter++;
		 }
	 }	  
	 
	 public void drawHoles()
	 {
		 targets.get(i).state++;
		 
		 for (Target t : targets)
		 {
				 t.myDraw();    
		 }
	 }
	  
	  	/**
	  	 * Schaltet alles aus was im Menu herumfliegen könnte
	  	 */
	    public void end()
	    {
	      clearObjects(); //Leert die ArrayList's damit im Menu keine Hühner usw. herumfliegen
	      Moorhuhn.getMyGameController().myPlane.readyToFly = false; //Stellt sicher das im Menu kein Flieger durch fliget
	      Moorhuhn.getMyGameController().myPlane.myBanner.readyToFly = false;
	      GameController.background = Moorhuhn.getMyImage().imgMenuBackground;
	      //Moorhuhn.getMyImage().flowerPower = Moorhuhn.getMyImage().imgMenuBackground; //Ändert das Hintergrundbild
	      Moorhuhn.getMyImage().imgScope = Moorhuhn.getMyImage().imgScopeBlack; //Nimmt wieder das normale Scope
	      Moorhuhn.getMyGameController().myZepelin.readyToFly = false; //Definiert dass der Zepelin nicht mehr Fliegen kann
	      Moorhuhn.getMyGameController().myClock.clock = ""; //Blendet die Uhr aus
	    }
	  	    
	  /**
	   * Wenn SPACE auf der Tastatur gedrückt wird, wird diese Class ausgeführt
	   */
	  public void restart()
	    {
	      clearObjects(); //Stellt sicher dass die ArrayList's leer sind
	      Moorhuhn.getMyGameController().createObjects(); //Füllt die ArrayList's wieder mit Objekten(Hühner, Schüsse)
	      Moorhuhn.getMyGameController().myScore.score = 0; //Stellt den Score auf 0
	      GameController.background = Moorhuhn.getMyImage().flowerPower; //Fügt als Hintergrundbild wieder das Feld ein
	      Moorhuhn.getMyGameController().myZepelin.readyToFly = true; //Definiert dass der Zepelin wieder Fliegen kann
	      Moorhuhn.getMyGameController().myZepelin.resetZepelin(); //Setzt den Zepelin zurück so dass er für eine neue Runde bereit ist
	      Moorhuhn.getMyGameController().myPlane.readyToFly = true; //Definiert dass der Flieger wieder Fliegen kann
	      Moorhuhn.getMyGameController().myPlane.myBanner.readyToFly = true;
	      Moorhuhn.getMyGameController().myPlane.counter = 0; //Setzt den Counter auf NULL damit der Flieger nicht am anfang durchfliegen kann
	      Moorhuhn.getMyGameController().myClock.tenSeconds = 6; //Setzt Sekunden zurück
	      Moorhuhn.getMyGameController().myClock.seconds = 0;
	      Moorhuhn.getMyGameController().myClock.minutes = 1; //Setzt den Counter von der Clock zurück
		  Moorhuhn.getMyGameController().myClock.red = 255; //Setzt die Schriftfarbe auf Weiss
		  Moorhuhn.getMyGameController().myClock.green = 255; //Setzt die Schriftfarbe auf Weiss
		  Moorhuhn.getMyGameController().myClock.blue = 255; //Setzt die Schriftfarbe auf Weiss
		  Moorhuhn.getMyGameController().myScore.red = 255; //Setzt die Schriftfarbe auf Weiss
		  Moorhuhn.getMyGameController().myScore.green = 255; //Setzt die Schriftfarbe auf Weiss
		  Moorhuhn.getMyGameController().myScore.blue = 255; //Setzt die Schriftfarbe auf Weiss
		  Moorhuhn.getMyGameController().myPlayer.noAmo = false;
		  Moorhuhn.getMyGameController().myPlayer.shotGun.scopeBlack();
	      counter = 0; //Setzt Counter zurück
	      inMenu = false;
	      i = 0;
		  holeCounter = 0;
		  targets.clear();
		  Sound.playSound(new File(Environment.getActualPath()+"\\bin\\sounds\\sndTheme.wav"));
	    }
	   	
	  
	    /**
	     * Leert die ArrayList's
	     */
	    public void clearObjects()
	    {
	    	Moorhuhn.getMyGameController().Shots.clear();
	    	Moorhuhn.getMyGameController().chickens.clear();
	    }
}
