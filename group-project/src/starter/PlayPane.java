package starter;

	import java.awt.event.MouseEvent;

	import acm.graphics.GImage;
	import acm.graphics.GObject;

	public class PlayPane extends GraphicsPane {
		private MainApplication program; // you will use program to get access to
											// all of the GraphicsProgram calls

		public PlayPane(MainApplication app) {
			this.program = app;

		}
		
		public void createPlayer() {
			
		}

		@Override
		public void showContents() {

		}

		@Override
		public void hideContents() {

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



