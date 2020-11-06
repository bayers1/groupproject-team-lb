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
	
	private GButton Next;
	private GButton Back;
	
	public CharacterSelectPane(MainApplication app) {
		super();
		program = app;
		Fire = new GImage("fire1.jpg", 200, 300);
		Next = new GButton("Next", RIGHT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		//Next.setFillColor(Color.yellow);
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
	}

	@Override
	public void showContents() {
		program.add(Fire);
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
		if (obj == Next) {
			program.switchToPlay();
		}
		else if (obj == Back) {
			program.switchToMenu();
		}
	}
}


