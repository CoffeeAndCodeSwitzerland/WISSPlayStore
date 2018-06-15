package Moorhuhn.src.shapes;

import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;
import Moorhuhn.src.views.Explosion;

/**
 * Diese Klasse stellt den Zepelin dar. Die Klasse bewegt den Zepelin und berechnet die Flug und Absturz-Physik.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Zepelin extends Shape{
	
//	  Points zepelinPoints;
	  Explosion zepelinExplosion;
	
//	  int defectState;
//	  int zepelinCounter;
	  int explosionCounter;
	  int hitCounter = 0;
	  int fireCounter = 0;
	  
	
	public Zepelin(PApplet p){
		super (p);
		myWidth = 450;
		myHeight = 100;
	    posZepelin();
	    zepelinExplosion = new Explosion(parent);
	    defaultControl();

	}
	
	//Macht dass der Zepelin 3 mal getroffen werden muss.
	public void checkHit()
	{
		if(hitCounter >= 3)
		{
			isShapeDead = 1;
		}
	}
			
	//Zeichnet Zepelin
	public void myDraw()
	  {
		//Prüft ob Zepelin getroffen wurde
		 if (isShapeDead == 0)
		 {//Zepelin nicht getroffen
			 parent.image(Moorhuhn.getMyImage().imgZepelin, xPos, yPos, myWidth, myHeight);//Zeichnet Zepelin ohne Schaden
		 }
		 else
		 {//Zepelin getroffen
			 parent.image(Moorhuhn.getMyImage().imgDeadZepelin, xPos, yPos, myWidth, myHeight);//Zeichnet Zepelin mit Schaden
		 }
	  }
		
	  public void checkState()
	  {
		 if (readyToFly == true && Moorhuhn.getMyGameController().myScore.score >= 88) //Prüft ob der Zepelin bereit ist und ob der Score mehr als 88 beträgt
		 {
		  if(isShapeDead == 0) //Prüft ob Zepelin getroffen wurde 
		  {
			  //Prüft ob der Zepelin sich im Spielfeld befindet, wennn nicht wird der Zepelin nicht mehr gezeichnet
			  if(xPos+myWidth > 0)
			  {
				  //Zeichnet und bewegt Zepelin 
				  moveZepelin();
				  myDraw();
			  }
		  }
		  else
		  {//Zepelin getroffen
			  moveZepelin();
		  }
		 }
	  }
	  
	  /**
	   * Bewegt Zepelin
	   */
	  public void moveZepelin()
	  {
		    if (isShapeDead == 0)//Prüft ob Zepelin getroffen wurde
		    {
		    	//Bewegt Zepelin
			    xPos += xSpeed;
			    yPos += ySpeed;
		    }
		    else//Wenn Zepelin getroffen wurde
		    {
		    	crashZepelin();	    	
		    }
	  }
	  
	  public void crashZepelin()
	  {
		  if(yPos+myHeight < parent.height)//Prüft ob Zepelin höher als den Boden ist
		  {
			  //Solange Zepelin höher als Boden ist, soll er nach unten Sinken
			  ySpeed = 5;
			  xPos += xSpeed;
			  yPos += ySpeed;
			  myDraw();
		  }
		  else//Wenn Zepelin den Boden berührt
		  {
			  isShapeDead++;
			  destroyZepelin();
			  showPoints();
			  showExplosion();
		  }
	  }
	  
	  public void destroyZepelin()
	  {
		  if (isShapeDead < 3)
		  {
			  //Legt die Position von der Explosion und der Punktzahl fest
			  zepelinExplosion.xPos = xPos-zepelinExplosion.myWidth/2;
			  zepelinExplosion.yPos = yPos-zepelinExplosion.myHeight/2;
		      Moorhuhn.getMyGameController().myScore.score += xPos/5; //Fügt die Punkte zum Score hinzu
		  }
	  }
	  
	  public void showExplosion()
	  {
		  if (explosionCounter < 6)
		  {
			  //Zeichnet die Explosion eine bestimmte Zeit
		      zepelinExplosion.myDraw();
		      explosionCounter++;
		  }
	  }
	  
	   public void showPoints()
		  {
			  if (shapeCounter > 30)
			  {
				  readyToFly = false; //Versichert das der Zepelin nicht erneut losfliegt
			  }
			  else
			  {
				  shapeCounter++;
			   	  showShapePoints(0,255,0,(xPos)/5);
			  }
		  }
	  
	  public void resetZepelin()
	  {
		  explosionCounter = 0;
		  hitCounter = 0;
		  posZepelin();
		  defaultControl();
	  }
		
		public void shotZepelin()
		  {
			  //Prüft ob Zepelin getrofen wird
	          if (parent.mouseX >= xPos && parent.mouseX <= xPos+myWidth && 
	        		  parent.mouseY >= yPos && parent.mouseY <= yPos+myHeight)
	          {
	        	  //Zepelin getroffen
	        	  hitCounter += 1;
	        	  checkHit();
	        	  Moorhuhn.getMyGameController().myPlayer.showHitmarker();
	        	  //Punkte werden in der Class Zepelin zum Score gezähl
	          }
		  }
}
