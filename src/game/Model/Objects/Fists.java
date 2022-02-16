package game.Model.Objects;

import game.Model.Enemies.Enemy;
import game.Model.Hero;
import game.Model.Types;

public class Fists extends Weapon {

    public Fists() {
        super(5, 1, Types.FISTS);
    }

    @Override
    public void attack(Hero l) {
    	Enemy e = l.tryCatching();
		if (e != null) {
            e.decrementHp(5);
            if(e.getHp()<=0)
                e.die();
        }
    }
}
