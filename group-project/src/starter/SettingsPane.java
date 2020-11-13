package starter;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane {
	private MainApplication program;
	private GButton Back;
	private GLabel soundLabel;
	private GLabel musicLabel;
	private GButton OnOff;
	private GButton  OnOff1;
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
	
	}
		
	@Override
	public void showContents() {
		program.add(Back);
		program.add(soundLabel);
		program.add(musicLabel);
		program.add(OnOff);
		program.add(OnOff1);
	}

	@Override
	public void hideContents() {
		program.remove(Back);
		program.remove(soundLabel);
		program.remove(musicLabel);
		program.remove(OnOff);
		program.remove(OnOff1);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == Back) {
			program.switchToMenu();
		}
		else if(obj == OnOff) {
			if(program.getSound()) {//checks the status of sound and turns on/off.
				program.stopSound("r2d2.mp3");
				program.setSound(false);
			}
			else {
				program.setSound(true);
				program.playSound("r2d2.mp3",true);
			}
		
		}
		else if(obj == OnOff1) {
			if(program.getMusic()) {//checks the status of music.
				program.stopMusic("somethinlikethis.mp3");
				program.setMusic(false);
			}
			else {
				program.setMusic(true);
				program.playSound("somethinlikethis.mp3",true);
			}
		}
	}
	}
