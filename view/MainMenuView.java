package view;

import javafx.scene.Group;

public 
class MainMenuView{
	private Group root;
	private View background, options, highlight;

	public MainMenuView (Group group) {
		root = group;
		GraphicPaths paths = new GraphicPaths();
		background = new View (paths.getPath("mainMenuBackground"), root, 960, 1080);
		options = new View (paths.getPath("mainMenuOptions"), root, 960, 1080);
		highlight = new View (paths.getPath("mainMenuHighlight"), root, 380, 565);
		background.setVisible(true);
		options.setVisible(true);
		highlight.setVisible(true);
	}
	
	public void updateHighlightPosition(int currentOption) {
        double currentX = highlight.getXposition();
        double currentY = highlight.getYposition();
        
		switch (currentOption) {
    		case 0:  highlight.relocate(380 - currentX, 565 - currentY); break;
    		case 1:  highlight.relocate(350 - currentX, 780 - currentY); break;
    		case 2:  highlight.relocate(350 - currentX, 1000 - currentY); break;
		}
	}
}
