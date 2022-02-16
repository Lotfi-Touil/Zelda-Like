package game.Controller.Listeners;

import game.Constants;
import game.Controller.Utils;
import game.Model.Hero;
import game.Model.Objects.Object;
import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.AudioClip;

import java.awt.image.BufferedImage;
import java.io.File;

public class FragmentListener implements ListChangeListener<Object> {
	private Canvas canvasOverlayContent;
	private Hero link;
	private BufferedImage tileset;
	private final AudioClip fragmentSound = new AudioClip(new File("assets/music/fragment.wav").toURI().toString());

	public FragmentListener(Canvas canvasOverlayContent, BufferedImage b, Hero l) {
		this.canvasOverlayContent=canvasOverlayContent;
		this.link=l;
		this.tileset=b;
	}
	
	@Override
	public void onChanged(Change<? extends Object> c) {
		fragmentSound.play();
		int count = 11;
		for(int i=0; i<this.link.getFragments().size(); i++) {
			Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, count, 6, Constants.FRAGMENT);
			count += 1;
		}
	}

}
