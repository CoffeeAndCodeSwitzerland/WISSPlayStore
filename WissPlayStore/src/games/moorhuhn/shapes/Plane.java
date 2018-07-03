package games.moorhuhn.shapes;

import games.moorhuhn.controllers.Moorhuhn;
import games.moorhuhn.controllers.MusicHandler;
import games.moorhuhn.views.Explosion;
import processing.core.PApplet;

/**
 * Diese Klasse stellt den Flieger dar. Die Klasse bewegt den Flieger und berechnet die Flug und Absturz-Physik.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Plane extends Shape{
	
//	  Points planePoints;
	  Explosion planeExplosion;
	  public Banner myBanner;

	  public int counter = 0; //Zählt aufwers, wenn gleich wie timer dann fliegt der Flieger los
	  int timer;
	  int explosionCounter = 0; //Zählt aufwerts, wenn = 6 verschwindet die Explosion
	  int bannerCounter = 0; //Zählt aufwerts
	  
	  MusicHandler music;
	  Moorhuhn moorhuhn;
	  
	  public Plane(PApplet p, MusicHandler musicHandler, Moorhuhn moorHuhn) //Constructor
	  {
		  	super (p);
		  	moorhuhn = moorHuhn;
		  	music = musicHandler;
			myWidth = 189;
			myHeight = 80;
			xPos = -myWidth;
			xSpeed = 10;
			imgShape = Moorhuhn.getMyImage().imgPlane;
			planeExplosion = new Explosion(parent, music);
			myBanner = new Banner(parent, moorhuhn);
			setDefault();
	  }
	  
	  public void setDefault()
	  {
		  yPos = (int)parent.random(parent.height/5)+50; //Bestimmt die Flughöhe im oberen fünftel
		  timer = (int)parent.random(3600/5)+parent.width/10+2500; //Bestimmt eine Zeit nach welcher der Flieger durchfliegen soll
	  }
	  
	  public void checkState()
	  {
	    if (isShapeDead < 1) //Prüft ob der Flieger getroffen wurde
	    { //Flieger nicht getroffen
	      myDraw();//Zeichnet Flieger
	      posPlane();//Bewegt Flieger
	      setBannerSpeed();//Bestimmt die Position(Geschwindigkeit) vom Banner
	    }
	    else
	    {
	    	//Flieger getroffen
	      isShapeDead++;
	      destroyPlane();
	 	 //Zeichnet die Fliegerpünkte
		   showShapePoints(0, 255, 0, (1400-pointsXPos)/5);
	      //myDraw(); //Wenn ausgenotiert dispawnt der Flieger in der Explosion
	      changeImg();
	      //checkBannerState(); //Wenn ausgenotiert dispawnt der Banner in der Explosion
	    }
	  }
	  
	  //Flugzeug wird gezeichnet
	  void myDraw(){
		  if (readyToFly == true)
		  {
		    parent.image(imgShape, xPos, yPos, myWidth, myHeight); 
		  }
	  }
	  
	  //Flugzeug wird positioniert
	  void posPlane()
	  {
	    counter ++;
	    if (readyToFly == true && counter > timer)
	    {
	    //Läst den Flieger fliegen
	    changeImg();
	    resetCounter();
	    }
	  }
	  
	  //Bewegt das Flugzeug
	  void changeImg(){
	     xPos += xSpeed; //x = 10
	     yPos += ySpeed; //y = 0
	  }
	  
		 void destroyPlane()
		  {
			//Bestimmt die Absturzgeschwindigkeit
			xSpeed = 0;
	        ySpeed = 10;
		    //Prüft Status
		    if (isShapeDead < 3) {
		        
		      //Setzt die Position der Explosion und Punkte
		      planeExplosion.xPos = xPos-planeExplosion.myWidth/2;
		      planeExplosion.yPos = yPos-planeExplosion.myHeight/2;
		      pointsXPos = xPos;
		      pointsYPos = yPos;
		    }
		    
		    //Bestimmt wie lange die Explosion gezeichnet wird
		    if (explosionCounter < 6)
		     {
		      //Zeichnet die Explosion
		      planeExplosion.myDraw();
		      explosionCounter++;
		     }
		     		    
		    //Prüft ob der Flieger ausserhalb des bildes ist
		    if (yPos > parent.height || xPos > parent.width)
		    {
		      explosionCounter = 0;
		      isShapeDead = 0;
		      resetCounter();
		      shapeCounter = 0;
		      counterPoints = 0;
		    }
		  }
	  
	  void resetCounter()
	  {
	    //Prüft ob der Flieger durch das ganze Feld geflogen ist
	     if (counter > timer+parent.width/10+myWidth)
	     {
	        counter = 0;
	        //Stellt den Timer neu ein und setzt den Flieger wieder auf eine beliebige Reiseflughöhe
	        setDefault();
	        //Positioniet den Flieger wieder ans linke äussere Ende (Ausserhalb)
	        xPos = -myWidth;
	        //Bestimmt die Geschwindigkeit
	        xSpeed = 10;
	        ySpeed = 0;
	      }
	  }
	       
       /**
        * Bestimmt mit welchem Abstand der Banner dem Flugzeug hinterher fliegt.
        */
	   void setBannerSpeed()
	    {
		   if (myBanner.isShapeDead < 1)
		   {
			      myBanner.xPos = xPos-myBanner.myWidth-20; //Ohne Seile müsste = xPos-myBanner.myWidth-50; sein.
			      myBanner.yPos = yPos+10;
		   }
		   myBanner.myDraw();
	    }
	    	 
	  public void shotPlane()
	    {          
	          //Prüft ob Flieger getroffen wird
	          if (parent.mouseX >= xPos && parent.mouseX <= xPos+myWidth && 
	        		  parent.mouseY >= yPos && parent.mouseY <= yPos+myHeight)
	          {
	        	  //Flieger getroffen
	        	  isShapeDead = 1;
	        	  moorhuhn.getMyGameController().myScore.score += (1400-xPos)/5;
	        	  moorhuhn.getMyGameController().myPlayer.showHitmarker();
	          }
	    }
}
