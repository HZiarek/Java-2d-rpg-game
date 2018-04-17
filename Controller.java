package application;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Controller {
	private Scene myScene;
	private Group root;
	private CharacterController character;
	private EnvironmentController environment;
	private UIController ui;
    
    boolean goNorth, goSouth, goEast, goWest, interactionLoot, interactionUse,
    	interactionActive, inCombat, onLadder;

	public Controller (Group group, Scene scene) {
		root = group;
		environment = new EnvironmentController(root);
		character = new CharacterController(root);
		ui = new UIController(root);
		myScene = scene;
		interactionLoot = false;
		interactionUse = false;
		inCombat = false;
		interactionActive = false;
	}
	
	public void start() {
		//createLevel();
		
		myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	                case UP:    goNorth = true; break;
	                case DOWN:  goSouth = true; break;
	                case LEFT:  goWest  = true; break;
	                case RIGHT: goEast  = true; break;
	                case E:		if (interactionActive) serviceInteraction(); break;
	                case ENTER:	Platform.exit(); break;
	            }
	        }
	    });

		myScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	                case UP:    goNorth = false; break;
	                case DOWN:  goSouth = false; break;
	                case LEFT:  goWest  = false; break;
	                case RIGHT: goEast  = false; break;
	            }
	        }
	    });
				
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (!inCombat && !onLadder)
            	{
            		exploration();
            		usingLadder();
            	}
            	if (inCombat)
            		combat();
            	if (onLadder)
            		usingLadder();
                }
        };
        timer.start();
	}
	
	

	private void serviceInteraction() {
		if (interactionLoot) {
			int money = environment.pickUpMoney();
			character.changeMoney(money);
			ui.updateGold(character.getMoney());
		}
	}
	
	private void exploration() {
		int dx = 0;
        if (goEast)  dx += 10;
        if (goWest)  dx -= 10;
        environment.relocate(dx, 0);
        
        interactionLoot = environment.checkInteractions(character.getCharacterModel());
        if (interactionLoot != interactionActive) {
        	interactionActive = interactionLoot;
        	ui.showEkeyLoot(interactionLoot);
        }
	}
	
	private void combat() {
		
	}
	
	private void usingLadder() {
		int dy = 0;
        if (goNorth) dy -= 10;
        if (goSouth) dy += 10;
        
        environment.relocate(0, dy);
	}
	/*
	public void createLevel() {
		heroImage = new Image (HERO_IMAGE_LOC);
		hero = new ImageView (heroImage);
		
		back.relocate(0, -300);
		chestImage = new Image (CHEST_IMAGE_LOC);
		chest = new ImageView (chestImage);
		eKeyImage = new Image (EKEY_IMAGE_LOC);
		eKey = new ImageView (eKeyImage);
		eKey.relocate(1000, 300);
		
		environment = new Group();
		environment.getChildren().add(chest);
		root.getChildren().add(environment);
		root.getChildren().add(hero);
		hero.relocate(900 - (hero.getBoundsInLocal().getWidth()/2), 500 - (hero.getBoundsInLocal().getHeight()/2));
		
		back.setLayoutY(-160);
		
		//modele
		chest1 = new Chest(300, 500, 100, 100, true, 200);
		character = new Character(900 - (hero.getBoundsInLocal().getWidth()/2), 500, 100, 100, 30, 30, 5, 3, 0);
		
		//napis - ilosc zlota
		pointsText = "Cash: $ " + character.getMoney();
		gold = new Text(pointsText);
		gold.setFont(new Font(50));
		root.getChildren().add(gold);
		gold.relocate(50, 50);
		
		//tymczasowy obrazek drabiny
		ladderImage = new Image ("file:graphic/ladder.jpg");
		ladderView = new ImageView (ladderImage);
		environment.getChildren().add(ladderView);
		ladderView.relocate(1000, 500);
		
		//drabina
		ladder = new Item(1000, 500, 200, 200, true);
		
		//niedziwiedz
		bear = new Bear (900 - (hero.getBoundsInLocal().getWidth()/2), 500, 100, 100, 30, 30, 5, 3, 0);
	}
	
	
	private void bearAttack () {
		if (!character.getDefenseIsActive())
			character.changeHp(-bear.getDamage());
		else {
			character.setDefenseIsActive(false);
			int tmpDamage = bear.getDamage()-character.getDefense();
			if (tmpDamage > 0)
				character.changeHp(-tmpDamage);
		}
	}
	
	private void characterHealing() {
		character.setHp(character.getMaxHp());
	}
	
	private void characterDefense() {
		character.setDefenseIsActive(true);
	}

	private void characterAttack () {
		if (!bear.getDefenseIsActive())
			bear.changeHp(-character.getDamage());
		else {
			bear.setDefenseIsActive(false);
			int tmpDamage = character.getDamage()-bear.getDefense();
			if (tmpDamage > 0)
				bear.changeHp(-tmpDamage);
		}
	}
*/	
}
