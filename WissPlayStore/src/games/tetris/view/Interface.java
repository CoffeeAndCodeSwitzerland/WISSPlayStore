package games.tetris.view;

import java.io.IOException;

import processing.core.PApplet;

public class Interface {

	//classes
	private PApplet parent;
	private BlockGrid bGrid;
	private Score score;
	private NextUnit nUnit;
	//Variables
	private int marginLeft;
	private int marginTop;
	private int scale;
	//Functions

	/*
	 * Sets more variables depending on other variables
	 */
	public Interface (int myWidth, int myHeight, PApplet p) throws IOException{
		parent = p;
		if (myWidth < myHeight) scale = myWidth/21;
		else scale = (myHeight/20);
		marginLeft = (myWidth-(21*scale))/2;
		marginTop = (myHeight-(20*scale))/2;
		score = new Score(scale, parent);
		bGrid = new BlockGrid(marginLeft, marginTop, scale, parent);
		nUnit = new NextUnit(scale, parent);
	}

	/**
	 * Draws everything
	 * @param points For displaying the current points
	 * @param position For knowing where to draw
	 * @param level again for displaying
	 * @param rows and once again for displaying
	 * @param arrayNextUnit once more for displaying
	 */
	public void show (int points,int[][] position, int level, int rows, boolean[][] arrayNextUnit) {
		parent.background(0);
		bGrid.show(position, level); 
		score.show(marginLeft + 11*scale,marginTop,points, level, rows, parent);
		nUnit.show(marginLeft + 11*scale,marginTop + 10*scale, arrayNextUnit);
	}

	/**
	 * Bridge for calculateScrore from the class score to GameController
	 * @param rows
	 * @param level
	 * @return
	 */
	public int calculateScore(int rows, int level) { 
		return score.calculateScore(rows, level); 
	}

	/**
	 * gives back score
	 * @return
	 */
	public Score getScoreObject(){
		return this.score;
	}

}
