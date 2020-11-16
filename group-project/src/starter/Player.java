package starter;

public class Player extends GameObject{

	private PlayerType type;
	public Player(double x, double y, int width, int height, PlayerType type) {
		super(x, y, width, height);
		setWidth(width);
		setHeight(height);
		this.type = type;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}
	
	public boolean move(double xVel, double yVel) {
		if(outOfBounds(xVel, yVel, getWidth(),getHeight())) return false;
		setY(yVel);
		return true;
	}
	
	public boolean collision() {
		return true;
	}
	
	/**
	 * 
	 * @param x where the object will be moving horizontally
	 * @param y where the object will be moving vertically
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean outOfBounds(double x, double y, int width, int height) {
		if (y <= (600-(height/2)) && y >= 0) return false;
		return true;
	}
	
	public String toString() {
		String characterDetails = "Height: "+ getHeight()
								+ "\nWidth: "	+ getWidth()
								+ "\nType: "	+ getType();
		
		return characterDetails;
	}

}
