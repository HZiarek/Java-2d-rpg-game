package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class View {
	private String GRAPHIC_IMAGE_LOC;
	private Image image;
	protected ImageView imageView;
	private Group hook;
	private double Xposition, Yposition, Xsize, Ysize;

	public View(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		GRAPHIC_IMAGE_LOC = graphicPath;
		this.hook = hook;
		Xposition = xposition; //mid
		Yposition = yposition; //bottom
		Xsize = xsize;
		Ysize = ysize;
		image = new Image(GRAPHIC_IMAGE_LOC);
		imageView = new ImageView(image);
		imageView.relocate(xposition - (imageView.getBoundsInLocal().getWidth()/2),
				yposition - (imageView.getBoundsInLocal().getHeight()));
	}
	
	public View(String graphicPath, Group hook, double xposition, double yposition) {
		GRAPHIC_IMAGE_LOC = graphicPath;
		this.hook = hook;
		Xposition = xposition;
		Yposition = yposition;
		image = new Image(GRAPHIC_IMAGE_LOC);
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
		return hook.getLayoutX() + Xposition - Xsize/2;
	}
	
	public double getYposition() {
		return hook.getLayoutY() + Yposition - Ysize;
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
	
	public CharacterView(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		super (graphicPath, hook, xposition, yposition, xsize, ysize);
	}
	
	public CharacterView(String graphicPath, Group hook, double xposition, double yposition) {
		super (graphicPath, hook, xposition, yposition);
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
