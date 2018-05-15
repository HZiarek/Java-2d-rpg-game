package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.DBConnect;
import database.GameStatistics;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.*;


public class GameplayController {
	private Scene myScene;
	private Group root;
	private CharacterController character;
	private EnvironmentController environment;
	private BanditController bandit;
	private UIController ui;
	private int activeSkill;
	private AnimationTimer timer;
	private long startTime;
	
	private MainMenuView mainMenuView;
	private int currentOption;
	
	private DatabaseController statistics;
    
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
		bandit = new BanditController(root, 1000, -750, 300, 300, 20, 20, 10, 1);//x, y, xsize ysize hp maxHp dmg def
		character = new CharacterController(root);
		ui = new UIController(root);
		startTime = System.nanoTime();
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
	                case ESCAPE:	endGame(); break;
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
				
		timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (mainMenuView != null) {
            		mainMenuView.updateHighlightPosition(currentOption);
            	}
            	else {
            		bandit.animation();
            		character.hpMovingAnimation();
            		if (!inCombat)
            			exploration();
            		else {
            			combat();
            		}
            			
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
		if (statistics != null) {
			statistics.hideWindow();
			statistics = null;
			return;
		}
		
		switch (currentOption) {
			case 0: startNewGame(); break;
			case 1: showStatistics(); break;
			case 2: Platform.exit(); break;
		}
	}
	
	private void showStatistics() {
		statistics = new DatabaseController(root);
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
			if (character.getMoney() == 500) {
				showFinalWindow(true);
				timer.stop();
			}
		}
		
		/*possibly to do later
		if (interactionUse) {
		}*/
	}
	
	private void exploration() {
		int dx = 0, dy = 0;
        if (goRight)  dx += 12;
        else if (goLeft)  dx -= 12;
        else if (goUp) dy -= 12;
        else if (goDown) dy += 12;
        
        moveCharacter(dx, dy);
        
        interactionLoot = environment.checkInteractionsLoot(character.getCharacterView());
        interactionUse = environment.checkInteractionsUse(character.getCharacterView());
        inCombat = bandit.checkInteractions(character.getCharacterView());
        interactionActive = interactionLoot || interactionUse;
        
        ui.showEkeyLoot(interactionLoot);
        ui.showEkeyUse(interactionUse);
        if (inCombat)
        	ui.showCombatUI();
	}
	
	private void combat() {
		if (!bandit.isFight()) {
			inCombat = false;
			ui.hideCombatUI();
			moveCharacter(1, 0);
		}
		
		if (isPlayerTurn) {
			return;
		}
			
		
		int opponentDamage = bandit.opponentsTurn();
		if (opponentDamage != 0) {
			isPlayerTurn = true;
			character.isMyTurn(true);
			if (!character.changeHpAndCheckIsDead(-opponentDamage)) {
				ui.showFinalWindow(false);
				ui.hideCombatUI();
				timer.stop();
				character.isMyTurn(false);
			}
			ui.changeHp(character.getHp());
		}
		else {
			character.isMyTurn(false);
			isPlayerTurn = false;
		}
	}
	
	private void moveCharacter (double dx, double dy) {
		if (dx == 0 && dy == 0)
			return;
		
        character.updateView(dx, dy);
 
		character.relocate(dx, dy);
		if (environment.checkCanMove(character.getCharacterView())) {
			bandit.relocate(dx, dy);
			environment.relocate(dx, dy);
		}
		
		character.relocate(-dx, -dy);
     
	}
	
	private void useSelectedSkill() {
		if (!inCombat || !isPlayerTurn)
			return;
		switch(activeSkill) {
			case 1: {bandit.attacked(-1);} break;
			case 2: {bandit.attacked(-7);} break;
			case 3: {character.heal(15); ui.changeHp(character.getHp()); bandit.isOpponentTurn();} break;
			default: Platform.exit();
		}
		
		isPlayerTurn = false;
	}
	
	private void showFinalWindow(boolean victoryTrueDeathFalse) {
		ui.showFinalWindow(victoryTrueDeathFalse);
	}
	
	private void endGame() {
		if (environment != null) {
			addStatistic();
		}
		Platform.exit();
	}
	
	private void addStatistic() {
		DBConnect databaseConnection = new DBConnect();
		GameStatistics newRow = new GameStatistics();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		
		newRow.setDateOfPlaying(dtf.format(localDate));
		
		long elapsedTime = System.nanoTime() - startTime;
		elapsedTime = elapsedTime/100000000;
		double insertedTime = elapsedTime;
		newRow.setTimeOfPlaying(elapsedTime/10);
		
		newRow.setMoney(character.getMoney());
		
		if (character.getMoney() == 500)
			newRow.setFinalResult("victory");
		else
			if (character.getHp() == 0)
			newRow.setFinalResult("death");
			else
				newRow.setFinalResult("quit");
		
		databaseConnection.addNewRow(newRow);
	}
}