package game.Model.Enemies;

import game.Constants;
import game.Model.BaseEnemyMovement.*;
import game.Model.Types;

public abstract class HeroBaseEnemy extends Enemy{
	private static int spawn_count=0;
	private int count;

	private TravelMode travelMode;

	public HeroBaseEnemy() {
 		super(180, posX(), posY(), 10, 1, Types.BASE_ENEMY);
 		this.count=0;
 		if(spawn_count%4==0) {
 			this.travelMode=new VerticalMovement();
 		}
 		else if(spawn_count%4==1) {
 			this.travelMode=new HorizontalMovement();
 		}
 		else if(spawn_count%4==2) {// -> |_
 			this.travelMode=new Vertical_Horizontal_Movement();
 		}
 		else if(spawn_count%4==3) {// -> _|
 			this.travelMode=new Horizontal_Vertical_Movement();
 		}
 		spawn_count++;
	}
	
	public HeroBaseEnemy(HeroBaseEnemy e) {
 		super(e.getHp(), e.getX(), e.getY(), 10, 1, Types.BASE_ENEMY);
 		this.count=0;
 		if(spawn_count%4==0) {
 			this.travelMode=new VerticalMovement();
 		}
 		else if(spawn_count%4==1) {
 			this.travelMode=new HorizontalMovement();
 		}
 		else if(spawn_count%4==2) {// -> |_
 			this.travelMode=new Vertical_Horizontal_Movement();
 		}
 		else if(spawn_count%4==3) {// -> _|
 			this.travelMode=new Horizontal_Vertical_Movement();
 		}
 		spawn_count++;
	}

	public void move() {
		this.travelMode.move(this);
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void die() {
		this.getEnv().getCurrentMap().getEnemies().remove(this);
	}

	public static int posX() {
		switch(spawn_count) {
			case 0 -> {
				return Constants.BASE_ENEMY_SPAWN1_X;
			}
			case 1 -> {
				return Constants.BASE_ENEMY_SPAWN2_X;
			}
			case 2 -> {
				return Constants.BASE_ENEMY_SPAWN3_X;
			}
			case 3 -> {
				return Constants.BASE_ENEMY_SPAWN4_X;
			}
			case 4 -> {
				return Constants.BASE_ENEMY_SPAWN5_X;
			}
			case 5 -> {
				return Constants.BASE_ENEMY_SPAWN6_X;
			}
			case 6 -> {
				return Constants.BASE_ENEMY_SPAWN7_X;
			}
			case 7 -> {
				return Constants.BASE_ENEMY_SPAWN8_X;
			}
			case 8 -> {
				return Constants.BASE_ENEMY_SPAWN9_X;
			}
			case 9 -> {
				return Constants.BASE_ENEMY_SPAWN10_X;
			}
			case 10 -> {
				return Constants.BASE_ENEMY_SPAWN11_X;
			}
			case 11 -> {
				return Constants.BASE_ENEMY_SPAWN12_X;
			}
			default -> {
				return 0;
			}
		}
	}

	public static int posY() {
		switch(spawn_count) {
		case 0 -> {
			return Constants.BASE_ENEMY_SPAWN1_Y;
		}
		case 1 -> {
			return Constants.BASE_ENEMY_SPAWN2_Y;
		}
		case 2 -> {
			return Constants.BASE_ENEMY_SPAWN3_Y;
		}
		case 3 -> {
			return Constants.BASE_ENEMY_SPAWN4_Y;
		}
		case 4 -> {
			return Constants.BASE_ENEMY_SPAWN5_Y;
		}
		case 5 -> {
			return Constants.BASE_ENEMY_SPAWN6_Y;
		}
		case 6 -> {
			return Constants.BASE_ENEMY_SPAWN7_Y;
		}
		case 7 -> {
			return Constants.BASE_ENEMY_SPAWN8_Y;
		}
		case 8 -> {
			return Constants.BASE_ENEMY_SPAWN9_Y;
		}
		case 9 -> {
			return Constants.BASE_ENEMY_SPAWN10_Y;
		}
		case 10 -> {
			return Constants.BASE_ENEMY_SPAWN11_Y;
		}
		case 11 -> {
			return Constants.BASE_ENEMY_SPAWN12_Y;
		}
		default -> {
			return 0;
		}
	}
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
