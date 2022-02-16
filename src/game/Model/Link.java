package game.Model;

import game.Constants;

public class Link extends Hero {

    public Link(double x, double y, double pas) {
        super(Constants.LINK_HP, x, y, pas);
    }

    public Link(Hero link) {
    	super(link.getHp(),link.getX(),link.getY(),Constants.PAS);
    }

    public void move(int direction) {
        int[][] mapCollision = getEnv().getCurrentMap().getMaps().get("collision");
        switch (direction) {
            case 0 -> { //UP
                if (getY() - 1 >= 0) {
                    if (mapCollision[(int) Math.floor(getY())][(int) Math.round(getX())] == -1
                    		&& !nextToEnemyUp()) {
                        setGlobalDirection(0);
                        setDx(0);
                        setDy(-1);
                        setY(getY() - getPas());
                    }
                }
            }
            case 1 -> { //RIGHT
                if (getX() + 1 <= 32) {
                    if (mapCollision[(int) Math.round(getY())][(int) (Math.ceil(getX()))] == -1
                    		&& !nextToEnemyRight()) {
                        setGlobalDirection(1);
                        setDx(1);
                        setDy(0);
                        setX(getX() + getPas());
                    }
                }
            }
            case 2 -> { //DOWN
                if (getY() + 1 <= 32) {
                    if (mapCollision[(int) (Math.ceil(getY()))][(int) Math.round(getX())] == -1
                    		&& !nextToEnemyDown()) {
                        setGlobalDirection(2);
                        setDx(0);
                        setDy(1);
                        setY(getY() + getPas());
                    }
                }
            }
            case 3 -> { //LEFT
                if (getX() - 1 >= 0) {
                    if (mapCollision[(int) Math.round(getY())][(int) Math.floor(getX())] == -1
                    		&& !nextToEnemyLeft()) {
                        setGlobalDirection(3);
                        setDx(-1);
                        setDy(0);
                        setX(getX() - getPas());
                    }
                }
            }
        }

        checkForMapSwitch();
    }

}