package Moorhuhn.src.views;

//import java.io.PrintWriter;

import processing.core.PApplet;

/**
 * Diese Klasse z�hlt die Punkte und stellt diese dar.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Score {
	PApplet parent;
	public int score = 0;
	int red = 255;
	int green = 255;
	int blue = 255;
	
	public Score(PApplet p){
		parent = p;
	}
	
	  
	  public void myDraw()
	  {
	      parent.fill(red,green,blue); //Bestimmmt die Textfarbe
	      parent.textSize(30); //Bestimmt die Textgr�sse
	      parent.text("Score: "+score, 20, 30); //Zeichnet den Score
	  }
	  
}
