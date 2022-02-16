package game.Controller.Sprites;

import game.Constants;
import game.Model.Hero;

public class SpriteHeart extends SpriteActor{
	
	private int spriteHeart1;
	private int spriteHeart2;
	private int spriteHeart3;
	private int spriteHeart4;
	
	public SpriteHeart(Hero link) {
		super(link);
		this.spriteHeart1 =firstSprite();
		this.spriteHeart2 =secondSprite();
		this.spriteHeart3 =thirdSprite();
		this.spriteHeart4 =fourthSprite();
		
	}

	private int firstSprite() {
		if(this.getActor().getHp()<=0)
			return Constants.EMPTY_HEART;
		else if(this.getActor().getHp()>=50)
			return Constants.FULL_HEART;
		else if(this.getActor().getHp()>=37.5)
			return Constants.THREE_QUARTER_HEART;
		else if(this.getActor().getHp()>=25)
			return Constants.HALF_HEART;
		else // hp >= 12.5)
			return Constants.QUARTER_HEART;
	}
	
	private int secondSprite() {
		if(this.getActor().getHp()<50)
			return Constants.EMPTY_HEART;
		else if(this.getActor().getHp()>=100)
			return Constants.FULL_HEART;
		else if(this.getActor().getHp()>=87.5)
			return Constants.THREE_QUARTER_HEART;
		else if(this.getActor().getHp()>=75)
			return Constants.HALF_HEART;
		else // hp >= 62.5)
			return Constants.QUARTER_HEART;
	}

	private int thirdSprite() {
		if(this.getActor().getHp()<100)
			return Constants.EMPTY_HEART;
		else if(this.getActor().getHp()>=150)
			return Constants.FULL_HEART;
		else if(this.getActor().getHp()>=137.5)
			return Constants.THREE_QUARTER_HEART;
		else if(this.getActor().getHp()>=125)
			return Constants.HALF_HEART;
		else // hp >= 112.5)
			return Constants.QUARTER_HEART;
	}

	private int fourthSprite() {
		if(this.getActor().getHp()<=150)
			return Constants.EMPTY_HEART;
		else if(this.getActor().getHp()>=200)
			return Constants.FULL_HEART;
		else if(this.getActor().getHp()>=187.5)
			return Constants.THREE_QUARTER_HEART;
		else if(this.getActor().getHp()>=175)
			return Constants.HALF_HEART;
		else // hp >= 162.5)
			return Constants.QUARTER_HEART;
	}

	public int getSpriteHeart1() { return this.spriteHeart1; }
	public int getSpriteHeart2() { return this.spriteHeart2; }
	public int getSpriteHeart3() { return spriteHeart3; }
	public int getSpriteHeart4() { return spriteHeart4; }

	@Override
	public int getSprite() {
		return 0;
	}
	
}
