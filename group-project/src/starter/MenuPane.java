package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

public class MenuPane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	
	public static final int MENU_BUTTON_HEIGHT = 60;
	public static final int MENU_BUTTON_WIDTH = 140;
	public static final int START_POS = 180;
	
	private GImage menuBackground, backing;
	private ArrayList<GButton> mButtons = new ArrayList<GButton>();
	private String[] gButtonStrings = {" P L A Y ","  SETTINGS  ","HOW TO PLAY","E X I T"};

	private RandomGenerator rgen;
	private Timer backgroundTimer;
	private int bgChange = 0, backingVelocity = -5;
	private GRect darkOverlay;
	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		rgen = RandomGenerator.getInstance();
		
		backing = new GImage("fireBackground.png", -200, 0);
		backing.setSize(1600,600);
		
		menuBackground = new GImage("menuBackground.png", 0, 0);
		menuBackground.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		menuBackground.sendToBack();
		
		darkOverlay = new GRect(0,0,1280,600);
		darkOverlay.setFilled(true);
		Color overlayColor = new Color(0, 0, 0, 0.2f);
		darkOverlay.setFillColor(overlayColor);
		
		//creating the main buttons for the menu
		for(int i = 0;i<4;i++) {
			GButton button = new GButton(gButtonStrings[i],
										 CENTER - (MENU_BUTTON_WIDTH/2),
										 START_POS+(i*MENU_BUTTON_HEIGHT)+(i*REG_PADDING),
										 MENU_BUTTON_WIDTH,
										 MENU_BUTTON_HEIGHT);
			button.setFillColor(BUTTON_COLOR);
			mButtons.add(button);
		}
	}
	
	@Override
	public void showContents() {
		program.add(backing);
		program.add(darkOverlay);
		program.add(menuBackground);
		
		for(GButton button:mButtons) {
			program.add(button);
		}
		backgroundTimer = new Timer(100,this);
		backgroundTimer.start();
	}

	public void setBacking() {
		int num = rgen.nextInt(1, 40);
		String fileName = "";
		if (num < 16) {
			fileName += "fire";
		}
		
		else if (num < 20) {
			fileName += "water";
		}
		
		else if(num < 25) {
			fileName += "earth";
		}
		else {
			fileName += "air";
			
		}
		backing.setImage(fileName + "Background.png");
		backing.setSize(1600,600);
	}
	
	@Override
	public void hideContents() {
		program.removeAll();
		backgroundTimer.stop();
		bgChange = 0;
		backing.setLocation(-200, 0);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == mButtons.get(0)) {
			program.playSound("newbutton.wav",false);
			program.switchToPlay();
		}
		if (obj == mButtons.get(1)) {
			program.playSound("newbutton.wav",false);
			program.switchToSettings();
		}
		if (obj == mButtons.get(2)) {
			program.playSound("newbutton.wav",false);
			program.switchToHowToPlay();	
		}
		if(obj == mButtons.get(3)) {
			program.playSound("newbutton.wav",false);
			try {Thread.sleep(200);
			} catch (InterruptedException e1) {e1.printStackTrace();}
			System.exit(0);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		//for hovering over buttons.
		for(int i = 0;i < mButtons.size();i++) {
			notHovered(mButtons.get(i));
			if(obj == mButtons.get(i)) hover(mButtons.get(i));
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(bgChange % 160 == 0) {
			setBacking();
			bgChange = 0;
		}
		if(bgChange % 20 == 0) {
			backingVelocity *= -1;
		}
		
		backing.move(backingVelocity, 0);
		bgChange++;
	}
	
}