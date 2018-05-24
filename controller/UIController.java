package controller;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import view.*;

/**
 * It provides management and control of the user interface (UI), which are visible during the core game play.
 * UI consists of: health points strip, information about task, information about amount of gold, image
 * prompting pressing the fitting button and final window, which is shown when the the game is over.
 * Everything what might be displayed on the screen is added to separate JavaFX group called the "uiGroup".
 */

class UIController {
	private Group root, uiGroup;
	
	private Sprite eKeyLootView, eKeyUseView, finalWindowView;
    private SkillSprite skillNr1, skillNr2, skillNr3;
    
    private TextSprite goldText, mainTaskText, victoryText, deathText;

	private HpController hp;

    
	public UIController(Group hook) {
		root = hook;
		uiGroup = new Group();
		root.getChildren().add(uiGroup);

		GraphicPaths paths = new GraphicPaths();
		
		eKeyLootView = new Sprite (paths.getPath("eKeyLoot"), uiGroup, 1680, 950);
		eKeyUseView = new Sprite (paths.getPath("eKeyUse"), uiGroup, 1680, 950);
		finalWindowView = new Sprite(paths.getPath("finalWindow"), uiGroup, 960, 1020);
		finalWindowView.setVisible(false);
		
		victoryText = new TextSprite("MISSON PASSED", uiGroup, 210, 600, 150, Color.GREEN);
		deathText = new TextSprite("YOU ARE DEAD", uiGroup, 270, 600, 150, Color.RED);
		
		goldText = new TextSprite("Gold: " + 0, uiGroup, 1550, 80, 50, Color.GOLDENROD);
		goldText.setVisible(true);
		
		mainTaskText = new TextSprite("Find 500 gold", uiGroup, 50, 880, 30, Color.BISQUE);
		mainTaskText.setVisible(true);
		
		skillNr1 = new SkillSprite (paths.getPath("skillAttack"), paths.getPath("skillHightlight"),
				root, 800, 1000);
		skillNr2 = new SkillSprite (paths.getPath("skillStrongAttack"), paths.getPath("skillHightlight"),
				root, 1000, 1000);
		skillNr3 = new SkillSprite (paths.getPath("skillHeal"), paths.getPath("skillHightlight"),
				root, 1200, 1000);
		
		hp = new HpController (hook, paths.getPath("hpStripe"), paths.getPath("pieceOfHp"), 50, 295, 65, 10);
		hp.setVisible(true);	
	}
	
	public void showEkeyLoot(boolean onOrOff){
		if (onOrOff)
			eKeyLootView.setVisible(true);
		else
			eKeyLootView.setVisible(false);
	}
	
	public void showEkeyUse(boolean onOrOff){
		if (onOrOff)
			eKeyUseView.setVisible(true);
		else
			eKeyUseView.setVisible(false);
	}
	
	public void updateGold(int gold) {
		goldText.setText("Gold: " + gold);
	}
	
	public void showCombatUI() {
		skillNr1.setVisible(true);
		skillNr2.setVisible(true);
		skillNr3.setVisible(true);
		skillNr1.setHighlight(true);
	}
	
	public void hideCombatUI() {
		skillNr1.setVisible(false);
		skillNr2.setVisible(false);
		skillNr3.setVisible(false);
		skillNr1.setHighlight(false);
		skillNr2.setHighlight(false);
		skillNr3.setHighlight(false);
	}
	
	public void setHighlight(int whichOne) {
		switch (whichOne) {
			case 1: {skillNr1.setHighlight(true); skillNr2.setHighlight(false); skillNr3.setHighlight(false);} break;
			case 2: {skillNr1.setHighlight(false); skillNr2.setHighlight(true); skillNr3.setHighlight(false);} break;
			case 3: {skillNr1.setHighlight(false); skillNr2.setHighlight(false); skillNr3.setHighlight(true);} break;
			default: {skillNr1.setHighlight(true); skillNr2.setHighlight(false); skillNr3.setHighlight(false);}
		}
		
	}
	
	public void changeHp(int newHp) {
		hp.changeHp(newHp);
	}
	
	public void showFinalWindow (boolean victoryTrueDeathFalse) {
		finalWindowView.setVisible(true);
		if (victoryTrueDeathFalse)
			victoryText.setVisible(true);
		else
			deathText.setVisible(true);
	}
}

