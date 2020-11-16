package starter;

public class GameSetUp {
	private Player player;
	private Obstacle obstacle;
	private PlayerType type;
	private int playerHeight;
	private int playerWidth;
	private double startX = 15;
	private double startY = 300;
	
	public GameSetUp(PlayerType type) {
		player = new Player(startX, startY, playerWidth, playerHeight, type);
	}

	public boolean movePlayer(double y) {
		if(y <= 560 && y >= 0) {
			player.setY(y);
			return true;
		}
		return false;
	}
	
	public Player getPlayer() {
		return player;
		
	}
	
	public void moveObstacle() {
		
	}
}
