package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program;
	
	public static final int MENU_BUTTON_HEIGHT = 60;
	public static final int MENU_BUTTON_WIDTH = 120;
	public static final int START_POS = 180;
	
	private GButton play;
	private GButton settings;
	private GButton howToPlay;
	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		//creating the main buttons for the menu
		play = new GButton("   Play   ", CENTER - (MENU_BUTTON_WIDTH/2), 
						   START_POS, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		play.setFillColor(Color.GRAY);
		
		settings = new GButton("Settings",CENTER - (MENU_BUTTON_WIDTH/2),
							   START_POS+MENU_BUTTON_HEIGHT+REG_PADDING, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		settings.setFillColor(Color.GRAY);
		
		howToPlay = new GButton("How to Play",CENTER - (MENU_BUTTON_WIDTH/2),
								START_POS+(2*MENU_BUTTON_HEIGHT)+(2*REG_PADDING), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		howToPlay.setFillColor(Color.GRAY);
	}

	@Override
	public void showContents() {
		program.add(play);
		program.add(settings);
		program.add(howToPlay);
	
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) {
			program.switchToCharSelect();
		}
		else if (obj == settings) {
			program.switchToSettings();
		}
		else if (obj == howToPlay) {
			program.switchToHowToPlay();
		}
	}
}
