package games.suvse.game;

import games.suvse.functions.RaceMenu;
import games.suvse.player.Player;
import games.suvse.quests.main.Intro;

public class Game extends Basic {

	Player player;
	Intro intro = new Intro();
	RaceMenu raceM = new RaceMenu();

	public Game() {
		intro.introSequence(1, null, false);
		player = new Player(raceM.createCharacter());
		intro.introSequence(2, player.race, player.gender);
		while (true) {
			player.walk();
		}
	}
}
