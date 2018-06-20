package games.tetris.view;

import processing.core.PApplet;

public class NextUnit {
	private int size;
	private PApplet parent;

	NextUnit (int scale, PApplet p) {
		size = scale;
		parent = p;
	}

	/**
	 * Draws the rectangle with the next unit in it.
	 * @param xPos
	 * @param yPos
	 * @param nextShape
	 */
	void show(int xPos, int yPos, boolean[][] nextShape) {
		parent.fill(255);
		parent.rect(xPos, yPos, size*10, size*10, size/2);
		parent.fill(0);
		for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) {
			if (nextShape[i][j]) parent.rect(xPos+size+j*size*2,yPos+size+i*size*2,size*2,size*2);
		}
	}

}
