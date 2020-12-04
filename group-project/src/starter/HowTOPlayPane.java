package starter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class HowTOPlayPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back;
	public static final int START_X = 100;
	public static final int START_Y = 200;
	public static final int PANE_X = 320;
	public static final int PANE_Y = 160;
	
	private GImage htpBackground, pane, left, right;
	private ArrayList<GImage> htpFeatures = new ArrayList<GImage>();
	
	private int page = 0;
	public HowTOPlayPane(MainApplication app) {
		super();
		program = app;
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		notHovered(Back);
		
		htpBackground = new GImage("howToPlayBackground.png", 0, 0);
		htpBackground.setSize(1280, 600);
		pane = new GImage("pane1.png", PANE_X, PANE_Y);
		left = new GImage("button_Left.png", 290, 300);
		right = new GImage("button_Right.png", 970, 300);
		
		GImage[] features = {htpBackground, pane, left, right};
		for(GImage feature: features) {
			htpFeatures.add(feature);
		}
	}
		
	@Override
	public void showContents() {
		for(GImage feature: htpFeatures) {
			program.add(feature);
		}
		program.add(Back);
	}

	@Override
	public void hideContents() {
		program.removeAll();
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
	
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		boolean buttonHover = false;
		if(!buttonHover) {
			notHovered(Back);
		}
		if(obj == Back) {
			hover(Back);
		}
		buttonHover = true;	
	}
}
