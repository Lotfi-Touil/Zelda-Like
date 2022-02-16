package game.Controller.Listeners;

import game.Constants;
import game.Controller.Sprites.SpritePNJ;
import game.Controller.Utils;
import game.Model.PNJ;
import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;

import java.awt.image.BufferedImage;

public class PNJsListener implements ListChangeListener<PNJ> {

    private BufferedImage tileset;
    private Canvas canvas;

    public PNJsListener(BufferedImage tileset, Canvas canvas) {
        this.tileset = tileset;
        this.canvas = canvas;
    }

    @Override
    public void onChanged(Change<? extends PNJ> change) {
        while (change.next()) {
            for (PNJ pnj : change.getAddedSubList()) {
                SpritePNJ spritePNJ = new SpritePNJ(pnj);
                Utils.drawOnCanvas(this.canvas, this.tileset, spritePNJ.getPnj().getX(), spritePNJ.getPnj().getY() - 1, spritePNJ.getSprite() - Constants.SPRITE_UP);
                Utils.drawOnCanvas(this.canvas, this.tileset, spritePNJ.getPnj().getX(), spritePNJ.getPnj().getY(), spritePNJ.getSprite());
                Utils.drawOnCanvas(this.canvas, this.tileset, spritePNJ.getPnj().getX(), spritePNJ.getPnj().getY() - 1, 2562);
            }
        }
    }
}
