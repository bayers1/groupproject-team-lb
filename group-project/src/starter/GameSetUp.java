package starter;

import java.util.ArrayList;

import acm.graphics.GImage;

public class GameSetUp {
	private Player player;
	private Obstacle obstacle;
	private PlayerType type;
	public static final int PLAYER_HEIGHT = 80;
	public static final int PLAYER_WIDTH = 40;
	private double startX = 15;
	private double startY = 300;
	
	private Obstacle topObstacle;
	private Obstacle bottomObstacle;
	private ArrayList<Obstacle> topObs;
	private ArrayList<Obstacle> bottomObs;
	private ArrayList<PowerUp> powerUps;
	
	public GameSetUp(PlayerType type) {
		player = new Player(startX, startY, PLAYER_WIDTH, PLAYER_HEIGHT, type);
		setGame();
	}

	public boolean movePlayer(double y) {
		return player.move(0, y);
	}
	
	public void setGame() {
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void moveObstacle() {
		
	}
}
