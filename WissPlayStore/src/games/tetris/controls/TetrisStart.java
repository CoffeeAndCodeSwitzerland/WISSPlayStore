package games.tetris.controls;

import processing.core.PApplet;

public class TetrisStart implements Runnable{
	/*
	public static void main(String[] args) {
		PApplet.main("games.tetris.controls.Tetris");
	}*/

	public static void myMain() {
		PApplet.main("games.tetris.controls.Tetris");
	}

	@Override
	public void run() {
		myMain();
	}
}
