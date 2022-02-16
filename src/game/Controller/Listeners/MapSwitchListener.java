package game.Controller.Listeners;

import game.Controller.Utils;
import game.Model.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.AudioClip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class MapSwitchListener implements ChangeListener<Boolean> {

    private Game env;
    private BufferedImage tileset;
    private Canvas[] canvas;
    private final AudioClip switchSound = new AudioClip(new File("assets/music/switch.wav").toURI().toString());

    public MapSwitchListener(Game env, BufferedImage tileset, Canvas... canvas) {
        this.env = env;
        this.tileset = tileset;
        this.canvas = canvas;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        if (t1) {
            System.out.println("switch to map " + this.env.getCurrentMap().getName());
            for (Canvas c : this.canvas) {
                Utils.clearCanvas(c);
            }

            for (String s : Arrays.asList("sol", "murs", "decors", "misc", "pnjs")) {
                Utils.mapToCanvas(this.tileset, this.canvas[0], this.env.getCurrentMap().getMaps().get(s));
            }
            Utils.mapToCanvas(this.tileset, this.canvas[3], this.env.getCurrentMap().getMaps().get("destructible"));
            Utils.mapToCanvas(this.tileset, this.canvas[4], this.env.getCurrentMap().getMaps().get("objects"));
            Utils.mapToCanvas(this.tileset, this.canvas[5], this.env.getCurrentMap().getMaps().get("derrieres"));
            switchSound.play();
        }
    }
}
