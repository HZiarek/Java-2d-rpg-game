package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class EnvironmentController {
    private Group root, environment;
    private Pane backgroundLayer;
	private Image backgroundImage;
	private ImageView backgroundView;
	private ChestController chestNr1, chestNr2, chestNr3;
	private LadderController ladderNr1, ladderNr2;
	int nrChestInteraction, nrLadderInteraction;
	
	//testowanie granic mapy
	private BoundaryController boudaries;

    
    public EnvironmentController(Group hook) {
    	root = hook;
    	nrChestInteraction = 0;
    	nrLadderInteraction = 0;
    	GraphicPaths paths = new GraphicPaths();
    	
    	backgroundImage = new Image(paths.getPath("background"));
    	backgroundView = new ImageView (backgroundImage);
    	backgroundLayer = new Pane();
		backgroundLayer.getChildren().add(backgroundView);
		environment = new Group (backgroundLayer);
		root.getChildren().add(environment);
		environment.relocate(0, -150);
		chestNr1 = new ChestController(environment, 580, 775, 300, 300, true, 1, 100);
		chestNr2 = new ChestController(environment, 1610, 460, 300, 300, true, 1, 100);
		chestNr3 = new ChestController(environment, 975, 1520, 300, 300, true, 1, 300);
		ladderNr1 = new LadderController(environment, 3050, 1515, 300, 300, true, 715, 1);
		ladderNr2 = new LadderController(environment, 1200, 800, 300, 300, true, 330, 2);
		
		boudaries = new BoundaryController(environment);
    }
    
    public boolean checkCanMoveAndRelocate(double dx, double dy, CharacterView heroView) {
    	if (!boudaries.checkCanMove(heroView) || dy != 0) {
    		environment.relocate(environment.getLayoutX() - dx, environment.getLayoutY() - dy);
    		return false;
    	}
    	return true;
    }
    
    public boolean checkMustStop(double dx, double dy, CharacterView heroView) {
    	return boudaries.checkMustStop(dx, dy, heroView);
    }
    
    public boolean checkInteractionsLoot(CharacterView heroView){
    	if (chestNr1.checkInteraction(heroView)) {
    		nrChestInteraction = 1; return true;
    	}
    	if (chestNr2.checkInteraction(heroView)) {
    		nrChestInteraction = 2; return true;
    	}
    	if (chestNr3.checkInteraction(heroView)) {
    		nrChestInteraction = 3; return true;
    	}
    	return false;
    }
    
    public boolean checkInteractionsUse(CharacterView heroView){
    	if (ladderNr1.checkInteraction(heroView)) {
    		nrLadderInteraction = 1; return true;
    	}
    	return false;
    }
    
    public int pickUpMoney() {
    	switch (nrChestInteraction) {
	        case 1:  return chestNr1.pickUpMoney();
	        case 2:  return chestNr2.pickUpMoney();
	        case 3:  return chestNr3.pickUpMoney();
	        default: return 0;
    	}
    }
    
    public void useLadder(CharacterView heroView) {
    	switch (nrLadderInteraction) {
	        case 1: checkCanMoveAndRelocate(ladderNr1.getXposition() - heroView.getXposition(), 0, heroView);//problem przemieszczenie hero
    	}
    }
    
    public boolean climbAndCheckIsStillOnLadder(double dy, CharacterView heroView) {
    	if (dy == 0)
    		return true;
    	
    	if (ladderNr1.getYposition() + dy - heroView.getYposition() < 0 && dy > 0) {
    		checkCanMoveAndRelocate(0, ladderNr1.getYposition() - heroView.getYposition(), heroView);
    		return false;
    	}
    	
    	if (heroView.getYposition() - ladderNr1.getYposition() + ladderNr1.getHeight() < 0 && dy < 0) {
    		checkCanMoveAndRelocate(0, ladderNr1.getYposition() - heroView.getYposition() - ladderNr1.getHeight(), heroView);
    		return false;
    	}
    	
    	checkCanMoveAndRelocate(0, dy, heroView);
    	return true;
    }

}

class ChestController{
	private Chest chestModel;
	private ChestView chestView;
	
	public ChestController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, int type, int value) {
		chestModel = new Chest(xposition, yposition, xsize, ysize, active, type, value);
		GraphicPaths paths = new GraphicPaths();
		switch (type) {
			case 1: chestView = new ChestView (paths.getPath("chest"), paths.getPath("chestOpen"), hook, xposition, yposition);
			default:  chestView = new ChestView (paths.getPath("chest"), paths.getPath("chestOpen"), hook, xposition, yposition);
		}
		chestView.setVisible(true);
	}
	
	public boolean checkInteraction (CharacterView heroView) {
		if (!chestModel.getActive())
			return false;
		return chestView.intersects(heroView);
	}
	
	public int pickUpMoney() {
		chestModel.setActive(false);
		chestView.changeImageOnOpen();
		return chestModel.getValue();
	}

	public void relocateModel(double dx, double dy) {
		chestModel.relocate(dx, dy);
	}
}

class LadderController{
	private Ladder ladderModel;
	private LadderView ladderView;
	
	public LadderController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, double height, int type) {
		
		ladderModel = new Ladder (xposition, yposition, xsize, ysize, active, height, type);
		
		GraphicPaths paths = new GraphicPaths();
		switch (type) {
			case 1: ladderView = new LadderView(paths.getPath("ladderLong"), hook, xposition, yposition); break;
			case 2: ladderView = new LadderView(paths.getPath("ladderShort"), hook, xposition, yposition); break;
			default:  ladderView = new LadderView(paths.getPath("ladder"), hook, xposition, yposition);
		}
		ladderView.setVisible(true);
	}
	
	public boolean checkInteraction (CharacterView heroView) {
		if (!ladderModel.getActive())
			return false;
		return ladderView.intersects(heroView);
	}
	
	public double getXpositionFromModel() {
		return ladderModel.getXposition();
	}
	
	public double getYpositionFromModel() {
		return ladderModel.getYposition();
	}
	
	public double getXposition() {
		return ladderView.getXposition();
	}
	
	public double getYposition() {
		return ladderView.getYposition();
	}
	
	public double getHeight() {
		return ladderModel.getHeight();
	}
}


//testowanie granic mapy
class BoundaryController{
	private View leftTopMove, rightTopMove,
					leftTopStop, rightTopStop;
	
	public BoundaryController(Group hook) {
		GraphicPaths paths = new GraphicPaths();
		leftTopMove = new View(paths.getPath("boundaryMove"), hook, 450, 1000);
		leftTopMove.setVisible(true);
		leftTopStop = new View(paths.getPath("boundaryStop"), hook, 40, 1000);
		leftTopStop.setVisible(true);
		
		
		rightTopMove = new View(paths.getPath("boundaryMove"), hook, 3000, 1000);
		rightTopMove.setVisible(true);
		rightTopStop = new View(paths.getPath("boundaryStop"), hook, 3200, 1000);
		rightTopStop.setVisible(true);
	}
	
	public boolean checkCanMove (CharacterView heroView) {
		return (leftTopMove.intersects(heroView) || rightTopMove.intersects(heroView));
	}
	
	public boolean checkMustStop (double dx, double dy, CharacterView heroView) {
		return ((leftTopStop.intersects(heroView) && dx < 0)
				|| (rightTopStop.intersects(heroView) && dx > 0));
	}
}
