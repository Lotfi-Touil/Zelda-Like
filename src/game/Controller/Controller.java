package game.Controller;

import game.Constants;
import game.Controller.Listeners.*;
import game.Controller.Sprites.SpriteEnemy;
import game.Controller.Sprites.SpriteLink;
import game.Controller.Sprites.SpriteProjectile;
import game.Model.Enemies.BaseEnemy;
import game.Model.Enemies.Boss;
import game.Model.Enemies.Enemy;
import game.Model.Enemies.MiniBoss;
import game.Model.*;
import game.Model.Objects.Fragment;
import game.Model.Objects.Projectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Game env;
    private SpriteLink spriteLink;
    private Timeline gameLoop;
    private static int time = 0;

    @FXML
    private Pane pane;
    @FXML
    private Canvas CanvasStatic;
    @FXML
    private Canvas CanvasDestructible;
    @FXML
    private Canvas CanvasPersonnage;
    @FXML
    private Canvas CanvasEnemies;
    @FXML
    private Canvas CanvasObjects;
    @FXML
    private Canvas CanvasProjectile;
    @FXML
    private Canvas CanvasDerrieres;
    @FXML
    private Canvas CanvasOverlay;
    @FXML
    private Canvas canvasOverlayContent;
    @FXML
    private Canvas canvasMenu;

    private final Label labelPnj = new Label();
    private BufferedImage tileset;
    private static BooleanProperty showInfosEnemy = new SimpleBooleanProperty(false);
    

    private final AudioClip swordSound = new AudioClip(new File("assets/music/sword.wav").toURI().toString());
    private final AudioClip arrowSound = new AudioClip(new File("assets/music/arrow.wav").toURI().toString());
    private final AudioClip walkSound = new AudioClip(new File("assets/music/walk.wav").toURI().toString());
    private final AudioClip fireballSound = new AudioClip(new File("assets/music/fireball.wav").toURI().toString());
    private final AudioClip enemyKillSound = new AudioClip(new File("assets/music/enemy_kill.wav").toURI().toString());
    private final AudioClip grassSound = new AudioClip(new File("assets/music/grass.wav").toURI().toString());
    private final AudioClip potionOnSound = new AudioClip(new File("assets/music/potion_on.wav").toURI().toString());
    private final AudioClip potionOffSound = new AudioClip(new File("assets/music/potion_off.wav").toURI().toString());
    private final AudioClip overlaySound = new AudioClip(new File("assets/music/overlay.wav").toURI().toString());
    private final AudioClip dyingSound = new AudioClip(new File("assets/music/dying.wav").toURI().toString());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupGame();
        
        spriteLink = new SpriteLink(this.env);
        drawLinkOnCanvas(false);

        setupListenersAndBinds();
        setupOverlay();

        pane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.out.println("BYE");
                ((Stage) pane.getScene().getWindow()).close();
            }
            else {
                handleKeyPress(keyEvent);
            }
        });
        setupGameLoop();
    }

    public void setupGame() {
        Hero l = new Link(15, 12, Constants.PAS);
        Map mapBase = new Map(
                "base",
                6,
                5,
                "assets/maps/map_beginning/m_static_sol.csv",
                "assets/maps/map_beginning/m_static_murs.csv",
                "assets/maps/map_beginning/m_static_decors.csv",
                "assets/maps/map_beginning/m_static_misc.csv",
                "assets/maps/map_beginning/m_destructible.csv",
                "assets/maps/map_beginning/m_objects.csv",
                "assets/maps/map_beginning/m_pnjs.csv",
                "assets/maps/map_beginning/m_derrieres.csv",
                "assets/maps/map_beginning/m_collision.csv",
                "assets/maps/map_beginning/m_overlay.csv"
        );
        Map mapDonjon = new Map(
                "donjon",
                15,
                31,
                "assets/maps/map_donjon/map_donjon_static_sol.csv",
                "assets/maps/map_donjon/map_donjon_static_murs.csv",
                "assets/maps/map_donjon/map_donjon_static_decors.csv",
                "assets/maps/map_donjon/map_donjon_static_misc.csv",
                "assets/maps/map_donjon/map_donjon_destructible.csv",
                "assets/maps/map_donjon/map_donjon_objects.csv",
                "assets/maps/map_donjon/map_donjon_pnjs.csv",
                "assets/maps/map_donjon/map_donjon_derrieres.csv",
                "assets/maps/map_donjon/map_donjon_collision.csv"
        );
        Map mapHouse = new Map(
                "house",
                15,
                12,
                "assets/maps/map_house/map_house_static_sol.csv",
                "assets/maps/map_house/map_house_static_murs.csv",
                "assets/maps/map_house/map_house_static_decors.csv",
                "assets/maps/map_house/map_house_static_misc.csv",
                "assets/maps/map_house/map_house_destructible.csv",
                "assets/maps/map_house/map_house_objects.csv",
                "assets/maps/map_house/map_house_pnjs.csv",
                "assets/maps/map_house/map_house_derrieres.csv",
                "assets/maps/map_house/map_house_collision.csv"
        );

        this.env = new Game(
                Constants.MAP_BLOCK_WIDTH,
                Constants.MAP_BLOCK_HEIGHT,
                l,
                mapHouse,
                mapBase,
                mapDonjon
        );
        l.setEnv(this.env);

        try {
            this.tileset = ImageIO.read(new File("assets/tilesets/tileset.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Utils.mapToCanvas(this.tileset, this.CanvasStatic, this.env.getCurrentMap().getMaps().get("sol"));
        Utils.mapToCanvas(this.tileset, this.CanvasStatic, this.env.getCurrentMap().getMaps().get("murs"));
        Utils.mapToCanvas(this.tileset, this.CanvasStatic, this.env.getCurrentMap().getMaps().get("decors"));
        Utils.mapToCanvas(this.tileset, this.CanvasStatic, this.env.getCurrentMap().getMaps().get("misc"));
        Utils.mapToCanvas(this.tileset, this.CanvasDestructible, this.env.getCurrentMap().getMaps().get("destructible"));
        Utils.mapToCanvas(this.tileset, this.CanvasObjects, this.env.getCurrentMap().getMaps().get("objects"));
        Utils.mapToCanvas(this.tileset, this.CanvasStatic, this.env.getCurrentMap().getMaps().get("pnjs"));
        Utils.mapToCanvas(this.tileset, this.CanvasDerrieres, this.env.getCurrentMap().getMaps().get("derrieres"));
        Utils.mapToCanvas(this.tileset, this.CanvasOverlay, this.env.getMaps().get("base").getMaps().get("overlay"));

        this.CanvasOverlay.setVisible(false);
        this.canvasOverlayContent.setVisible(false);
        this.canvasMenu.setVisible(true);

        BaseEnemy m1 = new BaseEnemy(),
                m2 = new BaseEnemy(),
                m3 = new BaseEnemy(),
                m4 = new BaseEnemy(),
                m5 = new BaseEnemy(),
                m6 = new BaseEnemy(),
                m7 = new BaseEnemy(),
                m8 = new BaseEnemy(),
                m9 = new BaseEnemy(),
                m10 = new BaseEnemy(),
                m11 = new BaseEnemy(),
                m12 = new BaseEnemy();

        for (BaseEnemy baseEnemy : Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12)) {
            baseEnemy.setEnv(env);
        }
        MiniBoss miniBoss1 = new MiniBoss(
                env,
                300,
                9,
                19,
                Constants.MINIBOSS_ATTACK_DAMAGE,
                Constants.MINIBOSS_ATTACK_RANGE
        );

        MiniBoss miniBoss2 = new MiniBoss(
                        env,
                        300,
                        20,
                        10,
                        Constants.MINIBOSS_ATTACK_DAMAGE,
                        Constants.MINIBOSS_ATTACK_RANGE
                );

        MiniBoss miniBoss3 = new MiniBoss(
                        env,
                        300,
                        13,
                        30,
                        Constants.MINIBOSS_ATTACK_DAMAGE,
                        Constants.MINIBOSS_ATTACK_RANGE
                );

        Boss boss = new Boss(
                env,
                500,
                15,
                8,
                Constants.MINIBOSS_ATTACK_DAMAGE,
                Constants.MINIBOSS_ATTACK_RANGE
        );
        this.env.setBossFinal(boss);
        mapBase.getEnemies().addAll(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, miniBoss1, miniBoss2, miniBoss3));
        mapDonjon.getEnemies().addAll(Arrays.asList(m11, m12, boss));
    }

    public void setupListenersAndBinds() {
        this.env.getCurrentMap().getPnjs().addListener(new PNJsListener(this.tileset, CanvasStatic));

        this.env.hasSwitchedMapProperty().addListener(new MapSwitchListener(
                this.env,
                this.tileset,
                CanvasStatic,
                CanvasPersonnage,
                CanvasEnemies,
                CanvasDestructible,
                CanvasObjects,
                CanvasDerrieres,
                CanvasProjectile
        ));
        spriteLink.getLink().xProperty().addListener(((observableValue, old, nouv) -> {
            Utils.clearCanvas(this.CanvasPersonnage);
            drawLinkOnCanvas(false);
        }));
        spriteLink.getLink().yProperty().addListener(((observableValue, number, t1) -> {
            Utils.clearCanvas(this.CanvasPersonnage);
            drawLinkOnCanvas(false);
        }));

        this.env.getMaps().forEach((k, v) -> {
            for(PNJ pnj : v.getPnjs()) {
                pnj.isSpeakingProperty().addListener(((observableValue, aBoolean, t1) -> {
                    if (t1) {
                        labelPnj.setText(pnj.getMessage());
                        labelPnj.setVisible(true);
                    }
                    else {
                        labelPnj.setVisible(false);
                    }
                }));
            }
            v.getDestructibles().addListener(new DestructiblesListener(CanvasDestructible));
            v.getObjects().addListener(new ObjectsListener(env, tileset, CanvasObjects));
        });

        this.spriteLink.getLink().getHpProperty().addListener(new HeartListener(this.canvasOverlayContent, this.tileset, this.env));
        this.env.getLink().getItems().addListener(new ItemListener(this.env.getLink(), this.canvasOverlayContent, this.tileset));
        this.env.getLink().getFragments().addListener(new FragmentListener(this.canvasOverlayContent, this.tileset, this.env.getLink()));
        this.env.getLink().getWeapons().addListener(new WeaponsListener(this.canvasOverlayContent, this.tileset));
        
        this.spriteLink.getLink().getHpProperty().addListener((obs, old, nouv) -> {
        	if(nouv.intValue() <= 0) {
        		menu("gameover");
        		this.gameLoop.stop();
        	}
        });
        this.env.getBossFinal().getHpProperty().addListener((obs, old, nouv) -> {
        	System.out.println("boss hp changed");
        	if(nouv.intValue() <= 0){
        		menu("win");
        		this.gameLoop.stop();
        	}
        });
        
        Map m = this.env.getMaps().get("base");
    	for(Enemy e : m.getEnemies()) {
    		Label l = new Label();
    		l.setTextFill(Color.BLACK);
            l.setFont(new Font("Serif", 12));
    		l.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
    		l.textProperty().bind(e.getHpProperty().asString());
    		l.translateXProperty().bind((e.xProperty().subtract(1)).multiply(Constants.BLOCK_SIZE));
    		l.translateYProperty().bind((e.yProperty().subtract(1)).multiply(Constants.BLOCK_SIZE));
    		l.setPrefWidth(23);
    		l.setPrefHeight(10);
    		this.pane.getChildren().add(l);
    		l.visibleProperty().bind(showInfosEnemy);
    		e.getHpProperty().addListener((obs, old, nouv) -> {
    			if(e.getHp()<=0)
    				this.pane.getChildren().remove(l);
    			else if(e.getHp()<=30)
    				l.setTextFill(Color.RED);
    		});
    	}
    }

	public void setupOverlay() {
    	//PNJ LABEL
        this.pane.getChildren().add(this.labelPnj);
        this.labelPnj.paddingProperty().set(new Insets(20));
        this.labelPnj.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.labelPnj.setTextFill(Color.WHITE);
        this.labelPnj.setPrefWidth(256);
        this.labelPnj.setPrefHeight(128);
        this.labelPnj.setTranslateX(144);
        this.labelPnj.setTranslateY(192);
        this.labelPnj.setFont(new Font("Serif", 11));
        this.labelPnj.setVisible(false);
        //HEARTH
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 11, 3, Constants.FULL_HEART);
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 12, 3, Constants.FULL_HEART);
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 13, 3, Constants.FULL_HEART);
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 14, 3, Constants.FULL_HEART);
        //WEAPONS
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 11, 1, Constants.FIST);
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 12, 1, Constants.FIST);
        Utils.drawOnCanvas(this.canvasOverlayContent, this.tileset, 13, 1, Constants.FIST);
        //MENU
        menu("start");
    }

    private void menu(String choice) {
    	this.canvasMenu.setVisible(true);
        GraphicsContext gcMenu = this.canvasMenu.getGraphicsContext2D();
        BufferedImage buffImgMenu = null;
		try {
			switch(choice) {
			case "start" -> buffImgMenu = ImageIO.read(new File("assets/menu/startMenu.png"));
			case "gameover" -> buffImgMenu = ImageIO.read(new File("assets/menu/gameoverMenu.png"));
			case "win" -> buffImgMenu = ImageIO.read(new File("assets/menu/winMenu.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Image imgMenu = SwingFXUtils.toFXImage(buffImgMenu, null);
        gcMenu.drawImage(imgMenu, 0, 0);
	}

	public void setupGameLoop() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(Constants.SECONDS_DURATION),
                (ev -> {
                    if (spriteLink.getLink().getHp() <= 0) {
                        dyingSound.play();
                        gameLoop.stop();
                        System.out.println("GAME OVER");
                    }
                    else if(time%60==0) {

                        for(Enemy e : this.env.getCurrentMap().getEnemies()) {
                            if(e!=null) {
                                e.tryCatching();
                            }
                            else
                                System.out.println("ennemi mort");
                        }
                        this.env.moveEnnemies();

                        if(this.env.getPotionActive()!=null) {
                    		this.env.getPotionActive().decrementDuration();
                    		if(this.env.getPotionActive().getDuration()<=0) {
                    			this.env.getPotionActive().endEffect(env);
                    			this.env.setPotionActive(null);
                    			potionOffSound.play();
                    		}
                    	}

                    }
                    else if (time % 15 == 0) {
                        Utils.clearCanvas(this.CanvasPersonnage);
                        drawLinkOnCanvas(true);
                        for(int i = 0; i < this.env.getCurrentMap().getObjects().size(); i++) {
                        	if(this.env.getCurrentMap().getObjects().get(i) instanceof Fragment) {
                        		Utils.drawOnCanvas(this.CanvasObjects, this.tileset, this.env.getCurrentMap().getObjects().get(i).getX(), this.env.getCurrentMap().getObjects().get(i).getY(), Constants.FRAGMENT);
                        	}
                        }
                    }
                    else if (time % 5 == 0) {
                        this.env.getProjectiles().removeIf(Projectile::attack);
                    }
                    else {
                        updateEnemiesOnCanvas();
                    }
                    
                    if(this.canvasMenu.isVisible() && time >= 1500 && this.canvasMenu.isVisible() && time < 1600) {
                    	this.canvasMenu.setVisible(false);
                    }

                    time++;
                })
        );
        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();
    }

    public void handleKeyPress(KeyEvent keyEvent) {
    	if(this.canvasMenu.isVisible() && time < 1500)
    		this.canvasMenu.setVisible(false);
    	
        for(PNJ p : this.env.getCurrentMap().getPnjs())
            p.setIsSpeaking(false);

        if (keyEvent.getCode() == KeyCode.SPACE) {

            if (spriteLink.getLink().attack_destructible()) {
                grassSound.play();
            }
            else {
                spriteLink.getLink().attack();
                if (spriteLink.getLink().getCurrent().getType() == Types.BOW) {
                    arrowSound.play();
                }
                else {
                    swordSound.play();
                }
            }
            attackOnCanvas();

        }
        else if (keyEvent.getCode()==KeyCode.E){
            spriteLink.getLink().interact();
        }
        else if(keyEvent.getCode()==KeyCode.P) {
        	this.env.getLink().activatePotion();
        	potionOnSound.play();
        }
        else if (keyEvent.getCode() == KeyCode.I) {
            this.CanvasOverlay.setVisible(!this.CanvasOverlay.isVisible());
            this.canvasOverlayContent.setVisible(!this.canvasOverlayContent.isVisible());
            overlaySound.play();
        }
        else if(keyEvent.getCode() == KeyCode.S) {
        	if(showInfosEnemy.getValue().equals(true)) {
        		showInfosEnemy.setValue(false);
        	}
        	else{
        		if(this.env.getCurrentMap().equals(this.env.getMaps().get("base")))
        		showInfosEnemy.setValue(true);
        	}
        }
        else {
            switch (keyEvent.getCode()) {
                case UP -> spriteLink.getLink().move(0);
                case RIGHT -> spriteLink.getLink().move(1);
                case DOWN -> spriteLink.getLink().move(2);
                case LEFT -> spriteLink.getLink().move(3);
                case V -> this.env.getLink().chooseWeapon(0); // fist
                case B -> this.env.getLink().chooseWeapon(1); // sword
                case N -> this.env.getLink().chooseWeapon(2); // bow
            }
            walkSound.play();
        }
    }

    public void updateEnemiesOnCanvas() {
        Utils.clearCanvas(this.CanvasEnemies);
        for(Enemy e : this.env.getCurrentMap().getEnemies()) {
            SpriteEnemy spriteEnemy = new SpriteEnemy(e);
            Utils.drawOnCanvas(this.CanvasEnemies, this.tileset, e.getX(), e.getY()-1, spriteEnemy.getSprite()- Constants.SPRITE_UP);
            Utils.drawOnCanvas(this.CanvasEnemies, this.tileset, e.getX(), e.getY(), spriteEnemy.getSprite());
            if (e.getType() == Types.BOSS) {
                Utils.drawOnCanvas(this.CanvasEnemies, this.tileset, e.getX() + 1, e.getY()-1, spriteEnemy.getSprite()- Constants.SPRITE_UP + Constants.SPRITE_NEXT);
                Utils.drawOnCanvas(this.CanvasEnemies, this.tileset, e.getX() + 1, e.getY(), spriteEnemy.getSprite() + Constants.SPRITE_NEXT);
            }
        }
        Utils.clearCanvas(this.CanvasProjectile);
        for (Projectile p : this.env.getProjectiles()) {
            SpriteProjectile spriteProjectile = new SpriteProjectile(p);
            Utils.drawOnCanvas(this.CanvasProjectile, this.tileset, spriteProjectile.getProjectile().getX(), spriteProjectile.getProjectile().getY(), spriteProjectile.getSprite());
        }
    }

    public void drawLinkOnCanvas(boolean inGameloop) {
        if (spriteLink.getLink().getHp() <= 0) {
            Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), spriteLink.getSprite());
        }
        else  {
            if (spriteLink.getLink().getCurrent().getType() == Types.FISTS) {
                if (inGameloop) {
                    Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY()-1, spriteLink.getSprite()- Constants.SPRITE_UP);
                    Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), spriteLink.getSprite());
                }
                else {
                    if (time % 2 == 0) {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY()-1, spriteLink.getSprite()- Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), spriteLink.getSprite());
                    }
                    else {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY()-1, spriteLink.getSprite()- Constants.SPRITE_UP + 80);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), spriteLink.getSprite() + 80);
                    }
                }
            }
            else {
                Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY()-1, spriteLink.getSprite()- Constants.SPRITE_UP);
                Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), spriteLink.getSprite());
                Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY()-1, spriteLink.getSprite() + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), spriteLink.getSprite() + Constants.SPRITE_NEXT);
            }
        }
    }

    public void attackOnCanvas() {
        Utils.clearCanvas(this.CanvasPersonnage);

        switch (spriteLink.getLink().getGlobalDirection()) {
            case Constants.UP -> {
                switch (spriteLink.getLink().getCurrent().getType()) {
                    case FISTS -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_UP_FISTS_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_UP_FISTS_ATTACK - Constants.SPRITE_UP);
                    }
                    case SWORD -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_UP_SWORD_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_UP_SWORD_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_UP_SWORD_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_UP_SWORD_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                    case BOW -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_UP_BOW_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_UP_BOW_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_UP_BOW_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_UP_BOW_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                }
            }
            case Constants.RIGHT -> {
                switch (spriteLink.getLink().getCurrent().getType()) {
                    case FISTS -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_RIGHT_FISTS_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_RIGHT_FISTS_ATTACK - Constants.SPRITE_UP);
                    }
                    case SWORD -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_RIGHT_SWORD_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_RIGHT_SWORD_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_RIGHT_SWORD_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_RIGHT_SWORD_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                    case BOW -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_RIGHT_BOW_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_RIGHT_BOW_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_RIGHT_BOW_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_RIGHT_BOW_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                }
            }
            case Constants.DOWN -> {
                switch (spriteLink.getLink().getCurrent().getType()) {
                    case FISTS -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_DOWN_FISTS_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_DOWN_FISTS_ATTACK - Constants.SPRITE_UP);
                    }
                    case SWORD -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_DOWN_SWORD_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_DOWN_SWORD_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_DOWN_SWORD_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_DOWN_SWORD_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                    case BOW -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_DOWN_BOW_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_DOWN_BOW_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_DOWN_BOW_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_DOWN_BOW_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                }
            }
            case Constants.LEFT -> {
                switch (spriteLink.getLink().getCurrent().getType()) {
                    case FISTS -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_LEFT_FISTS_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_LEFT_FISTS_ATTACK - Constants.SPRITE_UP);
                    }
                    case SWORD -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_LEFT_SWORD_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_LEFT_SWORD_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_LEFT_SWORD_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_LEFT_SWORD_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                    case BOW -> {
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY(), Constants.LINK_LEFT_BOW_ATTACK);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX(), spriteLink.getLink().getY() - 1, Constants.LINK_LEFT_BOW_ATTACK - Constants.SPRITE_UP);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY(), Constants.LINK_LEFT_BOW_ATTACK + Constants.SPRITE_NEXT);
                        Utils.drawOnCanvas(this.CanvasPersonnage, this.tileset, spriteLink.getLink().getX() + 1, spriteLink.getLink().getY() - 1, Constants.LINK_LEFT_BOW_ATTACK + Constants.SPRITE_NEXT - Constants.SPRITE_UP);
                    }
                }
            }
        }
    }
}