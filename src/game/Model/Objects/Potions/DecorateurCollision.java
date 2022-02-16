package game.Model.Objects.Potions;

import game.Constants;
import game.Model.Hero;

public class DecorateurCollision extends Hero {

	public DecorateurCollision(Hero link){
		super(link.getHp(), link.getX(), link.getY(), Constants.PAS);
	}

	public void move(int direction) {
		switch (direction) {
            case 0 -> { //UP
                if (getY() - 1 >= 0) {
                     setGlobalDirection(0);
                     setDx(0);
                     setDy(-1);
                     setY(getY() - getPas());
                }
            }
            case 1 -> { //RIGHT
                if (getX() + 1 <= 32) {
                   setGlobalDirection(1);
                   setDx(1);
                   setDy(0);
                   setX(getX() + getPas());
                }
            }
            case 2 -> { //DOWN
                if (getY() + 1 <= 32) {
                    setGlobalDirection(2);
                    setDx(0);
                    setDy(1);
                    setY(getY() + getPas());
                }
            }
            case 3 -> { //LEFT
                if (getX() - 1 >= 0) {
                    setGlobalDirection(3);
                    setDx(-1);
                    setDy(0);
                    setX(getX() - getPas());
                }
            }
        }

        checkForMapSwitch();
	}
}


