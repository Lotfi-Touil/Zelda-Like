package game.Model.BaseEnemyMovement;

public abstract class DoubleMovement implements TravelMode{

	private VerticalMovementUp upMovement;
	private VerticalMovementDown downMovement;
	private HorizontalMovementRight rightMovement;
	private HorizontalMovementLeft leftMovement;
	
	public DoubleMovement() {
		this.upMovement = new VerticalMovementUp();
		this.downMovement = new VerticalMovementDown();
		this.rightMovement = new HorizontalMovementRight();
		this.leftMovement = new HorizontalMovementLeft();
	}
	
	public VerticalMovementUp getUpMovement() {
		return upMovement;
	}

	public VerticalMovementDown getDownMovement() {
		return downMovement;
	}

	public HorizontalMovementRight getRightMovement() {
		return rightMovement;
	}

	public HorizontalMovementLeft getLeftMovement() {
		return leftMovement;
	}

}
