package starter;

public class Player extends GameObject{

	private PlayerType type;
	
	public Player(double x, double y, int width, int height, PlayerType type) {
		super(x, y, width, height);
		width = 80;
		height = 40;
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
								+ "Width: "	+ getWidth()
								+ "Type: "	+ getType();
		
		return characterDetails;
	}

}
