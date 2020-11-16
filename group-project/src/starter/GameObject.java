package starter;

public abstract class GameObject{

	private double x, y;
	private int width, height;
	public GameObject(double x, double y,
					  int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/*
	 * SETTERS
	 * */
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	/*
	 * GETTERS
	 * */
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public abstract boolean move(double xVel, double yVel);
	
	
}
