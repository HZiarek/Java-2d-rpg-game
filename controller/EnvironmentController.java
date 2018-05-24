package controller;

import javafx.scene.Group;
import view.*;
import model.*;

/**
 * It provides management and control of the environment (only without enemy);
 * contains special controllers for chests and boundaries. Everything what might be displayed on the screen
 * is added to separate JavaFX group called the "environmentGroup".
 */

class EnvironmentController {
    private Group root, environmentGroup;
    private MapSprite mapView;
	private ChestController chestNr1, chestNr2, chestNr3;
	int nrChestInteraction;
	
	//boundaries testing
	private StopperController boudaries;
    
    public EnvironmentController(Group hook) {
    	root = hook;
    	nrChestInteraction = 0;
    	GraphicPaths paths = new GraphicPaths();
    	
    	environmentGroup = new Group ();
		root.getChildren().add(environmentGroup);
		
		mapView = new MapSprite(paths.getPath("map"), environmentGroup);
		
		environmentGroup.relocate(-1100, -2000);
		
		chestNr1 = new ChestController(environmentGroup, 2800, 1150, 300, 300, true, 1, 100);
		chestNr2 = new ChestController(environmentGroup, 2700, 1150, 300, 300, true, 2, 100);
		chestNr3 = new ChestController(environmentGroup, 2150, 2650, 300, 300, true, 1, 300);
		
		boudaries = new StopperController(environmentGroup);
    }
    
    public boolean checkCanMove(FourPerspectiveSprite heroView) {
    	return boudaries.checkCanMove(heroView);
    }
    
    public void relocate(double dx, double dy) {
    	environmentGroup.relocate(environmentGroup.getLayoutX() - dx, environmentGroup.getLayoutY() - dy);
    }
    
    public boolean checkInteractionsLoot(FourPerspectiveSprite heroView){
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
    
    public boolean checkInteractionsUse(FourPerspectiveSprite heroView){
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
	private ChestSprite chestView;
	
	public ChestController(Group hook, double xposition, double yposition, double xsize, double ysize,
			boolean active, int type, int value) {
		chestModel = new Chest(xposition, yposition, xsize, ysize, active, type, value);
		GraphicPaths paths = new GraphicPaths();
		switch (type) {
			case 1: chestView = new ChestSprite (paths.getPath("chest"), paths.getPath("chestOpen"), 
					hook, xposition, yposition, xsize, ysize); break;
			case 2: chestView = new ChestSprite (paths.getPath("barrel"), paths.getPath("barrelOpen"),
					hook, xposition, yposition, xsize, ysize); break;
			default:  chestView = new ChestSprite (paths.getPath("chest"), paths.getPath("chestOpen"),
					hook, xposition, yposition, xsize, ysize); break;
		}
		chestView.setVisible(true);
	}
	
	public boolean checkInteraction (FourPerspectiveSprite heroView) {
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

class StopperController{
	private Sprite upMain, downMain, leftMain, rightMain, forestTop, forChest1, forChest2, forChest3;
	
	public StopperController(Group hook) {
		GraphicPaths paths = new GraphicPaths();
		upMain = new Sprite(paths.getPath("stopperMainHorizontal"), hook, 2000, 1000);
		//upMain.setVisible(true);
		
		downMain = new Sprite(paths.getPath("stopperMainHorizontal"), hook, 2000, 4000);
		//downMain.setVisible(true);
		
		leftMain = new Sprite(paths.getPath("stopperMainVertical"), hook, 500, 4000);
		//leftMain.setVisible(true);
		
		rightMain = new Sprite(paths.getPath("stopperMainVertical"), hook, 3500, 4000);
		//rightMain.setVisible(true);
		
		forestTop = new Sprite(paths.getPath("stopperForestTop"), hook, 2150, 1930);
		//forestTop.setVisible(true);
		
		forChest1 = new Sprite(paths.getPath("stopperChest"), hook, 2800, 1100);
		//forChest1.setVisible(true);
		
		forChest2 = new Sprite(paths.getPath("stopperChest"), hook, 2700, 1100);
		//forChest2.setVisible(true);
		
		forChest3 = new Sprite(paths.getPath("stopperChest"), hook, 2150, 2600);
		//forChest3.setVisible(true);
	}
	
	public boolean checkCanMove (FourPerspectiveSprite heroView) {
		return !(upMain.intersects(heroView)
				|| downMain.intersects(heroView)
				|| leftMain.intersects(heroView)
				|| rightMain.intersects(heroView)
				|| forestTop.intersects(heroView)
				|| forChest1.intersects(heroView) || forChest2.intersects(heroView) || forChest3.intersects(heroView));
	}
}
