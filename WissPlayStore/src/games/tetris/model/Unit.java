package games.tetris.model;

class Unit{
	  /**
	   * Gives the shape as boolean[4][4] from array shapes depending on the parameters
	   * @param shape
	   * @param rotation
	   * @return
	   */
	  boolean[][] getShape (int shape, int rotation) {
	    shape--;
	    boolean[][] newShape = new boolean[4][4];
	    for (int i = 0; i < 4; i++) {
			System.arraycopy(shapes[shape][rotation][i], 0, newShape[i], 0, 4);
	    }
	    return newShape;
	  }
	  
	 /**
	  * Array in which all units are and rotated.
	  * fu** all array i hate them just don't try to understand this it's a piece of shit
	  */
	 private boolean[][][][] shapes = {
	 //Shape I
	 //Turn 1
	 {{{false,false,true,false},
	 {false,false,true,false},
	 {false,false,true,false},
	 {false,false,true,false}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,false,false,false},
	 {true,true,true,true},
	 {false,false,false,false}},
	 //Turn 3
	 {{false,true,false,false},
	 {false,true,false,false},
	 {false,true,false,false},
	 {false,true,false,false}},
	 //Turn 4
	 {{false,false,false,false},
	 {true,true,true,true},
	 {false,false,false,false},
	 {false,false,false,false}}}, 
	 //Shape J
	 //Turn 1
	 {{{false,false,false,false},
	 {false,false,true,false},
	 {false,false,true,false},
	 {false,true,true,false}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,true,false,false},
	 {false,true,true,true},
	 {false,false,false,false}},
	 //Turn 3
	 {{false,false,false,false},
	 {false,false,true,true},
	 {false,false,true,false},
	 {false,false,true,false}},
	 //Turn 4
	 {{false,false,false,false},
	 {false,false,false,false},
	 {false,true,true,true},
	 {false,false,false,true}}},
	 //Shape L
	 //Turn 1
	 {{{false,false,false,false},
	 {false,false,true,false},
	 {false,false,true,false},
	 {false,false,true,true}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,false,false,false},
	 {false,true,true,true},
	 {false,true,false,false}},
	 //Turn 3
	 {{false,false,false,false},
	 {false,true,true,false},
	 {false,false,true,false},
	 {false,false,true,false}},
	 //Turn 4
	 {{false,false,false,false},
	 {false,false,false,true},
	 {false,true,true,true},
	 {false,false,false,false}}},
	 //Shape O
	 //Turn 1
	 {{{false,false,false,false},
	 {false,true,true,false},
	 {false,true,true,false},
	 {false,false,false,false}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,true,true,false},
	 {false,true,true,false},
	 {false,false,false,false}},
	 //Turn 3
	 {{false,false,false,false},
	 {false,true,true,false},
	 {false,true,true,false},
	 {false,false,false,false}},
	 //Turn 4
	 {{false,false,false,false},
	 {false,true,true,false},
	 {false,true,true,false},
	 {false,false,false,false}}},
	 //Shape S
	 //Turn 1
	 {{{false,false,false,false},
	 {false,false,true,true},
	 {false,true,true,false},
	 {false,false,false,false}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,false,true,false},
	 {false,false,true,true},
	 {false,false,false,true}},
	 //Turn 3
	 {{false,false,false,false},
	 {false,false,true,true},
	 {false,true,true,false},
	 {false,false,false,false}},
	 //Turn 4
	 {{false,false,false,false},
	 {false,false,true,false},
	 {false,false,true,true},
	 {false,false,false,true}}},
	 //Shape T
	 //Turn 1
	 {{{false,false,false,false},
	 {false,true,true,true},
	 {false,false,true,false},
	 {false,false,false,false}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,false,false,true},
	 {false,false,true,true},
	 {false,false,false,true}},
	 //Turn 3
	 {{false,false,false,false},
	 {false,false,false,false},
	 {false,false,true,false},
	 {false,true,true,true}},
	 //Turn 4
	 {{false,false,false,false},
	 {false,true,false,false},
	 {false,true,true,false},
	 {false,true,false,false}}},
	 //Shape Z
	 //Turn 1
	 {{{false,false,false,false},
	 {false,true,true,false},
	 {false,false,true,true},
	 {false,false,false,false}},
	 //Turn 2
	 {{false,false,false,false},
	 {false,false,false,true},
	 {false,false,true,true},
	 {false,false,true,false}},
	 //Turn 3
	 {{false,false,false,false},
	 {false,true,true,false},
	 {false,false,true,true},
	 {false,false,false,false}},
	 //Turn 4
	 {{false,false,false,false},
	 {false,false,false,true},
	 {false,false,true,true},
	 {false,false,true,false}}},
	  };
	  
	}
