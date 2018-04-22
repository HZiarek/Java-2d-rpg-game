package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class View {
	protected Image image;
	protected ImageView imageView;
	protected Group hook;
	private double Xposition, Yposition, Xsize, Ysize;

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

class CharacterView extends View{
	private Image imageLeft;
	private Image imageBack;
	private enum direction{
		RIGHT, LEFT, BACK
	} 
	direction viewDirection;
	
	public CharacterView(String graphicPathRight, String graphicPathLeft, String graphicPathBack,
			Group hook, double xposition, double yposition, double xsize, double ysize) {
		
		super (graphicPathRight, hook, xposition, yposition, xsize, ysize);
		imageLeft = new Image(graphicPathLeft);
		imageBack = new Image(graphicPathBack);
		viewDirection = direction.RIGHT;
	}
	
	public CharacterView(String graphicPathRight, String graphicPathLeft, String graphicPathBack,
			Group hook, double xposition, double yposition) {
		super (graphicPathRight, hook, xposition, yposition);
		imageLeft = new Image(graphicPathLeft);
		imageBack = new Image(graphicPathBack);
		viewDirection = direction.RIGHT;
	}
	
	public void changeImageOnLeft () {
		imageView.setImage(imageLeft);
	}
	
	public void changeImageOnBack () {
		imageView.setImage(imageBack);
	}
	
	public void changeImageOnRight () {
		imageView.setImage(image);
	}
	
	public void updateView(int dx, boolean ladder) {
		if (ladder) {
			if (viewDirection == direction.BACK)
				return;
			else {
				imageView.setImage(imageBack);
				viewDirection = direction.BACK;
			}		
		}
		if (dx>0) {
			if (viewDirection == direction.RIGHT)
				return;
			else {
				imageView.setImage(image);
				viewDirection = direction.RIGHT;
			}	
		}
		if (dx<0) {
			if (viewDirection == direction.LEFT)
				return;
			else {
				imageView.setImage(imageLeft);
				viewDirection = direction.LEFT;
			}
		}
	}
}

class LadderView extends View{
	
	public LadderView(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		super (graphicPath, hook, xposition, yposition, xsize, ysize);
	}
	
	public LadderView(String graphicPath, Group hook, double xposition, double yposition) {
		super (graphicPath, hook, xposition, yposition);
	}
}

class BearView extends View{
	
	public BearView(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		super (graphicPath, hook, xposition, yposition, xsize, ysize);
	}
	
	public BearView(String graphicPath, Group hook, double xposition, double yposition) {
		super (graphicPath, hook, xposition, yposition);
	}
}

