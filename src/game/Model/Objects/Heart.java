package game.Model.Objects;

import game.Model.Game;
import game.Model.Types;

public class Heart extends Object {

    public Heart(int x, int y) {
        super(x, y, Types.HEART);
    }

    @Override
    public void interact(Game env) {
        env.getLink().setHp(env.getLink().getHp() + 50);
    }
}
