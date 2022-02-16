package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class VerticalMovementUp {

	public void move(HeroBaseEnemy b) {
		if(!b.nextToLinkVerticalUp(b, b.getEnv().getLink())) {
			b.setY(b.getY()-1*b.getPas());
			b.setDy(-1);
			b.setGlobalDirection(0);
			b.setCount(b.getCount()+1);
		}
	}
	
}
