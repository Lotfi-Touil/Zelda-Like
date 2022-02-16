package game.Model.Objects;

import game.Model.Enemies.Enemy;
import game.Model.Game;
import game.Model.Types;

public class Arrow extends Projectile {

	public Arrow(Game env, int x, int y) {
		super(env, x, y, env.getLink().getGlobalDirection(), Types.ARROW);
	}

	@Override
	public boolean isHit() {
		for(Enemy e : getEnv().getCurrentMap().getEnemies()) {
			if(Math.round(e.getX())==this.getX() && Math.round(e.getY())==this.getY()) {
				e.decrementHp(getEnv().getLink().getCurrent().getAttackPoint());
				if(e.getHp()<=0) {
					e.die();
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean attack() {
		this.setPorte(getPorte()-1);
		if(!isHit() && this.getPorte()>0) {
			moveProjectile();
			return false;
		}
		return true;
	}
}
