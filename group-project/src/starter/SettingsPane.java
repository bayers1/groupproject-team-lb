package starter;

import java.awt.event.MouseEvent;
import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back;
	
	public SettingsPane(MainApplication app) {
		super();
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		program = app;
	}
		
	@Override
	public void showContents() {
		program.add(Back);
	}

	@Override
	public void hideContents() {
		program.remove(Back);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == Back) {
			program.switchToMenu();
		}
	}
	}
