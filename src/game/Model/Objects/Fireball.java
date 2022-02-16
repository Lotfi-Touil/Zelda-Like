package game.Model.Objects;

import game.Model.Game;
import game.Model.Hero;
import game.Model.Types;

public class Fireball extends Projectile {

    public Fireball(Game env, int x, int y, int direction) {
        super(env, x, y, direction, Types.FIREBALL);
    }

    @Override
    public boolean isHit() {
        Hero link = getEnv().getLink();
        if(Math.round(link.getX())==this.getX() && Math.round(link.getY())==this.getY()) {
            link.decrementHp(25);
            return true;
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
