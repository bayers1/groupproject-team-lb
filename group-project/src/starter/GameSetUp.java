package starter;

public class GameSetUp {
	private Player player;
	private Obstacle obstacle;
	private PlayerType type;
	private int playerHeight;
	private int playerWidth;
	private double startX = 0;
	private double startY = 300;
	
	public GameSetUp(PlayerType type) {
		player = new Player(startX, startY, playerWidth, playerHeight, type);
	}

	public boolean movePlayer() {
		return false;
	}
	public Player getPlayer() {
		return player;
	}
	public void moveObstacle() {
	}
}
