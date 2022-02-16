package game.Controller.Listeners;

import game.Constants;
import game.Controller.Sprites.SpriteHeart;
import game.Controller.Utils;
import game.Model.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.AudioClip;

import java.awt.image.BufferedImage;
import java.io.File;

public class HeartListener implements ChangeListener<Number> {
	
	private Canvas canvasOverlayContent;
	private BufferedImage tileset;
	private Game env;
	private final AudioClip hurtSound = new AudioClip(new File("assets/music/hurt.wav").toURI().toString());

	public HeartListener(Canvas canvasOverlayContent, BufferedImage tileset, Game env) {
		this.canvasOverlayContent = canvasOverlayContent;
		this.tileset=tileset;
		this.env=env;
	}

	@Override
	public void changed(ObservableValue<? extends Number> obs, Number old, Number nouv) {
		SpriteHeart sh = new SpriteHeart(this.env.getLink());
		Utils.removeFromCanvas(this.canvasOverlayContent, 11, 3, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
		Utils.removeFromCanvas(this.canvasOverlayContent, 12, 3, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
		Utils.removeFromCanvas(this.canvasOverlayContent, 13, 3, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
		Utils.removeFromCanvas(this.canvasOverlayContent, 14, 3, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
		Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 11, 3, sh.getSpriteHeart1());
		Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 12, 3, sh.getSpriteHeart2());
		Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 13, 3, sh.getSpriteHeart3());
		Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 14, 3, sh.getSpriteHeart4());
		hurtSound.play();
	}

}
