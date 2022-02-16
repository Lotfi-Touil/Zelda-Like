package game.Controller.Listeners;

import game.Constants;
import game.Controller.Sprites.SpriteObject;
import game.Controller.Utils;
import game.Model.Game;
import game.Model.Objects.Object;
import game.Model.Types;
import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.AudioClip;

import java.awt.image.BufferedImage;
import java.io.File;

public class ObjectsListener implements ListChangeListener<Object> {

    private Game env;
    private BufferedImage tileset;
    private Canvas canvas;
    private final AudioClip pickSound = new AudioClip(new File("assets/music/pick.wav").toURI().toString());
    private final AudioClip heartSound = new AudioClip(new File("assets/music/heart.wav").toURI().toString());

    public ObjectsListener(Game env, BufferedImage tileset, Canvas canvas) {
        this.env = env;
        this.tileset = tileset;
        this.canvas = canvas;
    }

    @Override
    public void onChanged(Change<? extends Object> change) {
        while(change.next()) {
            for (Object o : change.getRemoved()) {

                SpriteObject spriteObject = new SpriteObject(o, this.env);

                int x = spriteObject.getObject().getX();
                int y = spriteObject.getObject().getY();

                Utils.removeFromCanvas(this.canvas, x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);

                if (spriteObject.getSprite() == Constants.CHEST_OPENED) {
                    Utils.drawOnCanvas(this.canvas, this.tileset, x, y, Constants.CHEST_OPENED);
                }

                if (spriteObject.getObject().getType() == Types.HEART) {
                    heartSound.play();
                } else {
                    pickSound.play();
                }
            }
        }
    }
}
