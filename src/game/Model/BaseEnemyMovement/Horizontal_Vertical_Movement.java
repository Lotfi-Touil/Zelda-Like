package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class Horizontal_Vertical_Movement extends DoubleMovement  {

	@Override
	public void move(HeroBaseEnemy b) {
		if(b.getCount()%12<3) {
			this.getRightMovement().move(b);
		}
		else if(b.getCount()%12 >= 3 && b.getCount()%12 < 6){
			this.getUpMovement().move(b);
		}
		else if(b.getCount()%12 >= 6 && b.getCount()%12 < 9){
			this.getDownMovement().move(b);
		}
		else if(b.getCount()%12 >= 9 && b.getCount()%12 < 12){
			this.getLeftMovement().move(b);
		}
	}

}
