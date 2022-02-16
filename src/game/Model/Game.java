package game.Model;

import game.Model.Enemies.Boss;
import game.Model.Enemies.Enemy;
import game.Model.Objects.Bow;
import game.Model.Objects.Potions.Potion;
import game.Model.Objects.Projectile;
import game.Model.Objects.Sword;
import game.Model.Objects.Weapon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private Hero link;
    private Boss bossFinal;
    private Map currentMap;
    private java.util.Map<String, Map> maps;
    private BooleanProperty hasSwitchedMap;
    private ArrayList<Projectile> projectiles;
    private Potion potionActive;
    private final ArrayList<Weapon> REWARDS;

    public Game(int w, int h, Hero link, Map... argsMaps) {
        this.link = link;
        this.currentMap = argsMaps[0];
        this.maps = new HashMap<>();
        this.hasSwitchedMap = new SimpleBooleanProperty(false);
        this.projectiles = new ArrayList<Projectile>();
        this.potionActive = null;

        this.REWARDS = new ArrayList<>();
        this.REWARDS.add(new Sword());
        this.REWARDS.add(new Bow(this));

        for(int i = 0; i < argsMaps.length; i++) {
            this.maps.put(argsMaps[i].getName(), argsMaps[i]);
        }
    };

    public ArrayList<Weapon> getREWARDS() {
        return REWARDS;
    }

    public BooleanProperty hasSwitchedMapProperty() {
        return hasSwitchedMap;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public java.util.Map<String, Map> getMaps() {
        return maps;
    }

    public void setCurrentMap(Map map) {
        this.currentMap = map;
    }

    public Hero getLink() {
        return this.link;
    }
    
    public void setLink(Hero link) {
    	this.link=link;
    }
    public Boss getBossFinal() {
        return this.bossFinal;
    }
    
    public void setBossFinal(Boss bossFinal) {
    	this.bossFinal=bossFinal;
    }
    public void switchMap(Map newMap, double returnX, double returnY) {
        setCurrentMap(newMap);
        this.hasSwitchedMap.set(true);

        this.link.setX(returnX);
        this.link.setY(returnY);

        this.hasSwitchedMap.set(false);
    }

	public void moveEnnemies() {
		for(Enemy e : currentMap.getEnemies()) {
			e.action();
		}
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}
	
	public void setPotionActive(Potion p) {
		this.potionActive = p;
	}
	
	public Potion getPotionActive() {
		return this.potionActive;
	}
}