package game.Model.Objects;

import game.Model.Enemies.MiniBoss;
import game.Model.Game;
import game.Model.Types;

public class Fragment extends Object{
	private MiniBoss miniboss;

	public Fragment(int x, int y, MiniBoss mb) {
		super(x, y, Types.FRAGMENT);
		this.miniboss = mb;
	}

	@Override
	public void interact(Game env) {
		System.out.println("interaction fragment");
		env.getLink().getFragments().add(this);
		env.getLink().getItems().add(this);
//		env.getObjects().remove(this);
	}
	
}
