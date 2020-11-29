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
	private GButton Back,OnOff,OnOff1;
	private GLabel soundLabel;
	private GLabel musicLabel;
	private ArrayList<GButton> sButtons = new ArrayList<GButton>();
	public SettingsPane(MainApplication app) {
		super();
		Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		program = app;
		soundLabel = new GLabel("Sound",150,200);
		soundLabel.setFont(new Font("Ariel", Font.PLAIN,25));
		soundLabel.setColor(Color.black);
		musicLabel = new GLabel("Music",150,400);
		musicLabel.setFont(new Font("Ariel", Font.PLAIN,25));
		musicLabel.setColor(Color.black);
		OnOff = new GButton("ON/OFF",800,165,50,50);
		OnOff.setColor(Color.BLUE);
		OnOff1 =new GButton("ON/OFF",800,365,50,50);
		OnOff1.setColor(Color.BLUE);
		GButton[] buttons = {Back,OnOff,OnOff1};
		for(GButton button:buttons) {
			sButtons.add(button);
		}
	}
	@Override
	public void showContents() {
		program.add(soundLabel);
		program.add(musicLabel);
		for(GButton button:sButtons) {
			program.add(button);
		}
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
		else if(obj == sButtons.get(1)){
			if(program.getSound()) {//checks the status of sound and turns on/off.
				program.stopSound(program.getSoundFiles()[3]);
				program.setSound(false);
			}
			else {
				program.setSound(true);
				//program.playSound("r2d2.mp3",true);
			}
		
		}
		else if(obj == sButtons.get(2)){
			if(program.getMusic()) {//checks the status of music.
				program.stopMusic(program.getSoundFiles()[2]);
				program.setMusic(false);
			}
			else {
				program.setMusic(true);
				program.playMusic(program.getSoundFiles()[2],true);
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
	}
}
