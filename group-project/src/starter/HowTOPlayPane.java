package starter;

import java.awt.event.MouseEvent;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class HowTOPlayPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back;
	public static final int START_X = 100;
	public static final int START_Y = 200;
	
	public HowTOPlayPane(MainApplication app) {
		super();
		program = app;
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
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
