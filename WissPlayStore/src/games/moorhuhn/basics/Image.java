package games.moorhuhn.basics;

import games.moorhuhn.controllers.Moorhuhn;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Hier werden alle Bilder geladen damit diese nur einmal geladen werden müssen.
 * V1.0 / 20.04.2018
 * @author Patrick Bauersfeld
 *
 */
public class Image {
	
	
	PApplet parent;
	
	String bilderPath ="games/moorhuhn/data/";
	
	public Image(PApplet p) {
	  parent = p;
	}
	 //Huhn
	  public PImage imgDeadHuhn = Moorhuhn.me.loadImage(bilderPath+"imgDeadHuhn.png");
	  public PImage imgHuhn1 = Moorhuhn.me.loadImage(bilderPath+"imgHuhn1.png");
	  public PImage imgHuhn2 = Moorhuhn.me.loadImage(bilderPath+"imgHuhn2.png");
	  public PImage imgHuhn1g = Moorhuhn.me.loadImage(bilderPath+"imgHuhn1g.png");
	  public PImage imgHuhn2g = Moorhuhn.me.loadImage(bilderPath+"imgHuhn2g.png");
	  
	  //Shots
	  public PImage imgShot = Moorhuhn.me.loadImage(bilderPath+"imgShot.png");
	  
	  //Plane
	  public PImage imgPlane = Moorhuhn.me.loadImage(bilderPath+"imgPlane.png");
	  
	  //Banner
	  public PImage imgBanner = Moorhuhn.me.loadImage(bilderPath+"imgBanner.png");
	  public PImage imgDeadBanner = Moorhuhn.me.loadImage(bilderPath+"imgDeadBanner.png");
	  
	  
	  //Menu
	  public PImage imgMenuHuhn = Moorhuhn.me.loadImage(bilderPath+"imgMenuHuhn.png");
	  public PImage imgHole = Moorhuhn.me.loadImage(bilderPath+"imgHole.png");
	  
	  //Explosion
	  public PImage gifExplosion = Moorhuhn.me.loadImage(bilderPath+"gifExplosion.gif");
	  
	  //Scope
	  public PImage imgScopeBlack = Moorhuhn.me.loadImage(bilderPath+"imgScopeBlack.png");
	  public PImage imgScopeRed = Moorhuhn.me.loadImage(bilderPath+"imgScopeRed.png");
	  public PImage imgWideScopeBlack = Moorhuhn.me.loadImage(bilderPath+"imgWideScopeBlack.png");
	  public PImage imgWideScopeRed = Moorhuhn.me.loadImage(bilderPath+"imgWideScopeRed.png");
	  public PImage imgScope = imgScopeBlack;
	  
	  //Hitmarker
	  public PImage imgWhiteHitmarker = Moorhuhn.me.loadImage(bilderPath+"imgHitmarker.png");
	  PImage imgRedHitmarker = Moorhuhn.me.loadImage(bilderPath+"imgHitmarker.png");//Falsches Bild
	  
	  //Background
	  public PImage imgMenuBackground = Moorhuhn.me.loadImage(bilderPath+"imgMenuBackground.png");
	  public PImage flowerPower = Moorhuhn.me.loadImage(bilderPath+"flowerPower.png");
	  
	  //Zepelin
	  public PImage imgZepelin = Moorhuhn.me.loadImage(bilderPath+"imgZepelin.png");
	  public PImage imgDeadZepelin = Moorhuhn.me.loadImage(bilderPath+"imgDeadZepelin.png");
	  
	  //Gun
	  public PImage imgShotgun = Moorhuhn.me.loadImage(bilderPath+"imgShotgun.png");
	  public PImage imgSawedShotgun = Moorhuhn.me.loadImage(bilderPath+"imgSawedShotgun.png");
}
