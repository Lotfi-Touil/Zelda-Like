package game.Model.Objects;

import game.Model.Game;
import game.Model.Types;

public abstract class Projectile extends Item {

    private Game env;
    private int x, y;
    private int porte;
    private int direction;

    public Projectile(Game env, int x, int y, int direction, Types type) {
        super(type);
        this.env = env;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.porte = 15;
    }

    public Game getEnv() {
        return env;
    }

    public void setEnv(Game env) {
        this.env = env;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void moveProjectile() {
        switch (this.direction) {
            case 0 -> {
                this.setY(this.getY()-1);
            }
            case 1 -> {
                this.setX(this.getX()+1);
            }
            case 2 -> {
                this.setY(this.getY()+1);
            }
            case 3 -> {
                this.setX(this.getX()-1);
            }
        }
    }

    public abstract boolean isHit();
    public abstract boolean attack();
}
