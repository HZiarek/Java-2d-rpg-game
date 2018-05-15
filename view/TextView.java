package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextView {
	 private Text text;
	 private Group hook;
	 private double Xposition;
	 private double Yposition;
	 private boolean isVisible;
	
	public TextView(String content,  Group hook, double xposition, double yposition, int size, Color color) {
		this.hook = hook;
		Xposition = xposition;
		Yposition = yposition;
		text = new Text(content);
		text.setFont(Font.font ("Verdana", FontWeight.BOLD, size));
		text.setFill(color);
		text.relocate(Xposition,// - (text.getBoundsInLocal().getWidth()/2),
				Yposition - (text.getBoundsInLocal().getHeight()));
		isVisible = false;
	}
	
	public void setVisible(boolean onOrOff) {
		if ((onOrOff && isVisible) || (!onOrOff && !isVisible))
			return;
		
		if (onOrOff) {
			hook.getChildren().add(text);
			isVisible = true;
		}
		else {
			hook.getChildren().remove(text);
			isVisible = false;
		}
	}
	
	public void relocate(double x, double y) {
		Xposition += x;
		Yposition += y;
		text.relocate(Xposition - (text.getBoundsInLocal().getWidth()/2),
				Yposition - (text.getBoundsInLocal().getHeight()));
	}
	
	public double getXposition() {
		return hook.getLayoutX() + Xposition;// - Xsize/2;
	}
	
	public void setText(String newText) {
		text.setText(newText);
	}
	
	public double getYposition() {
		return hook.getLayoutY() + Yposition;// - Ysize;
	}
	
	
	public void setColor(Color newColor) {
		text.setFill(newColor);
	}

}
