package games.tetris.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import processing.core.PApplet;

public class Score {
	private int size;
	private int highscore;

	Score(int scale, PApplet p) throws IOException {
		size = scale;
		highscore = convertStringToInteger(readHighscore(p));
	}

	/**
	 * Shows the score, level and rows cleared
	 * @param xPos
	 * @param yPos
	 * @param score
	 * @param level
	 * @param rows
	 * @param parent
	 */
	void show(int xPos, int yPos, int score, int level, int rows, PApplet parent) {
		parent.fill(255);
		parent.rect(xPos, yPos, 10*size, 5*size, size/4);
		parent.fill(0);
		parent.textSize(size-size/4);
		parent.text(" Score: " + score +
				"\n Level: " + level +
				"\n Row: " + rows +
				"\n Highscore: " + highscore,
				xPos, yPos + size);
	}
	/**
	 * Calculates the score depending on the rows cleared
	 * @param rows
	 * @param level
	 * @return
	 */
	int calculateScore(int rows, int level){   
		switch(rows){
		case 1:
			return 40 * (level +1);
		case 2:
			return 100 * (level + 1); 
		case 3:
			return 300 * (level +1);
		case 4:
			return 1200 * (level + 1);
		}
		return 0;
	}

	/**
	 * converts String to integer if you can't read the name of the function
	 * @param input
	 * @return
	 */
	public int convertStringToInteger(String input){
		if (input != null)
			return Integer.parseInt(input);

		return 0;
	}

	/**
	 * read z highest score ever reached from a file named High.score
	 * @param parent
	 * @return
	 * @throws IOException
	 */
	public String readHighscore(PApplet parent) throws IOException{
		BufferedReader input = parent.createReader("games/tetris/data/High.score");
		return input.readLine();
	}

	/**
	 * Writes the highest score ever reached in a file named High.score
	 * @param score
	 * @param parent
	 */
	public void writeHighscore(int score, PApplet parent) {
		PrintWriter output = parent.createWriter("games/tetris/data/High.score");
		output.println(PApplet.str(score));
		output.flush();
		output.close();
	}

}
