package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class GameOverPane extends GraphicsPane {
	private MainApplication program;
	
	public static final int MENU_BUTTON_HEIGHT = 60;
	public static final int MENU_BUTTON_WIDTH = 140;
	public static final int START_POS = 180;
	
	private GButton mainMenu;

	public GameOverPane(MainApplication app) {
		super();
		program = app;
		
		mainMenu = new GButton("Main Menu", RIGHT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		mainMenu.setFillColor(BUTTON_COLOR);
	}

	@Override
	public void showContents() {
		program.add(mainMenu);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == mainMenu) {
			program.switchToMenu();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		boolean buttonHover = false;
		if(!buttonHover) {
			notHovered(mainMenu);
		}
		
		if(obj == mainMenu) {
			hover(mainMenu);
		}
		
		buttonHover = true;
		
	}
}
