package Moorhuhn.src.shapes;

import Moorhuhn.src.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Diese Klasse stellt die Flagge hinter dem Flieger je nach Status dar.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Banner extends Shape{
	
	  public Banner(PApplet p){
		  	super (p);
			imgShape = Moorhuhn.getMyImage().imgBanner;
			imgDeadShape = Moorhuhn.getMyImage().imgDeadBanner;
	  }
	  
	   public void isBannerShot()
	   {
	          //Prüft ob Banner getrofen wird
	          if (parent.mouseX >= xPos-Moorhuhn.getMyGameController().myPlayer.shotGun.spray && 
	        	parent.mouseX <= xPos+myWidth+Moorhuhn.getMyGameController().myPlayer.shotGun.spray && 
	        	parent.mouseY >= yPos-Moorhuhn.getMyGameController().myPlayer.shotGun.spray && 
	        	parent.mouseY <= yPos+myHeight+Moorhuhn.getMyGameController().myPlayer.shotGun.spray)
	          {
	        	  //Banner getroffen
	        	  isShapeDead = 1;
	        	  Moorhuhn.getMyGameController().myScore.score -= 50;
	        	  Moorhuhn.getMyGameController().myPlayer.showHitmarker();
	          }
	   }
	  
	      /**
	       * Prüft ob Banner Tod(Getroffen) ist
	       */
	      void myDraw()
	        {
	    	  if (readyToFly == true)
	    	  {
		          if (isShapeDead < 1)
	              {//Nicht getroffen
		        	  if (parent == null) System.out.println("parent is NULL!!");
		        	  if (imgShape == null) System.out.println("Banner is NULL!!");
		        	  
		              parent.image(imgShape, xPos, yPos, myWidth, myHeight); //Zeichnet fröhlicher Banner
	              }
	           else
	              {//Getroffen
		              parent.image(imgDeadShape, xPos, yPos, myWidth, myHeight); //Zeichnet trauriger Banner
		              killBanner();
	              }
	    	  }
	        }        
	        
	    /**
	     * Prüft ob der Bannern getroffen wurde
	     */
	    public void killBanner()
	     {
	        isShapeDead++;
	    	showShapePoints(255,0,0,-50); //Zeichnet Bannerpünkte
	        moveDeadBanner(); //Sagt dass sich der Banner nun alleine bewegen muss
	            
	        if (yPos > parent.height || xPos > parent.width) //Prüft ob der Banner ausserhalb des Fensters ist
	        {
	           isShapeDead = 0;
	           counterPoints = 0;
	           //Setzt die Geschwindigkeit auf null
	           xSpeed = 0;
	           ySpeed = 0;
	        }
	      }
	    
	      /**
	       * Bewegt den Banner sobald dieser getroffen wurde
	       */
	      void moveDeadBanner()
	        {
	          xSpeed = 5;
	          ySpeed = 3;
	          xPos += xSpeed;
	          yPos += ySpeed;
	        }	
	    
}
