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

	public class PlayPane extends GraphicsPane implements ActionListener {
		private MainApplication program; // you will use program to get access to
										 // all of the GraphicsProgram calls
		private GameSetUp gameSetUp;
		public static final int BAR_HEIGHT = 12;
		public static final int BAR_LOCY = 20;
		
		public static final int OBS_MAX = 5;
		public static final int POWERUP_MAX = 1;
		
		public static final String IMG_EXTENSION = ".png";
		public double startX = 15;
		public double startY = 300;
		
		private String sceneType;
		
		private GLabel scoreDisplay, pauseDisplay;
		private GImage character, powerUp;
		private GImage scene,scene2;
	
		private Timer timer;
		private GRect invulBar, scoreBacking, pauseBacking;
		
		short obstacleSpawn = 50, topOccurRate = 2;
		
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
	
		private boolean gameStarted = false;
		private boolean pause = false;
		
		private GLabel pausemenuLabel;
	    private GButton restartGame;
	    private GButton exitGame;
	    
	    private float backgroundSpeed = -0.5f;  //new addition.
	   
	    private PlayerType playerType;
		public PlayPane(MainApplication app,PlayerType playerType) {
			super();
			rgen = RandomGenerator.getInstance();
			program = app;
			this.playerType = playerType;
		
			
			scoreBacking = new GRect(WINDOW_WIDTH - 250, REG_PADDING, 250, OUTSIDE_PADDING);
			scoreBacking.setFilled(true);
			Color backing = new Color(0, 0, 0, 0.6f);
			scoreBacking.setFillColor(backing);
			
			pauseBacking = new GRect(0, REG_PADDING - 5, 225, OUTSIDE_PADDING);
			pauseBacking.setFilled(true);
			pauseBacking.setFillColor(backing);
			
			scoreDisplay = new GLabel("Current Score: " + score, WINDOW_WIDTH - 225, OUTSIDE_PADDING + 5);
			scoreDisplay.setColor(Color.WHITE);
			scoreDisplay.setFont(new Font("Serif", Font.PLAIN, 18));
			scoreDisplay.sendToFront();
			
			pauseDisplay = new GLabel("Press SPACE to pause", 15, OUTSIDE_PADDING);
			pauseDisplay.setColor(Color.WHITE);
			pauseDisplay.setFont(new Font("Serif", Font.PLAIN, 18));
			
			
			invulBar = new GRect(640, BAR_LOCY, 0, BAR_HEIGHT);
			invulBar.setFilled(true);
			invulBar.setFillColor(BUTTON_COLOR);
			
			topObstacles = new ArrayList<GImage>();
			bottomObstacles = new ArrayList<GImage>();
			powerUps = new ArrayList<GImage>();
		}

		@Override
		public void showContents() {
			gameSetUp = new GameSetUp(playerType);
			drawPlayer(playerType);
			drawScene();
			drawScene2();
			//System.out.println(gameSetUp.getPlayer());
			
			//starts drawing the obstacles
			timer = new Timer(20,this);
			timer.start();
			gameStarted = true;
			program.add(scoreBacking);
			program.add(pauseBacking);
			program.add(scoreDisplay);
			program.add(pauseDisplay);
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
		
		public void drawScene2() { // Added for background image moving effect.//called in showContents.
			String fileName = sceneType + "Background" + IMG_EXTENSION;
			scene2 = new GImage(fileName, 1280,0);
			scene2.setSize(1600, 600);
			program.add(scene2);
			scene2.sendToBack();
		}
		
		 public void movingBackground() { // scenes moving. called in actionPerformed.
		        if (scene.getX() > -1599) {
		           scene.setLocation(scene.getX() + backgroundSpeed,scene.getY());  
		        }
		        else {
		            scene.setLocation(1280, scene.getY()); //resetting.
		        }
		        if (scene2.getX() > -1599) {
		           scene2.setLocation(scene2.getX() + backgroundSpeed,scene2.getY());  
		        } 
		        else {
		            scene2.setLocation(1280,scene2.getY());
		        }
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
			if (occurs < 24) return;
			
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
			if(invulnerable == false) {
				program.remove(invulBar);
			}
		}
		/**
		 * Removes everything from Screen when player loses
		 * Displays score and highest as navigations on the screen
		 */
		public void gameOver(int score) {
			gameLeft();
			program.switchToGameOver(score);
		}
		
		public void gameLeft() {
			timer.stop();
			
			//remove all images on screen
			program.removeAll();
			
			//resets the ArrayLists
			topObstacles.clear();
			bottomObstacles.clear();
			powerUps.clear();
		
			resetData();
			gameSetUp.removeCache();
	
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
			invulnerable = false;
			scoreDisplay.setFont(new Font("Serif", Font.PLAIN, 18));
			scoreDisplay.setColor(Color.WHITE);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			GObject obj = program.getElementAt(e.getX(), e.getY());
			if(obj == exitGame) {
				pause = false;
				gameLeft();
				program.switchToMenu();
			}
			if(obj == restartGame) {
				pause = false;
				gameLeft();
				program.switchToCharacterSelection();
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			if(!pause) {
				if(gameSetUp.movePlayer(e.getY())) {
					character.setLocation(15, e.getY());
				}
			}				
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE && gameStarted) { //press space bar to pause and un-pause.
				pause = !pause;
				if(pause) {
					pauseMenu();
					timer.stop();
				}
				else {
					exitpauseMenu();
					timer.setInitialDelay(3000); //can simply use a delay so that by then user resets its cursor.
					timer.start();
				}
			}
		}
	
		
		/**
		 * moves the top obstacles only
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
		
		/**
		 * moves the bottom obstacles only
		 */
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
		
		/**
		 * moves the powerUps
		 */
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
				//program.playSound("powerup.wav",false);
				
			}
			else if(pUpType == PowerUpType.BONUS) {
				score += (100 * multiplier);
				//program.playSound("powerup.wav",false);
			}
			else if(pUpType == PowerUpType.SLOW) {
				movementModifier = 0.8f;
				slowDownEndTime = totalGameTime + 4;
				//program.playSound("powerup.wav",false);
			}
			else {
				invulBar.setSize(350, BAR_HEIGHT);
				invulnerable = true;
				invulnerableEndTime = totalGameTime + 7;
				
				program.add(invulBar);
				//program.playSound("powerup.wav",false);
				
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
			scoreBacking.sendToFront();
			scoreDisplay.sendToFront();
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
		
		public void pauseMenu() {
			pausemenuLabel = new GLabel("PAUSED", WINDOW_WIDTH / 2 - 70, WINDOW_HEIGHT / 2 - 50);
			pausemenuLabel.setFont(new Font("Helvetica", Font.BOLD, 44));
			pausemenuLabel.setColor(Color.BLACK);
	        program.add(pausemenuLabel);

	        restartGame = new GButton("RESTART", WINDOW_WIDTH / 2 - 50, WINDOW_HEIGHT / 2 - 20, 120, 50);
	        restartGame.setFillColor(BUTTON_COLOR);
	        program.add(restartGame);

	        exitGame = new GButton("E X I T", WINDOW_WIDTH / 2 - 50, WINDOW_HEIGHT / 2 + 40, 120, 50);
	        exitGame.setFillColor(BUTTON_COLOR);
	        program.add(exitGame);
	        pause = true;
	    }

	    public void exitpauseMenu() {
	        program.remove(pausemenuLabel);
	        program.remove(restartGame);
	        program.remove(exitGame);
	        pause = false;
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
			
			pauseBacking.sendToFront();
			pauseDisplay.sendToFront();
			
			moveTopObstacles();
			moveBottomObstacles();
			movePowerUps();
			movingBackground();// new addition.For movement of background or scene.
			checkCollision();

			if(gotPowerUp()) {
				program.playSound("newpowerup.wav",false);//just to check.
			}
			
			velX = -8.0f * multiplier;
			if (difficultyTracker % 1200 == 0) {
				multiplier += .2;				
				difficultyTracker = 0;
			}
			
			if (difficultyTracker % 50 == 0) {
				drawScore();
				totalGameTime++;
				if(invulnerable)invulBar.setSize(invulBar.getWidth() - 50, BAR_HEIGHT);
			}
			
			invulBar.setLocation(640 - (invulBar.getWidth()/2), BAR_LOCY);
			
			checkDurations();
			
			totalCount++;
			difficultyTracker++;		
		}
	}

	

