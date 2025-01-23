/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

/**
 *
 * @author Julian
 */
public class SpaceRunnerSubscene extends SubScene {
    
    private final static String FONT_PATH = "file:src/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "file:src/model/resources/red_panel.png";
    
    
    
    
    public SpaceRunnerSubscene() {
        super(new AnchorPane(),600,600);
        prefWidth(600);
        prefHeight(400);
        
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(backgroundImage));
        
        
        
    }
    
    
    
}
