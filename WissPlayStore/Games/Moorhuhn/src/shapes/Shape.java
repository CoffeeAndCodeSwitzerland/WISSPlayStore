package Moorhuhn.src.shapes;


import processing.core.PApplet;
import processing.core.PImage;

public abstract class Shape {
	  int myWidth = 180;
	  int myHeight = 60;
	  int xPos = 0;
	  int yPos = 0;
	  int xSpeed = 0;
	  int ySpeed = 0;
	  int counterPoints = 0;
	  int timerPoints = 30;
	  int pointsXSpeed = 0;
	  int pointsYSpeed = -1;
	  int pointsXPos;
	  int pointsYPos;
	  public boolean readyToFly = true;
	  PApplet parent;
	  
	  
	  int shapeCounter = 0;
	  int isShapeShot = 0; //Bestimmt ob der Shape getroffen wurde
	  int isShapeDead = 0;  //Prüft ob der Shape Tod(getroffen) wurde

	  PImage imgShape;
	  PImage imgDeadShape;
	  
	  public Shape(PApplet p)
	  {
		  parent = p;  
	  }
	  
	  protected void showPoints(int r, int g, int b, int p)
	  {
          //Bestimmt Farbe, Grösse und Text
    	  parent.fill(r,g,b);
    	  parent.textSize(35);
          parent.text(Integer.toString(p), pointsXPos,pointsYPos);
          counterPoints++;
	  }
	  
	  //Schreibt die Punkte vom Shape
	   void showShapePoints(int r, int g, int b, int p)
	    {
		   
	      if (isShapeDead < 3)
	        {
	    	  pointsXPos = xPos;
	    	  pointsYPos = yPos;
	        }
	      else
	      {
	          if (counterPoints <= timerPoints)//PB 19.04.2018 13:49 Huhn: (shapePoints.counterPoints < shapePoints.timerPoints)
	            {
	        	  showPoints(r,g,b,p);
	        	  movePoints();
	            }
	          else if (counterPoints >= timerPoints)
	          {
	        	  counterPoints = 0;
	          }
	      }
	    }
	   
	   void movePoints()
	   {
	        pointsXPos += pointsXSpeed;
	        pointsYPos += pointsYSpeed;
	   }
	   
	   
		public void defaultControl()
		{
			  xSpeed = -2;
			  ySpeed = 0;
			  isShapeDead = 0;
			  shapeCounter = 0;
			  //explosionCounter = 0;
		}
		
		  public void posZepelin()
		  {
			  //Positioniert Zepelin
		      xPos = parent.width;
		      yPos = (int)parent.random(parent.height/5); //Bestimmt die Flughöhe im oberen fünftel;
		  }
}
