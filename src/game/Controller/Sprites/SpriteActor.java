package game.Controller.Sprites;

import game.Model.Actor;

public abstract class SpriteActor implements Sprite {
    private Actor actor;

    public SpriteActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
    	return this.actor;
    }
    
    public abstract int getSprite();
}
