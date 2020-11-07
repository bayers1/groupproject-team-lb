package starter;

import java.awt.Image;
import java.util.ArrayList;

import acm.graphics.GImage;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };
	//private static final String[] Characters = {"Fire.jpg","Water.jpg","Earth.jpg","Wind.jpg"};

	private PlayPane playPane;
	private MenuPane menu;
	private CharacterSelectPane charPane;
	private int count;
	private SettingsPane settingPane;
	private HowTOPlayPane howtoplayPane;
	//private GImage img1;
	
	static String file;
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		menu = new MenuPane(this);
		charPane = new CharacterSelectPane(this);
		playPane = new PlayPane(this);
		settingPane = new SettingsPane(this);
		howtoplayPane = new HowTOPlayPane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		//playRandomSound();
		count++;
		switchToScreen(menu);
	}
	public void switchToCharSelect() {
		switchToScreen(charPane);
		
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
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}

	

	
}
