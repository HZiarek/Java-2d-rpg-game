package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SkillView extends View{
	private Image highlightImage;
	private ImageView highlightView;
	boolean isHighlight;
	
	
	public SkillView(String graphicPathSkill, String graphicPathHighlight, Group hook, double xposition, double yposition, double xsize, double ysize) {
		super (graphicPathSkill, hook, xposition, yposition, xsize, ysize);
		highlightImage = new Image(graphicPathHighlight);
		highlightView = new ImageView(highlightImage);
		highlightView.relocate(xposition - (highlightView.getBoundsInLocal().getWidth()/2),
				yposition - (highlightView.getBoundsInLocal().getHeight()) + 20); //na sztywno zaszyty highlight
		isHighlight = false;
	}
	
	public SkillView(String graphicPathSkill, String graphicPathHighlight, Group hook, double xposition, double yposition) {
		super (graphicPathSkill, hook, xposition, yposition);
		highlightImage = new Image(graphicPathHighlight);
		highlightView = new ImageView(highlightImage);
		highlightView.relocate(xposition - (highlightView.getBoundsInLocal().getWidth()/2),
				yposition - (highlightView.getBoundsInLocal().getHeight()) + 20);//na sztywno zaszyty highlight
		isHighlight = false;
	}
	
	public void setHighlight(boolean onOrOff) {
			if (onOrOff) {
				if (isHighlight)
					return;
				else {
					hook.getChildren().add(highlightView);
					isHighlight = true;
				}
			}
			else {
				if (!isHighlight)
					return;
				else {
					hook.getChildren().remove(highlightView);
					isHighlight = false;
				}
			}
	}
}
