package starter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class SettingsPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back,OnOff,OnOff1;
	private GLabel soundLabel, musicLabel,soundStatus,musicStatus;
	private ArrayList<GButton> sButtons = new ArrayList<GButton>();
	private ArrayList<GLabel> slabels = new ArrayList<GLabel>();
	private GRect textBox = new GRect(280, 450, 570, 40);
	private GLabel statusLabel = new GLabel("Sound and Music are always on by default.", textBox.getX() + 20, textBox.getY() + 25);
	private boolean isSoundOff = false;
	private boolean isMusicOff = false;
	private GLabel settings = new GLabel("S E T T I N G S",430, 100);
	
	public SettingsPane(MainApplication app) {
		super();
		program = app;
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		soundLabel = new GLabel("Sound",280,200);
		soundLabel.setFont(new Font("Ariel", Font.PLAIN,25));
		soundLabel.setColor(Color.black);
		musicLabel = new GLabel("Music",280,400);
		musicLabel.setFont(new Font("Ariel", Font.PLAIN,25));
		musicLabel.setColor(Color.black);
		OnOff = new GButton("",800,165,50,50);
		OnOff.setColor(Color.BLUE);
		soundStatus = new GLabel("ON");
		soundStatus.setLocation(810,195);
		soundStatus.setFont(new Font("Ariel", Font.PLAIN,18));
		musicStatus = new GLabel("ON");
		musicStatus.setLocation(810, 395);
		musicStatus.setFont(new Font("Ariel", Font.PLAIN,18));
		OnOff1 =new GButton("",800,365,50,50);
		OnOff1.setColor(Color.BLUE);
		GLabel[] labels = {soundLabel,musicLabel,soundStatus,musicStatus};
		GButton[] buttons = {Back,OnOff,OnOff1};
		for(GButton button:buttons) {
			sButtons.add(button);
		}
		for(GLabel label:labels) {
			slabels.add(label);
		}
		textBox.setFillColor(Color.yellow);
		textBox.setFilled(true);
		statusLabel.setFont(new Font("Ariel", Font.PLAIN,25));
		settings.setFont(new Font("Ariel", Font.PLAIN,40));
	}
	@Override
	public void showContents() {
		for(GButton button:sButtons) {
			program.add(button);
		}
		for(GLabel label:slabels) {
			program.add(label);
		}
		program.add(textBox);
		program.add(statusLabel);
		program.add(settings);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == sButtons.get(0)) {
			program.switchToMenu();
		}
		else if(obj == sButtons.get(1) || obj == slabels.get(2)){
			if(program.getSound()) {//checks the status of sound and turns on/off.
				program.stopSound(program.getSoundFiles()[3]);
				program.setSound(false);
				slabels.get(2).setLabel("OFF");
				isSoundOff = true;	
			}
			else {
				program.setSound(true);
				slabels.get(2).setLabel("ON");
				isSoundOff = false;
			}
		
		}
		else if(obj == sButtons.get(2)|| obj == slabels.get(3)){
			if(program.getMusic()) {//checks the status of music.
				program.stopMusic(program.getSoundFiles()[2]);
				program.setMusic(false);
				slabels.get(3).setLabel("OFF");
				isMusicOff = true;
			}
			else {
				program.setMusic(true);
				program.playMusic(program.getSoundFiles()[2],true);
				slabels.get(3).setLabel("ON");
				isMusicOff = false;
			}
			
		}
	
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		for(int i = 0;i < sButtons.size();i++) {
			notHovered(sButtons.get(i));
			if(obj == sButtons.get(i)) {
			hover(sButtons.get(i));
			}
	}
		if(obj == null) {
			statusLabel.setLabel("Sound and Music are always on by default.");
		}
		if(obj == sButtons.get(1) || obj == slabels.get(2)) {
			if(isSoundOff)statusLabel.setLabel("Game Sound is currently off.");
			else statusLabel.setLabel("Game Sound is currently on.");
		}
	   if(obj == sButtons.get(2) || obj == slabels.get(3)) {
		   	if(isMusicOff)statusLabel.setLabel("Game Music is currently off.");
			else statusLabel.setLabel("Game Music is currently on.");
		}
	}

	
}
