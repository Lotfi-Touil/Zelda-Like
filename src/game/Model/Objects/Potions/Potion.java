package game.Model.Objects.Potions;

import game.Model.Game;
import game.Model.Hero;
import game.Model.Objects.Object;
import game.Model.Types;

public abstract class Potion extends Object{

	private int duration;

	private Hero link;

	public Potion(int x, int y) {
		super(x, y, Types.POTION);
		this.duration = 10;
		this.link = null;
	}

	public abstract void interact(Game env);

	public abstract void consume(Game env);

	public abstract void endEffect(Game env);

	public int getDuration() {
		return this.duration;
	}
	
	public void decrementDuration() {
		this.duration = duration -1;
	}
}
