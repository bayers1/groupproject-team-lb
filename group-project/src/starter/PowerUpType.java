package starter;

public enum PowerUpType {
	multi, slow, bonus, invul;

	public String toString() {
		switch(this) {
			case multi:
				return "Score Multiplier";
			case slow:
				return "Object Slowdown";
			case bonus:
				return "Bonus Point";
			case invul:
				return "invulnerability";
		}
		return "N/A";
	}

}
