package game.Controller.Sprites;

import game.Constants;
import game.Model.Game;
import game.Model.Objects.Potions.Potion;

public class SpritePotion extends SpriteObject {

    private Potion potion;

    public SpritePotion(Potion potion, Game env) {
        super(potion, env);
        this.potion = potion;
    }

    public Potion getPotion() {
        return potion;
    }

    @Override
    public int getSprite() {
        int res = -1;

        res = Constants.POTION;
        
        

        return res;
    }
}
