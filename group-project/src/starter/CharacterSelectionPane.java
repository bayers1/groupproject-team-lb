package starter;

import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class CharacterSelectionPane extends GraphicsPane {
	public static final int IMAGE_HEIGHT = 125;
	public static final int IMAGE_WIDTH = 250;
	
	private MainApplication program;
	private GImage Fire, Water, Earth, Air, background;
	private GButton Back;
	private GRect border;
	private GImage lastDragonSelection;
	private PlayerType playerType;
	
	public  CharacterSelectionPane(MainApplication app) {
		super();
		program = app;
		
		background = new GImage("selectionBackground.png", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		Fire = new GImage("fireDragon.png", 120, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Fire.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		Water = new GImage("waterDragon.png", 120 + IMAGE_WIDTH + REG_PADDING, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Water.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		Earth = new GImage("earthDragon.png", 120 + (2*IMAGE_WIDTH) + (2*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Earth.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		Air = new GImage("airDragon.png", 120 + (3*IMAGE_WIDTH) + (3*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
		Air.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
	}
	
	public void showContents() {
		program.add(background);
		program.add(Fire);
		program.add(Water);
		program.add(Earth);
		program.add(Air);
		program.add(Back);	
	}
	
	public void hideContents() {
		program.removeAll();
		if(border != null) {
			program.remove(border);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == Back) {
			program.playSound("newbutton.wav",false);
			program.switchToMenu();
		}
		else if(obj == Fire) {
			playerType = PlayerType.FIRE;
			program.switchToPlay(playerType);
		}
		else if(obj == Water) {
			playerType = PlayerType.WATER;
			program.switchToPlay(playerType);
		}
		else if(obj == Earth) {
			playerType = PlayerType.EARTH;
			program.switchToPlay(playerType);
		}
		else if(obj == Air) {
			playerType = PlayerType.AIR;
			program.switchToPlay(playerType);
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		boolean buttonHover = false;
		if(!buttonHover) {
			notHovered(Back);
		}
		if(obj == Back) {
			hover(Back);
		}
		
		if(obj==Fire) {
			hoverImage(Fire);
		}
		else if(obj == Water) {
			hoverImage(Water);
		}
		else if(obj == Air) {
			hoverImage(Air);
		}
		else if(obj == Earth) {
			hoverImage(Earth);
		}
		
		if(obj == null || obj != lastDragonSelection) {
			removeBorder();
		}
	
		buttonHover = true;	
	}
	
	private void removeBorder() {
		if(border != null) {
			program.remove(border);
			border = null;
			lastDragonSelection = null;
		}
	}

	private void hoverImage(GImage img) {
		if(border == null) {
			border = new GRect(img.getBounds().getX()-1,img.getBounds().getY()-1,img.getBounds().getWidth()+1,img.getBounds().getHeight()+1);
			program.add(border);
			img.sendToFront();
			lastDragonSelection = img;
		}
	}
	

}
