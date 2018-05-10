package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class UIController {
	private Group root, ui;
	private Image eKeyLootImage, eKeyUseImage;
	private ImageView eKeyLootView, eKeyUseView;
    
    private Text goldText, mainTaskText;
    private String goldString, mainTask;
    
	private boolean interactionActiveUse;
	private boolean interactionActiveLoot;
	
	private SkillView skillNr1, skillNr2, skillNr3;
	
	private HpController hp;

    
	public UIController(Group hook) {
		root = hook;
		ui = new Group();
		root.getChildren().add(ui);
		interactionActiveUse = false;
		interactionActiveLoot = false;
		
		GraphicPaths paths = new GraphicPaths();
		
		eKeyLootImage = new Image (paths.getPath("eKeyLoot"));
		eKeyLootView = new ImageView (eKeyLootImage);
		eKeyLootView.relocate(1580, 850);

		eKeyUseImage = new Image (paths.getPath("eKeyUse"));
		eKeyUseView = new ImageView (eKeyUseImage);
		eKeyUseView.relocate(1580, 850);
		
		goldString = "Gold " + 0;
		goldText = new Text(goldString);
		goldText.setFont(Font.font ("Verdana", FontWeight.BOLD, 50));
		goldText.setFill(Color.GOLDENROD);
		ui.getChildren().add(goldText);
		goldText.relocate(1500, 10);
		
		mainTask = "Find 500 gold";
		mainTaskText = new Text(mainTask);
		mainTaskText.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		mainTaskText.setFill(Color.BISQUE);
		ui.getChildren().add(mainTaskText);
		mainTaskText.relocate(10, 880);
		
		skillNr1 = new SkillView (paths.getPath("skillAttack"), paths.getPath("skillHightlight"),
				root, 800, 900);
		skillNr2 = new SkillView (paths.getPath("skillHeal"), paths.getPath("skillHightlight"),
				root, 1000, 900);
		skillNr3 = new SkillView (paths.getPath("skillDefense"), paths.getPath("skillHightlight"),
				root, 1200, 900);
		
		hp = new HpController (hook);
	}
	
	public void showEkeyLoot(boolean onOrOff){
		if (onOrOff == interactionActiveLoot)
			return;
		if (onOrOff)
			ui.getChildren().add(eKeyLootView);
		else
			ui.getChildren().remove(eKeyLootView);
		interactionActiveLoot = onOrOff;
	}
	
	public void showEkeyUse(boolean onOrOff){
		if (onOrOff == interactionActiveUse)
			return;
		if (onOrOff)
			ui.getChildren().add(eKeyUseView);
		else
			ui.getChildren().remove(eKeyUseView);
		interactionActiveUse = onOrOff;
	}
	
	public void updateGold(int gold) {
		goldString = "Gold: " + gold;
		goldText.setText(goldString);
	}
	
	public void tmpFight() {
		mainTask = "FIGHT!";
		mainTaskText.setText(mainTask);
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
}

class HpController{
	private View hp[], stripeView;
	private Group hpGroup;
	private int currentHp;
	
	public HpController (Group hook) {
		hpGroup = new Group();
		hook.getChildren().add(hpGroup);
		GraphicPaths paths = new GraphicPaths();
		currentHp = 49;
		
		stripeView = new View (paths.getPath("hpStripe"), hpGroup, 295, 65);
		stripeView.setVisible(true);
		
		hp = new View[50];
		int tmpCounter = 0;

		for (int i = 0; i < 50; i++) {
			hp[i] = new View(paths.getPath("pieceOfHp"), hpGroup, 50 + tmpCounter, 60);
			tmpCounter += 10;
			hp[i].setVisible(true);
		}
		
	}

	public void changeHp(int newHp) {
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
}
