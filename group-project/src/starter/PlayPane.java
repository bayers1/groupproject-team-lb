package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.RandomGenerator;

	public class PlayPane extends GraphicsPane implements ActionListener {
		private MainApplication program; // you will use program to get access to
										 // all of the GraphicsProgram calls
		
		private GameSetUp gameSetUp;
		public static final int IMAGE_HEIGHT = 400;
		public static final int IMAGE_WIDTH = 250;
		public static final String IMG_EXTENSION = ".png";
		public double startX = 15;
		public double startY = 300;
		private boolean selection = true;
		
		private GImage Fire;
		private GImage Water;
		private GImage Earth;
		private GImage Wind;
		private GLabel gameOver;
		private GImage character;
		private GImage powerUp;
		private GButton Back;
		private Timer timer;
		GObject someObj;
		private int count = 0;
		private int max = 10;
		private ArrayList<GImage> obstacles;
		private RandomGenerator num;
		
		public PlayPane(MainApplication app) {
			super();
			num = RandomGenerator.getInstance();
			program = app;
			Fire = new GImage("Fire.jpg", 120, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Water = new GImage("Water.jpg", 120 + IMAGE_WIDTH + REG_PADDING, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Earth = new GImage("Earth.jpg", 120 + (2*IMAGE_WIDTH) + (2*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Wind = new GImage("Wind.jpg", 120 + (3*IMAGE_WIDTH) + (3*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
			gameOver = new GLabel("You Lose", WINDOW_HEIGHT/2, WINDOW_WIDTH/2);
			obstacles = new ArrayList<GImage>();
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
			character.setSize(80,40);
			program.add(character);
		}
		
		
		/*Random Number Generator decides which obstacle type to spawn
		 * Obstacle's design is based off which character the player selected
		 */
		public void drawObstacle() {
			//TODO:Display Obstacle
			if (num.nextInt(1, 20) >= 1 || num.nextInt(1, 20) <= 7) {
				GImage obs1 = new GImage("obstacle1.jpg",WINDOW_WIDTH,0);
				obs1.setLocation(WINDOW_WIDTH,WINDOW_HEIGHT-obs1.getHeight());
				program.add(obs1);
				obstacles.add(obs1); 
			}
			
			if (num.nextInt(1, 20) >= 8 || num.nextInt(1, 20) <= 14) {
			
			}
			
			if (num.nextInt(1, 20) >= 15 || num.nextInt(1, 20) <= 20) {
			
			}
			

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
			boolean gameStart = false;
			if (obj == Back) {
				program.switchToMenu();
			}
			else if(someObj == Fire) {
				drawGame(PlayerType.FIRE);
				gameStart = true;
			}
			else if(someObj == Water) {
				drawGame(PlayerType.WATER);
				gameStart = true;
			}
			else if(someObj == Earth) {
				drawGame(PlayerType.EARTH);
				gameStart = true;
			}
			else if(someObj == Wind) {
				drawGame(PlayerType.AIR);
				gameStart = true;
			}
			if(gameStart) {
				timer = new Timer(25,this);
				timer.start();
				hideSelection();
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			if(selection)return;
			
			if(gameSetUp.movePlayer(e.getY())) {
				character.setLocation(15, e.getY());
			}
			//test code for location of player and it's image
			//System.out.println(character.getY() + ", " + gameSetUp.getPlayer().getY());
		}
		
		private void moveObstacles(){
			ArrayList<GImage> tempList = new ArrayList<GImage>();
			for(int i = 0;i<obstacles.size();i++) {
				obstacles.get(i).move(-5,0);
				if((obstacles.get(i).getX()+ obstacles.get(i).getWidth()) < 0) {
					tempList.add(obstacles.get(i));
				}
			}
			obstacles.removeAll(tempList);
		}
		
		private boolean checkCollision() {
			for(int i = 0;i<obstacles.size();i++) {
				if(obstacles.get(i).getBounds().intersects(character.getBounds())) {
				return true;
				}
			}
			return false;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(count % 100 == 0 && obstacles.size()<max) {
				drawObstacle();
				count = 0;
			}
			moveObstacles();
			if(checkCollision()) {
				System.out.println("Gameover");
				timer.stop();
				gameOver();
			}
			count++;
		}
		
	}

	

