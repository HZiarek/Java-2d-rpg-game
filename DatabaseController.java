package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

import Database.*;
import javafx.scene.Group;

class DatabaseController {
	private DBConnect databaseConnect;
	private Group statisticsGroup;
	private View backgroundView;
	private List<GameStatistics> statisticsRows;
	private TextView idText[];
	private TextView dateOfPlayingText[];
	private TextView timeOfPlayingText[];
	private TextView moneyText[];
	private TextView finalResultText[];
	private int numberOfRows;

	public DatabaseController(Group hook) {
		statisticsGroup = new Group();
		hook.getChildren().add(statisticsGroup);
		GraphicPaths paths = new GraphicPaths();
		backgroundView = new View(paths.getPath("statisticsBackground"), statisticsGroup, 960, 1080);
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
		idText = new TextView[10];
		dateOfPlayingText = new TextView[10];
		timeOfPlayingText = new TextView[10]; 
		moneyText = new TextView[10];
		finalResultText = new TextView[10];
		statisticsRows = databaseConnect.getAllRows();
		
		int verticalDistance = 400;
		numberOfRows = statisticsRows.size();
		for (int i = 0; i < 10 && i < numberOfRows; i++) {
			idText[i] = new TextView(""+statisticsRows.get(i).getIdStat(), statisticsGroup,
					100, verticalDistance, 60, Color.WHITE);
			idText[i].setVisible(true);
			
			dateOfPlayingText[i] = new TextView(""+statisticsRows.get(i).getDateOfPlaying(), statisticsGroup,
					200, verticalDistance, 60, Color.WHITE);
			dateOfPlayingText[i].setVisible(true);
			
			DecimalFormat df=new java.text.DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			timeOfPlayingText[i] = new TextView(""+statisticsRows.get(i).getTimeOfPlaying()+" s",
					statisticsGroup, 700, verticalDistance, 60, Color.WHITE);
			timeOfPlayingText[i].setVisible(true);
			
			moneyText[i] = new TextView(""+statisticsRows.get(i).getMoney(), statisticsGroup,
					1200, verticalDistance, 60, Color.WHITE);
			moneyText[i].setVisible(true);
			
			finalResultText[i] = new TextView(""+statisticsRows.get(i).getFinalResult(), statisticsGroup,
					1500, verticalDistance, 60, Color.WHITE);
			finalResultText[i].setVisible(true);
			
			
			verticalDistance +=100;
			
		}
	}

}
