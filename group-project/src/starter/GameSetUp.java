package starter;

import java.util.ArrayList;

import acm.util.RandomGenerator;

public class GameSetUp {
	private Player player;
	public static final int PLAYER_HEIGHT = 80;
	public static final int PLAYER_WIDTH = 40;
	private double startX = 15;
	private double startY = 300;
	
	private ArrayList<PowerUp> powerUps;
	RandomGenerator rgen;
	
	public GameSetUp(PlayerType type) {
		player = new Player(startX, startY, PLAYER_WIDTH, PLAYER_HEIGHT, type);
		setGame();
		
		powerUps = new ArrayList<PowerUp>();
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
	
	public PowerUp createPowerUp(int rNum) {
		int height = 100;
		int width = 225;

		PowerUpType pUpType;
		
		if (rNum < 8) {
			pUpType = PowerUpType.MULTI;
		}
		else if (rNum < 16) {
			pUpType = PowerUpType.BONUS;
		}
		else if (rNum < 18) {
			pUpType = PowerUpType.SLOW;
		}
		else {
			pUpType = PowerUpType.INVUL;
		}
				
		PowerUp pUp = new PowerUp(1280, 0, width, height, pUpType);
		powerUps.add(pUp);
		
		return pUp;
	};
	
	public PowerUpType getPower(int i) {
		PowerUpType someType = powerUps.get(i).getType();
		powerUps.remove(powerUps.get(i));
		return someType;
	}
	
	public void removePowerUp(int i) {
		powerUps.remove(powerUps.get(i));
	}
	public void removeCache(){
		powerUps.clear();
	}
}
