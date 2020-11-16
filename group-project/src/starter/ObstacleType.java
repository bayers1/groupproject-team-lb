package starter;

public enum ObstacleType {
	FIRE1, FIRE2, WATER1, WATER2, EARTH1, EARTH2, AIR1, AIR2;
	
	public String toString() {
		switch(this) {
			case FIRE1: 
				return "fire1";
			case FIRE2:
				return "fire2";
			case WATER1:
				return "water1";
			case WATER2:
				return "water2";
			case EARTH1:
				return "earth1";
			case EARTH2:
				return "earth2";
			case AIR1:
				return "air1";
			case AIR2:
				return "air2";
			}
		return "N/A";
	}

}
