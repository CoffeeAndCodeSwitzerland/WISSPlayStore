package games.tetris.model;

import processing.core.PApplet;

public class GameOver {

	/**
	 * Shows a gameoverscreen
	 * @param gameoverText
	 * @param p
	 * @param score
	 */
	public void show(String gameoverText, PApplet p, int score) {
		p.background(0);
		p.fill(255);
		p.textSize(p.height/10);
		p.text(gameoverText + score, 0, p.height/6);
		p.text("New Game",0, p.height/2);
		p.text("How to play",0, p.height - p.height/6);
	}



}
