package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FourPerspectiveSprite extends Sprite{
	private Image imageFront;
	private Image imageLeft;
	private Image imageBack;
	private Image imageRight;
	private ImageView perspectiveView;
	private enum perspective{
		RIGHT, LEFT, BACK, FRONT
	} 
	perspective viewDirection;
	
	public FourPerspectiveSprite(String graphicPathFloor, String graphicPathFront,
			String graphicPathRight, String graphicPathLeft, String graphicPathBack,
			Group hook, double xposition, double yposition) {
		
		super (graphicPathFloor, hook, xposition, yposition);
		imageLeft = new Image(graphicPathLeft);
		imageFront = new Image(graphicPathFront);
		imageBack = new Image(graphicPathBack);
		imageRight = new Image(graphicPathRight);
		perspectiveView = new ImageView(imageLeft);
		perspectiveView.relocate(xposition - (perspectiveView.getBoundsInLocal().getWidth()/2),
				yposition - (perspectiveView.getBoundsInLocal().getHeight()));
		viewDirection = perspective.LEFT;
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
	
	public void setVisible(boolean onOrOff) {
		/*
		if (onOrOff) {
			hook.getChildren().add(perspectiveView);
		}
		else {
			hook.getChildren().remove(perspectiveView);
		}*/
		if ((onOrOff && isVisible) || (!onOrOff && !isVisible))
			return;
		
		if (onOrOff) {
			hook.getChildren().add(perspectiveView);
			isVisible = true;
		}
		else {
			hook.getChildren().remove(perspectiveView);
			isVisible = false;
		}
	}
}
