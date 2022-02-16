package game.Model.Objects;

import game.Model.Enemies.Boss;
import game.Model.Game;
import game.Model.Hero;
import game.Model.Types;

public class Flamethrower extends Weapon {

    private Game env;
    private Boss boss;

    public Flamethrower(Game env, Boss boss) {
        super(10, 1, Types.FLAMETHROWER);
        this.env = env;
        this.boss = boss;
    }

    @Override
    public void attack(Hero l) {
        Fireball f = new Fireball(this.env, boss.getCoordonne(boss.getX(), boss.getY())[0], boss.getCoordonne(boss.getX(), boss.getY())[1], boss.getGlobalDirection());
        this.env.getProjectiles().add(f);
    }
}
