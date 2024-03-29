package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Provides displaying images on screen.
 * Uses JavaFX, especially classes Image and ImageView
 *
 * @param  graphicPath the location of the image on local disk
 * @param  hook the node, which is the parent of image
 * @param  xposition position the image on the screen, distance from left edge of window
 * @param  yposition position the image on the screen, distance from top edge of window
 * @param  xsize optional parameter, it it uses in interactions detecting
 * @param  ysize optional parameter, it it uses in interactions detecting
 */


public class Sprite {
	protected Image image;
	protected ImageView imageView;
	protected Group hook;
	protected double Xposition;
	protected double Yposition;
	private double Xsize;
	private double Ysize;
	protected boolean isVisible;

	public Sprite(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		this.hook = hook;
		Xposition = xposition; //mid
		Yposition = yposition; //bottom
		Xsize = xsize;
		Ysize = ysize;
		image = new Image(graphicPath);
		imageView = new ImageView(image);
		imageView.relocate(xposition - (imageView.getBoundsInLocal().getWidth()/2),
				yposition - (imageView.getBoundsInLocal().getHeight()));
		isVisible = false;
	}
	
	public Sprite(String graphicPath, Group hook, double xposition, double yposition) {
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
		if ((onOrOff && isVisible) || (!onOrOff && !isVisible))
			return;
		
		if (onOrOff) {
			hook.getChildren().add(imageView);
			isVisible = true;
		}
		else {
			hook.getChildren().remove(imageView);
			isVisible = false;
		}
	}
	
	public Rectangle2D getBoundary(){
	       //return new Rectangle2D(imageView.getBoundsInLocal().getMinX() - (imageView.getBoundsInLocal().getWidth()/2),
	    	//	   imageView.getBoundsInLocal().getMinY() - (imageView.getBoundsInLocal().getHeight()),Xsize,Ysize);
		return new Rectangle2D(hook.getLayoutX() + Xposition - Xsize/2, hook.getLayoutY() + Yposition - Ysize, Xsize, Ysize);
	}
		
	public boolean intersects (Sprite example){
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
	
	public boolean getIsVisible() {
		return isVisible;
	}
}

