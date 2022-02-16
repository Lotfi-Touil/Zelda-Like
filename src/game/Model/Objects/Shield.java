package game.Model.Objects;

import game.Model.Game;
import game.Model.Hero;
import game.Model.Types;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Shield extends Item {
	
	private IntegerProperty defensePoint;
	private Game env;
	
	public Shield(Game env) {
		super(Types.SHIELD);
		this.env = env;
		this.defensePoint=new SimpleIntegerProperty(50);
	}
	
	public void beAttacked(int val) {
		Shield s = this.env.getLink().getShield();
		Hero l = this.env.getLink();
		
		int ecart = val - s.getDefensePoint();
		s.decrementDp(val);
		if(ecart>0) {
 			l.setHp((l.getHp()-ecart)); 
		}
		if(s.getDefensePoint()<=0) {
			l.setShield(null);
		}
	}

	public void decrementDp(int value) {
		this.setDefensePoint(this.getDefensePoint()-value);
	}
	
	public void setDefensePoint(int defensePoint) {
		this.defensePoint.setValue(defensePoint);;
	}

	public int getDefensePoint() {
		return this.defensePoint.getValue();
	}

	public IntegerProperty getDefensePointProperty() {
		return this.defensePoint;
	}

}