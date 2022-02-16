 package game.Model.Enemies;

 import game.Model.Actor;
 import game.Model.Hero;
 import game.Model.Types;

public abstract class Enemy extends Actor {
	 
	 private int attackPoint;
	 private int attackRange;

	 public abstract void move();
	 public abstract void die();
	 
 	public Enemy(int hp, double x, double y, int ap, int ar, Types type) {
 		super(hp, x, y, 0.4, type);
 		this.attackPoint=ap;
 		this.attackRange=ar;
 	}

	public int getAttackRange() {
		return this.attackRange;
	}

 	public void decrementHp(int value) {
 		this.setHp(this.getHp()-value);
 	}

	public boolean nextToLinkHorizontalLeft(Enemy e) {
		Hero l = this.getEnv().getLink();
		if(e.getX()-1.5 <= l.getX() && l.getX() <= e.getX()+0.5 && this.canTake(e, l, 0.9) ) {
			System.out.println(e.getX()+""+l.getX());
			return true;
		}
		return false;
	}
	public boolean nextToLinkHorizontalRight(Enemy e) {
		Hero l = this.getEnv().getLink();
		if(e.getX()-0.5 <= l.getX() && l.getX() <= e.getX()+1.5 && this.canTake(e, l, 0.9)) {
			return true;
		}
		return false;
	}
	public boolean nextToLinkVerticalUp(Enemy e) {
		Hero l = this.getEnv().getLink();
		if(e.getY()-1.5 <= l.getY() && l.getY() <= e.getY() && this.canTake(e, l, 0.9))
			return true;
		return false;
	}
	public boolean nextToLinkVerticalDown(Enemy e) {
		Hero l = this.getEnv().getLink();
		if(e.getY() <= l.getY() && l.getY() <= e.getY()+2 && this.canTake(e, l, 0.9))
			return true;
		return false;
	}
	
	@Override
	public Actor tryCatching() {
		Hero l = this.getEnv().getLink();
		if(canTake(this, l, this.getAttackRange())) {
			this.hurt();
		}
		return null;
	}
	
	public void hurt() {
		Hero l = this.getEnv().getLink();
		l.decrementHp(this.getAttackPoint());
	}

	public void action() {
		this.move();
	}

	public int getAttackPoint() {
		return this.attackPoint;
	}
	
  public void setHp(int hp) {
	  this.getHpProperty().setValue(hp);
  }
  
 }
