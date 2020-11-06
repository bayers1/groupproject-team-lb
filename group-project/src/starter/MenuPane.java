package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton play;
	private GButton settings;
	private GButton howToPlay;
	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		play = new GButton("Play", CENTER - 50, 150, 100, 80);
		play.setFillColor(Color.RED);
		settings = new GButton("Settings",CENTER - 50, 240, 100, 80);
		settings.setFillColor(Color.green);
		howToPlay = new GButton("How to Play",CENTER - 50, 330, 100, 80);
		howToPlay.setFillColor(Color.yellow);
		
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
			program.switchToCharSelect();
		}
	}
}
