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
		
		
		public static final int OBS_MAX = 5;
		public static final int POWERUP_MAX = 1;
		
		public static final String IMG_EXTENSION = ".png";
		public double startX = 15;
		public double startY = 300;
		private boolean selection = true;

		private String sceneType;
		private GImage Fire, Water, Earth, Wind;
		private GLabel scoreDisplay;
		private GImage character, powerUp;
		private GImage scene;
		private GButton Back;
		private Timer timer;
		
		GObject someObj;
		private int topCount = 0;
		private int bottomCount = 0;
		private int totalCount = 0;
		private int powerUpCount = 0;
		private float velX = -8;
		private float multiplier = 1.0f;
		private int score = 0;
		private double pointsEarned;
		
		private int gameTime = 0;
		
		private ArrayList<GImage> topObstacles;
		private ArrayList<GImage> bottomObstacles;
		private ArrayList<GImage> powerUps;
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
			
			
			scoreDisplay = new GLabel("Current Score: " + score, WINDOW_WIDTH-200, REG_PADDING);
			scoreDisplay.setColor(Color.RED);
			
			topObstacles = new ArrayList<GImage>();
			bottomObstacles = new ArrayList<GImage>();
			powerUps = new ArrayList<GImage>();
			
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
		 * The drawGame method draws the objects for the game
		 * @param type
		 * 		the type will dictate the display or art style of objects
		 */
		public void drawGame(PlayerType type) {
			selection = false;
			hideSelection();
			
			gameSetUp = new GameSetUp(type);
			drawPlayer(type);
			drawScene();
			//System.out.println(gameSetUp.getPlayer());
			
			//starts drawing the obstacles
			timer = new Timer(24,this);
			timer.start();
			
			program.add(scoreDisplay);
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
		
		public void drawScene() {
			String fileName = sceneType + "Background" + IMG_EXTENSION;
			
			scene = new GImage(fileName, 0, 0);
			scene.setSize(1600, 600);
			program.add(scene);
			scene.sendToBack();
		}
		
		
		/**
		 * The method drawTopObstacle()
		 * decides which obstacle type to draw
		 * Obstacle's design is based off which character the player selected
		 * draws the TOP obstacles only
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
		
		/**
		 * The method drawBottomObstacle()
		 * decides which obstacle type to draw
		 * Obstacle's design is based off which character the player selected
		 * draws the BOTTOM obstacles only
		 */
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
		public void drawPowerUp() {
			int random = rgen.nextInt(1, 20);
			int occurs = rgen.nextInt(1, 30);
			if (occurs < 26) return;
			
			String fileName = "";
			if (random < 8) {
				fileName += "multi";
			}
			else if (random < 16) {
				fileName += "bonus";
			}
			else if (random < 18) {
				fileName += "slow";
			}
			else {
				fileName += "invul";
			}
			fileName += IMG_EXTENSION;
			powerUp = new GImage(fileName, WINDOW_WIDTH, 300);
			powerUp.setSize(225, 100);
			powerUp.sendToFront();
			program.add(powerUp);
			powerUps.add(powerUp);
			
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
			timer.stop();
			
			//remove all images on screen
			program.removeAll();
			
			//resets the ArrayLists
			topObstacles.clear();
			bottomObstacles.clear();
			powerUps.clear();
			
			resetData();
			selection = true;
			
			program.switchToGameOver();
		}
		
		/**
		 * helper method to revert the game data(cache)
		 * back from the start
		 * Does Not reset Highscore that is stored
		 */
		public void resetData() {
			topCount = 0;
			bottomCount = 0;
			totalCount = 0;
			powerUpCount = 0;
			velX = -8;
			multiplier = 1.0f;
			score = 0;
			gameTime = 0;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			GObject obj = program.getElementAt(e.getX(), e.getY());
			someObj = obj;
			
			if (obj == Back) {
				program.switchToMenu();
			}
			else if(someObj == Fire) {
				drawGame(PlayerType.FIRE);
			}
			else if(someObj == Water) {
				drawGame(PlayerType.WATER);
			}
			else if(someObj == Earth) {
				drawGame(PlayerType.EARTH);
			}
			else if(someObj == Wind) {
				drawGame(PlayerType.AIR);
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
		
		/*Checks what type the top obstacle 
		 * is and moves it accordingly
		 */
		private void moveTopObstacles(){			
			for(int i = 0; i<topObstacles.size(); i++) {
				int velY = 0;
				if (topObstacles.get(i).getWidth() == 80) {
					velY = 2;
				}
				topObstacles.get(i).move(velX, velY);
				
				//remove Obstacle when it gets out of screen
				if(topObstacles.get(i).getX() < -100) {
					program.remove(topObstacles.get(i));
					topObstacles.remove(topObstacles.get(i));
				}
			}
		}
		
		private void moveBottomObstacles(){
			for(int i = 0; i<bottomObstacles.size(); i++) {
				int velY = 0;
				if (bottomObstacles.get(i).getWidth() == 80) {
					velY = -2;
				}
				bottomObstacles.get(i).move(velX, velY);
				
				//remove Obstacle when it gets out of screen
				if(bottomObstacles.get(i).getX() < -100) {
					program.remove(bottomObstacles.get(i));
					bottomObstacles.remove(bottomObstacles.get(i));
				}
			}
		}
		
		private void movePowerUps(){
			for(int i = 0; i < powerUps.size(); i++) {
				powerUps.get(i).move(velX, 0);
				
				if(powerUps.get(i).getX() < -80) {
					program.remove(powerUps.get(i));
					powerUps.remove(powerUps.get(i));
				}
			}
		}
		
		
		/**
		 * the checkCollision() method checks for collision 
		 * between player and obstacles
		 * @return true when player and obstacle do collide 
		 * 		   otherwise false
		 */
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
		
		private boolean gotPowerUp() {
			for(int i = 0;i<powerUps.size();i++) {
				if(powerUps.get(i).getBounds().intersects(character.getBounds())) {
					program.remove(powerUps.remove(i));
					System.out.println("got powerup");
					return true;
				}
			}
			return false;
		}
		
		//helper method to scale points earned
		//based on multiplier
		private void scalePointsEarned() {
			pointsEarned = 20 * multiplier;
		}
		
		//helper method
		private void updateScore() {
			scalePointsEarned();
			score += pointsEarned;
		}
		
		private void drawScore() {
			updateScore();
			scoreDisplay.setLabel("Current Score: " + score);			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(topCount % TOP_OCCUR == 0 && topObstacles.size()< OBS_MAX) {
				drawTopObstacle();
				topCount = 0;
			}
			if(bottomCount % BOTTOM_OCCUR == 0 && topObstacles.size()< OBS_MAX) {
				drawBottomObstacle();
				bottomCount = 0;
			}
			
			if (powerUpCount % 90 == 0 || powerUpCount % 290 == 0) {
				drawPowerUp();
				powerUpCount = 0;
			}
			
			moveTopObstacles();
			moveBottomObstacles();
			movePowerUps();
			
			if(checkCollision()) {
				program.playSound(program.getSoundFiles()[3],false);
				program.stopMusic(program.getSoundFiles()[2]);
				gameOver();
			}
			gotPowerUp(); //just to check.		
			velX = -8.0f * multiplier;
			if (gameTime % 1200 == 0) {
				multiplier += .2;
				System.out.println(velX);
				
				gameTime = 0;
			}
			
			drawScore();
			
			topCount++;
			bottomCount++;
			totalCount++;
			powerUpCount++;
			gameTime++;
		}
		
	}

	

