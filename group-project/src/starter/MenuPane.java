package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program;
	
	public static final int MENU_BUTTON_HEIGHT = 60;
	public static final int MENU_BUTTON_WIDTH = 140;
	public static final int START_POS = 180;
	
	private GButton play,settings, howToPlay, exitGame;
	private ArrayList<GButton> mButtons = new ArrayList<GButton>();
	public MenuPane(MainApplication app) {
		super();
		program = app;

		//creating the main buttons for the menu
		play = new GButton(" P L A Y ", CENTER - (MENU_BUTTON_WIDTH/2), 
						   START_POS, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		play.setFillColor(BUTTON_COLOR);
		
		settings = new GButton("  SETTINGS  ",CENTER - (MENU_BUTTON_WIDTH/2),
							   START_POS+MENU_BUTTON_HEIGHT+REG_PADDING, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		settings.setFillColor(BUTTON_COLOR);
		
		howToPlay = new GButton("HOW TO PLAY",CENTER - (MENU_BUTTON_WIDTH/2),
								START_POS+(2*MENU_BUTTON_HEIGHT)+(2*REG_PADDING), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		howToPlay.setFillColor(BUTTON_COLOR);
		
		exitGame = new GButton("E X I T",CENTER - (MENU_BUTTON_WIDTH/2),
				START_POS+(3*MENU_BUTTON_HEIGHT)+(3*REG_PADDING), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		exitGame.setFillColor(BUTTON_COLOR);
		
		GButton[] buttons = {play,settings,howToPlay,exitGame}; 
		for(GButton button:buttons) {
			mButtons.add(button);
		}
	}

	@Override
	public void showContents() {
		for(GButton button:mButtons) {
			program.add(button);
		}
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == mButtons.get(0)) {
			program.switchToPlay();
		}
		if (obj == mButtons.get(1)) {
			program.switchToSettings();
		}
		if (obj == mButtons.get(2)) {
			program.switchToHowToPlay();
		}
		if(obj == mButtons.get(3)) {
			System.exit(0);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		boolean buttonHover = false;
		if(!buttonHover) {
		for(int i = 0;i < mButtons.size();i++) {
			notHovered(mButtons.get(i));
		}
		}
		if(obj == mButtons.get(0)) {
			hover(mButtons.get(0));
		}
		if(obj == mButtons.get(1)) {
			hover(mButtons.get(1));
		}
		if(obj == mButtons.get(2)) {
			hover(mButtons.get(2));
		}
		if(obj == mButtons.get(3)) {
			hover(mButtons.get(3));
		}
		buttonHover = true;
		
	}
}
