package view;

import javafx.scene.Group;
import javafx.scene.image.Image;

public class AnimationSprite extends Sprite{
	private Image frames[];
	private int numberOfFrames;

	public AnimationSprite(String graphicPath, Group hook, double xposition, double yposition, int numberOfFrames) {
		super(graphicPath+"1.png", hook, xposition, yposition);
		this.numberOfFrames = numberOfFrames;
		frames = new Image[numberOfFrames];
		for (int i = 0; i < numberOfFrames; i++) {
			frames[i] = new Image(graphicPath+(i+1)+".png");
		}
	}
	
	public void animate(int currentFrame) {
		if (currentFrame >= numberOfFrames) {
			setVisible(false);
			return;
		}
		imageView.setImage(frames[currentFrame]);
	}

}
