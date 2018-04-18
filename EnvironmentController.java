package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

class EnvironmentController {
    private static final String BACKGROUND_IMAGE_LOC = "file:graphic/back.jpg";
    
    private Group root, environment;
    private Pane backgroundLayer;
	private Image backgroundImage;
	private ImageView backgroundView;
	private ChestController chestNr1, chestNr2, chestNr3;
	private LadderController ladderNr1;
	int nrChestInteraction, nrLadderInteraction;

    
    public EnvironmentController(Group hook) {
    	root = hook;
    	nrChestInteraction = 0;
    	nrLadderInteraction = 0;
    	
    	backgroundImage = new Image(BACKGROUND_IMAGE_LOC);
    	backgroundView = new ImageView (backgroundImage);
    	backgroundLayer = new Pane();
		backgroundLayer.getChildren().add(backgroundView);
		environment = new Group (backgroundLayer);
		root.getChildren().add(environment);
		environment.relocate(0, -150);
		chestNr1 = new ChestController(environment, 500, 750, 300, 300, true, 100);
		chestNr2 = new ChestController(environment, 1540, 410, 300, 300, true, 100);
		chestNr3 = new ChestController(environment, 880, 1460, 300, 300, true, 300);
		ladderNr1 = new LadderController(environment, 3000, 1300, 300, 300, true, 200);
    }
    
    public void relocate(double dx, double dy) {
    	environment.relocate(environment.getLayoutX() - dx, environment.getLayoutY() - dy);
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
    
    public void useLadder() {
    	switch (nrLadderInteraction) {
	        case 1: environment.relocate(- ladderNr1.getXpositionFromModel() + 900, environment.getLayoutY() - 10);//problem przemieszczenie hero
    	}
    }
    
    public void climbOnLadder(double dy, CharacterView heroView) {
    	if (dy == 0)
    		return;
    	if (ladderNr1.getYposition() <= heroView.getXposition() - dy)
    			//&& ladderNr1.getYposition() + ladderNr1.getHeight() >= heroView.getXposition() - dy)
    		 environment.relocate(environment.getLayoutX(), environment.getLayoutY());
    }

}

class ChestController{
	private static final String CHEST_IMAGE_LOC = "file:graphic/chest.png";
	private static final String CHEST_OPEN_IMAGE_LOC = "file:graphic/chestOpen.png";
	private Chest chestModel;
	private ChestView chestView;
	
	public ChestController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, int value) {
		chestModel = new Chest(xposition, yposition, xsize, ysize, active, value);
		chestView = new ChestView (CHEST_IMAGE_LOC, CHEST_OPEN_IMAGE_LOC, hook, xposition, yposition);
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
	private static final String LADDER_IMAGE_LOC = "file:graphic/ladder.jpg";
	private Ladder ladderModel;
	private LadderView ladderView;
	
	public LadderController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, double height) {
		
		ladderModel = new Ladder (xposition, yposition - 450, xsize, ysize, active, height);
		ladderView = new LadderView(LADDER_IMAGE_LOC, hook, xposition, yposition);
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
