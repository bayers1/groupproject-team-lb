package starter;

public class GameSetUp {
	private Player player;
	private Obstacle obstacle;
	private PlayerType type;
	public static final int PLAYER_HEIGHT = 80;
	public static final int PLAYER_WIDTH = 40;
	private double startX = 15;
	private double startY = 300;
	
	public GameSetUp(PlayerType type) {
		player = new Player(startX, startY, PLAYER_WIDTH, PLAYER_HEIGHT, type);
	}

	public boolean movePlayer(double y) {
		if(player.outOfBounds(0, y, PLAYER_WIDTH, PLAYER_HEIGHT)) {
			player.move(y);
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
