package starter;

public class Obstacle extends GameObject{
	
	private ObstacleType type;
	private boolean isTop;
	public static final int OBS_WIDTH = 80;
	public static final int S1_HEIGHT = 400;
	public static final int S2_HEIGHT = 200;
	public Obstacle(double x, double y, int width, int height, ObstacleType type, boolean isTop) {
		super(x, y, width, height);
		if (type == ObstacleType.STATIC1) {
			setWidth(OBS_WIDTH);
			setHeight(S1_HEIGHT);
		}
		else if (type == ObstacleType.STATIC2) {
			setWidth(OBS_WIDTH);
			setHeight(S2_HEIGHT);
		}
		else {
			setWidth(OBS_WIDTH);
			setHeight(OBS_WIDTH);
		}
		this.type = type;
		this.isTop = isTop;
	}
	
	public ObstacleType getType() {
		return type;
	}

	public void setType(ObstacleType type) {
		this.type = type;
	}
	
	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public boolean move(double xVelocity, double yVelocity) {
		return false;
	}
}
