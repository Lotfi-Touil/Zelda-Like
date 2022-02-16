package game.Model.Objects.Potions;

import game.Model.Game;
import game.Model.Hero;
import game.Model.Link;

public class PotionCollision extends Potion {
	
	public PotionCollision(int x, int y) {
		super(x,y);
	}

	@Override
	public void interact(Game env) {
		env.getLink().setPotions(this);
		env.getLink().getItems().add(this);
	}

	public void consume(Game env) {
		env.getLink().getItems().remove(this);
		Hero link = env.getLink();
		link = new DecorateurCollision(env.getLink());
		env.setLink(link);
		env.getLink().setEnv(env);
	}
	
	public void endEffect(Game env) {
		Hero link = env.getLink();
		link = new Link(env.getLink());
		env.setLink(link);
		env.getLink().setEnv(env);
	}

}
