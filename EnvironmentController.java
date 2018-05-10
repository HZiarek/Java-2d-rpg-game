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
	int nrChestInteraction, nrLadderInteraction;
	
	//testowanie granic mapy
	private StopperController boudaries;

    
    public EnvironmentController(Group hook) {
    	root = hook;
    	nrChestInteraction = 0;
    	nrLadderInteraction = 0;
    	GraphicPaths paths = new GraphicPaths();
    	
    	backgroundImage = new Image(paths.getPath("map"));
    	backgroundView = new ImageView (backgroundImage);
    	backgroundLayer = new Pane();
		backgroundLayer.getChildren().add(backgroundView);
		environment = new Group (backgroundLayer);
		root.getChildren().add(environment);
		environment.relocate(-1100, -2000);
		
		chestNr1 = new ChestController(environment, 2800, 1100, 300, 300, true, 1, 100);
		chestNr2 = new ChestController(environment, 2700, 1100, 300, 300, true, 2, 100);
		chestNr3 = new ChestController(environment, 2200, 2600, 300, 300, true, 1, 300);
		
		boudaries = new StopperController(environment);
    }
    
    public boolean checkCanMove(FourPerspectiveView heroView) {
    	return boudaries.checkCanMove(heroView);
    }
    
    public void relocate(double dx, double dy) {
    	environment.relocate(environment.getLayoutX() - dx, environment.getLayoutY() - dy);
    }
    
    public boolean checkInteractionsLoot(FourPerspectiveView heroView){
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
    
    public boolean checkInteractionsUse(FourPerspectiveView heroView){
    	//option to do
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
	private Chest chestModel;
	private ChestView chestView;
	
	public ChestController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, int type, int value) {
		chestModel = new Chest(xposition, yposition, xsize, ysize, active, type, value);
		GraphicPaths paths = new GraphicPaths();
		switch (type) {
			case 1: chestView = new ChestView (paths.getPath("chest"), paths.getPath("chestOpen"), 
					hook, xposition, yposition, xsize, ysize); break;
			case 2: chestView = new ChestView (paths.getPath("barrel"), paths.getPath("barrelOpen"),
					hook, xposition, yposition, xsize, ysize); break;
			default:  chestView = new ChestView (paths.getPath("chest"), paths.getPath("chestOpen"),
					hook, xposition, yposition, xsize, ysize); break;
		}
		chestView.setVisible(true);
	}
	
	public boolean checkInteraction (FourPerspectiveView heroView) {
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

//testowanie granic mapy
class StopperController{
	private View upMain, downMain, leftMain, rightMain, forestTop, forChest1, forChest2, forChest3;
	
	public StopperController(Group hook) {
		GraphicPaths paths = new GraphicPaths();
		upMain = new View(paths.getPath("stopperMainHorizontal"), hook, 2000, 1000);
		//upMain.setVisible(true);
		
		downMain = new View(paths.getPath("stopperMainHorizontal"), hook, 2000, 4000);
		//downMain.setVisible(true);
		
		leftMain = new View(paths.getPath("stopperMainVertical"), hook, 500, 4000);
		//leftMain.setVisible(true);
		
		rightMain = new View(paths.getPath("stopperMainVertical"), hook, 3500, 4000);
		//rightMain.setVisible(true);
		
		forestTop = new View(paths.getPath("stopperForestTop"), hook, 2200, 1900);
		//forestTop.setVisible(true);
		
		forChest1 = new View(paths.getPath("stopperChest"), hook, 2800, 1100);
		//forChest1.setVisible(true);
		
		forChest2 = new View(paths.getPath("stopperChest"), hook, 2700, 1100);
		//forChest2.setVisible(true);
		
		forChest3 = new View(paths.getPath("stopperChest"), hook, 2200, 2600);
		//forChest3.setVisible(true);
	}
	
	public boolean checkCanMove (FourPerspectiveView heroView) {
		return !(upMain.intersects(heroView)
				|| downMain.intersects(heroView)
				|| leftMain.intersects(heroView)
				|| rightMain.intersects(heroView)
				|| forestTop.intersects(heroView)
				|| forChest1.intersects(heroView) || forChest2.intersects(heroView) || forChest3.intersects(heroView));
	}
}
