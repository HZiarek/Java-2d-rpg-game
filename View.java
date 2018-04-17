package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class View {
	private String GRAPHIC_IMAGE_LOC;
	private Image image;
	private ImageView imageView;
	private Group hook;
	double Xposition, Yposition, Xsize, Ysize;

	public View(String graphicPath, Group hook, double xposition, double yposition, double xsize, double ysize) {
		GRAPHIC_IMAGE_LOC = graphicPath;
		this.hook = hook;
		image = new Image(GRAPHIC_IMAGE_LOC);
		imageView = new ImageView(image);
		imageView.relocate(xposition - (imageView.getBoundsInLocal().getWidth()/2),
				yposition - (imageView.getBoundsInLocal().getHeight()));
	}
	
	public void setVisible() {
		hook.getChildren().add(imageView);
	}
	
	public Rectangle2D getBoundary(){
	       return new Rectangle2D(Xposition,Yposition,Xsize,Ysize);
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
		
}
