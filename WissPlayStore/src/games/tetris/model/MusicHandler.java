
//please ignore we only used this for 1 sound not important at all

package games.tetris.model;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import games.tetris.controls.Tetris;
import processing.core.PApplet;

public class MusicHandler {
	private PApplet parent;
	private ArrayList<String> thiccMusicc = new ArrayList<>();
	private Minim minim;
	private AudioPlayer song;

	private int songs = 1;

	public MusicHandler(Tetris fileHandler){
		parent = fileHandler;
		minim = new Minim(fileHandler);
		initArrayList();
	}
	private void initArrayList(){
		thiccMusicc.add("games/tetris/data/xp.mp3");
		//add your music here :3
	}

	//plays a random song
	public void play(){
		int rnd = (int) parent.random(1, songs);
		song = minim.loadFile(thiccMusicc.get(rnd));
		parent.delay(1000);
		try {
			song.play();
		} catch(NullPointerException ignored) {

		}

	}
	//plays the song, matching the given index. if the index doesn't exist, skip to a random song
	public void play(int index){
		if (index > songs || index < 0){
			song.pause();
			play();
		} else {
			song = minim.loadFile(thiccMusicc.get(index));
			song.play();

		}
	}
	public boolean isPlaying(){
		return song.isPlaying();
	}
	public boolean isSongOfDeadlySin(){
		return song.getMetaData().fileName().equalsIgnoreCase("nope.mp3");
	}
}
