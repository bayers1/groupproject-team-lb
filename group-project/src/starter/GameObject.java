package starter;

public abstract class GameObject {

	private double x, y, velocityX, velocityY;
	private int width, height;
	public GameObject(double x, double y, 
					  double velocityX, double velocityY,
					  int width, int height) {
		this.x = x;
		this.y = y;
		this.velocityX =  velocityX;
		this.velocityY =  velocityY;
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
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
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
	public double getVelocityX() {
		return velocityX;
	}
	public double getVelocityY() {
		return velocityY;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public abstract boolean move();
	public abstract void bounds();
	
	
}
