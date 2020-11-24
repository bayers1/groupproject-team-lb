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
		
		public static final int TOP_OCCUR = 150;
		public static final int BOTTOM_OCCUR = 50;
		
		public static final String IMG_EXTENSION = ".png";
		public double startX = 15;
		public double startY = 300;
		private boolean selection = true;

		private String sceneType;
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
		private int topCount = 0;
		private int bottomCount = 0;
		private int totalCount = 0;
		private int max = 10;
		private float velX = -8;
		private float multiplyer = 1.0f;
		
		private int gameTime = 0;
		
		private ArrayList<GImage> topObstacles;
		private ArrayList<GImage> bottomObstacles;
		private RandomGenerator rgen;
		
		public PlayPane(MainApplication app) {
			super();
			rgen = RandomGenerator.getInstance();
			program = app;
			
			Fire = new GImage("Fire.jpg", 120, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Water = new GImage("Water.jpg", 120 + IMAGE_WIDTH + REG_PADDING, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Earth = new GImage("Earth.jpg", 120 + (2*IMAGE_WIDTH) + (2*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Wind = new GImage("Wind.jpg", 120 + (3*IMAGE_WIDTH) + (3*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
			gameOver = new GLabel("You Lose", WINDOW_HEIGHT/2, WINDOW_WIDTH/2);
			topObstacles = new ArrayList<GImage>();
			bottomObstacles = new ArrayList<GImage>();
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
				sceneType = "fire";
			}
			else if (type == PlayerType.WATER) {
				fileName += "water";
				sceneType = "water";
			}
			else if (type == PlayerType.EARTH) {
				fileName += "earth";
				sceneType = "earth";
			}
			else {
				fileName += "air";
				sceneType = "air";
			}
			fileName += "Dragon" + IMG_EXTENSION;
			
			character = new GImage(fileName, startX, startY);
			character.setSize(80,40);
			program.add(character);
		}
		
		
		/*Random Number Generator decides which obstacle type to spawn
		 * Obstacle's design is based off which character the player selected
		 */
		public void drawTopObstacle() {
			String fileName = sceneType;
			
			int num = rgen.nextInt(1, 40);
			int height;
			int width = 120;
			
			if (num < 11) {
				fileName += "Static1";
				height = 180;
			}
			
			else if (num < 37) {
				fileName += "Static2";
				height = 330;
				width = 110;
			}
			
			else {
				fileName += "Moving";
				height = 80;
				width = 80;
			}

			fileName += "Top";
			fileName += IMG_EXTENSION;
			
			GImage obs = new GImage(fileName,WINDOW_WIDTH,0);
			
			obs.setSize(width,height);
			
			program.add(obs);
			topObstacles.add(obs);
		}
		
		public void drawBottomObstacle() {
			String fileName = sceneType;

			int num = rgen.nextInt(1, 40);
			int height;
			int width = 120;

			//to make sure that player can get through
			if(totalCount % TOP_OCCUR == 0){
				num = 2;
				totalCount = 0;
			}
			
			if (num < 11) {
				fileName += "Static1";
				height = 180;
			}
			
			else if (num < 37) {
				fileName += "Static2";
				height = 270;
				width = 90;
				if((totalCount % BOTTOM_OCCUR == 0) && (totalCount % TOP_OCCUR != 0)){
					height = 360;
					width = 120;
				}
			}
			
			else {
				fileName += "Moving";
				height = 80;
				width = 80;
			}

			fileName += "Bottom";
			fileName += IMG_EXTENSION;
			
			GImage obs = new GImage(fileName,WINDOW_WIDTH,0);
			
			obs.setSize(width,height);
			obs.setLocation(WINDOW_WIDTH,WINDOW_HEIGHT-obs.getHeight());
			
			program.add(obs);
			bottomObstacles.add(obs);
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
		
		private void moveTopObstacles(){
			ArrayList<GImage> tempList = new ArrayList<GImage>();
			
			for(int i = 0;i<topObstacles.size();i++) {
				topObstacles.get(i).move(velX,0);
				if((topObstacles.get(i).getX()+ topObstacles.get(i).getWidth()) < 0) {
					tempList.add(topObstacles.get(i));
				}
			}
			topObstacles.removeAll(tempList);
		}
		
		private void moveBottomObstacles(){
			ArrayList<GImage> tempList = new ArrayList<GImage>();
			
			for(int i = 0;i<bottomObstacles.size();i++) {
				bottomObstacles.get(i).move(velX,0);
				
				if((bottomObstacles.get(i).getX()+ bottomObstacles.get(i).getWidth()) < 0) {
					tempList.add(bottomObstacles.get(i));
				}
			}
			bottomObstacles.removeAll(tempList);
		}
		
		
		private boolean checkCollision() {
			for(int i = 0; i <topObstacles.size();i++) {
				if(topObstacles.get(i).getBounds().intersects(character.getBounds())) {
					return true;
				}
			}
			for(int i = 0; i <bottomObstacles.size();i++) {
				if(bottomObstacles.get(i).getBounds().intersects(character.getBounds())) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(topCount % TOP_OCCUR == 0 && topObstacles.size()<max) {
				drawTopObstacle();
				topCount = 0;
			}
			if(bottomCount % BOTTOM_OCCUR == 0 && topObstacles.size()<max) {
				drawBottomObstacle();
				bottomCount = 0;
			}
			
			moveTopObstacles();
			moveBottomObstacles();
			if(checkCollision()) {
				System.out.println("Gameover");
				timer.stop();
				gameOver();
			}
			
			velX = -8.0f * multiplyer;
			if (gameTime % 1200 == 0) {
				multiplyer += .1;
				System.out.println(velX);
			}
			
			topCount++;
			bottomCount++;
			totalCount++;
			gameTime++;
		}
		
	}

	

