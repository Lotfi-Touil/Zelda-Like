package game.Model.Objects.Potions;

import game.Model.Enemies.HeroBaseEnemy;

public class DecorateurInvisibilite extends HeroBaseEnemy{
	
	public DecorateurInvisibilite() {
		super();
	}
	public DecorateurInvisibilite(HeroBaseEnemy e) {
		super(e);
	}

	@Override
	public void attack() {
		//can't attack
	}
}
