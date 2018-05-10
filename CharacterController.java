package application;

import javafx.scene.Group;

class CharacterController {
	private FourPerspectiveView characterView;
	private Character characterModel;

    public CharacterController(Group hook) {
    	GraphicPaths paths = new GraphicPaths();
    	characterView = new FourPerspectiveView (paths.getPath("characterFloor"),
    			paths.getPath("characterFront"),
    			paths.getPath("characterRight"),
    			paths.getPath("characterLeft"),
    			paths.getPath("characterBack"),
    			hook, 960, 690);
		characterView.setVisible(true);
		characterModel = new Character(1000, 650, 200, 200, 50, 50, 5, 5, 0);//x, y, xsize ysize hp maxHp dmg def money
    }
    
    public Character getCharacterModel(){
    	return characterModel;
    }
    
    public FourPerspectiveView getCharacterView(){
    	return characterView;
    }
    
    public void changeMoney(int money) {
    	characterModel.changeMoney(money);
    }
    
    public int getMoney() {
    	return characterModel.getMoney();
    }
    
    public void updateView(double dx, double dy) {
    	characterView.updateView(dx, dy);
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
	
	public void relocate(double dx, double dy) {
		characterView.relocate(dx, dy);
	}
	
	public void setVisible(boolean yesOrNot) {
		characterView.setVisible(yesOrNot);
	}

}
