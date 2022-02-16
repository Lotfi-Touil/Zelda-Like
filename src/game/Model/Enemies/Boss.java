package game.Model.Enemies;

import game.Constants;
import game.Model.Game;
import game.Model.Hero;
import game.Model.Objects.Flamethrower;
import game.Model.Pathfinding.A_Star;
import game.Model.Pathfinding.Node;
import game.Model.Types;

import java.util.Iterator;

public class Boss extends Enemy {

    private A_Star a_star;
    private Iterator<Node> pathIterator;
    private Hero l;
    private Flamethrower flamethrower;

    public Boss(Game env, int hp, int x, int y, int ap, int ar) {
        super(hp, x, y, ap, ar, Types.BOSS);
        setEnv(env);

        this.l = this.getEnv().getLink();
        this.flamethrower = new Flamethrower(env, this);

        this.a_star = new A_Star(getEnv().getCurrentMap().getMaps().get("collision"), false);
        this.a_star.findPath((int) getX(),(int) getY(), (int) l.getX(), (int) l.getY(), false);
        this.pathIterator = this.a_star.getPathIterator();

        l.xProperty().addListener(((observableValue, number, t1) -> {
            if (canTake(this, l, 5)) {
                this.a_star.findPath((int) getX(),(int) getY(), (int) l.getX(), (int) l.getY(), false);
                this.pathIterator = this.a_star.getPathIterator();
            }
        }));
        l.yProperty().addListener(((observableValue, number, t1) -> {
            if (canTake(this, l, 5)) {
                this.a_star.findPath((int) getX(),(int) getY(), (int) l.getX(), (int) l.getY(), false);
                this.pathIterator = this.a_star.getPathIterator();
            }
        }));
    }

    public void die() {
        this.getEnv().getCurrentMap().getEnemies().remove(this);
    }
    
	@Override
	public void attack() {
		tryCatching();
	}

    @Override
    public void move() {
        if (canTake(this, l, 5)) {
            if (this.pathIterator.hasNext()) {
                Node nextMove = this.pathIterator.next();
                int[][] mapCollision = getEnv().getCurrentMap().getMaps().get("collision");
                if (mapCollision[nextMove.y][nextMove.x] == -1) {
                    if (Double.compare(getX(), nextMove.x) < 0) {
                        setGlobalDirection(Constants.RIGHT);
                    }
                    if (Double.compare(getX(), nextMove.x) > 0) {
                        setGlobalDirection(Constants.LEFT);
                    }
                    if (Double.compare(getY(), nextMove.y) < 0) {
                        setGlobalDirection(Constants.DOWN);
                    }
                    if (Double.compare(getY(), nextMove.y) > 0) {
                        setGlobalDirection(Constants.UP);
                    }

                    setX(nextMove.x);
                    setY(nextMove.y);
                    flamethrower.attack(l);
                }
            }
        }
    }

}
