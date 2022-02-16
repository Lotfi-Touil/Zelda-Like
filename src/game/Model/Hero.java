package game.Model;

import game.Constants;
import game.Model.Enemies.Enemy;
import game.Model.Objects.Object;
import game.Model.Objects.*;
import game.Model.Objects.Potions.Potion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public abstract class Hero extends Actor {
	
	private ObservableList<Item> items;
    private ObservableList<Weapon> weapons;
	private Weapon current;
	private Shield shield;
	private IntegerProperty ammo;
	private Potion potions;
    private ObservableList<Fragment> fragments;

    public Hero(int hp, double x, double y, double pas) {
		super(hp, x, y, pas, Types.LINK);
		this.items =FXCollections.observableArrayList();
        this.current = new Fists();
        this.shield = null;
        this.ammo = new SimpleIntegerProperty(10);
        this.weapons = FXCollections.observableArrayList();
        initializeWeapons();
        this.fragments = FXCollections.observableArrayList();
        this.potions = null;
	}

    public ObservableList<Weapon> getWeapons() {
        return weapons;
    }

    public ObservableList<Fragment> getFragments() {
        return fragments;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public IntegerProperty ammoProperty() {
        return ammo;
    }

    public Enemy tryCatching() {
		for(Enemy e : this.getEnv().getCurrentMap().getEnemies()){
			if(this.getCurrent()!=null) {
				if(canTake(this, e, this.getCurrent().getAttackRange())){
					return e;
				}
			}
		}
		return null;
	}
	public abstract void move(int direction);
	
	public void attack() {
		if (isArmed()) {
    		this.getCurrent().attack(this.getEnv().getLink());
        }
	}

    private void initializeWeapons() {
        for(int i=0; i<3; i++) {
            this.weapons.add(new Fists());
        }
    }

    public void importWeapon(Weapon w) {
        switch(w.getType()) {
            case SWORD -> this.weapons.set(1, w);
            case BOW -> this.weapons.set(2, w);
        }
    }

    public void chooseWeapon(int choice) {
        this.setCurrent(this.weapons.get(choice));
        this.ammo = new SimpleIntegerProperty(10);
    }

    public void setAmmo(int i) {
    	this.ammo.setValue(i);
    }

    public int getValueAmmo() {
    	return this.ammo.getValue();
    }
    
    public IntegerProperty getAmmo() {
    	return this.ammo;
    }
    
    public Potion getPotions() {
    	return this.potions;
    }
    
    public void setPotions(Potion p) {
    	this.potions =p;
    }
	public Weapon getCurrent() {
    	return this.current;
    }

    public void addEquipment(Item e) {
    	if(items.size()<10)
    	this.items.add(e);
    }
        
 	public void decrementHp(int value) {
 		if(this.shield==null) {
 			this.setHp(this.getHp()-value); }
 		else 
 			this.shield.beAttacked(value);
 	}
 	
    public boolean nextToEnemyLeft() {
        for(Enemy e : this.getEnv().getCurrentMap().getEnemies()) {
            if(canTake(this, e, 0.8)) {
                if(this.getX()-1.5 <= e.getX() && e.getX() <= this.getX()-0.5) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean nextToEnemyRight() {
        for(Enemy e : this.getEnv().getCurrentMap().getEnemies()) {
            if(canTake(this, e, 0.8)) {
                if(this.getX()+0.5 <= e.getX() && e.getX() <= this.getX()+1.5) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean nextToEnemyDown() {
        for(Enemy e : this.getEnv().getCurrentMap().getEnemies()) {
            if(canTake(this, e, 0.9)) {
                if(this.getY() <= e.getY() && e.getY() <= this.getY()+2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean nextToEnemyUp() {
        for(Enemy e : this.getEnv().getCurrentMap().getEnemies()) {
            if(canTake(this, e, 0.9)) {
                if(this.getY()-1.5 <= e.getY() && e.getY() <= this.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean attack_destructible() {
        int[][] mapDestructible = getEnv().getCurrentMap().getMaps().get("destructible");
        int[][] mapCollision = getEnv().getCurrentMap().getMaps().get("collision");
        int[] tabCoordonne =this.getCoordonne(getX(), getY());
        boolean res = false;
        
        int x = tabCoordonne[0];
        int y = tabCoordonne[1];
        if (mapDestructible[y][x] != -1) {
        	mapCollision[y][x] = -1;
        	mapDestructible[y][x] = -1;
            res = true;
        }
        if (res) {
            int finalY = y;
            int finalX = x;

            this.getEnv().getCurrentMap().getDestructibles().removeIf(d_i -> d_i == Constants.MAP_BLOCK_WIDTH * finalY + finalX);
            return true;
        }

        return false;
    }
    
    public Shield getShield() {
		return this.shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}
	
	public boolean isArmed() {
    	return this.current!=null;
    }

    public void interact() {
        int[][] mapObjects = getEnv().getCurrentMap().getMaps().get("objects");
        int[][] mapCollision = getEnv().getCurrentMap().getMaps().get("collision");
        int[] tabCoordonne =this.getCoordonne(getX(), getY());

        int x = tabCoordonne[0];
        int y = tabCoordonne[1];

        Iterator<Object> objectIterator = this.getEnv().getCurrentMap().getObjects().iterator();
        while (objectIterator.hasNext()) {
            Object e = objectIterator.next();
            if(e.getType()==Types.FRAGMENT) {
                System.out.println("c'est un fragment");
                System.out.println("frag x/y : "+e.getX()+"/"+e.getY());
                System.out.println("Link x/y : "+x+"/"+y);

                if(x-1.5 < e.getX() && x+1.5 > e.getX()
                        && y-1.5 < e.getY() && y+1.5 > e.getY()){
                    e.interact(getEnv());
                    objectIterator.remove();
                }
            }
            if (x == e.getX() && y == e.getY()) {
                if (mapObjects[y][x] != -1) {
                    e.interact(getEnv());
                    if (mapObjects[y][x] == Constants.LIFE_HEART || mapObjects[y][x] == Constants.POTION) {
                        mapCollision[y][x] = -1;
                        mapObjects[y][x] = -1;
                    }
                    if (mapObjects[y][x] == Constants.CHEST_CLOSED) {
                        mapObjects[y][x] = Constants.CHEST_OPENED;
                    }
                    objectIterator.remove();
                }
            }
        }

        for(PNJ p : getEnv().getCurrentMap().getPnjs()) {
            if (p.getX() == x && p.getY() == y) {
                p.interact(getEnv());
            }
        }
    }

    public void setCurrent(Weapon e) {
        this.current = e;
        this.current.setType(e.getType());
    }
    
    public void activatePotion() {
    	if(this.potions != null) {
    		this.potions.consume(getEnv());
    		this.getEnv().setPotionActive(potions);
    		this.potions = null;
    	}
    }
    
    @Override
    public void setHp(int hp) {
        getHpProperty().setValue(hp);
        if (getHp() > 200) {
            getHpProperty().setValue(200);
        }
    }

    public void checkForMapSwitch() {
        // Changement maison vers exterieur
        if ((getX() >= 15 && getX() < 17) && (getY() < 22 && getY() > 21)) {
            if (getEnv().getCurrentMap().getName().equals("house")) {
                getEnv().switchMap(
                        getEnv().getMaps().get("base"),
                        getEnv().getMaps().get("base").getLink_start_x(),
                        getEnv().getMaps().get("base").getLink_start_y()
                );
            }
        }

        // Changement exterieur vers maison
        if ((getX() >= 6 && getX() < 7) && (getY() < 4 && getY() >= 3)) {
            if (getEnv().getCurrentMap().getName().equals("base")) {
                getEnv().switchMap(
                        getEnv().getMaps().get("house"),
                        16,
                        18
                );
            }
        }

        // Changement exterieur vers donjon
        if ((getX() >= 5 && getX() < 6) && (getY() < 14 && getY() > 13)) {
            if (getFragments().size() > 2) {
                if (getEnv().getCurrentMap().getName().equals("base")) {
                    getEnv().switchMap(
                            getEnv().getMaps().get("donjon"),
                            getEnv().getMaps().get("donjon").getLink_start_x(),
                            getEnv().getMaps().get("donjon").getLink_start_y()
                    );
                }
            }
        }

        // Changement donjon vers exterieur
        if ((getX() > 14 && getX() < 17) && (getY() < 32 && getY() > 31)) {
            if (getEnv().getCurrentMap().getName().equals("donjon")) {
                getEnv().switchMap(
                        getEnv().getMaps().get("base"),
                        5,
                        15
                );
            }
        }
    }

}
