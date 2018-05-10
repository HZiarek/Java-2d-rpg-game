package application;

import javafx.scene.Group;

class OpponentsController {
    private Group root, opponents;
    private BanditController bear;
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
		bear = new BanditController(opponents, 1200, 100, 200, 200, 15, 15, 10, 1);//x, y, xsize ysize hp maxHp dmg def
    }
	
    public void relocate(double dx, double dy) {
    	//opponents.relocate(opponents.getLayoutX() - dx, opponents.getLayoutY() - dy);
    	opponents.setLayoutX(opponents.getLayoutX() - dx);
    	opponents.setLayoutY(opponents.getLayoutY() - dy);
    }
    
    public boolean checkInteractions(CreatureView heroView){
    	if (bear.checkInteraction(heroView)) {
    		nrOpponentInteraction = 1;
    		numberOfOpponentsInFight++;
    		return true;
    	}
    	return false;
    }
    
    public int checkFight() {
    	return nrOpponentInteraction;
    }
    
    public void attacked(int howManyPoints) {
    	if (bear.changeHpAndCheckIsDead(howManyPoints))
    		numberOfOpponentsInFight--;
    }
    
	
	public boolean isFight() {
		if (numberOfOpponentsInFight == 0)
			return false;
		else
			return true;
	}
	
	public boolean opponentsTurn (CharacterController character) {
		pseudoTimeCounter++;
		if (pseudoTimeCounter < 120)
			return false;
		else
			pseudoTimeCounter = 0;
		bear.makeMove(character);
		return true;
	}
}

class BanditController{
	private Bandit banditModel;
	private CreatureView banditView;
	
	public BanditController(Group hook, double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def) {
		GraphicPaths paths = new GraphicPaths();
		banditView = new CreatureView (paths.getPath("banditFloor"),
    			paths.getPath("banditFront"),
    			paths.getPath("banditRight"),
    			paths.getPath("banditLeft"),
    			paths.getPath("banditBack"),
    			hook, xposition, yposition, xsize, ysize);
		banditModel = new Bandit(xposition, yposition, xsize, ysize, hp, maxHp, dmg, def);
		banditView.setVisible(true);
	}
	
	public boolean checkInteraction (CreatureView heroView) {
		if (!banditModel.getAlive())
			return false;
		return banditView.intersects(heroView);
	}
	
	public boolean changeHpAndCheckIsDead(int howManyPoints) {
		if (!banditModel.changeHpAndCheckIsDead(howManyPoints)) {
			banditView.setVisible(false);
			return true;
		}
		else
			return false;
	}
	
	public void makeMove (CharacterController character) {
		character.changeHpAndCheckIsDead(-banditModel.getDamage());
	}
}
