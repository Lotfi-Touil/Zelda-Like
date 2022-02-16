package game;

public class Constants {

    // MAP, APP
    public static final int PX_WIDTH = 512;
    public static final int PX_HEIGHT = 512;
    public static final int BLOCK_SIZE = 16;
    public static final int MAP_BLOCK_WIDTH = PX_WIDTH / BLOCK_SIZE;
    public static final int MAP_BLOCK_HEIGHT = PX_HEIGHT / BLOCK_SIZE;
    
    // MAP + INVENTORY
    public static final int LINK_HP = 200;

    // GAME ATTRIBUTES
    public static final double SECONDS_DURATION = 0.018;
    public static final double PAS = 0.3;

    // MOVEMENT DIRECTIONS
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    // OBJECTS, ITEMS
    public static final int CHEST_CLOSED = 2240;
    public static final int CHEST_OPENED = 2241;
    public static final int LIFE_HEART = 2360;

    // SPRITES ATTRIBUTES
    public static final int SPRITE_UP = 40;
    public static final int SPRITE_NEXT = 1;

    // LINK SPRITES
    public static final int LINK_UP_BASE_1 = 11560;
    public static final int LINK_RIGHT_BASE_1 = 11561;
    public static final int LINK_DOWN_BASE_1 = 11562;
    public static final int LINK_LEFT_BASE_1 = 11563;
    public static final int LINK_UP_FISTS_ATTACK = 11720;
    public static final int LINK_RIGHT_FISTS_ATTACK = 11721;
    public static final int LINK_DOWN_FISTS_ATTACK = 11722;
    public static final int LINK_LEFT_FISTS_ATTACK = 11723;
    public static final int LINK_UP_SWORD = 11568;
    public static final int LINK_RIGHT_SWORD = 11570;
    public static final int LINK_DOWN_SWORD = 11572;
    public static final int LINK_LEFT_SWORD = 11574;
    public static final int LINK_UP_SWORD_ATTACK = 11688;
    public static final int LINK_RIGHT_SWORD_ATTACK = 11690;
    public static final int LINK_DOWN_SWORD_ATTACK = 11692;
    public static final int LINK_LEFT_SWORD_ATTACK = 11694;
    public static final int LINK_UP_BOW = 11768;
    public static final int LINK_RIGHT_BOW = 11770;
    public static final int LINK_DOWN_BOW = 11772;
    public static final int LINK_LEFT_BOW = 11774;
    public static final int LINK_UP_BOW_ATTACK = 11848;
    public static final int LINK_RIGHT_BOW_ATTACK = 11850;
    public static final int LINK_DOWN_BOW_ATTACK = 11852;
    public static final int LINK_LEFT_BOW_ATTACK = 11854;

    public static final int LINK_DEAD = 11537;

    // BOSS SPRITES
    public static final int BOSS_UP_BASE = 12402;
    public static final int BOSS_RIGHT_BASE = 12482;
    public static final int BOSS_DOWN_BASE = 12322;
    public static final int BOSS_LEFT_BASE = 12562;

    // MINIBOSS SPRITES
    public static final int MINIBOSS_UP_BASE = 12612;
    public static final int MINIBOSS_RIGHT_BASE = 12613;
    public static final int MINIBOSS_DOWN_BASE = 12614;
    public static final int MINIBOSS_LEFT_BASE = 12615;
    // MINIBOSS ATTRIBUTES
    public static final int MINIBOSS_ATTACK_DAMAGE = 10;
    public static final int MINIBOSS_ATTACK_RANGE = 2;

    // BASE ENEMY SPRITES
    public static final int BASE_ENEMY_UP = 12247;
    public static final int BASE_ENEMY_RIGHT = 12327;
    public static final int BASE_ENEMY_DOWN = 12167;
    public static final int BASE_ENEMY_LEFT = 12407;

    // BASE ENEMIES SPAWN POSITION
    public static final int BASE_ENEMY_SPAWN1_X = 2;
    public static final int BASE_ENEMY_SPAWN2_X = 8;
    public static final int BASE_ENEMY_SPAWN3_X = 6;
    public static final int BASE_ENEMY_SPAWN4_X = 12;
    public static final int BASE_ENEMY_SPAWN5_X = 20;
    public static final int BASE_ENEMY_SPAWN6_X = 23;
    public static final int BASE_ENEMY_SPAWN7_X = 15;
    public static final int BASE_ENEMY_SPAWN8_X = 20;
    public static final int BASE_ENEMY_SPAWN9_X = 31;
    public static final int BASE_ENEMY_SPAWN10_X = 26;
    public static final int BASE_ENEMY_SPAWN11_X = 10;
    public static final int BASE_ENEMY_SPAWN12_X = 21;

    public static final int BASE_ENEMY_SPAWN1_Y = 3;
    public static final int BASE_ENEMY_SPAWN2_Y = 10;
    public static final int BASE_ENEMY_SPAWN3_Y = 22;
    public static final int BASE_ENEMY_SPAWN4_Y = 30;
    public static final int BASE_ENEMY_SPAWN5_Y = 7;
    public static final int BASE_ENEMY_SPAWN6_Y = 1;
    public static final int BASE_ENEMY_SPAWN7_Y = 2;
    public static final int BASE_ENEMY_SPAWN8_Y = 23;
    public static final int BASE_ENEMY_SPAWN9_Y = 11;
    public static final int BASE_ENEMY_SPAWN10_Y = 18;
    public static final int BASE_ENEMY_SPAWN11_Y = 29;
    public static final int BASE_ENEMY_SPAWN12_Y = 21;

    // PNJ SPRITES
    public static final int PNJ_DOWN = 2460;

    // PROJECTILES SPRITES
    public static final int ARROW_UP = 12725;
    public static final int ARROW_RIGHT = 12765;
    public static final int ARROW_DOWN = 12764;
    public static final int ARROW_LEFT = 12763;
    public static final int FIREBALL = 2367;

    // HEARTH SPRITES
    public static final int EMPTY_HEART = 2248;
    public static final int FULL_HEART = 2244;
    public static final int HALF_HEART = 2246;
    public static final int QUARTER_HEART = 2247;
    public static final int THREE_QUARTER_HEART = 2245;
    
    // WEAPONS SPRITES
    public static final int FIST = 12689;
    public static final int SWORD = 12722;
    public static final int BOW = 12723;
    
    //ITEM SPRITES
    public static final int POTION = 12729;
    public static final int CAP = 12647;
    public static final int FRAGMENT = 12687;
//    public static final int FRAGMENT = 2400;	
    
}
