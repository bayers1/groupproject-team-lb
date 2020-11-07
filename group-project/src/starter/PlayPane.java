package starter;

import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

	public class PlayPane extends GraphicsPane {
		private MainApplication program; // you will use program to get access to
										 // all of the GraphicsProgram calls
			
		public static final int IMAGE_HEIGHT = 400;
		public static final int IMAGE_WIDTH = 250;
		
		//private GLabel someLabel;
	
		public void setUpGame() {
			
		}
		public void createPlayer() {
			
		}
		public PlayPane(MainApplication app) {
			super();
			program = app;
		}

		@Override
		public void showContents() {
		//	program.add(someLabel);
		}

		@Override
		public void hideContents() {
			//program.remove(someLabel);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			GObject obj = program.getElementAt(e.getX(), e.getY());
			/*
			if (obj == img) {
				program.switchToMenu();
			}
			*/
		}
	}



