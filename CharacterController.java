package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

class CharacterController {
	private static final String HERO_IMAGE_LOC_RIGHT = "file:graphic/character.png";
	private static final String HERO_IMAGE_LOC_LEFT = "file:graphic/character.png";
	private static final String HERO_IMAGE_LOC_BACK = "file:graphic/character.png";
    
	private CharacterView characterView;
	private Character characterModel;

    public CharacterController(Group hook) {
    	characterView = new CharacterView (HERO_IMAGE_LOC_RIGHT, hook, 1000, 350);
		characterView.setVisible(true);
		characterModel = new Character(900, 350,
				200, 200, 30, 30, 5, 3, 0);
    }
    
    public Character getCharacterModel(){
    	return characterModel;
    }
    
    public CharacterView getCharacterView(){
    	return characterView;
    }
    
    public void changeMoney(int money) {
    	characterModel.changeMoney(money);
    }
    
    public int getMoney() {
    	return characterModel.getMoney();
    }

}
