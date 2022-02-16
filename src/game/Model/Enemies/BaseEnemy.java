package game.Model.Enemies;

public class BaseEnemy extends HeroBaseEnemy {

 	public BaseEnemy() {
 		super();
 	}
 	public BaseEnemy(HeroBaseEnemy e) {
 		super(e);
 	}
 	
	@Override
	public void attack() {
		tryCatching();
	}

 }
