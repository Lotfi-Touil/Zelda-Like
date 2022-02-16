package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class HorizontalMovement implements TravelMode {

	private HorizontalMovementRight rightMovement;
	private HorizontalMovementLeft leftMovement;
	
	public HorizontalMovement() {
		this.rightMovement = new HorizontalMovementRight();
		this.leftMovement = new HorizontalMovementLeft();
	}
	
	@Override
	public void move(HeroBaseEnemy b) {
		if(b.getCount()%8<4) {
			this.rightMovement.move(b);
		}
		else {
			this.leftMovement.move(b);
		}	
	}

}
