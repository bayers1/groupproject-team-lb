package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
 

	public class PlayPane extends GraphicsPane implements ActionListener {
		private MainApplication program; // you will use program to get access to
										 // all of the GraphicsProgram calls
		
		private GameSetUp gameSetUp;
		public static final int IMAGE_HEIGHT = 400;
		public static final int IMAGE_WIDTH = 250;
		
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
		private GRect invulBar, scoreBacking;
		
		short obstacleSpawn = 50, topOccurRate = 2;
		
		GObject someObj;
		private int totalCount = 0;
		private float velX = -6;
		private float multiplier = 1.0f;
		private int score = 0;
		private int difficultyTracker = 0;
		private int totalGameTime = 0;
		
		private int times2 = 1, times2EndTime = 0;
		private float movementModifier = 1.0f;
		private int slowDownEndTime = 0;
		private boolean invulnerable = false;
		private int invulnerableEndTime = 0;
		
		private ArrayList<GImage> topObstacles;
		private ArrayList<GImage> bottomObstacles;
		private ArrayList<GImage> powerUps;
		private RandomGenerator rgen;
		private GRect border;
		private GImage lastDragonSelection;
		
		private boolean gameStarted = false;
		private boolean pause = false;
		
		public PlayPane(MainApplication app) {
			super();
			rgen = RandomGenerator.getInstance();
			program = app;
			
			Fire = new GImage("Fire.jpg", 120, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Water = new GImage("Water.jpg", 120 + IMAGE_WIDTH + REG_PADDING, (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Earth = new GImage("Earth.jpg", 120 + (2*IMAGE_WIDTH) + (2*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Wind = new GImage("Wind.jpg", 120 + (3*IMAGE_WIDTH) + (3*REG_PADDING), (WINDOW_HEIGHT / 2) - IMAGE_HEIGHT / 2);
			Back = new GButton("Back", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
			
			scoreBacking = new GRect(WINDOW_WIDTH - 250, REG_PADDING, 250, OUTSIDE_PADDING);
			scoreBacking.setFilled(true);
			Color backing = new Color(0, 0, 0, 0.6f);
			scoreBacking.setFillColor(backing);
			
			scoreDisplay = new GLabel("Current Score: " + score, WINDOW_WIDTH - 225, OUTSIDE_PADDING + 5);
			scoreDisplay.setColor(Color.WHITE);
			scoreDisplay.setFont(new Font("Serif", Font.PLAIN, 18));
			scoreDisplay.sendToFront();
			
			invulBar = new GRect(640, 0, 0, 25);
			invulBar.setFilled(true);
			invulBar.setFillColor(Color.YELLOW);
			
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
			timer = new Timer(20,this);
			timer.start();
			gameStarted = true;
			program.add(scoreBacking);
			program.add(scoreDisplay);
			program.add(invulBar);
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
			totalCount = 0;
			
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
			if(totalCount % (obstacleSpawn* topOccurRate) == 0){
				num = 2;	
			}
			
			if (num < 6) {
				fileName += "Static1";
				height = 180;
			}
			
			else if (num < 37) {
				fileName += "Static2";
				height = 360;
				width = 120;
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
			int occurs = rgen.nextInt(1, 30);
			int random = rgen.nextInt(1, 20);
			int yLoc = rgen.nextInt(150, 450);
			if (occurs < 0) return;
			
			PowerUp newPower = gameSetUp.createPowerUp(random);
			PowerUpType powerUpType = newPower.getType();
			
			String fileName = "";
			if(powerUpType == PowerUpType.MULTI) {
				fileName += "multi";
			}
			else if(powerUpType == PowerUpType.BONUS) {
				fileName += "bonus";
			}
			else if(powerUpType == PowerUpType.SLOW) {
				fileName += "slow";
			}
			else {
				fileName += "invul";
			}
			
			fileName += IMG_EXTENSION;
			powerUp = new GImage(fileName, WINDOW_WIDTH + 225, yLoc);
			powerUp.setSize(newPower.getWidth(), newPower.getHeight());
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
			if(border != null) {
				program.remove(border);
			}
		}
		
		/**
		 * Removes everything from Screen when player loses
		 * Displays score and highest as navigations on the screen
		 */
		public void gameOver(int score) {
			timer.stop();
			
			//remove all images on screen
			program.removeAll();
			
			//resets the ArrayLists
			topObstacles.clear();
			bottomObstacles.clear();
			powerUps.clear();
		
			resetData();
			gameSetUp.removeCache();
			selection = true;
			
			program.switchToGameOver(score);
		}
		
		/**
		 * helper method to revert the game data(cache)
		 * back from the start
		 * Does Not reset high score that is stored
		 */
		public void resetData() {
			totalCount = 0;
			velX = -4;
			multiplier = 1.0f;
			score = 0;
			difficultyTracker = 0;
			totalGameTime = 0;
			times2 = 1;
			times2EndTime = 0;
			movementModifier = 1.0f;
			slowDownEndTime = 0;
			invulnerableEndTime = 0;
			scoreDisplay.setFont(new Font("Serif", Font.PLAIN, 18));
			scoreDisplay.setColor(Color.WHITE);
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
			GObject obj = program.getElementAt(e.getX(), e.getY());
			boolean buttonHover = false;
			if(!buttonHover) {
				notHovered(Back);
			}
			if(obj == Back) {
				hover(Back);
			}
			
			if(obj==Fire) {
				hoverImage(Fire);
			}
			else if(obj == Water) {
				hoverImage(Water);
			}
			else if(obj == Wind) {
				hoverImage(Wind);
			}
			else if(obj == Earth) {
				hoverImage(Earth);
			}
			
			if(obj == null || obj != lastDragonSelection) {
				removeBorder();
			}
		
			buttonHover = true;	
			if(selection)return;
			
			if(gameSetUp.movePlayer(e.getY())) {
				character.setLocation(15, e.getY());
			}
			/*
			if(!pause) {
				if(gameSetUp.movePlayer(e.getY())) {
				character.setLocation(15, e.getY());
				}
			}
			*/	
		}
		
		/*
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_P && gameStarted) {
				pause = !pause;
				if(pause) {
					timer.stop();
				}
				else {
					timer.start();
					
				}
			}
		}
		*/
		
		private void removeBorder() {
			if(border != null) {
				program.remove(border);
				border = null;
				lastDragonSelection = null;
			}
		}

		private void hoverImage(GImage img) {
			if(border == null) {
				border = new GRect(img.getBounds().getX()-1,img.getBounds().getY()-1,img.getBounds().getWidth()+1,img.getBounds().getHeight()+1);
				program.add(border);
				img.sendToFront();
				lastDragonSelection = img;
			}
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
				topObstacles.get(i).move(movementModifier*velX, velY);
				
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
				bottomObstacles.get(i).move(movementModifier*velX, velY);
				
				//remove Obstacle when it gets out of screen
				if(bottomObstacles.get(i).getX() < -100) {
					program.remove(bottomObstacles.get(i));
					bottomObstacles.remove(bottomObstacles.get(i));
				}
			}
		}
		
		private void movePowerUps(){
			for(int i = 0; i < powerUps.size(); i++) {
				powerUps.get(i).move(movementModifier*velX, 0);
				
				if(powerUps.get(i).getX() < -80) {
					program.remove(powerUps.get(i));
					powerUps.remove(powerUps.get(i));
					gameSetUp.removePowerUp(i);
				}
			}
		}
		
		
		/**
		 * the checkCollision() method checks for collision 
		 * between player and obstacles
		 * @return true when player and obstacle do collide 
		 * 		   otherwise false
		 */
		private void checkCollision() {
			if(invulnerable) return;
			
			boolean collided = false;
			for(int i = 0; i <topObstacles.size();i++) {
				if(topObstacles.get(i).getBounds().intersects(character.getBounds())) {
					collided = true;
				}
			}
			for(int i = 0; i <bottomObstacles.size();i++) {
				if(bottomObstacles.get(i).getBounds().intersects(character.getBounds())) {
					collided = true;
				}
			}
			if(collided) {
				program.playSound(program.getSoundFiles()[3],false);
				program.stopMusic(program.getSoundFiles()[2]);
				gameOver(score);
			}
		}
		
		private boolean gotPowerUp() {
			for(int i = 0;i<powerUps.size();i++) {
				if(powerUps.get(i).getBounds().intersects(character.getBounds())) {
					determinePower(gameSetUp.getPower(i));
					program.remove(powerUps.get(i));
					powerUps.remove(powerUps.get(i));
					return true;
				}
			}
			return false;
		}
		
		/**
		 * based on powerUp collided with determine
		 * what to do
		 */
		private void determinePower(PowerUpType pUpType) {
			if(pUpType == PowerUpType.MULTI) {
				scoreDisplay.setFont(new Font("Serif", Font.BOLD, 20));
				scoreDisplay.setColor(Color.RED);
				times2 = 2;
				times2EndTime = totalGameTime + 15;
			}
			else if(pUpType == PowerUpType.BONUS) {
				score += (100 * multiplier);
			}
			else if(pUpType == PowerUpType.SLOW) {
				movementModifier = 0.8f;
				slowDownEndTime = totalGameTime + 4;
			}
			else {
				invulBar.setSize(350, 25);
				invulnerable = true;
				invulnerableEndTime = totalGameTime + 7;
			}
		}
		
		//helper method to scale points earned
		//based on multiplier
		private double PointsEarned() {
			double pointsEarned = times2 * (10 * multiplier);
			return pointsEarned;
		}
		
		//helper method
		private void updateScore() {
			score += PointsEarned();
		}
		
		private void drawScore() {
			updateScore();
			scoreDisplay.setLabel("Current Score: " + score);	
			//System.out.println(score);
		}
		
		private void checkDurations() {
			//ends duration of times2 PowerUp "multi type"
			if(totalGameTime == times2EndTime) {
				times2 = 1;
				scoreDisplay.setFont(new Font("Serif", Font.PLAIN, 18));
				scoreDisplay.setColor(Color.WHITE);
			}
			
			if(totalGameTime == slowDownEndTime) {
				movementModifier = 1.0f;
			}
			
			if(totalGameTime == invulnerableEndTime) {
				invulnerable = false;
			}
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(totalCount % (obstacleSpawn * topOccurRate) == 0 && topObstacles.size()< OBS_MAX) {
				drawTopObstacle();
			}
			if(totalCount % obstacleSpawn == 0 && topObstacles.size()< OBS_MAX) {
				drawBottomObstacle();
				drawPowerUp();
			}
			
			moveTopObstacles();
			moveBottomObstacles();
			movePowerUps();
			
			checkCollision();

			gotPowerUp(); //just to check.
			
			velX = -8.0f * multiplier;
			if (difficultyTracker % 1200 == 0) {
				multiplier += .2;				
				difficultyTracker = 0;
			}
			
			invulBar.setSize(invulBar.getWidth() - 1, 25);
			
			if (difficultyTracker % 50 == 0) {
				drawScore();
				totalGameTime++;
			}
			
			invulBar.setLocation(640 - (invulBar.getWidth()/2), 0);
			checkDurations();
			
			totalCount++;
			difficultyTracker++;		
		}
	}

	

