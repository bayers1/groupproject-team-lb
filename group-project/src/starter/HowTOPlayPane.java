package starter;

import java.awt.event.MouseEvent;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class HowTOPlayPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back;
	private GLabel someLabel,someLabel1;
	
	public HowTOPlayPane(MainApplication app) {
		super();
		program = app;
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		
		someLabel = new GLabel("First hit the play button, which would take you to the character selection screen, and after that you will be taken to the actual game scene",60,100);
		someLabel.setFont(new Font("Ariel", Font.PLAIN,18));
		someLabel1 = new GLabel("In this game you just have to avoid obstacles and you will have power ups to assist, but it will keep getting difficult.",100,200);
		someLabel1.setFont(new Font("Ariel", Font.PLAIN,20));
		
	}
		
	@Override
	public void showContents() {
		program.add(Back);
		program.add(someLabel);
		program.add(someLabel1);
	}

	@Override
	public void hideContents() {
		program.remove(Back);
		program.remove(someLabel);
		program.remove(someLabel1);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == Back) {
			program.switchToMenu();
		}
	}
}
