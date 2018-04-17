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
	int nrChestInteraction;

    
    public EnvironmentController(Group hook) {
    	root = hook;
    	nrChestInteraction = 0;
    	
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
    }
    
    public void relocate(double dx, double dy) {
    	environment.relocate(environment.getLayoutX() - dx, environment.getLayoutY() - dy);
    	//relocation models
    	chestNr1.relocateModel(-dx, -dy);
    	chestNr2.relocateModel(-dx, -dy);
    	chestNr3.relocateModel(-dx, -dy);
    }
    
    public boolean checkInteractions(Character heroModel){
    	if (chestNr1.checkInteraction(heroModel)) {
    		nrChestInteraction = 1; return true;
    	}
    	if (chestNr2.checkInteraction(heroModel)) {
    		nrChestInteraction = 2; return true;
    	}
    	if (chestNr3.checkInteraction(heroModel)) {
    		nrChestInteraction = 3; return true;
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

}

class ChestController{
	private static final String CHEST_IMAGE_LOC = "file:graphic/chest.png";
	private Chest chestModel;
	private Image chestImage;
	private ImageView chestView;
	private Group environment;
	
	public ChestController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, int value) {
		environment = hook;
		chestModel = new Chest (xposition, yposition - 450, xsize, ysize, active, value);
		chestImage = new Image(CHEST_IMAGE_LOC);
		chestView = new ImageView(chestImage);
		chestView.relocate(xposition, yposition - (chestView.getBoundsInLocal().getHeight()/2));
		environment.getChildren().add(chestView);
	}
	
	public boolean checkInteraction (Character heroModel) {
		if (!chestModel.getActive())
			return false;
		return chestModel.intersects(heroModel);
	}
	
	public int pickUpMoney() {
		chestModel.setActive(false);
		return chestModel.getValue();
	}

	public void relocateModel(double dx, double dy) {
		chestModel.relocate(dx, dy);
	}
}

class LadderController{
	private static final String LADDER_IMAGE_LOC = "file:graphic/ladder.jpg";
	private Ladder ladderModel;
	private Image ladderImage;
	private ImageView ladderView;
	private Group environment;
	
	public LadderController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, int bottomPoint, int topPoint) {
		environment = hook;
		ladderModel = new Ladder (xposition, yposition - 450, xsize, ysize, active, bottomPoint, topPoint);
		ladderImage = new Image(LADDER_IMAGE_LOC);
		ladderView = new ImageView(ladderImage);
		ladderView.relocate(xposition, yposition - (ladderView.getBoundsInLocal().getHeight()/2));
		environment.getChildren().add(ladderView);
	}
	
	public boolean checkInteraction (Character heroModel) {
		if (!ladderModel.getActive())
			return false;
		return ladderModel.intersects(heroModel);
	}

	public void relocateModel(double dx, double dy) {
		ladderModel.relocate(dx, dy);
	}
}
