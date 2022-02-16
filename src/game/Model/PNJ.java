package game.Model;

import game.Model.Objects.Object;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PNJ extends Object {
	
	private static int count = 0;
	private final String message;
	private Game env;
	private BooleanProperty isSpeaking;

	public PNJ(int x, int y) {
		super(x, y, Types.PNJ);

		if(count==0)
			this.message="Pour passer à travers les buissons, \n détruit les en attaquant!";
		else if(count==1)
			this.message="Le donjon final se trouve en dessous \nde moi, dans la grotte!";
		else if(count==2)
			this.message="Tues les 3 MiniBoss et récupères leurs \n fragments pour entrer dans la grotte!";
		else
			this.message="Tu peux ouvrir des coffres, récupérer \n des coeurs et utiliser des potions.\n Sors d'ici!";
		
		this.isSpeaking = new SimpleBooleanProperty(false);

		count++;
	}

	public BooleanProperty isSpeakingProperty() {
		return isSpeaking;
	}

	public void setIsSpeaking(boolean isSpeaking) {
		this.isSpeaking.set(isSpeaking);
	}

	public String getMessage() {
		return message;
	}

	public boolean canTake(PNJ researcher, Actor researched, int scope) {
		return (researcher.getY() - scope <= researched.getY() && researched.getY() <= researcher.getY() + scope) &&
				(researcher.getX() - scope <= researched.getX() && researched.getX() <= researcher.getX() + scope);
	}

	@Override
	public void interact(Game env) {
		if (canTake(this, env.getLink(), 1)) {
			setIsSpeaking(true);
		}
	}
}
