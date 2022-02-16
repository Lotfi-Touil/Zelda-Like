package game.Controller.Sprites;

import game.Constants;
import game.Model.Enemies.Enemy;

public class SpriteEnemy extends SpriteActor {

    private Enemy enemy;
    public SpriteEnemy(Enemy enemy) {
        super(enemy);
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getSprite() {
        int res = -1;

        switch (enemy.getType()) {
            case BASE_ENEMY -> {
                switch (enemy.getGlobalDirection()) {
                    case Constants.UP -> res = Constants.BASE_ENEMY_UP;
                    case Constants.RIGHT -> res = Constants.BASE_ENEMY_RIGHT;
                    case Constants.DOWN -> res = Constants.BASE_ENEMY_DOWN;
                    case Constants.LEFT -> res = Constants.BASE_ENEMY_LEFT;
                }
            }
            case MINI_BOSS -> {
                switch (enemy.getGlobalDirection()) {
                    case Constants.UP -> res = Constants.MINIBOSS_UP_BASE;
                    case Constants.RIGHT -> res = Constants.MINIBOSS_RIGHT_BASE;
                    case Constants.DOWN -> res = Constants.MINIBOSS_DOWN_BASE;
                    case Constants.LEFT -> res = Constants.MINIBOSS_LEFT_BASE;
                }
            }
            case BOSS -> {
                switch (enemy.getGlobalDirection()) {
                    case Constants.UP -> res = Constants.BOSS_UP_BASE;
                    case Constants.RIGHT -> res = Constants.BOSS_RIGHT_BASE;
                    case Constants.DOWN -> res = Constants.BOSS_DOWN_BASE;
                    case Constants.LEFT -> res = Constants.BOSS_LEFT_BASE;
                }
            }
        }

        return res;
    }
}
