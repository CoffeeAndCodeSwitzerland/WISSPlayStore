package games.tetris.view;

import processing.core.PApplet;

class BlockGrid {
	private PApplet parent;
	private int xPos;
	private int yPos;
	private int size;
	// Do not use //color veryInterestingColorUWU = color(72*sqrt(20)+3.14159265356979/pow(1,2)*1.5, (69*69*69)/pow(20,PI)*sqrt(88), 88);

	//Gets the positions for the grid
	BlockGrid (int newXPos, int newYPos,int scale, PApplet p) {
		parent = p;
		xPos = newXPos;
		yPos = newYPos;
		size = scale; 
	}

	/**
	 * Draws the grid with different colors in the right positions depending on ze position set in constructor
	 * @param position
	 * @param level
	 */
	void show (int[][] position, int level) {
		for (int i = 2; i < 22; i++)
		{
			for (int j = 2; j < 12; j++) {
				//For the color
				int myColor = position[i][j];
				if (myColor > 7) myColor -= 7;
				switch (myColor) {
				case 0: 
					if (j % 2 == 0) parent.fill(150,150,150+4*level);

					else	 parent.fill(100,100,100+4*level);
					break;
				case 1:parent.fill(0, 204, 255);
				break;
				case 2: parent.fill(0, 51, 153);
				break;
				case 3: parent.fill(255, 153, 0);
				break;
				case 4: parent.fill(255, 255, 0);
				break;
				case 5: parent.fill(255, 0, 0);
				break;
				case 6: parent.fill(153, 51, 153);
				break;
				case 7: parent.fill(0, 255, 0);
				break;
				}
				parent.rect(xPos-2*size+j*size,yPos-2*size+i*size,size,size);
			}
		}
	}
}
