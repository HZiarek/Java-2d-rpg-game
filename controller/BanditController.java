package controller;

import javafx.scene.Group;
import view.*;
import model.*;

/**
 * It provides management and control of the enemy (model and view) with all additions: health points strip,
 * arrow showing whose turn, text informing about change of health points and attack animation.
 * Everything what might be displayed on the screen is added to separate JavaFX group called the "banditGroup".
 */

class BanditController {
	private Creature banditModel;
	private BanditView banditView;
    
    private int pseudoTimeCounter;

    public BanditController(Group hook, double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def) {
    	
    	pseudoTimeCounter = 0;
		banditView = new BanditView(hook, xposition, yposition, hp);
		banditModel = new Creature(xposition, yposition, xsize, ysize, hp, maxHp, dmg);
		banditView.setVisibleBody(true);
    }
	
    public void relocate(double dx, double dy) {
    	banditView.relocate(dx, dy);
    }
    
    public boolean checkInteractions(FourPerspectiveSprite heroView){
    	if (!banditModel.getAlive())
			return false;

    	if (banditView.intersects(heroView)) {
    		banditModel.setInFight(true);
    		banditView.setVisible(true);
    		return true;
    	}
    	return false;
    }
    
    public void attacked(int howManyPoints) {
    	if (changeHpAndCheckIsDead(howManyPoints))
    		banditModel.setInFight(false);
    	else
    		banditView.turnPointerSetVisible(true);
    }
    
	public boolean isFight() {
		return banditModel.getInFight();
	}
	
	public int opponentsTurn () {
		pseudoTimeCounter++;
		if (pseudoTimeCounter < 120)
			return 0;
		
		banditView.turnPointerSetVisible(false);
		pseudoTimeCounter = 0;
		banditView.attackAnimationReady();
		return banditModel.getDamage();
	}
	
	public void isOpponentTurn() {
		banditView.turnPointerSetVisible(true);
	}
	
	public void animation() {
		banditView.animation(banditModel.getAlive());
	}
	
	private boolean changeHpAndCheckIsDead(int howManyPoints) {
		banditView.getDamageAnimationReady(howManyPoints);
		if (!banditModel.changeHpAndCheckIsDead(howManyPoints)) {
			banditView.setVisible(false);
			return true;
		}
		
		banditView.changeHp(banditModel.getHp());
		return false;
	}
	
	public void showHpStripe(boolean yesOrNo) {
		banditView.showHpStripe(yesOrNo);
	}
}
