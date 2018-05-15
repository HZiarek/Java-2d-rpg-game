package controller;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import view.*;

class UIController {
	private Group root, ui;
	
	private View eKeyLootView, eKeyUseView, finalWindowView;
    private SkillView skillNr1, skillNr2, skillNr3;
    
    private TextView goldText, mainTaskText, victoryText, deathText;

	private HpController hp;

    
	public UIController(Group hook) {
		root = hook;
		ui = new Group();
		root.getChildren().add(ui);

		GraphicPaths paths = new GraphicPaths();
		
		eKeyLootView = new View (paths.getPath("eKeyLoot"), ui, 1680, 950);
		eKeyUseView = new View (paths.getPath("eKeyUse"), ui, 1680, 950);
		finalWindowView = new View(paths.getPath("finalWindow"), ui, 960, 1020);
		finalWindowView.setVisible(false);
		
		victoryText = new TextView("MISSON PASSED", ui, 210, 600, 150, Color.GREEN);
		deathText = new TextView("YOU ARE DEAD", ui, 270, 600, 150, Color.RED);
		
		goldText = new TextView("Gold: " + 0, ui, 1550, 80, 50, Color.GOLDENROD);
		goldText.setVisible(true);
		
		mainTaskText = new TextView("Find 500 gold", ui, 50, 880, 30, Color.BISQUE);
		mainTaskText.setVisible(true);
		
		skillNr1 = new SkillView (paths.getPath("skillAttack"), paths.getPath("skillHightlight"),
				root, 800, 1000);
		skillNr2 = new SkillView (paths.getPath("skillStrongAttack"), paths.getPath("skillHightlight"),
				root, 1000, 1000);
		skillNr3 = new SkillView (paths.getPath("skillHeal"), paths.getPath("skillHightlight"),
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

class HpController{
	private View hp[], stripeView;
	private Group hpGroup;
	private int currentHp;
	private int maxHp;
	
	public HpController (Group hook, String stripePath, String hpPath, int numberOfPieces,
			double xposition, double yposition, double interval) {
		hpGroup = new Group();
		hook.getChildren().add(hpGroup);
		currentHp = numberOfPieces - 1;
		maxHp = numberOfPieces;
		
		stripeView = new View (stripePath, hpGroup, xposition, yposition);
		double startPoint = (numberOfPieces/2)*interval - interval/2;
		
		hp = new View[50];
		int tmpCounter = 0;

		for (int i = 0; i < maxHp; i++) {
			hp[i] = new View(hpPath, hpGroup, xposition - startPoint + tmpCounter, yposition - 5);
			tmpCounter += interval;
		}
		
	}

	public void changeHp(int newHp) {
		if (newHp > maxHp)
			return;
		if (newHp != 0)
			newHp -= 1;
		if (newHp == currentHp)
			return;
		if (newHp > currentHp)
			for (int i = currentHp; i <= newHp; i++)
				hp[i].setVisible(true);
		else
			for (int i = currentHp; i >= newHp; i--)
				hp[i].setVisible(false);
		currentHp = newHp;
	}
	
	public void setVisible(boolean onOrOff){
		stripeView.setVisible(onOrOff);
		for (int i = 0; i < maxHp; i++) {
			hp[i].setVisible(onOrOff);
		}
	}
}
