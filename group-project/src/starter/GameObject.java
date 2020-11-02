package starter;

public class GameObject {

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
	void setX(double x) {
		this.x = x;
	}
	void setY(double y) {
		this.y = y;
	}
	void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	void setWidth(int width) {
		this.width = width;
	}
	void setHeight(int height) {
		this.height = height;
	}
	
	/*
	 * GETTERS
	 * */
	double getX() {
		return x;
	}
	double getY() {
		return y;
	}
	double getVelocityX() {
		return velocityX;
	}
	double getVelocityY() {
		return velocityY;
	}
	int getWidth() {
		return width;
	}
	int getHeight() {
		return height;
	}
	
	
	
	
	
}
