package game.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Actor {

    private Game env;
    private DoubleProperty x, y;
    private Types type;
    private double pas;

    private IntegerProperty hp;

    // global direction clockwise: up = 0, right = 1, down = 2, left = 3
    private int globalDirection;
    private int dx,dy;
    
    public abstract Actor tryCatching();
    public abstract void attack();
    public abstract void setHp(int hp);

    public Actor(int hp, double x, double y, double pas, Types type) {
        this.env = null;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.type = type;
        this.pas = pas;

        this.globalDirection=0;
        this.dx = 0;
        this.dy = -1;
        this.hp=new SimpleIntegerProperty(hp);
    }

    public Types getType() {
        return type;
    }

    public int getGlobalDirection() {
		return globalDirection;
	}
	public void setGlobalDirection(int globalDirection) {
		this.globalDirection = globalDirection;
	}
	public boolean canTake(Actor researcher, Actor researched, double scope) {
    	if(	(researcher.getY()-scope<= researched.getY() && researched.getY()<=researcher.getY()+scope) &&
			(researcher.getX()-scope<= researched.getX() && researched.getX()<=researcher.getX()+scope) 
			) {
    		return true;
    	}
    	return false;
    }
	public int[] getCoordonne(double x, double y) {
		int[] tabCoordonne = new int[2];
		switch (getGlobalDirection()) {
	        case 0 -> {
	            x = (int) Math.round(getX());
	            y = (int) Math.floor(getY());
	        }
	        case 1 -> {
	            x = (int) Math.ceil(getX());
	            y = (int) Math.round(getY());
	        }
	        case 2 -> {
	            x = (int) Math.round(getX());
	            y = (int) Math.ceil(getY());
	        }
	        case 3 -> {
	            x = (int) Math.floor(getX());
	            y = (int) Math.round(getY());
	        }
		}
		tabCoordonne[0]=(int) x;
		tabCoordonne[1]=(int) y;
		return tabCoordonne;

	}
	
	public boolean nextToLinkHorizontalLeft(Actor researcher, Actor researched) {
		if(researcher.getX()-1.5 <= researched.getX() && researched.getX() <= researcher.getX()+0.5 && this.canTake(researcher, researched, 0.8) ) {
			return true;
		}
		return false;
	}
	public boolean nextToLinkHorizontalRight(Actor researcher, Actor researched) {
		if(researcher.getX()-0.5 <= researched.getX() && researched.getX() <= researcher.getX()+1.5 && this.canTake(researcher, researched, 0.8)) {
			return true;
		}
		return false;
	}
	public boolean nextToLinkVerticalUp(Actor researcher, Actor researched) {//this.canTake(e, l, 0.9)
		if(researcher.getY()-1.5 <= researched.getY() && researched.getY() <= researcher.getY() && this.canTake(researcher, researched, 0.8))
			return true;
		return false;
	}
	public boolean nextToLinkVerticalDown(Actor researcher, Actor researched) {
		if(researcher.getY() <= researched.getY() && researched.getY() <= researcher.getY()+2 && this.canTake(researcher, researched, 0.8))
			return true;
		return false;
	}
	
	public boolean onSameX(Actor researcher, Actor researched) {
		if(researcher.getX()-0.5 <= researched.getX() && researched.getX() <= researcher.getX()+0.5) {
			return true;
		}
		return false;
	}
	
	public boolean onSameY(Actor researcher, Actor researched) {
		if(researcher.getY()-0.5 <= researched.getY() && researched.getY() <= researcher.getY()+0.5) {
			return true;
		}
		return false;
	}
	
	public int caseX() {
		return (int) (this.getX()/16);
	}
	
	public int caseY() {
		return (int) (this.getY()/16);
	}

    public void setEnv(Game env) {
        this.env = env;
    }

    public Game getEnv() {
        return env;
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public double getPas() {
        return pas;
    }

    public void setPas(double pas) {
        this.pas = pas;
    }

//    public void setHp(int hp) {
//        this.hp.setValue(hp);
//    }
    
    public int getHp() {
    	return this.hp.getValue();
    }

    public IntegerProperty getHpProperty() {
    	return this.hp;
    }
    
    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }


}