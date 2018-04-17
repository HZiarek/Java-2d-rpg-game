package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

class CharacterController {
	private static final String HERO_IMAGE_LOC = "file:graphic/character.png";
    
    private Group root;
	private Image characterImage;
	private ImageView characterView;
	private Character characterModel;

	
    public CharacterController(Group hook) {
    	root = hook;
    	
    	characterImage = new Image(HERO_IMAGE_LOC);
    	characterView = new ImageView (characterImage);
		root.getChildren().add(characterView);
		characterView.relocate(900 - (characterView.getBoundsInLocal().getWidth()/2), 350);
		characterModel = new Character(900 - (characterView.getBoundsInLocal().getWidth()/2), 350,
				200, 200, 30, 30, 5, 3, 0);

    }
    
    public Character getCharacterModel(){
    	return characterModel;
    }
    
    public void changeMoney(int money) {
    	characterModel.changeMoney(money);
    }
    
    public int getMoney() {
    	return characterModel.getMoney();
    }

}
