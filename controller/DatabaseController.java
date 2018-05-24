package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import database.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import view.*;

class DatabaseController {
	private DBConnect databaseConnect;
	private Group statisticsGroup;
	private Sprite backgroundView;
	private List<GameStatistics> statisticsRows;
	private TextSprite idText[];
	private TextSprite dateOfPlayingText[];
	private TextSprite timeOfPlayingText[];
	private TextSprite moneyText[];
	private TextSprite finalResultText[];
	private int numberOfRows;

	public DatabaseController(Group hook) {
		statisticsGroup = new Group();
		hook.getChildren().add(statisticsGroup);
		GraphicPaths paths = new GraphicPaths();
		backgroundView = new Sprite(paths.getPath("statisticsBackground"), statisticsGroup, 960, 1080);
		backgroundView.setVisible(true);
		databaseConnect = new DBConnect();
		statisticsRows = new ArrayList<GameStatistics>();
		showStatistics();
	}
	
	public void hideWindow() {
		backgroundView.setVisible(false);
		for (int i = 0; i < 10 && i < numberOfRows; i++)
			idText[i].setVisible(false);
		for (int i = 0; i < 10 && i < numberOfRows; i++)
			dateOfPlayingText[i].setVisible(false);
		for (int i = 0; i < 10 && i < numberOfRows; i++)
			timeOfPlayingText[i].setVisible(false);
		for (int i = 0; i < 10 && i < numberOfRows; i++)
			moneyText[i].setVisible(false);
		for (int i = 0; i < 10 && i < numberOfRows; i++)
			finalResultText[i].setVisible(false);
	}
	
	public void showWindow() {
		backgroundView.setVisible(true);
	}
	
	private void showStatistics() {
		idText = new TextSprite[10];
		dateOfPlayingText = new TextSprite[10];
		timeOfPlayingText = new TextSprite[10]; 
		moneyText = new TextSprite[10];
		finalResultText = new TextSprite[10];
		statisticsRows = databaseConnect.getAllRows();
		
		int verticalDistance = 290;
		numberOfRows = statisticsRows.size();
		for (int i = 0; i < 10 && i < numberOfRows; i++) {
			idText[i] = new TextSprite(""+(i + 1), statisticsGroup,
					20, verticalDistance, 50, Color.WHITE);
			idText[i].setVisible(true);
			
			dateOfPlayingText[i] = new TextSprite(""+statisticsRows.get(numberOfRows - 1 - i).getDateOfPlaying(),
					statisticsGroup, 110, verticalDistance, 50, Color.WHITE);
			dateOfPlayingText[i].setVisible(true);
			
			DecimalFormat df=new java.text.DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			timeOfPlayingText[i] = new TextSprite(""+statisticsRows.get(numberOfRows - 1 - i).getTimeOfPlaying()+" s",
					statisticsGroup, 600, verticalDistance, 50, Color.WHITE);
			timeOfPlayingText[i].setVisible(true);
			
			moneyText[i] = new TextSprite(""+statisticsRows.get(numberOfRows - 1 - i).getMoney(), statisticsGroup,
					1150, verticalDistance, 50, Color.WHITE);
			moneyText[i].setVisible(true);
			
			finalResultText[i] = new TextSprite(""+statisticsRows.get(numberOfRows - 1 - i).getFinalResult(), statisticsGroup,
					1530, verticalDistance, 50, Color.WHITE);
			finalResultText[i].setVisible(true);
			
			
			verticalDistance +=78;
			
		}
	}

}
