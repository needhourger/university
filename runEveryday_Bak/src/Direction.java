
public enum Direction {
	UP(0),
	RIGHT(1),
	DOWN(2),
	DOUBLEUP(3),
	DOUBLEDOWN(4),
	LEFT(6);
	@SuppressWarnings("unused")
	private final int directionCode;
	Direction(int directionCode){
		this.directionCode=directionCode;
	}

}
