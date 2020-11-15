package starter;

public class Obstacle extends GameObject{
	
private ObstacleType type;
	
	public Obstacle(double x, double y, int width, int height, ObstacleType type) {
		
		super(x, y, width, height);
		this.type = type;
	}
	
	public boolean move() {
		return false;
	}
	
	public void bounds() {;
	
	}
}
