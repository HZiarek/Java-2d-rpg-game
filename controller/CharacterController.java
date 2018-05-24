package controller;

import javafx.scene.Group;
import view.*;
import model.Character;

/**
 * It provides management and control of the character with all additions:
 * arrow showing whose turn, text informing about change of health points and attack animation.
 */

class CharacterController {
	private CharacterView characterView;
	private Character characterModel;


    public CharacterController(Group hook) {
    	characterView = new CharacterView(hook);
		characterView.setVisible(true);
		characterModel = new Character(1000, 650, 200, 200, 50, 50, 5, 5, 0);//x, y, xsize ysize hp maxHp dmg def money
    }
    
    public Character getCharacterModel(){
    	return characterModel;
    }
    
    public FourPerspectiveSprite getCharacterView(){
    	return characterView.getCharacterSprite();
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
	
	public void animation() {
		characterView.animation();
	}
	
	public boolean changeHpAndCheckIsDead(int howManyPoints) {
		characterView.getDamageAnimationReady(howManyPoints);
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
		characterView.healAnimationReady(howManyPoints);
		characterModel.changeHpAndCheckIsDead(howManyPoints);
	}
	
	public void isMyTurn (boolean isIt) {
		characterView.turnPointerSetVisible(isIt);
	}
	
	public void isAttacking() {
		characterView.attackAnimationReady();
	}

}
