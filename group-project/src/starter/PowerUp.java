package starter;

public class PowerUp extends GameObject{
	private PowerUpType type;

	public PowerUp(double x, double y, int width, int height) {
		super(x, y, width, height);
	}
	
	public PowerUp(double x, double y, int width, int height, PowerUpType type) {
		super(x, y, width, height);
		this.type = type;
	}
	
	public PowerUpType getType() {
		return type;
	}

	public void setType(PowerUpType type) {
		this.type = type;
	}
	
	public boolean move() {
		return true;
	}
	
	public void bounds() {
		
	}

}
