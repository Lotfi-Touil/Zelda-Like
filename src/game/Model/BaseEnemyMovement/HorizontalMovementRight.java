package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class HorizontalMovementRight {

	public void move(HeroBaseEnemy b) {
		if(!b.nextToLinkHorizontalRight(b, b.getEnv().getLink())) {
			b.setX(b.getX()+1*b.getPas());
			b.setDx(1);
			b.setGlobalDirection(1);
			b.setCount(b.getCount()+1);
		}
	}

}
