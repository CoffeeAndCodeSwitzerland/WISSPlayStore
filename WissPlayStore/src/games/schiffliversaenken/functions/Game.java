/**
 * Game creator
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	17.04.2018
 */

package games.schiffliversaenken.functions;

import games.schiffliversaenken.view.GameView;
import processing.core.PApplet;

public class Game extends PApplet {

	GameControler myGameController;
	GameView myGameView;
	Variables myVar;
	int state = 0;
	String result = "";

    // Processing
    public void settings() {
    	size(1600,900);
		if (surface != null)
			surface.setResizable(true);
    }
    
    public void setup() {
    	myVar = new Variables(width,height);
        myGameController = new GameControler(width,height);
		myGameView = new GameView(this, myGameController.getPlayerFields(1), myGameController.getPlayerFields(2));
    }

    public void draw(){
    	myGameView.setBackgroundLight();
     	myVar = new Variables(width,height);
        myGameController.draw();
    	myGameView.show(myVar, myGameController.activePlayer);
    	switch(state) {
    	case 3:
    		break;
    	case 0:
    		fill(0);
    		textAlign(CENTER);
    		text ("Enter IP from Server\n"+result, (float)(width*0.5),(float)(height*0.05));
    		break;
    	case 2:
    		fill(255, 2, 2); 
    		textAlign(CENTER);
    	    text ("Thanks \n"+result, 133, 633); 
    	    break;
    	}
    }
    

    public void mousePressed(){
        if (myGameController.winningPlayer() == false) {
        	myGameController.buttonClicked(this.mouseX,this.mouseY,myVar, myGameView);
        	}
    }
    
    public void keyPressed() {
    	if (key==ENTER||key==RETURN) {
    		 
    	    state++;
    	    myGameController.friendsIP = result;
    	  } else
    	  result = result + key;
		switch (key) {
		case 'r': 
			if(myGameController.restartGame == true) {
				restartGame();
			}
			break;
		case 's': 
			myGameController.startServer();
			break;
		case 'c':
			myGameController.startClient();
			break;
		}
    }
    
	public void restartGame() {
		setup();
	}
}
