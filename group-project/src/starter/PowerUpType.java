package starter;

public enum PowerUpType {
	MULTI, SLOW, BONUS, INVUL;

	public String toString() {
		switch(this) {
			case MULTI:
				return "Score Multiplier";
			case SLOW:
				return "Object Slowdown";
			case BONUS:
				return "Bonus Point";
			case INVUL:
				return "invulnerability";
		}
		return "N/A";
	}

}
