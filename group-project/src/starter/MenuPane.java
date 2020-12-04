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
	
	private GImage menuBackground;
	private ArrayList<GButton> mButtons = new ArrayList<GButton>();
	private String[] gButtonStrings = {" P L A Y ","  SETTINGS  ","HOW TO PLAY","E X I T"};
	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		menuBackground = new GImage("menuBackground.png", 0, 0);
		menuBackground.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		menuBackground.sendToBack();
		//creating the main buttons for the menu
		for(int i = 0;i<4;i++) {
			GButton button = new GButton(gButtonStrings[i], CENTER - (MENU_BUTTON_WIDTH/2), START_POS+(i*MENU_BUTTON_HEIGHT)+(i*REG_PADDING), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
			button.setFillColor(BUTTON_COLOR);
			mButtons.add(button);
		}
	}
	@Override
	public void showContents() {
		program.add(menuBackground);
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
		
		//for hovering over buttons.
		for(int i = 0;i < mButtons.size();i++) {
			notHovered(mButtons.get(i));
			if(obj == mButtons.get(i)) hover(mButtons.get(i));
		}
	}
	
}