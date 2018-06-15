package Moorhuhn.src.shapes;

import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Diese Klasse stellt das Huhn dar. Es bewegt das Huhn und berechnet die Flug und Absturz-Physik.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Huhn extends Shape{
	
	  int scale = 0;
	  int swingCounter = 1;
	  boolean invertDirection;
	  
	  
	  public Huhn (PApplet p) //Constructor
	  {
		  super (p);
		  setPosition(); 
	  }
	  
	  /**
	   * Diese Methode setzt die Position des Huhnes beim Spawn
	   */
	  public void setPosition() 
	  {
		  	//Macht xSpeed und ySpeed random
		    xSpeed = (int)parent.random(4)+1; 
		    ySpeed = (int)parent.random(2)+1;
		    //Bestimmt die Position des Huhnes wo es Spawnt (im Spielfeld)
		    xPos = (int)parent.random(parent.width-200);
		    yPos = (int)parent.random(parent.height-200);
		    scale = (int)parent.random(35)+80; //Bestimmt die Grösse zwischen 80 und 115
		    //Bestimmt die Grössse des Huhnes (Bild ist Quadratisch)
		    myWidth = scale;
		    myHeight = scale;
		    direction();
	  }
	  
	  /**
	   * Diese Methode berechnet in welche Richtung das Huhn fliegt
	   */
	  void direction() {
		  	//Prüft ob xPosition auf der rechten Seite des Spielfeldes ist. Wenn ja fliegt das Huhn nach links
		    if (xPos > (parent.width/2))
		    {
			    xSpeed *= -1;
			    invertDirection = true; //Bestimmt dass das Huhn nach links fliegt
		    }
		    else
		    {
		    	invertDirection = false; //Bestimmt dass das Huhn nach rechts fliegt
		    }
		   
		    //Prüft ob das Huhn in der unteren Hälfte des Spielfeldes ist
		    if (yPos > (parent.height/2))
		    {
		    	//Wenn ja fliegt das Huhn nach oben
		    	ySpeed *= -1;
		    }
		  }
	  
	    public void checkState() 
	      {
		        if (isShapeDead == 0) //Prüft ob Huhn getroffen wurde
		        {//Nicht getroffen
		          changeImg();
		          countSwing();
		          moveHuhn();
		          myDraw();
		          respawn();
		        }
		        else
		        {//Getroffen
		          isShapeDead++;
		          showShapePoints(0, 255, 0, (35-(scale/5)));
		          killHuhn();
		          moveHuhn();
		          myDraw();
		          respawn();
		        }
	      }
	    
		  void changeImg () 
		    {    
			  //Prüft in welche Richtung das Huhn fliegt. Darauf wird dass Bild gespiegelt oder nicht
		       if (invertDirection == false)
		         {
		          switch (swingCounter) 
		            {
		              case 1:
		                imgShape = Moorhuhn.getMyImage().imgHuhn1;
		              break;
		              case 15:   
		                imgShape = Moorhuhn.getMyImage().imgHuhn2;
		              break;
		            }
		          }
		          
		          
		          else{
		             switch (swingCounter) 
		               {
		                  case 1:
		                    imgShape = Moorhuhn.getMyImage().imgHuhn1g;
		                  break;
		                  case 15:   
		                    imgShape = Moorhuhn.getMyImage().imgHuhn2g;
		                  break;
		              }
		            }
		    }  
		  
	/**
	 * Diese Methode zählt die Flügelschläge und setzt diese nach 30 Schlägen zurück
	 */
	 void countSwing()
	 	{
		  swingCounter ++; 
		  if (swingCounter > 30)
		     {
		       swingCounter = 1; 
		     }
		}
	  	 
	 /**
	  * In dieser Methode wird das Huhn bei jedem Frame neu positioniert
	  */
	  void moveHuhn()
	  {
	       xPos += xSpeed;
	       yPos += ySpeed;
	  }
	  
	  /**
	   * Zeichnet Huhn
	   */
	  void myDraw () 
	    {
		  parent.image(imgShape, xPos, yPos, myWidth, myHeight); 
	    }
	  
	  /**
	   * Prüft ob dass Huhn im Feld ist. Wenn nicht dann wird dass Huhn neu positioniert
	   */
	    void respawn()
	    {
	       if (xPos > parent.width || yPos > parent.height || xPos < 0-myWidth || yPos < 0-myHeight)
	          {
	              isShapeDead = 0;
	              setPosition();
	              changeImg();
	          }
	    }
	    
	    
	    
	    void killHuhn()
	      {
	      imgShape = Moorhuhn.getMyImage().imgDeadHuhn;
	      xSpeed = 0;
	      ySpeed = 20;
	      }
	    	      	  
	    public void shotHuhn()
	    {
	          //Prüft ob Huhn getrofen wurde
	          //Satz des Pythagoras
	          double xCenter = myWidth/2;
	          double yCenter = myHeight/2;
	          double dhCXPos = parent.mouseX-(xPos+xCenter);
	          double dhCYPos = parent.mouseY-(yPos+yCenter);
	          double hypothenuse = Math.sqrt(dhCXPos*dhCXPos + dhCYPos*dhCXPos);
	          hypothenuse *= 1.25; //Falls die Hitbox zu klein wäre, einfach diese Zeile entfernen!!!!
	          
	          //Treffer
	          if(hypothenuse < (myWidth/2)+Moorhuhn.getMyGameController().myPlayer.shotGun.spray)
	              {
	               isShapeDead = 1;
	               Moorhuhn.getMyGameController().myScore.score += 35-(scale/5);
	               showShapePoints(0, 255, 0, (35-(scale/5)));   
	               Moorhuhn.getMyGameController().myPlayer.showHitmarker();
	               //HuhnDead-Sound
	               //Sound.playSound(new File("Sounds/sndHuhnDead.wav"));
	              }
	        }
	    
}
