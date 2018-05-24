package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class CharacterView {
	private FourPerspectiveSprite characterSprite;
	private Sprite turnPointer;
	private TextSprite hpChange;
	private int animationAttackFrameCounter;
	private int animationHealFrameCounter;
	private int animationHpChangeFrameCounter;
	private AnimationSprite attackAnimation;
	private AnimationSprite healAnimation;
	private Group characterGroup;

	public CharacterView(Group hook) {
		characterGroup = new Group();
    	hook.getChildren().add(characterGroup);
		
		GraphicPaths paths = new GraphicPaths();
    	characterSprite = new FourPerspectiveSprite (paths.getPath("characterFloor"),
    			paths.getPath("characterFront"),
    			paths.getPath("characterRight"),
    			paths.getPath("characterLeft"),
    			paths.getPath("characterBack"),
    			hook, 960, 690);
		turnPointer = new Sprite(paths.getPath("turnPointer"), characterGroup, 920, 450);
		hpChange = new TextSprite("", characterGroup, 900, 400, 50, Color.RED);
		animationAttackFrameCounter = 0;
		animationHealFrameCounter = 0;
		animationHpChangeFrameCounter = 0;
		
		attackAnimation = new AnimationSprite(paths.getPath("characterAnimation")+"attack_", characterGroup, 960, 690, 18);
		healAnimation = new AnimationSprite(paths.getPath("characterAnimation")+"heal_", characterGroup, 930, 670, 11);
	}
	
	public void setVisible(boolean onOrOff) {
		characterSprite.setVisible(onOrOff);
	}
	
	public void updateView(double dx, double dy) {
		characterSprite.updateView(dx, dy);
	}
	
    public FourPerspectiveSprite getCharacterSprite(){
    	return characterSprite;
    }
    
	public void relocate(double dx, double dy) {
		characterSprite.relocate(dx, dy);
	}
	
	public void getDamageAnimationReady(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		hpChange.setColor(Color.RED);
		hpChange.setVisible(true);
	}
	
	public void healAnimationReady(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		hpChange.setColor(Color.LIMEGREEN);
		hpChange.setVisible(true);
		healAnimation.setVisible(true);
	}
	
	public void turnPointerSetVisible (boolean isIt) {
		turnPointer.setVisible(isIt);
	}
	
	public void animation() {
		if (hpChange.getIsVisible())
			hpChangeAnimation();
		if (healAnimation.getIsVisible())
			healAnimation();
		if (attackAnimation.getIsVisible())
			attackAnimation();
	}
	
	private void hpChangeAnimation(){
		hpChange.relocate(0, -1);
		animationHpChangeFrameCounter++;
		if (animationHpChangeFrameCounter == 49) {
			hpChange.setText("");
		}
		
		if (animationHpChangeFrameCounter == 50) {
			hpChange.setVisible(false);
			animationHpChangeFrameCounter = 0;
			hpChange.relocate(0, 51);
		}
	}
	
	private void healAnimation() {
		healAnimation.animate(animationHealFrameCounter/5);
		animationHealFrameCounter++;
		if (animationHealFrameCounter == 55) {
			healAnimation.setVisible(false);
			animationHealFrameCounter = 0;
		}
	}
	
	private void attackAnimation() {
		attackAnimation.animate(animationAttackFrameCounter/2);
		animationAttackFrameCounter++;
		if (animationAttackFrameCounter == 36) {
			attackAnimation.setVisible(false);
			characterSprite.setVisible(true);
			animationAttackFrameCounter = 0;
		}
	}
	
	public void attackAnimationReady() {
		attackAnimation.setVisible(true);
		characterSprite.setVisible(false);
	}
}
