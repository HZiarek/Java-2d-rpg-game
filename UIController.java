package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class UIController {
	private static final String EKEY_LOOT_IMAGE_LOC = "file:graphic/e-key.png";
	private static final String EKEY_USE_IMAGE_LOC = "file:graphic/e-key-use.png";
	
	private Group root, ui;
	private Image eKeyLootImage, eKeyUseImage;
	private ImageView eKeyLootView, eKeyUseView;
    
    private Text goldText, mainTaskText;
    private String goldString, mainTask;
    
	private boolean interactionActiveUse;
	private boolean interactionActiveLoot;
    
	public UIController(Group hook) {
		root = hook;
		ui = new Group();
		root.getChildren().add(ui);
		interactionActiveUse = false;
		interactionActiveLoot = false;;
		
		eKeyLootImage = new Image (EKEY_LOOT_IMAGE_LOC);
		eKeyLootView = new ImageView (eKeyLootImage);
		eKeyLootView.relocate(1650, 850);

		eKeyUseImage = new Image (EKEY_USE_IMAGE_LOC);
		eKeyUseView = new ImageView (eKeyUseImage);
		eKeyUseView.relocate(1650, 850);
		
		goldString = "Gold " + 0;
		goldText = new Text(goldString);
		goldText.setFont(Font.font ("Verdana", FontWeight.BOLD, 50));
		goldText.setFill(Color.GOLDENROD);
		ui.getChildren().add(goldText);
		goldText.relocate(1500, 10);
		
		mainTask = "Main task:\n	Find 500 gold";
		mainTaskText = new Text(mainTask);
		mainTaskText.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		mainTaskText.setFill(Color.BISQUE);
		ui.getChildren().add(mainTaskText);
		mainTaskText.relocate(10, 880);
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

}
