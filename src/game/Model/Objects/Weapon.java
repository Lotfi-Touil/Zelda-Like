package game.Model.Objects;

import game.Model.Hero;
import game.Model.Types;

public abstract class Weapon extends Item {
	private int attackPoint;
	private int attackRange;

	public abstract void attack(Hero link);
	
	public Weapon(int attackPoint, int ar, Types type) {
		super(type);
		this.attackPoint = attackPoint;
		this.attackRange=ar;
		setType(type);
	}
	
	public int getAttackPoint() {
		return this.attackPoint;
	}
	public int getAttackRange() {
		return attackRange;
	}
}
