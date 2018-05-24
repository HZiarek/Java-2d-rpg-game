package view;

import javafx.scene.Group;

public 
class MainMenuView{
	private Group root;
	private Sprite background, options, highlight;

	public MainMenuView (Group group) {
		root = group;
		GraphicPaths paths = new GraphicPaths();
		background = new Sprite (paths.getPath("mainMenuBackground"), root, 960, 1080);
		options = new Sprite (paths.getPath("mainMenuOptions"), root, 960, 1080);
		highlight = new Sprite (paths.getPath("mainMenuHighlight"), root, 380, 565);
		background.setVisible(true);
		options.setVisible(true);
		highlight.setVisible(true);
	}
	
	public void updateHighlightPosition(int currentOption) {
        double currentX = highlight.getXposition();
        double currentY = highlight.getYposition();
        
		switch (currentOption) {
    		case 0:  highlight.relocate(385 - currentX, 565 - currentY); break;
    		case 1:  highlight.relocate(385 - currentX, 780 - currentY); break;
    		case 2:  highlight.relocate(385 - currentX, 1000 - currentY); break;
		}
	}
}
