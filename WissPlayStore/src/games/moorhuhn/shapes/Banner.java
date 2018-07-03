package games.moorhuhn.shapes;

import games.moorhuhn.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Diese Klasse stellt die Flagge hinter dem Flieger je nach Status dar.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Banner extends Shape{
	
	Moorhuhn moorhuhn;
	
	  public Banner(PApplet p, Moorhuhn moorHuhn){
		  	super (p);
		  	moorhuhn = moorHuhn;
			imgShape = Moorhuhn.getMyImage().imgBanner;
			imgDeadShape = Moorhuhn.getMyImage().imgDeadBanner;
	  }
	  
	   public void isBannerShot()
	   {
	          //Pr�ft ob Banner getrofen wird
	          if (parent.mouseX >= xPos-moorhuhn.getMyGameController().myPlayer.shotGun.spray && 
	        	parent.mouseX <= xPos+myWidth+moorhuhn.getMyGameController().myPlayer.shotGun.spray && 
	        	parent.mouseY >= yPos-moorhuhn.getMyGameController().myPlayer.shotGun.spray && 
	        	parent.mouseY <= yPos+myHeight+moorhuhn.getMyGameController().myPlayer.shotGun.spray)
	          {
	        	  //Banner getroffen
	        	  isShapeDead = 1;
	        	  moorhuhn.getMyGameController().myScore.score -= 50;
	        	  moorhuhn.getMyGameController().myPlayer.showHitmarker();
	          }
	   }
	  
	      /**
	       * Pr�ft ob Banner Tod(Getroffen) ist
	       */
	      void myDraw()
	        {
	    	  if (readyToFly == true)
	    	  {
		          if (isShapeDead < 1)
	              {//Nicht getroffen
		        	  if (parent == null) System.out.println("parent is NULL!!");
		        	  if (imgShape == null) System.out.println("Banner is NULL!!");
		        	  
		              parent.image(imgShape, xPos, yPos, myWidth, myHeight); //Zeichnet fr�hlicher Banner
	              }
	           else
	              {//Getroffen
		              parent.image(imgDeadShape, xPos, yPos, myWidth, myHeight); //Zeichnet trauriger Banner
		              killBanner();
	              }
	    	  }
	        }        
	        
	    /**
	     * Pr�ft ob der Bannern getroffen wurde
	     */
	    public void killBanner()
	     {
	        isShapeDead++;
	    	showShapePoints(255,0,0,-50); //Zeichnet Bannerp�nkte
	        moveDeadBanner(); //Sagt dass sich der Banner nun alleine bewegen muss
	            
	        if (yPos > parent.height || xPos > parent.width) //Pr�ft ob der Banner ausserhalb des Fensters ist
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
