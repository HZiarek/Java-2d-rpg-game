package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;

class OpponentsController {
    private Group root, opponents;
    private BanditController bandit;
    private int nrOpponentInteraction;
    private int numberOfOpponentsInFight;
    
    private int pseudoTimeCounter;

    
    public OpponentsController(Group hook) {
    	root = hook;
    	opponents = new Group();
    	root.getChildren().add(opponents);
    	
    	nrOpponentInteraction = 0;
    	numberOfOpponentsInFight = 0;
    	pseudoTimeCounter = 0;
		bandit = new BanditController(opponents, 700, -760, 300, 300, 20, 20, 10, 1);//x, y, xsize ysize hp maxHp dmg def
    }
	
    public void relocate(double dx, double dy) {
    	//opponents.relocate(opponents.getLayoutX() - dx, opponents.getLayoutY() - dy);
    	opponents.setLayoutX(opponents.getLayoutX() - dx);
    	opponents.setLayoutY(opponents.getLayoutY() - dy);
    }
    
    public boolean checkInteractions(FourPerspectiveView heroView){
    	if (bandit.checkInteraction(heroView)) {
    		nrOpponentInteraction = 1;
    		bandit.showHp();
    		bandit.isMyTurn(true);
    		numberOfOpponentsInFight++;
    		return true;
    	}
    	return false;
    }
    
    public int checkFight() {
    	return nrOpponentInteraction;
    }
    
    public void attacked(int howManyPoints) {
    	if (bandit.changeHpAndCheckIsDead(howManyPoints))
    		numberOfOpponentsInFight--;
    	else
    		bandit.isMyTurn(true);
    }
    
	
	public boolean isFight() {
		if (numberOfOpponentsInFight == 0)
			return false;
		else
			return true;
	}
	
	public int opponentsTurn () {
		pseudoTimeCounter++;
		if (pseudoTimeCounter < 120)
			return 0;
		
		bandit.isMyTurn(false);
		pseudoTimeCounter = 0;
		return bandit.attack();
	}
	
	public void animation() {
		bandit.hpMovingAnimation();
	}
	
	public void isOpponentTurn() {
		bandit.isMyTurn(true);
	}
}

class BanditController{
	private Bandit banditModel;
	private FourPerspectiveView banditView;
	private HpController hp;
	private View turnPointer;
	private TextView hpChange;
	private boolean animation;
	private int animationFrameCounter;
	
	public BanditController(Group hook, double xposition, double yposition, double xsize, double ysize,
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
