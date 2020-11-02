package starter;

public enum PlayerType {
	fire, water, earth, air;
	
	public String PlayerType() {
		switch(this) {
			case fire: 
				return "fireDragon";
			case water:
				return "waterDragon";
			case earth:
				return "earthDragon";
			case air:
				return "airDragon";
		}
		return "N/A";
	}

}
