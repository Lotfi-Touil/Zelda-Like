package game.Model.Objects;

import game.Model.Game;
import game.Model.Types;

public abstract class Object extends Item {
    private int x, y;
    private Types type;

    public Object(int x, int y, Types type) {
    	super(Types.OBJECT);
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public abstract void interact(Game env);
}
