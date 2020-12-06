package starter;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = {"button.wav", "newbutton.wav", "music.mp3","gameover.wav"};

	//panes
	private PlayPane playPane;
	private GameOverPane gameOverPane;
	private MenuPane menu;
	private SettingsPane settingPane;
	private HowTOPlayPane howtoplayPane;
	private CharacterSelectionPane characterSelect;
	
	private AudioPlayer audio;
	static String file;
	private boolean isSoundOn = true;
	private boolean isMusicOn = true;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		audio = AudioPlayer.getInstance();
		menu = new MenuPane(this);
		settingPane = new SettingsPane(this);
		howtoplayPane = new HowTOPlayPane(this);
		characterSelect = new CharacterSelectionPane(this);
		switchToMenu();
		
	}

	public void switchToMenu() {
		playMusic("music.mp3",true);
		switchToScreen(menu);
	}
	
	public void switchToPlay(PlayerType playerType) {
		playPane = new PlayPane(this,playerType);
		switchToScreen(playPane);
	}
	
	public void switchToSettings() {
		switchToScreen(settingPane);
	}
	
	public void switchToHowToPlay() {
		switchToScreen(howtoplayPane);
	}
	
	public void switchToGameOver(int score) {
		gameOverPane = new GameOverPane(this,score);
		switchToScreen(gameOverPane);
	}
	public void switchToCharacterSelection() {
		switchToScreen(characterSelect);
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
