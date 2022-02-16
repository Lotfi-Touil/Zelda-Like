package game.Model.Objects;

import game.Model.Game;
import game.Model.Types;

import java.util.Random;

public class Chest extends Object {
    private Item item;

    public Chest(int x, int y) {
        super(x, y, Types.CHEST_CLOSED);
    }

    @Override
    public void interact(Game env) {
        Random random = new Random();
        setType(Types.CHEST_OPENED);
        Weapon w = env.getREWARDS().get(random.nextInt(env.getREWARDS().size()));

        env.getLink().importWeapon(w);
        env.getLink().getItems().add(w);

//        if (random.nextDouble() <= 0.25) {
//            env.getLink().setShield(new Shield(env));
//        }
    }
}
