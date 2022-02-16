package game.Controller.Sprites;

import game.Constants;
import game.Model.Game;
import game.Model.Objects.Chest;

public class SpriteChest extends SpriteObject {
    private Chest chest;

    public SpriteChest(Chest chest, Game env) {
        super(chest, env);
        this.chest = chest;
    }

    public Chest getChest() {
        return chest;
    }

    @Override
    public int getSprite() {
        int res = -1;

        switch (this.chest.getType()) {
            case CHEST_OPENED -> res = Constants.CHEST_OPENED;
            case CHEST_CLOSED -> res = Constants.CHEST_CLOSED;
        }

        return res;
    }
}
