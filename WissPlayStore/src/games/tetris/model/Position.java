package games.tetris.model;

import processing.core.PApplet;

public class Position{
	//objects
	private Unit unit = new Unit();
	private PApplet parent;

	//fields
	private int[][] position = new int[24][14];
	private int rotation = 0;
	private boolean[][] arrayNextShape;

	private int startY = 0;
	private int startX = 5;
	private int shapeYPos = 2;
	private int shapeXPos = 5;
	private int currentShape;
	private int nextShape;
	private boolean[][] shape;

	/**
	 *  Constructor
	 * @param p for processing
	 */
	public Position(PApplet p) {
		parent = p;
		currentShape = (int)parent.random(1,8);
		nextShape = (int)parent.random(1,8);
	}

	/**
	 * Does some calculation and math and meth for ze game
	 */
	public void mySetup () {
		shape = unit.getShape(currentShape,getRotation()); 
		//Makes "border" in the array
		for (int i = 22; i < 24; i++) {
			for (int j = 0; j < 14; j++)
			{getPosition()[i][j] = 1;}
		}
		for (int i = 0; i < 22; i++){
			for (int j = 0; j < 2; j++){
				getPosition()[i][j] = 1;
				getPosition()[i][j+12] = 1;
			}
		}
		//sets some variables
		shapeYPos = startY;
		shapeXPos = startX;
		setArrayNextShape(unit.getShape(nextShape, getRotation()));
	}

	//Functions
	/**
	 * Checks if the shape can go to the position
	 * @param xMove
	 * @param yMove
	 * @return
	 */
	public boolean canMoveShape(int xMove, int yMove) {
		boolean canMove = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j]) {
					if (getPosition()[shapeYPos+yMove+i][shapeXPos+xMove+j] < 8 && getPosition()[shapeYPos+yMove+i][shapeXPos+xMove+j] != 0) {
						j = 4;
						i = 4;
						canMove = false;
					}
				}
			}
		}
		return canMove;
	}

	/**
	 * Places the shape in the array position
	 * @param myShape
	 */
	private void placeShape(int myShape) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j]) getPosition()[shapeYPos+i][shapeXPos+j] = myShape;
			}
		}
	}

	/**
	 * Moves the shape in the direction specified with the parameters
	 * @param xMove
	 * @param yMove
	 */
	public void move(int xMove, int yMove) {
		shape = unit.getShape(currentShape,getRotation());
		placeShape(0);
		if (canMoveShape(xMove,yMove)) {
			shapeXPos += xMove;
			shapeYPos += yMove;
		}
		placeShape(currentShape+7);
	}

	/**
	 * Rotates the shape
	 */
	public void rotateShape() {
		placeShape(0);
		setRotation(getRotation() + 1);
		if (getRotation() > 3) setRotation(0);
		shape = unit.getShape(currentShape,getRotation());
		if (!canMoveShape(0,0)){
			setRotation(getRotation() - 1);
			if (getRotation() < 0) setRotation(3);
		}
		shape = unit.getShape(currentShape,getRotation());
		placeShape(currentShape+7);
	}

	/**
	 * Clears full rows and gives back the number of cleared rows
	 * @return
	 */
	public int clearRow () {
		int rows = 0;
		for (int i = 21; i > 2; i--)
		{
			int temp = 0;
			for (int j = 2; j < 12; j++) {
				if (getPosition()[i][j] != 0) temp++;
			}
			if (temp == 10) {
				rows++;
				for (int i2 = i; i2 > 2; i2--) {
					System.arraycopy(getPosition()[i2 - 1], 2, getPosition()[i2], 2, 10);
				}
				i++;
			}
		}
		return rows;
	}

	/**
	 * Changes some variables for a new shape
	 */
	public void makeNewShape() {
		placeShape(currentShape);
		shapeYPos = startY;
		shapeXPos = startX;

		currentShape = nextShape;
		nextShape = (int)parent.random(1,8);
		setArrayNextShape(unit.getShape(nextShape, getRotation())); 
	}

	//gettaz nd settaz
	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int[][] getPosition() {
		return position;
	}

	public void setPosition(int[][] position) {
		this.position = position;
	}

	public boolean[][] getArrayNextShape() {
		return arrayNextShape;
	}

	public void setArrayNextShape(boolean[][] arrayNextShape) {
		this.arrayNextShape = arrayNextShape;
	}


}
