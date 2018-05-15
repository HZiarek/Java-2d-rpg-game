package controller;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import view.*;
import model.Character;

class CharacterController {
	private FourPerspectiveView characterView;
	private Character characterModel;
	private View turnPointer;
	private TextView hpChange;
	private boolean animation;
	private int animationFrameCounter;

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
		turnPointer = new View(paths.getPath("turnPointer"), hook, 920, 450);
		
		hpChange = new TextView("", hook, 900, 400, 50, Color.RED);
		hpChange.setVisible(true);
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
	
	public void hpMovingAnimation() {
		if (!animation)
			return;
		
		hpChange.relocate(0, -1);
		animationFrameCounter++;
		if (animationFrameCounter == 50) {
			animation = false;
			animationFrameCounter = 0;
			hpChange.setText("");
			hpChange.relocate(0, 51);
		}
	}
	
	public boolean changeHpAndCheckIsDead(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		hpChange.setColor(Color.RED);
		animation = true;
		return characterModel.changeHpAndCheckIsDead(howManyPoints);
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
	
	public void heal(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		hpChange.setColor(Color.LIMEGREEN);
		animation = true;
		characterModel.changeHpAndCheckIsDead(howManyPoints);
	}
	
	public void isMyTurn (boolean isIt) {
		turnPointer.setVisible(isIt);
	}

}
