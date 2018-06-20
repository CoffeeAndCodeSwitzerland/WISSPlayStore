package games.tetris.controls;

import java.io.IOException;

import games.tetris.model.GameOver;
import games.tetris.model.MusicHandler;
import processing.core.PApplet;

public class Tetris extends PApplet{

	//Fields
	private String gameoverText = "0_";
	private boolean gameover = true;
	private boolean tutorial = false;

	//Once every tick second updates the screen and moves the shape 1 down
	private int tick = 20;
	private int thicc = tick;

	//Objects
	private GameController game;
	private GameOver gOver = new GameOver();
	private MusicHandler music = new MusicHandler(this);

	//ArrayList for Music

	public void settings() {
		size(1200, 500);
	}

	public void setup() {
		game = new GameController(width, height, this);


		surface.setResizable(true);
		text("Loading",100,100);
		music.play(0);
		delay(1700);
	}

	/**
	 * Refreshes the screen with Coke when block automatically drops 1 down
	 */
	public void draw() {

		if (!gameover && thicc >= tick) {
			if (game.getTick() != 0) tick = game.getTick();
			gameover = game.dropOne();
			thicc = 0;
		}
		if (gameover) {
			if (!tutorial) {
				gOver.show(gameoverText, this, game.score);
				try{game.writeHighscore();} catch(IOException ignored){}
			}
			else {
				background(0);
				text("Its a fucking Tetris\nyou know how to play",0,height/3);
			}
		}
		thicc++;
	}

	/**
	 * for ze _xX_itz_controlz_pvp_hd_lp_us_uhd4kgetrekt_ineedalife_Xx_
	 */
	public void keyPressed() {
		switch (key) {
		case 'd': game.move(1,0);
		break;
		case 'a': game.move(-1,0);
		break;
		case 's': 
			if (!gameover) {
				game.move(0,1);
				game.score += game.level;
			}
			break;
		case 'w': game.rotateShape();
		break;
		case ' ': 
			while (game.pos.canMoveShape(0,1)) { 
				game.move(0,1); 
				game.score += (game.level+1)*1.5;
			}
			game.makeNewShape();
			break;
		case 'r': 
			restartGame();
			break;
		case '1': game.rowsCleared = 100;
		break;
		case '2': game.rowsCleared = 190;
		break;
		} 
	}

	/**
	 * thicc mousecontrolz
	 */
	public void mousePressed() {
		tutorial = false;
		if (mouseY > height - height/3) tutorial = true;
		else if (mouseY > height / 3) restartGame();
	}

	/**
	 * Restarts the game in the laziest way possible
	 */
	private void restartGame() {
		gameoverText = "GAMEOVER SCORE:";
		game = new GameController(width, height, this);
		try {game.resize(width, height); } catch(IOException ignored) {}
		gameover = false;
	}
}
