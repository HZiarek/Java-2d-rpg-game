package application;

import javafx.scene.Group;

class CharacterController {
	private CharacterView characterView;
	private Character characterModel;

    public CharacterController(Group hook) {
    	GraphicPaths paths = new GraphicPaths();
    	characterView = new CharacterView (paths.getPath("characterRight"),
    			paths.getPath("characterLeft"),
    			paths.getPath("characterBack"),
    			hook, 1000, 650);
		characterView.setVisible(true);
		characterModel = new Character(1000, 650, 200, 200, 50, 50, 5, 5, 0);//x, y, xsize ysize hp maxHp dmg def money
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
    
    public void updateView(int dx, boolean ladder) {
    	characterView.updateView(dx, ladder);
    }
    
	public void healMax() {
		characterModel.setHp(characterModel.getMaxHp());
	}
	
	public void defense() {
		characterModel.setDefenseIsActive(true);
	}
	
	public void changeHpAndCheckIsDead(int howManyPoints) {
		if (!characterModel.changeHpAndCheckIsDead(howManyPoints))
			characterView.setVisible(false);
	}
	
	public int getHp() {
		return characterModel.getHp();
	}

}
