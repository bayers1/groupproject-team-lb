package starter;

import acm.graphics.GImage;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };

	private PlayPane playPane;
	private MenuPane menu;
	private CharacterSelectPane charPane;
	private SettingsPane settingsPane;
	private int count;
	//private GImage img1;
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		
	}

	public void run() {
		//img1 = new GImage("menu_bg.jpg",1280,600);
		//img1.setVisible(true);
		//add(img1);
		System.out.println("Hello, world!");
		playPane = new PlayPane(this);
		menu = new MenuPane(this);
		charPane = new CharacterSelectPane(this);
		settingsPane = new SettingsPane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		//playRandomSound();
		count++;
		switchToScreen(menu);
	}

	public void switchToPlay() {
		switchToScreen(playPane);
	}
	
	public void switchToCharSelect() {
		switchToScreen(charPane);
	}
	
	public void switchToSettings() {
		switchToScreen(settingsPane);
		
	}
	public void switchToHowToPlay() {
		
	}
	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
}
