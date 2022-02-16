package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class VerticalMovementDown{

	public void move(HeroBaseEnemy b) {
		if(!b.nextToLinkVerticalDown(b, b.getEnv().getLink())) {
			b.setY(b.getY()+1*b.getPas());
			b.setDy(1);
			b.setGlobalDirection(2);
			b.setCount(b.getCount()+1);
		}
	}
}
