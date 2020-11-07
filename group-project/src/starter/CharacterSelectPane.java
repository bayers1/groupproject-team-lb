package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class CharacterSelectPane extends GraphicsPane{
	private MainApplication program;
	
	public static final int IMAGE_HEIGHT = 400;
	public static final int IMAGE_WIDTH = 250;
	
	private GImage Fire;
	private GImage Water;
	private GImage Earth;
	private GImage Wind;
	
	private GButton Next;
	private GButton Back;
	private PlayPane playPane;
	
	GObject someObj;
	public CharacterSelectPane(MainApplication app) {
		super();
		program = app;
		Fire = new GImage("Fire.jpg", 120, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Water = new GImage("Water.jpg", 120 + IMAGE_WIDTH + REG_PADDING, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Earth = new GImage("Earth.jpg", 120 + (2*IMAGE_WIDTH) + (2*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Wind = new GImage("Wind.jpg", 120 + (3*IMAGE_WIDTH) + (3*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Next = new GButton("Next", RIGHT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		program.setBackground(Color.cyan);
	}

	@Override
	public void showContents() {
		program.add(Fire);
		program.add(Water);
		program.add(Earth);
		program.add(Wind);
		
		program.add(Next);
		program.add(Back);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		someObj = obj;
		if (obj == Next) {
			program.switchToPlay();
		}
		else if (obj == Back) {
			program.switchToMenu();			
		}
		else if(someObj == Fire) {
			program.switchToPlay();
		}
		else if(someObj == Water) {
			program.switchToPlay();
		}
		
		else if(someObj == Earth) {
			program.switchToPlay();
		}
		
		else if(someObj == Wind) {
			program.switchToPlay();
		}
		else {
		}
	}
}


