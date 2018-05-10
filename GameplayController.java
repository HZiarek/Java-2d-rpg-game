package application;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

class GameplayController {
	private Scene myScene;
	private Group root;
	private CharacterController character;
	private EnvironmentController environment;
	private OpponentsController opponents;
	private UIController ui;
	private int activeSkill;
	
	private MainMenuView mainMenuView;
	private int currentOption;
    
    boolean goUp, goDown, goRight, goLeft,
    	interactionLoot, interactionUse, interactionActive,
    	inCombat, isPlayerTurn;

	public GameplayController (Stage stage) {
		root = new Group();
		myScene = new Scene(root);
		mainMenuView = new MainMenuView(root);
		stage.setScene(myScene);
		
		interactionLoot = false;
		interactionUse = false;
		inCombat = false;
		interactionActive = false;
		activeSkill = 1;
		isPlayerTurn = false;
		currentOption = 0;
	}
	
	private void createLevel() {
		environment = new EnvironmentController(root);
		opponents = new OpponentsController(root);
		character = new CharacterController(root);
		ui = new UIController(root);
	}
	
	public void start() {
		//createLevel();
		
		myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	                case UP:    useUpKey(); break;
	                case DOWN:  useDownKey(); break;
	                case LEFT:  goLeft  = true; break;
	                case RIGHT: goRight  = true; break;
	                case E:		if (interactionActive) serviceInteraction(); break;
	                case DIGIT1:	{ui.setHighlight(1); activeSkill = 1;} break;
	                case DIGIT2:	{ui.setHighlight(2); activeSkill = 2;} break;
	                case DIGIT3:	{ui.setHighlight(3); activeSkill = 3;} break;
	                case ENTER:		useEnterKey(); break;
	                case ESCAPE:	Platform.exit(); break;
	            }
	        }
	    });

		myScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	                case UP:    goUp = false; break;
	                case DOWN:  goDown = false; break;
	                case LEFT:  goLeft  = false; break;
	                case RIGHT: goRight  = false; break;
	            }
	        }
	    });
				
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (mainMenuView != null) {
            		mainMenuView.updateHighlightPosition(currentOption);
            	}
            	else {
            		if (!inCombat)
            			exploration();
            		else
            			combat();
            	}}
        };
        timer.start();
	}
	
	private void useUpKey() {
		if (mainMenuView == null)
			goUp = true;
		else
			if (currentOption == 0)
				currentOption = 2;
			else
				currentOption = (currentOption - 1)%3;
	}
	
	private void useDownKey() {
		if (mainMenuView == null)
			goDown = true;
		else
			currentOption = (currentOption + 1)%3;
	}
	
	private void useEnterKey() {
		if (mainMenuView == null)
			useSelectedSkill();
		else
			chooseOptionInMainMenu();
	}
	
	private void chooseOptionInMainMenu() {
		switch (currentOption) {
			case 0: startNewGame(); break;
			case 1: break;
			case 2: Platform.exit(); break;
		}
	}
	
	private void startNewGame() {
		mainMenuView = null;
		createLevel();
	}
	
	private void serviceInteraction() {
		if (interactionLoot) {
			int money = environment.pickUpMoney();
			character.changeMoney(money);
			ui.updateGold(character.getMoney());
		}
		
		/*possibly to do later
		if (interactionUse) {
			
		}*/
	}
	
	private void exploration() {
		int dx = 0, dy = 0;
        if (goRight)  dx += 12;
        if (goLeft)  dx -= 12;
        if (goUp) dy -= 12;
        if (goDown) dy += 12;
        
        moveCharacter(dx, dy);
        
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
		if (isPlayerTurn)
			return;
		isPlayerTurn = opponents.opponentsTurn(character);
		ui.changeHp(character.getHp());
	}
	
	private void moveCharacter (double dx, double dy) {
		if (dx == 0 && dy == 0)
			return;
		
        character.updateView(dx, dy);
 
		//environment.getNextMaxStepCharacterRelocateX(dx, character.getCharacterView());
		
        opponents.relocate(dx, dy);
		environment.relocate(dx, dy);

	}
	
	private void useSelectedSkill() {
		if (!inCombat || !isPlayerTurn)
			return;
		switch(activeSkill) {
			case 1: opponents.attacked(-10); break;
			case 2: {character.healMax(); ui.changeHp(character.getHp());} break;
			case 3: character.defense(); break;
			default: Platform.exit();
		}
		
		isPlayerTurn = false;
	}
}