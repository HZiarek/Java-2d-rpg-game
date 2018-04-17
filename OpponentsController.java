package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class OpponentsController {
	
}

class BearController{
	private static final String BEAR_IMAGE_LOC = "file:graphic/bear.jpg";
	private Bear bearModel;
	private Image bearImage;
	private ImageView bearView;
	private Group environment;
	
	public BearController(Group hook, double xposition, double yposition, double xsize, double ysize,
			int hp, int maxHp, int dmg, int def) {
		environment = hook;
		bearModel = new Bear(xposition, yposition - 450, xsize, ysize, hp, maxHp, dmg, def);
		bearImage = new Image(BEAR_IMAGE_LOC);
		bearView = new ImageView(bearImage);
		bearView.relocate(xposition, yposition - (bearView.getBoundsInLocal().getHeight()/2));
		environment.getChildren().add(bearView);
	}
	
	public boolean checkInteraction (Character heroModel) {
		return bearModel.intersects(heroModel);
	}

	public void relocateModel(double dx, double dy) {
		bearModel.relocate(dx, dy);
	}
}
