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
	
	private GButton play;
	private GButton settings;
	private GButton howToPlay;
	private GButton exitGame;
	GButton button;
	private ArrayList<GButton> gButtons = new ArrayList<GButton>();
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
	}

	@Override
	public void showContents() {
		program.add(play);
		program.add(settings);
		program.add(howToPlay);
		program.add(exitGame);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) {
			program.switchToPlay();
		}
		else if (obj == settings) {
			program.switchToSettings();
		}
		else if (obj == howToPlay) {
			program.switchToHowToPlay();
		}
		else if(obj == exitGame) {
			System.exit(0);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		boolean buttonHover = false;
		if(!buttonHover) {
			notHovered(play);
			notHovered(settings);
			notHovered(howToPlay);
			notHovered(exitGame);
		}
		
		if(obj == play) {
			hover(play);
		}
		else if(obj == settings) {
			hover(settings);
		}
		else if(obj == howToPlay) {
			hover(howToPlay);
		}
		else if(obj == exitGame) {
			hover(exitGame);
		}
			buttonHover = true;
		
	}
}
