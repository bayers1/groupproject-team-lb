package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton rect;
	private GButton rect2;
	private GButton rect3;
	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Play", 580, 150, 100, 80);
		rect.setFillColor(Color.RED);
		rect2 = new GButton("Settings",580, 240, 100, 80);
		rect2.setFillColor(Color.green);
		rect3 = new GButton("How to Play",580, 330, 100, 80);
		rect3.setFillColor(Color.yellow);
		
	}

	@Override
	public void showContents() {
		program.add(rect);
		program.add(rect2);
		program.add(rect3);
	
	}

	@Override
	public void hideContents() {
		program.remove(rect);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToPlay();
		}
	}
}
