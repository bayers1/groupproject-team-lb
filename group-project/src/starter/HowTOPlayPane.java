package starter;

import java.awt.event.MouseEvent;
import java.awt.Font;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class HowTOPlayPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back;
	public static final int START_X = 100;
	public static final int START_Y = 200;
	public static final int PANE_X = 320;
	public static final int PANE_Y = 160;
	
	private GImage pane;
	private GImage title;
	private GImage left;
	private GImage right;
	private int page = 0;
	public HowTOPlayPane(MainApplication app) {
		super();
		program = app;
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		pane = new GImage("pane1.png", PANE_X, PANE_Y);
		title = new GImage("howToPlay.png", 0, 0);
		left = new GImage("button_Left.png", 290, 300);
		right = new GImage("button_Right.png", 970, 300);
	}
		
	@Override
	public void showContents() {
		program.add(Back);
		program.add(pane);
		program.add(title);
		program.add(left);
		program.add(right);
	}

	@Override
	public void hideContents() {
		program.removeAll();
		//program.remove(Back);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == Back) {
			program.switchToMenu();
		}
		else if(obj == right) {
			page++;
		}
		else if(obj == left) {
			if(page == 0) page = 6;
			page--;
		}
		int pageNum = (page % 5) + 1;
		String panePage = "pane" + Integer.toString(pageNum)+ ".png";
		pane.setImage(panePage);
	}
}
