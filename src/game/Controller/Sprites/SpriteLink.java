package game.Controller.Sprites;

import game.Constants;
import game.Model.Game;
import game.Model.Hero;

public class SpriteLink implements Sprite{

    private Game env;
    private static int count = 0;

    public SpriteLink(Game env) {
        this.env = env;
    }

    public Hero getLink() {
        return env.getLink();
    }
    
    public void setLink(Hero h) {
    	this.env.setLink(h);
    }

    @Override
    public int getSprite() {
        int res = -1;

        if (env.getLink().getHp() <= 0) {
            res = Constants.LINK_DEAD;
        }
        else {
            switch (env.getLink().getGlobalDirection()) {
                case Constants.UP -> {
                    switch (env.getLink().getCurrent().getType()) {
                        case BOW -> res = Constants.LINK_UP_BOW;
                        case SWORD -> res = Constants.LINK_UP_SWORD;
                        case FISTS -> res = Constants.LINK_UP_BASE_1;
                    }
                }
                case Constants.RIGHT -> {
                    switch (env.getLink().getCurrent().getType()) {
                        case BOW -> res = Constants.LINK_RIGHT_BOW;
                        case SWORD -> res = Constants.LINK_RIGHT_SWORD;
                        case FISTS -> res = Constants.LINK_RIGHT_BASE_1;
                    }
                }
                case Constants.DOWN -> {
                    switch (env.getLink().getCurrent().getType()) {
                        case BOW -> res = Constants.LINK_DOWN_BOW;
                        case SWORD -> res = Constants.LINK_DOWN_SWORD;
                        case FISTS -> res = Constants.LINK_DOWN_BASE_1;
                    }
                }
                case Constants.LEFT -> {
                    switch (env.getLink().getCurrent().getType()) {
                        case BOW -> res = Constants.LINK_LEFT_BOW;
                        case SWORD -> res = Constants.LINK_LEFT_SWORD;
                        case FISTS -> res = Constants.LINK_LEFT_BASE_1;
                    }
                }
            }
        }

        count++;
        return res;
    }
}
