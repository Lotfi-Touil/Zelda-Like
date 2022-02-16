package game.Model.Objects;

import game.Model.Enemies.Enemy;
import game.Model.Hero;
import game.Model.Types;

public class Sword extends Weapon{
	
	public Sword() {
		super(20, 1, Types.SWORD);
	}

	@Override
	public void attack(Hero l) {
		Enemy e = l.tryCatching();
		if (e != null) {
            e.decrementHp(20);
            if(e.getHp()<=0)
                e.die();
        }  	
	}
}
