package game.Model.Objects.Potions;

import game.Model.Enemies.BaseEnemy;
import game.Model.Enemies.Enemy;
import game.Model.Enemies.HeroBaseEnemy;
import game.Model.Game;

public class PotionInvisibilite extends Potion{
	
	public PotionInvisibilite(int x, int y) {
		super(x, y);
	}

	@Override
	public void interact(Game env) {
		env.getLink().setPotions(this);
		env.getLink().getItems().add(this);
	}

	public void consume(Game env) {
		env.getLink().getItems().remove(this);
		for(int i=env.getCurrentMap().getEnemies().size()-1; i>0; i--) {
			Enemy e = env.getCurrentMap().getEnemies().get(i);
			if(e instanceof HeroBaseEnemy) {
				HeroBaseEnemy baseEnemy = (HeroBaseEnemy) e;
				baseEnemy = new DecorateurInvisibilite((HeroBaseEnemy) e);
				env.getCurrentMap().getEnemies().remove(e);
				env.getCurrentMap().getEnemies().add(baseEnemy);
				baseEnemy.setEnv(env);
			}
		}
	}
	
	public void endEffect(Game env) {
		for(int i=env.getCurrentMap().getEnemies().size()-1; i>0; i--) {
			Enemy e = env.getCurrentMap().getEnemies().get(i);
			if(e instanceof HeroBaseEnemy) {
				HeroBaseEnemy baseEnemy = (HeroBaseEnemy) e;
				baseEnemy = new BaseEnemy((HeroBaseEnemy) e);
				env.getCurrentMap().getEnemies().remove(e);
				env.getCurrentMap().getEnemies().add(baseEnemy);
				baseEnemy.setEnv(env);
			}
		}
	}
	
}
