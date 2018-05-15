package view;

import javafx.scene.Group;
import javafx.scene.image.Image;

public class ChestView extends View{
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
