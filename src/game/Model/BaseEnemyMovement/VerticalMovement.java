package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class VerticalMovement implements TravelMode {

	private VerticalMovementUp upMovement;
	private VerticalMovementDown downMovement;
	
	public VerticalMovement() {
		this.upMovement = new VerticalMovementUp();
		this.downMovement = new VerticalMovementDown();
	}
	
	
	@Override
	public void move(HeroBaseEnemy b) {
		if(b.getCount()%8<4) {
			this.downMovement.move(b);
		}
		else {
			this.upMovement.move(b);
		}	
	}

}
