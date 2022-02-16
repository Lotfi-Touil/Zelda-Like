package game.Controller.Sprites;

import game.Constants;
import game.Model.Objects.Projectile;
import game.Model.Types;
import javafx.scene.media.AudioClip;

import java.io.File;

public class SpriteProjectile implements Sprite {
    private Projectile projectile;
    private final AudioClip fireballSound = new AudioClip(new File("assets/music/fireball.wav").toURI().toString());

    public SpriteProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public int getSprite() {
        int res = -1;

        if (projectile.getType() == Types.ARROW) {
            switch (projectile.getDirection()) {
                case Constants.UP -> res = Constants.ARROW_UP;
                case Constants.RIGHT -> res = Constants.ARROW_RIGHT;
                case Constants.DOWN -> res = Constants.ARROW_DOWN;
                case Constants.LEFT -> res = Constants.ARROW_LEFT;
            }
        }
        else {
            res = Constants.FIREBALL;
        }

        return res;
    }
}
