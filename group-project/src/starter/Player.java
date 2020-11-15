package starter;

public class Player extends GameObject{

	private PlayerType type;
	public static final int PLAYER_WIDTH = 80;
	public static final int PLAYER_HEIGHT = 40;
	public Player(double x, double y, int width, int height, PlayerType type) {
		super(x, y, width, height);
		setWidth(PLAYER_WIDTH);
		setHeight(PLAYER_HEIGHT);
		this.type = type;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}
	
	public boolean move() {
		return true;
	}

	public boolean OutOfBounds() {
		return true;
	}
	
	public boolean collision() {
		return true;
	}
	
	public void bounds() {
		
	}
	
	public String toString() {
		String characterDetails = "Height: "+ getHeight()
								+ "\nWidth: "	+ getWidth()
								+ "\nType: "	+ getType();
		
		return characterDetails;
	}

}
