package game.Controller.Sprites;

import game.Constants;
import game.Model.PNJ;

public class SpritePNJ implements Sprite {

    private PNJ pnj;

    public SpritePNJ(PNJ pnj) {
        this.pnj = pnj;
    }

    public PNJ getPnj() {
        return pnj;
    }

    @Override
    public int getSprite() {
        return Constants.PNJ_DOWN;
    }
}
