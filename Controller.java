package application;

import com.sun.javafx.webkit.UIClientImpl;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

class Controller {
	private Scene myScene;
	private Group root;
	private CharacterController character;
	private EnvironmentController environment;
	private OpponentsController opponents;
	private UIController ui;
	private int activeSkill;
    
    boolean goNorth, goSouth, goEast, goWest,
    	interactionLoot, interactionUse, interactionActive,
    	inCombat, onLadder,
    	playerTurn;

	public Controller (Group group, Scene scene) {
		root = group;
		environment = new EnvironmentController(root);
		opponents = new OpponentsController(root);
		character = new CharacterController(root);
		ui = new UIController(root);
		myScene = scene;
		interactionLoot = false;
		interactionUse = false;
		inCombat = false;
		interactionActive = false;
		activeSkill = 1;
		playerTurn = false;
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
	                case DIGIT1:	{ui.setHighlight(1); activeSkill = 1;} break;
	                case DIGIT2:	{ui.setHighlight(2); activeSkill = 2;} break;
	                case DIGIT3:	{ui.setHighlight(3); activeSkill = 3;} break;
	                case ENTER:		useSelectedSkill(); break;
	                case ESCAPE:	Platform.exit(); break;
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
            		exploration();
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
		
		if (interactionUse) {
			onLadder = true;
			environment.useLadder(character.getCharacterView());
			ui.showEkeyUse(false);
		}
	}
	
	private void exploration() {
		int dx = 0, dy = 0;
        if (goEast)  dx += 10;
        if (goWest)  dx -= 10;
        if (goNorth) dy -= 10;
        if (goSouth) dy += 10;
        
        character.updateView(dx, false);
        relocateBackground(dx, 0);
        
        interactionLoot = environment.checkInteractionsLoot(character.getCharacterView());
        interactionUse = environment.checkInteractionsUse(character.getCharacterView());
        inCombat = opponents.checkInteractions(character.getCharacterView());
        interactionActive = interactionLoot || interactionUse;
        
        ui.showEkeyLoot(interactionLoot);
        ui.showEkeyUse(interactionUse);
        if (inCombat)
        	ui.showCombatUI();
	}
	
	private void combat() {
		if (!opponents.isFight()) {
			inCombat = false;
			ui.hideCombatUI();
		}
		if (playerTurn)
			return;
		playerTurn = opponents.opponentsTurn(character);
		ui.changeHp(character.getHp());
	}
	
	private void usingLadder() {
		int dy = 0;
        if (goNorth) dy -= 10;
        if (goSouth) dy += 10;
        
        onLadder = environment.climbAndCheckIsStillOnLadder(dy, character.getCharacterView());
	}
	
	private void relocateBackground (double dx, double dy) {
		if (dx == 0 && dy == 0)
			return;
		environment.relocate(dx, dy);
		opponents.relocate(dx, dy);
	}
	
	private void useSelectedSkill() {
		if (!inCombat || !playerTurn)
			return;
		switch(activeSkill) {
			case 1: opponents.attacked(-10); break;
			case 2: {character.healMax(); ui.changeHp(character.getHp());} break;
			case 3: character.defense(); break;
			default: Platform.exit();
		}
		
		playerTurn = false;
	}
}
