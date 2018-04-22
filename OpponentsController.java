package application;

import javafx.scene.Group;

class OpponentsController {
    private Group root, opponents;
    private BearController bear;
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
		bear = new BearController(opponents, 1650, 1360, 300, 300, 100, 15, 5, 10);
    }
	
    public void relocate(double dx, double dy) {
    	//opponents.relocate(opponents.getLayoutX() - dx, opponents.getLayoutY() - dy);
    	opponents.setLayoutX(opponents.getLayoutX() - dx);
    	opponents.setLayoutY(opponents.getLayoutY() - dy);
    }
    
    public boolean checkInteractions(CharacterView heroView){
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

class BearController{
	private Bear bearModel;
	private BearView bearView;
	
	public BearController(Group hook, double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def) {
		GraphicPaths paths = new GraphicPaths();
		bearView = new BearView (paths.getPath("bear"), hook, xposition, yposition, xsize, ysize);
		bearModel = new Bear(xposition, yposition - 450, xsize, ysize, hp, maxHp, dmg, def);
		bearView.setVisible(true);
	}
	
	public boolean checkInteraction (CharacterView heroView) {
		if (!bearModel.getAlive())
			return false;
		return bearView.intersects(heroView);
	}
	
	public boolean changeHpAndCheckIsDead(int howManyPoints) {
		if (!bearModel.changeHpAndCheckIsDead(howManyPoints)) {
			bearView.setVisible(false);
			return true;
		}
		else
			return false;
	}
	
	public void makeMove (CharacterController character) {
		character.changeHpAndCheckIsDead(-bearModel.getDamage());
	}
}
