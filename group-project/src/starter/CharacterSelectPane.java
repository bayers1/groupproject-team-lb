package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class CharacterSelectPane extends GraphicsPane {
	private MainApplication program;
	
	private GImage Fire;
	private GImage Water;
	private GImage Earth;
	private GImage Wind;
	
	public CharacterSelectPane(MainApplication app) {
		super();
		program = app;
		Fire = new GImage("fire1.jpg", 200, 300);
		
	}

	@Override
	public void showContents() {
		program.add(Fire);
	}

	@Override
	public void hideContents() {
	}
}


