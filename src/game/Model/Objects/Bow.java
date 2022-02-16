package game.Model.Objects;

import game.Model.Game;
import game.Model.Hero;
import game.Model.Types;

public class Bow extends Weapon{

	private Game env;


	public Bow(Game env) {
		super(15, 1, Types.BOW);
		this.env = env;
	}

	@Override
	public void attack(Hero l) {
		int tab[]=this.env.getLink().getCoordonne(this.env.getLink().getX(), this.env.getLink().getY());
		if(l.getValueAmmo()>0) {
			Arrow f = new Arrow(this.env,tab[0],tab[1]);
			this.env.getProjectiles().add(f);
			l.setAmmo(l.getValueAmmo()-1);
		}
		else {
			l.setAmmo(l.getValueAmmo()-1);
		}
	}
}
