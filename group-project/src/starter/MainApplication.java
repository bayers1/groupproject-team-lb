package starter;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3","Dark-theme.mp3","gameover.wav"};
	//private static final String[] Characters = {"Fire.jpg","Water.jpg","Earth.jpg","Wind.jpg"};

	private PlayPane playPane;
	private MenuPane menu;
	private int count;
	private SettingsPane settingPane;
	private HowTOPlayPane howtoplayPane;
	//private GImage img1;
	private AudioPlayer audio;
	static String file;
	private boolean isSoundOn = true;
	private boolean isMusicOn = true;
	//private GButton button;
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		audio = AudioPlayer.getInstance();
		menu = new MenuPane(this);
		playPane = new PlayPane(this);
		settingPane = new SettingsPane(this);
		howtoplayPane = new HowTOPlayPane(this);
		switchToMenu();
		
	}

	public void switchToMenu() {
		//playSound("r2d2.mp3",true);
		playMusic("Dark-theme.mp3",true);
		count++;
		switchToScreen(menu);
	}
	
	public void switchToPlay() {
		switchToScreen(playPane);
	}
	
	public void switchToSettings() {
		switchToScreen(settingPane);
	}
	public void switchToHowToPlay() {
		switchToScreen(howtoplayPane);
	}
	private void playRandomSound() {
		
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	public void stopSound(String fileName) {
		audio.stopSound(MUSIC_FOLDER,fileName);
	}
	public void playSound(String fileName,boolean loop) {
		if(isSoundOn) {
		audio.playSound(MUSIC_FOLDER,fileName,loop);
		}
	}
	/*
	private void playRandomMusic() {
		audio.playMusic(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	*/
	public void setSound(boolean turnOn) {
		isSoundOn = turnOn;
		
	}
	public boolean getSound() {
		return isSoundOn;
	}
	public void stopMusic(String fileName) {
		audio.stopMusic(MUSIC_FOLDER,fileName);//already here just using them for functionality.
	}
	public void playMusic(String fileName,boolean loop) {
		if(isMusicOn) {
			audio.playMusic(MUSIC_FOLDER,fileName,loop);
		}
	}
	public void setMusic(boolean turnOn) {
		isMusicOn = turnOn;
	}
	public boolean getMusic() {
		return isMusicOn;
	}
	public String[] getSoundFiles(){
		return  SOUND_FILES;
	}
}
