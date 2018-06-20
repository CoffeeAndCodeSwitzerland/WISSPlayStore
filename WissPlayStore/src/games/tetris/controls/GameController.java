package games.tetris.controls;

import java.io.IOException;

import games.tetris.model.Position;
import games.tetris.view.Interface;
import games.tetris.view.Score;
import processing.core.PApplet;

class GameController{
	//Classes
	private PApplet parent;
	private Interface iFace;
	Position pos;
	//Variables
	int rowsCleared;
	int score = 0;
	int level;
	private boolean gameover;

	//Functions
	GameController (int myWidth, int myHeight, PApplet p) {
		parent = p;
		pos = new Position(parent);
		try {iFace = new Interface(myWidth, myHeight, parent); } catch(IOException ignored) {}
		pos.mySetup();
		level = 0;

	}

	/**
	 * Moves the block 1 position unit down
	 * @return
	 */
	boolean dropOne() {
		if (pos.canMoveShape(0,1)) move(0,1); 
		else {
			makeNewShape();
		}
		return gameover;
	}

	/**
	 * moves the shape in the direction set with ze parameters
	 * @param xMove
	 * @param yMove
	 */
	void move(int xMove, int yMove) {
		pos.move(xMove, yMove);
		showInterface();
	}

	//Rotates the Shape clockwise
	void rotateShape() {
		pos.rotateShape();
		showInterface(); 
	}

	/**
	 * Changes the speed of the game depending on the level
	 * @return
	 */
	int getTick() {
		switch (level) {
		case 0: return 48;
		case 1: return 43;
		case 2: return 38;
		case 3: return 33;
		case 4: return 28;
		case 5: return 23;
		case 6: return 18;
		case 7: return 13;
		case 8: return 8;
		case 9: return 6;
		case 10: return 5;
		case 13: return 4;
		case 16: return 3;
		case 19: return 2;
		case 29: return 1;
		default: return 0; 
		}
	}

	/**
	 * Changes the Level depending on the rows cleared
	 */
	private void updateLevel() {
		level = rowsCleared/10;
	}

	/**
	 * to lazy to write IFace.... so I made this func
	 */
	private void showInterface() {
		iFace.show(score,pos.getPosition(), level, rowsCleared, pos.getArrayNextShape());
	}

	/**
	 * Gets a new shape when the previous got to the bottom and died #dedAF lol #metHarambeInHeaven
	 */
	void makeNewShape () {
		pos.makeNewShape();
		int tempRows = pos.clearRow();
		pos.setRotation(0);
		score += iFace.calculateScore(tempRows, level);
		rowsCleared += tempRows;
		gameover = !pos.canMoveShape(0,0);
		showInterface();
		updateLevel();
	}
	
	/**
	 * Refreshes the size of some variables in ze Interface
	 * @param myWidth
	 * @param myHeight
	 * @throws IOException
	 */
	void resize (int myWidth,int myHeight) throws IOException {
		iFace = new Interface(myWidth, myHeight, parent);
	}

	/**
	 * Saves the highscore in an .score file in the data folder or so
	 * @throws IOException
	 */
	void writeHighscore() throws IOException{
		Score score = iFace.getScoreObject();
		if (this.score > score.convertStringToInteger(score.readHighscore(parent))){
			score.writeHighscore(this.score, parent);
		}
	}
}
