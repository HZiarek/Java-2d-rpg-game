package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;

class BanditController {
	private Group banditGroup;
	private Bandit banditModel;
	private FourPerspectiveView banditView;
	private HpController hp;
	private View turnPointer;
	private TextView hpChange;
	private boolean animation;
	private int animationFrameCounter;
    
    private int pseudoTimeCounter;

    
    public BanditController(Group hook, double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def) {
    	banditGroup = new Group();
    	hook.getChildren().add(banditGroup);
    	
    	pseudoTimeCounter = 0;
    	
    	animation = false;
		animationFrameCounter = 0;
		
		GraphicPaths paths = new GraphicPaths();
		banditView = new FourPerspectiveView (paths.getPath("banditFloor"),
    			paths.getPath("banditFront"),
    			paths.getPath("banditRight"),
    			paths.getPath("banditLeft"),
    			paths.getPath("banditBack"),
    			banditGroup, xposition, yposition, xsize, ysize);
		banditModel = new Bandit(xposition, yposition, xsize, ysize, hp, maxHp, dmg, def);
		banditView.setVisible(true);
		this.hp = new HpController (banditGroup, paths.getPath("banditHpStripe"), paths.getPath("banditPieceOfHp"),
				hp, xposition - 20, yposition + 70, 10);
		turnPointer = new View(paths.getPath("turnPointer"), banditGroup, xposition - 40, yposition - 300);
		hpChange = new TextView("", banditGroup, xposition + 40, yposition - 250, 50, Color.RED);
		hpChange.setVisible(true);
		
    	
    	//bandit = new BaController(opponents, 700, -760, 300, 300, 20, 20, 10, 1);//x, y, xsize ysize hp maxHp dmg def
    }
	
    public void relocate(double dx, double dy) {
    	//banditGroup.relocate(banditGroup.getLayoutX() + dx, banditGroup.getLayoutY() + dy);
    	banditGroup.setLayoutX(banditGroup.getLayoutX() - dx);
    	banditGroup.setLayoutY(banditGroup.getLayoutY() - dy);
    }
    
    public boolean checkInteractions(FourPerspectiveView heroView){
    	if (!banditModel.getAlive())
			return false;

    	if (banditView.intersects(heroView)) {
    		banditModel.setInFight(true);
    		hp.setVisible(true);
    		turnPointer.setVisible(true);
    		return true;
    	}
    	return false;
    }
    
//    public int checkFight() {
//    	return nrOpponentInteraction;
//    }
    
    public void attacked(int howManyPoints) {
    	if (changeHpAndCheckIsDead(howManyPoints))
    		banditModel.setInFight(false);
    	else
    		turnPointer.setVisible(true);
    }
    
	
	public boolean isFight() {
		return banditModel.getInFight();
	}
	
	public int opponentsTurn () {
		pseudoTimeCounter++;
		if (pseudoTimeCounter < 120)
			return 0;
		
		turnPointer.setVisible(false);
		pseudoTimeCounter = 0;
		return banditModel.getDamage();
	}
	
	public void animation() {
		hpMovingAnimation();
	}
	
	public void isOpponentTurn() {
		turnPointer.setVisible(true);
	}
	
	private void hpMovingAnimation() {
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
	
	private boolean changeHpAndCheckIsDead(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		animation = true;
		if (!banditModel.changeHpAndCheckIsDead(howManyPoints)) {
			banditView.setVisible(false);
			hp.setVisible(false);
			turnPointer.setVisible(false);
			return true;
		}
		
		hp.changeHp(banditModel.getHp());
		return false;
	}
}

class BaController{
	private Bandit banditModel;
	private FourPerspectiveView banditView;
	private HpController hp;
	private View turnPointer;
	private TextView hpChange;
	private boolean animation;
	private int animationFrameCounter;
	
	public BaController(Group hook, double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def) {
		animation = false;
		animationFrameCounter = 0;
		
		GraphicPaths paths = new GraphicPaths();
		banditView = new FourPerspectiveView (paths.getPath("banditFloor"),
    			paths.getPath("banditFront"),
    			paths.getPath("banditRight"),
    			paths.getPath("banditLeft"),
    			paths.getPath("banditBack"),
    			hook, xposition, yposition, xsize, ysize);
		banditModel = new Bandit(xposition, yposition, xsize, ysize, hp, maxHp, dmg, def);
		banditView.setVisible(true);
		this.hp = new HpController (hook, paths.getPath("banditHpStripe"), paths.getPath("banditPieceOfHp"),
				hp, xposition - 20, yposition + 70, 10);
		turnPointer = new View(paths.getPath("turnPointer"), hook, xposition - 40, yposition - 300);
		hpChange = new TextView("", hook, xposition + 40, yposition - 250, 50, Color.RED);
		hpChange.setVisible(true);
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
	
	public boolean checkInteraction (FourPerspectiveView heroView) {
		if (!banditModel.getAlive())
			return false;
		return banditView.intersects(heroView);
	}
	
	public boolean changeHpAndCheckIsDead(int howManyPoints) {
		hpChange.setText("" + howManyPoints);
		animation = true;
		if (!banditModel.changeHpAndCheckIsDead(howManyPoints)) {
			banditView.setVisible(false);
			hp.setVisible(false);
			turnPointer.setVisible(false);
			return true;
		}
		
		hp.changeHp(banditModel.getHp());
		return false;
	}
	
	public int attack() {
		return banditModel.getDamage();
	}
	
	public void showHp() {
		hp.setVisible(true);
	}
	
	public void isMyTurn (boolean isIt) {
		turnPointer.setVisible(isIt);
	}
	
}
