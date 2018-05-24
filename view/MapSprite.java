package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public 
class MapSprite {
    private Pane backgroundLayer;
	private Image backgroundImage;
	private ImageView backgroundView;
	
    public MapSprite(String graphicPath, Group hook) {
    	GraphicPaths paths = new GraphicPaths();
    	backgroundImage = new Image(paths.getPath("map"));
    	backgroundView = new ImageView (backgroundImage);
    	backgroundLayer = new Pane();
    	backgroundLayer.getChildren().add(backgroundView);
    	hook.getChildren().add(backgroundLayer);
    }
}
