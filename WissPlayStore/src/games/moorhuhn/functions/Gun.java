package games.moorhuhn.functions;

import games.moorhuhn.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Controls the size of spray and color of the scope.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Gun {
	PApplet parent;
	int pointsShotGun = 1750; //Notwendige Punkte damit man die abgesägte ShotGun bekommt
	public int spray = 0;
	int stateGun = 1;
	int myWidth = 200;
	int myHeight = 50;
	
	Moorhuhn moorhuhn;
	
	Gun(PApplet p, Moorhuhn moorHuhn){
		parent = p;
		moorhuhn = moorHuhn;
	}
	  
	  /**
	   * Controls the shape and the color of the scope
	   */
	  public void scopeBlack()
	    {
	      if (moorhuhn.getMyGameController().myScore.score < pointsShotGun)
	        {
	    	  Moorhuhn.getMyImage().imgScope = Moorhuhn.getMyImage().imgScopeBlack;
	        }
	      else
	        {
	          if (moorhuhn.getMyGameController().myScore.score >= pointsShotGun)
	            {
	        	  Moorhuhn.getMyImage().imgScope = Moorhuhn.getMyImage().imgWideScopeBlack;
	            }
	        }
	    }
	    
	    /**
	     * Controls the shape and the color of the scope
	     */
	    public void scopeRed()
	    {
	      if (moorhuhn.getMyGameController().myScore.score < pointsShotGun)
	        {
	    	  Moorhuhn.getMyImage().imgScope = Moorhuhn.getMyImage().imgScopeRed;
	        }
	      else
	        {
	          if (moorhuhn.getMyGameController().myScore.score >= pointsShotGun)
	            {
	        	  Moorhuhn.getMyImage().imgScope = Moorhuhn.getMyImage().imgWideScopeRed;
	            }
	        }
	    }
	    
		  public void gunSettings()
		  {
		    if (moorhuhn.getMyGameController().myScore.score < pointsShotGun)
		      {
		        spray = 0;
		      }
		    else
		    {
		      if (moorhuhn.getMyGameController().myScore.score >= pointsShotGun)
		        {
		          spray = 50;
		        }
		    }
		  }
	  
	  
	  
	  public void stateGun()
	  {
		  if (moorhuhn.getMyGameController().myMenu.inMenu == false)
		  {
			  parent.fill(255,255,255);
			     parent.textSize(30);
			     
			     if (moorhuhn.getMyGameController().myScore.score < pointsShotGun)
			     {
			       stateGun = 1;
			     }
			     else if (moorhuhn.getMyGameController().myScore.score > pointsShotGun)
			     {
			       stateGun = 2;
			     }
			     
			     switch (stateGun)
			     {
			       case 1: parent.image(Moorhuhn.getMyImage().imgShotgun, (parent.width/2)-(myWidth/2), 10, myWidth, myHeight); break;
			       case 2: parent.image(Moorhuhn.getMyImage().imgSawedShotgun, (parent.width/2)-(myWidth/2), 10, myWidth, myHeight); break;
			     }
		  }
	  }
}
