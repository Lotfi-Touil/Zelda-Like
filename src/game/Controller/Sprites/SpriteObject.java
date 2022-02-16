package game.Controller.Sprites;

import game.Model.Game;
import game.Model.Objects.Object;

public class SpriteObject implements Sprite {
    private Object object;
    private Game env;

    public SpriteObject(Object object, Game env) {
        this.object = object;
        this.env = env;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public int getSprite() {
        return env.getCurrentMap().getMaps().get("objects")[object.getY()][object.getX()];
    }
}
