package game.Model.BaseEnemyMovement;

import game.Model.Enemies.HeroBaseEnemy;

public class HorizontalMovementLeft {

	public void move(HeroBaseEnemy b) {
		if(!b.nextToLinkHorizontalLeft(b, b.getEnv().getLink()))	{
			b.setX(b.getX()-1*b.getPas());
			b.setDx(-1);
			b.setGlobalDirection(3);
			b.setCount(b.getCount()+1);
		}
	}

}
