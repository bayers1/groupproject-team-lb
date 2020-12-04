package starter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;


public class SettingsPane extends GraphicsPane {
	private MainApplication program;
	
	private GImage settingsBackground;
	private GButton Back, soundButtonOn, soundButtonOff, musicButtonOn, musicButtonOff;
	private GLabel soundLabel, musicLabel;
	private ArrayList<GButton> sButtons = new ArrayList<GButton>();
	
	public static final int FEATURE_INDENT = 300;
	public static final int BUTTON_INDENT = 780;
	
	public SettingsPane(MainApplication app) {
		super();
		program = app;
		
		settingsBackground = new GImage("settingsBackground.png",0,0);
		settingsBackground.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		settingsBackground.sendToBack();
		
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		
		soundLabel = new GLabel("Sound",300,200);
		soundLabel.setFont(new Font("elephant", Font.PLAIN,24));
		soundLabel.setColor(Color.black);
		
		musicLabel = new GLabel("Music",300,300);
		musicLabel.setFont(new Font("elephant", Font.PLAIN,24));
		musicLabel.setColor(Color.black);
			
		soundButtonOn = new GButton("ON",BUTTON_INDENT,165,50,50);
		musicButtonOn = new GButton("ON",BUTTON_INDENT,265,50,50);
		soundButtonOff = new GButton("OFF",BUTTON_INDENT,165,50,50);
		musicButtonOff = new GButton("OFF",BUTTON_INDENT,265,50,50);
		
		GButton[] buttons = {Back, soundButtonOff, musicButtonOff, soundButtonOn, musicButtonOn};
		for(GButton button:buttons) {
			sButtons.add(button);
		}
	}
	private void addingButtons() {
		if (program.getSound())program.add(soundButtonOn);
		else program.add(soundButtonOff);
		
		if (program.getMusic())program.add(musicButtonOn);
		else program.add(musicButtonOff);	
	}
	@Override
	public void showContents() {
		program.add(settingsBackground);
		program.add(Back);
		program.add(soundLabel);
		program.add(musicLabel);
		addingButtons();
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
		//soundButton
		else if(obj == soundButtonOff || obj == soundButtonOn){
			if(program.getSound()) {//checks the status of sound and turns on/off.
				program.stopSound(program.getSoundFiles()[3]);
				program.setSound(false);
				program.remove(soundButtonOn);
				program.add(soundButtonOff);
			}
			else {
				program.setSound(true);
				program.remove(soundButtonOff);
				program.add(soundButtonOn);
			}
		
		}
		//musicButton
		else if(obj == musicButtonOff || obj == musicButtonOn){
			if(program.getMusic()) {//checks the status of music.
				program.stopMusic(program.getSoundFiles()[2]);
				program.setMusic(false);
				program.remove(musicButtonOn);
				program.add(musicButtonOff);
			}
			else {
				program.setMusic(true);
				program.playMusic(program.getSoundFiles()[2],true);
				program.remove(musicButtonOff);
				program.add(musicButtonOn);
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		//for hovering over buttons.
		for(int i = 0;i < sButtons.size();i++) {
			notHovered(sButtons.get(i));
			if(obj == sButtons.get(i)) {
			hover(sButtons.get(i));
			}
		}
	}
}
