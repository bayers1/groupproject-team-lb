package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	GButton button;
	private ArrayList<GButton> gButtons = new ArrayList<GButton>();
	public MenuPane(MainApplication app) {
		super();
		program = app;
		/*
		for (int i = 0; i < 6; i++) {
			GButton button = new GButton(gbuttonStrings[i], 280, 235 + 55 * i, MainApplication.BUTTON_HEIGHT,
					MainApplication.BUTTON_WIDTH);
			button.setFillColor(Color.GREEN);
			gButtons.add(button);
		}
		*/
		//creating the main buttons for the menu
		play = new GButton("   Play   ", CENTER - (MENU_BUTTON_WIDTH/2), 
						   START_POS, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		play.setFillColor(Color.cyan);
		
		settings = new GButton("Settings",CENTER - (MENU_BUTTON_WIDTH/2),
							   START_POS+MENU_BUTTON_HEIGHT+REG_PADDING, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		settings.setFillColor(Color.cyan);
		
		howToPlay = new GButton("How to Play",CENTER - (MENU_BUTTON_WIDTH/2),
								START_POS+(2*MENU_BUTTON_HEIGHT)+(2*REG_PADDING), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		howToPlay.setFillColor(Color.cyan);
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
			program.switchToPlay();
		}
		else if (obj == settings) {
			program.switchToSettings();
		}
		else if (obj == howToPlay) {
			program.switchToHowToPlay();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		Color highlightColor = new Color(0,220,0);
		boolean something = true;
		if(something) {
			play.setFillColor(Color.cyan);
			settings.setFillColor(Color.cyan);
			howToPlay.setFillColor(Color.cyan);
		}
			if(obj == play) {
			play.setFillColor(highlightColor);
			something = false;
		}
			else if(obj == settings) {
			settings.setFillColor(highlightColor);
			something = false;
		}
			else if(obj == howToPlay) {
			howToPlay.setFillColor(highlightColor);
			something = false;
		}
	
	}
}
