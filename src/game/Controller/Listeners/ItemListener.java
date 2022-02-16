package game.Controller.Listeners;

import game.Constants;
import game.Controller.Utils;
import game.Model.Hero;
import game.Model.Objects.Item;
import game.Model.Types;
import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ItemListener implements ListChangeListener<Item> {

	private Hero link;
	private Canvas canvasInventaire;
	private Canvas canvasOverlayContent;
	private BufferedImage tileset;
	private Types type;
	
	public ItemListener(Hero l, Canvas canvasOverlayContent, BufferedImage tileset) {
		this.link = l;
		this.canvasOverlayContent = canvasOverlayContent;
		this.tileset = tileset;
	}
	
	@Override
	public void onChanged(Change<? extends Item> arg0) {
		ArrayList<Types> typesList = new ArrayList<>();
		Utils.removeFromCanvas(this.canvasOverlayContent, 0, 0, 160, 128);
		int count = 1;
		for(Item i : this.link.getItems()) {
			this.type = i.getType();
			if (!typesList.contains(i.getType())) {
				Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, count, 2, getSpriteFromType(this.type));
				typesList.add(i.getType());
			}
			count += 1;
		}
	}

	private int getSpriteFromType(Types type) {
		int res = -1;
		switch(type) {
			case POTION -> res = Constants.POTION;
			case CAP -> res = Constants.CAP;
			case FRAGMENT -> res = Constants.FRAGMENT;
			case FISTS -> res = Constants.FIST;
			case BOW -> res = Constants.BOW;
			case SWORD -> res = Constants.SWORD; 
		}
		return res;
	}

}
