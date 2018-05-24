package controller;

import javafx.scene.Group;
import view.Sprite;

/**
 * It provides management and control of the health points stripe.
 * Public methods:
 *  - changeHp(newHp) - allows to change size of red stripe representing number of character's health points;
 *  - setVisible (boolean) - allows to show or hide stripe.
 */

public class HpController {
	private Sprite hp[], stripeView;
	private Group hpGroup;
	private int currentHp;
	private int maxHp;
	
	public HpController (Group hook, String stripePath, String hpPath, int numberOfPieces,
			double xposition, double yposition, double interval) {
		hpGroup = new Group();
		hook.getChildren().add(hpGroup);
		currentHp = numberOfPieces - 1;
		maxHp = numberOfPieces;
		
		stripeView = new Sprite (stripePath, hpGroup, xposition, yposition);
		double startPoint = (numberOfPieces/2)*interval - interval/2;
		
		hp = new Sprite[50];
		int tmpCounter = 0;

		for (int i = 0; i < maxHp; i++) {
			hp[i] = new Sprite(hpPath, hpGroup, xposition - startPoint + tmpCounter, yposition - 5);
			tmpCounter += interval;
		}
		
	}

	public void changeHp(int newHp) {
		if (newHp > maxHp)
			return;
		if (newHp != 0)
			newHp -= 1;
		if (newHp == currentHp)
			return;
		if (newHp > currentHp)
			for (int i = currentHp; i <= newHp; i++)
				hp[i].setVisible(true);
		else
			for (int i = currentHp; i >= newHp; i--)
				hp[i].setVisible(false);
		currentHp = newHp;
	}
	
	public void setVisible(boolean onOrOff){
		stripeView.setVisible(onOrOff);
		for (int i = 0; i < maxHp; i++) {
			hp[i].setVisible(onOrOff);
		}
	}
}

