package view;

import controller.HpController;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class BanditView {
	private FourPerspectiveSprite banditSprite;
	private Sprite turnPointer;
	private TextSprite hpChange;
	private int animationAttackFrameCounter;
	private int animationHpChangeFrameCounter;
	private AnimationSprite attackAnimation;
	
	private Group banditGroup;
	private HpController hp;
	
	public BanditView(Group hook, double xposition, double yposition, int hp) {
		banditGroup = new Group();
    	hook.getChildren().add(banditGroup);
		
		GraphicPaths paths = new GraphicPaths();
    	banditSprite = new FourPerspectiveSprite (paths.getPath("banditFloor"),
    			paths.getPath("banditFront"),
    			paths.getPath("banditRight"),
    			paths.getPath("banditLeft"),
    			paths.getPath("banditBack"),
    			banditGroup, xposition, yposition);
    	turnPointer = new Sprite(paths.getPath("turnPointer"), banditGroup, xposition - 40, yposition - 300);
    	hpChange = new TextSprite("", banditGroup, xposition + 40, yposition - 250, 50, Color.RED);
		animationAttackFrameCounter = 0;
		animationHpChangeFrameCounter = 0;
		
		attackAnimation = new AnimationSprite(paths.getPath("banditAnimation")+"attack_",
				banditGroup, xposition, yposition, 19);
		
		this.hp = new HpController(banditGroup, paths.getPath("banditHpStripe"), paths.getPath("banditPieceOfHp"),
				hp, xposition - 20, yposition + 70, 10);
		this.hp.setVisible(true);
	}
	
    public void relocate(double dx, double dy) {
    	banditGroup.setLayoutX(banditGroup.getLayoutX() - dx);
    	banditGroup.setLayoutY(banditGroup.getLayoutY() - dy);
    }
    
    public boolean intersects(FourPerspectiveSprite heroView) {
    	return banditSprite.intersects(heroView);
    }
	
	public void setVisible(boolean onOrOff) {
		banditSprite.setVisible(onOrOff);
	}
	
	public void updateView(double dx, double dy) {
		banditSprite.updateView(dx, dy);
	}
    
    public void changeHp(int howManyPoints) {
    	hp.changeHp(howManyPoints);
    }
    
	public void getDamageAnimationReady(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		hpChange.setColor(Color.RED);
		hpChange.setVisible(true);
	}
	
	public void turnPointerSetVisible (boolean isIt) {
		turnPointer.setVisible(isIt);
	}
	
	public void animation() {
		if (hpChange.getIsVisible())
			hpChangeAnimation();
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
	
	private void attackAnimation() {
		attackAnimation.animate(animationAttackFrameCounter/2);
		animationAttackFrameCounter++;
		if (animationAttackFrameCounter == 38) {
			attackAnimation.setVisible(false);
			animationAttackFrameCounter = 0;
		}
	}
	
	public void attackAnimationReady() {
		attackAnimation.setVisible(true);
	}
	
	public void showHpStripe(boolean yesOrNo) {
		this.hp.setVisible(yesOrNo);
	}
}
