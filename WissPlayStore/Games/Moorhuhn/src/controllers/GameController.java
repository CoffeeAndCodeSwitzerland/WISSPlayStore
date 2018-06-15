package Moorhuhn.src.controllers;

import java.awt.image.ImageFilter;
import java.util.ArrayList;

import Moorhuhn.src.functions.Player;
import processing.core.PApplet;
import processing.core.PImage;
import Moorhuhn.src.shapes.Banner;
import Moorhuhn.src.shapes.Huhn;
import Moorhuhn.src.shapes.Plane;
import Moorhuhn.src.shapes.Zepelin;
import Moorhuhn.src.views.Clock;
import Moorhuhn.src.views.Hitmarker;
import Moorhuhn.src.views.Menu;
import Moorhuhn.src.views.Score;
import Moorhuhn.src.views.Shot;

/**
 * Der GameController steuert das ganze Spiel. Er zeichnet alle Klassen
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class GameController {
	  
	  PApplet parent;
	  public Score myScore;
	  public Menu myMenu;
	  public Hitmarker myHitmarker;
	  public Zepelin myZepelin;
	  public Plane myPlane;
	  public Player myPlayer;
	  Banner myBanner;
	  public Clock myClock;
	  
	  int numberHuhner = 8;
	  int numberShots = 8;
	  

	  public ArrayList<Huhn>chickens = new ArrayList<Huhn>();
	  public ArrayList<Shot>Shots = new ArrayList<Shot>();
	  
	  public static PImage background;
	    
	  GameController(PApplet p) {  //Constructor
		  parent = p;	  
		  parent.background(0);
		  parent.setSize(parent.width,parent.height);
		  background = Moorhuhn.getMyImage().flowerPower;
		  myScore = new Score(parent);
		  myMenu = new Menu(parent);
		  myHitmarker = new Hitmarker(parent);
		  myPlane = new Plane(parent);
		  myZepelin = new Zepelin(parent);
		  myPlayer = new Player(parent);
		  myBanner = new Banner(parent);
		  myClock = new Clock(parent);
		  createObjects();
	  }
	  

	  public void createObjects() 
	  {
		    //Huhner / füllt die ArrayList mit Hühner
		    for(int i = 1; i <= 2; i++)
		    {
		      chickens.add(new Huhn(parent));
		    }
		  
		    //Shots / füllt die ArrayList mit Shots
		    for(int i = 1; i <= 8; i++)
		    {
		      Shots.add(new Shot(parent,parent.width-i*30,parent.height-100));
		    }
	  }

	  
	  void myDraw() 
	  {
		  	  //Hintergrundbild ist in der Background-Klasse
		  	  int yPosBackground = (parent.height-2160)/7*3;
		  	  parent.image(background, 0, yPosBackground);
		      myScore.myDraw();
		      myPlane.checkState(); //Prüft den Status vom Plane / Plane wird im checkState gezeichnet
		      myZepelin.checkState(); //Prüft den Status vom Zepelin / Zepelin wird im checkState gezeichnet
		      
		      //TODO / Huhn überarbeiten
		      for (Huhn h : chickens) 
		        {
		          h.checkState();
		        }
		      
		      for (Shot s : Shots) 
		        {
		          s.myDraw(); //Schüsse werden gezeichnet
		        }
		      
		       myMenu.timer(); //Prüft wann die Zeit abgelaufen ist
		       myClock.clock(); //Uhr wird gezeichnet
		       myHitmarker.showHitmarker(); //TODO / Zeichnet Hitmarker, macht keinen Sinn player hat Hitmarker aber nicht der GameController
		       myPlayer.shotGun.gunSettings();
		       myPlayer.doReload(); //TODO / pro class muss es eine methode geben welche gezeichnet wird.
		       myPlayer.shotGun.stateGun(); //It writes the name of Gun
	  	}
	  
	  
	  /**
	  * Methode wenn Tastaturtaste gedrückt wird
	   */
	  void myKeyPressed()
	  {
	   myPlayer.myKeyPressed();
	  }
	        
	   /**
	   * Methode wenn Maustaste gedrückt wird
	   */
	   void gotShot()
	   {
	    myPlayer.gotShot();
	   }
	    
}
