package games.moorhuhn.controllers;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class MusicHandler extends PApplet{
	private Minim minim;
	public AudioPlayer player;
	private ArrayList<String> thiccSoundz = new ArrayList<>();
	

	public MusicHandler(Moorhuhn filehandler) {
		minim = new Minim(filehandler);
		initArrayList();
	}
	

	
	private void initArrayList(){
		thiccSoundz.add("games/moorhuhn/sounds/sndTheme.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndEmptyGun.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndExplosion.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndHitmarker.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndHuhn.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndHuhnDead.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndMenu.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndReload.wav");
		thiccSoundz.add("games/moorhuhn/sounds/sndShotgun.wav");
		//add your music here :3
		System.out.println(thiccSoundz.size());
	}
	
	public void playSound(String sound) {
		int soundNr = 0;
		switch (sound) {
		case "sndTheme":
			soundNr = 0;
			break;
		case "sndEmptyGun":
			soundNr = 1;
			break;
		case "sndExplosion":
			soundNr = 2;
			break;
		case "sndHitmarker":
			soundNr = 3;
			break;
		case "sndHuhn":
			soundNr = 4;
			break;
		case "sndHuhnDead":
			soundNr = 5;
			break;
		case "sndMenu":
			soundNr = 6;
			break;
		case "sndReload":
			soundNr = 7;
			break;
		case "sndShotgun":
			soundNr = 8;
			break;
		}
	player = minim.loadFile(thiccSoundz.get(soundNr));
	player.play();
	}
}
