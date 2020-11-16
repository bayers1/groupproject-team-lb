package starter;

public enum ObstacleType {
	STATIC1, STATIC2, MOVING;
	
	public String toString() {
		switch(this) {
			case STATIC1: 
				return "static1";
			case STATIC2:
				return "static2";
			case MOVING:
				return "moving";
			}
		return "N/A";
	}

}
