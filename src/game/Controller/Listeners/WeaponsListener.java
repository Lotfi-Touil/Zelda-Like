package game.Controller.Listeners;

import game.Constants;
import game.Controller.Utils;
import game.Model.Objects.Weapon;
import game.Model.Types;
import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;

import java.awt.image.BufferedImage;

public class WeaponsListener implements ListChangeListener<Weapon>{
	private Canvas canvasOverlayContent;
	private BufferedImage tileset;
	private Types type;

	public WeaponsListener(Canvas canvasOverlayContent, BufferedImage tileset) {
		this.canvasOverlayContent=canvasOverlayContent;
		this.tileset=tileset;
	}

	@Override
	public void onChanged(Change<? extends Weapon> c) {
		int  count = 11;
		for(int i=0; i<c.getList().size(); i++) {
			this.type = c.getList().get(i).getType();
			Utils.removeFromCanvas(this.canvasOverlayContent, count, 1, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
			Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, count, 1, getSpriteFromType(this.type));
			count += 1;
		}
	}
	
	private int getSpriteFromType(Types type) {
		int res = -1;
		switch(type) {
			case FISTS -> res = Constants.FIST;
			case BOW -> res = Constants.BOW;
			case SWORD -> res = Constants.SWORD;
		}
		return res;
	}
	
}
