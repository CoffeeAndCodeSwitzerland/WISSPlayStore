package games.moorhuhn.views;

import games.moorhuhn.controllers.Moorhuhn;
import processing.core.PApplet;

/**
 * Diese Klasse berechnet den Timer und stellt diesen dar.
 * V1.0 / 20.04.2018
 * @author CÈSc07
 *
 */
public class Clock 
{
  //variable declaration
  int timer = 0;
  int seconds = 9;
  int tenSeconds = 5;
  int minutes = 1;
  int tenMinutes = 0;
  int red = 255; int green = 255; int blue = 255;
  //end variable declaration
	PApplet parent;
	String clock = "Time: "+tenMinutes+minutes+":"+tenSeconds+seconds;
	
	Moorhuhn moorhuhn;
	
	public Clock(PApplet p, Moorhuhn moorHuhn) //Constructor
	{
		parent = p;
		moorhuhn = moorHuhn;
	}
	
	/**
	 * Macht eine Uhr oben rechts in der Ecke
	 */
	public void clock()
	{	
		
		if(timer == 60) {
			if(tenSeconds == 6) {
				tenSeconds = 5;
				seconds = 9;
			}
	      seconds -= 1;    //adds 1 second to the timer if framerate of 60 is reached
	      if(seconds < 0) {  //adds the 10th second if the second is over 9 
	        seconds = 9;
	        tenSeconds -= 1;
	      }
	      if(tenSeconds == 0 && seconds == 0) {
	        tenSeconds = 5;
	        seconds = 9;
	        minutes -= 1;    //adds 1 minute if the variable tenSeconds is 6
	      }
	      timer = 0;
	    }
	    timer++;
	   
	  
	    //Verhindert dass im Menu die Uhr weiterl‰uft
		if (moorhuhn.getMyGameController().myMenu.inMenu == false)
		{
			clock = "Time: "+tenMinutes+minutes+":"+tenSeconds+seconds;
			parent.fill(red,green,blue);
	    	parent.textSize(30);
	    	parent.text(clock+"", parent.width-200, 30);
		}
	}
	
	
}
