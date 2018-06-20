package games.moorhuhn.controllers;

import java.io.File;

import games.moorhuhn.basics.Environment;
import games.moorhuhn.basics.Image;
import games.moorhuhn.basics.Sound;
import processing.core.PApplet;

/**
 * This is the mainclass. This class starts the game.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Moorhuhn extends PApplet{
	
	public static PApplet me;
	private static Image myImage;
	private static GameController myGameController;
	private static Sound mySound;
	
	public static GameController getMyGameController() {
		if (myGameController == null) {
		   myGameController = new GameController(me);
		}
		return myGameController;
	}

	public static Image getMyImage() {
		if (myImage == null) {
		   myImage = new Image(me);
		}
		return myImage;
	}
	
	public static Sound getMySound() {
		if (mySound == null) {
			mySound = new Sound();
		}
		return mySound;
	}

	public static void main(String[] args) {
		PApplet.main("controllers.Moorhuhn");
	}
	
	 //Window size 1400x800 is the same as the background image 
	public void settings() {
		  fullScreen();		
	}
	

	public void setup() {
		   frameRate(60);
		   me = this;
		   Sound.playSound(new File(Environment.getActualPath()+"\\bin\\sounds\\sndTheme.wav"));
	}

		public void draw() {
		  cursor(getMyImage().imgScope, 25, 25);
		  getMyGameController().myDraw();
		}

		public void keyPressed() {
			getMyGameController().myKeyPressed();
		}

		public void mousePressed(){
			getMyGameController().gotShot();
		}
	
}
