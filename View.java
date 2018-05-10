package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class View {
	protected Image image;
	protected ImageView imageView;
	protected Group hook;
	protected double Xposition;
	protected double Yposition;
	private double Xsize;
	private double Ysize;

	public View(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		this.hook = hook;
		Xposition = xposition; //mid
		Yposition = yposition; //bottom
		Xsize = xsize;
		Ysize = ysize;
		image = new Image(graphicPath);
		imageView = new ImageView(image);
		imageView.relocate(xposition - (imageView.getBoundsInLocal().getWidth()/2),
				yposition - (imageView.getBoundsInLocal().getHeight()));
	}
	
	public View(String graphicPath, Group hook, double xposition, double yposition) {
		this.hook = hook;
		Xposition = xposition;
		Yposition = yposition;
		image = new Image(graphicPath);
		imageView = new ImageView(image);
		Xsize = imageView.getBoundsInLocal().getWidth();
		Ysize = imageView.getBoundsInLocal().getHeight();
		imageView.relocate(xposition - (imageView.getBoundsInLocal().getWidth()/2),
				yposition - (imageView.getBoundsInLocal().getHeight()));
	}
	
	public void setVisible(boolean onOrOff) {
		if (onOrOff)
			hook.getChildren().add(imageView);
		else
			hook.getChildren().remove(imageView);
	}
	
	public Rectangle2D getBoundary(){
	       //return new Rectangle2D(imageView.getBoundsInLocal().getMinX() - (imageView.getBoundsInLocal().getWidth()/2),
	    	//	   imageView.getBoundsInLocal().getMinY() - (imageView.getBoundsInLocal().getHeight()),Xsize,Ysize);
		return new Rectangle2D(hook.getLayoutX() + Xposition - Xsize/2, hook.getLayoutY() + Yposition - Ysize, Xsize, Ysize);
	}
		
	public boolean intersects (View example){
		return example.getBoundary().intersects( this.getBoundary() );
	}
	
	public void relocate(double x, double y) {
		Xposition += x;
		Yposition += y;
		imageView.relocate(Xposition - (imageView.getBoundsInLocal().getWidth()/2),
				Yposition - (imageView.getBoundsInLocal().getHeight()));
	}
	
	public double getXposition() {
		return hook.getLayoutX() + Xposition;// - Xsize/2;
	}
	
	public double getYposition() {
		return hook.getLayoutY() + Yposition;// - Ysize;
	}
}

class ChestView extends View{
	private Image imageOpen;
	
	public ChestView(String graphicPath, String graphicOpenPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		super (graphicPath, hook, xposition, yposition, xsize, ysize);
		imageOpen = new Image(graphicOpenPath);
	}
	
	public ChestView(String graphicPath, String graphicOpenPath, Group hook, double xposition, double yposition) {
		super (graphicPath, hook, xposition, yposition);
		imageOpen = new Image(graphicOpenPath);
	}
	
	public void changeImageOnOpen () {
		imageView.setImage(imageOpen);
	}
}

class CreatureView extends View{
	private Image imageFront;
	private Image imageLeft;
	private Image imageBack;
	private Image imageRight;
	private ImageView perspectiveView;
	private enum perspective{
		RIGHT, LEFT, BACK, FRONT
	} 
	perspective viewDirection;
	
	public CreatureView(String graphicPathFloor, String graphicPathFront, String graphicPathRight, String graphicPathLeft, String graphicPathBack,
			Group hook, double xposition, double yposition, double xsize, double ysize) {
		
		super (graphicPathFloor, hook, xposition, yposition, xsize, ysize);
		imageLeft = new Image(graphicPathLeft);
		imageFront = new Image(graphicPathFront);
		imageBack = new Image(graphicPathBack);
		imageRight = new Image(graphicPathRight);
		perspectiveView = new ImageView(imageFront);
		perspectiveView.relocate(xposition - (perspectiveView.getBoundsInLocal().getWidth()/2),
				yposition - (perspectiveView.getBoundsInLocal().getHeight()));
		hook.getChildren().add(perspectiveView);
		viewDirection = perspective.FRONT;
	}
	
	public CreatureView(String graphicPathFloor, String graphicPathFront,String graphicPathRight, String graphicPathLeft, String graphicPathBack,
			Group hook, double xposition, double yposition) {
		super (graphicPathFloor, hook, xposition, yposition);
		imageLeft = new Image(graphicPathLeft);
		imageFront = new Image(graphicPathFront);
		imageBack = new Image(graphicPathBack);
		imageRight = new Image(graphicPathRight);
		perspectiveView = new ImageView(imageFront);
		perspectiveView.relocate(xposition - (perspectiveView.getBoundsInLocal().getWidth()/2),
				yposition - (perspectiveView.getBoundsInLocal().getHeight()));
		hook.getChildren().add(perspectiveView);
		viewDirection = perspective.FRONT;
	}
	
	public void changeImageOnLeft () {
		imageView.setImage(imageLeft);
	}
	
	public void changeImageOnBack () {
		imageView.setImage(imageBack);
	}
	
	public void changeImageOnRight () {
		imageView.setImage(imageRight);
	}
	
	public void updateView(double dx, double dy) {
		if (dy>0) {
			if (viewDirection == perspective.FRONT)
				return;
			else {
				perspectiveView.setImage(imageFront);
				viewDirection = perspective.FRONT;
			}	
		}
		if (dy<0) {
			if (viewDirection == perspective.BACK)
				return;
			else {
				perspectiveView.setImage(imageBack);
				viewDirection = perspective.BACK;
			}
		}
		if (dx>0) {
			if (viewDirection == perspective.RIGHT)
				return;
			else {
				perspectiveView.setImage(imageRight);
				viewDirection = perspective.RIGHT;
			}	
		}
		if (dx<0) {
			if (viewDirection == perspective.LEFT)
				return;
			else {
				perspectiveView.setImage(imageLeft);
				viewDirection = perspective.LEFT;
			}
		}
	}
}


class MainMenuView{
	private Group root;
	private View background, options, highlight;

	public MainMenuView (Group group) {
		root = group;
		GraphicPaths paths = new GraphicPaths();
		background = new View (paths.getPath("mainMenuBackground"), root, 960, 1080);
		options = new View (paths.getPath("mainMenuOptions"), root, 960, 1080);
		highlight = new View (paths.getPath("mainMenuHighlight"), root, 380, 565);
		background.setVisible(true);
		options.setVisible(true);
		highlight.setVisible(true);
	}
	
	public void updateHighlightPosition(int currentOption) {
        double currentX = highlight.getXposition();
        double currentY = highlight.getYposition();
        
		switch (currentOption) {
    		case 0:  highlight.relocate(380 - currentX, 565 - currentY); break;
    		case 1:  highlight.relocate(350 - currentX, 780 - currentY); break;
    		case 2:  highlight.relocate(350 - currentX, 1000 - currentY); break;
		}
	}
}

