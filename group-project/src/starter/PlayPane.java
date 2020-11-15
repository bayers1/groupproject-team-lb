package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

	public class PlayPane extends GraphicsPane {
		private MainApplication program; // you will use program to get access to
										 // all of the GraphicsProgram calls
		
		private GameSetUp gameSetUp;
		public static final int IMAGE_HEIGHT = 400;
		public static final int IMAGE_WIDTH = 250;
		public static final String IMG_EXTENSION = ".png";
		public double startX = 15;
		public double startY = 300;
		public double lastY = startY;
		private boolean selection = true;
		
		private GImage Fire;
		private GImage Water;
		private GImage Earth;
		private GImage Wind;
		private GLabel gameOver;
		private GImage character;
		private GImage powerUp;
		private GButton Back;
		
		GObject someObj;
		
		public PlayPane(MainApplication app) {
			super();
			program = app;
			Fire = new GImage("Fire.jpg", 120, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Water = new GImage("Water.jpg", 120 + IMAGE_WIDTH + REG_PADDING, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Earth = new GImage("Earth.jpg", 120 + (2*IMAGE_WIDTH) + (2*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Wind = new GImage("Wind.jpg", 120 + (3*IMAGE_WIDTH) + (3*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
			gameOver = new GLabel("You Lose", WINDOW_HEIGHT/2, WINDOW_WIDTH/2);
			
		}

		@Override
		public void showContents() {
			program.add(Fire);
			program.add(Water);
			program.add(Earth);
			program.add(Wind);
			
			program.add(Back);
		}

		/**
		 * This method is used to draw the player based on 
		 * the input from character selection 
		 */
		public void drawPlayer(PlayerType type) {
			String fileName = "";
			if (type == PlayerType.FIRE) {
				fileName += "fire";
			}
			else if (type == PlayerType.WATER) {
				fileName += "water";
			}
			else if (type == PlayerType.EARTH) {
				fileName += "earth";
			}
			else {
				fileName += "air";
			}
			fileName += "Dragon" + IMG_EXTENSION;
			
			character = new GImage(fileName, startX, startY);
			program.add(character);
		}
		
		public void drawObstacle() {
			//TODO:Display Obstacle
		}
		
		/**
		 * This method is used to draw a certain Power Up
		 * that will randomly appear as the player is
		 * in the game
		 * Will work on this later
		 */
		/*public void drawPowerUp(PowerUpType type) {
			String fileName = "";
			if (type == PowerUpType.MULTI) {
				fileName += "multi";
			}
			else if (type == PowerUpType.SLOW) {
				fileName += "slow";
			}
			else if (type == PowerUpType.BONUS) {
				fileName += "bonus";
			}
			else {
				fileName += "invul";
			}
			fileName += "" + IMG_EXTENSION;
			
			powerUp = new GImage(fileName, 700, 100);
			program.add(powerUp);
			
		}*/
		
		public void drawGame(PlayerType type) {
			gameSetUp = new GameSetUp(type);
			drawPlayer(type);
			System.out.println(gameSetUp.getPlayer());
			selection = false;
		}
		
		@Override
		public void hideContents() {
			program.removeAll();
		}
		
		/**
		 * hides the character selection view after player chooses
		 * character
		 */
		public void hideSelection() {
			program.remove(Fire);
			program.remove(Water);
			program.remove(Earth);
			program.remove(Wind);
			program.remove(Back);
		}
		
		/**
		 * Removes everything from Screen when player loses
		 * Displays score and highest as navigations on the screen
		 */
		public void gameOver() {
			program.removeAll();
			program.add(gameOver);
			//TODO: display scores as well as options to play again or go back to main menu
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			GObject obj = program.getElementAt(e.getX(), e.getY());
			someObj = obj;
			
			if (obj == Back) {
				program.switchToMenu();			
			}
			else if(someObj == Fire) {
				hideSelection();
				drawGame(PlayerType.FIRE);
			}
			else if(someObj == Water) {
				hideSelection();
				drawGame(PlayerType.WATER);
			}
			else if(someObj == Earth) {
				hideSelection();
				drawGame(PlayerType.EARTH);
			}
			else if(someObj == Wind) {
				hideSelection();
				drawGame(PlayerType.AIR);
			}
			else {
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			if(selection)return;
			
			if(gameSetUp.movePlayer(e.getY())) {
				character.move(0, e.getY() - lastY);
			}
			lastY = e.getY();
			gameSetUp.getPlayerLocation(e.getY());
			//System.out.println(gameSetUp.getPlayer().getY());
		}
	
	}



