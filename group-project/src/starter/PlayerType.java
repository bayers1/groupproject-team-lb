package starter;

public enum PlayerType {
	FIRE, WATER, EARTH, AIR;
	
	public String toString(){
		switch(this) {
			case FIRE: 
				return "fireDragon";
			case WATER:
				return "waterDragon";
			case EARTH:
				return "earthDragon";
			case AIR:
				return "airDragon";
		}
		return "N/A";
	}

}
