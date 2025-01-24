/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.InfoLabel;
import Model.SpaceRunnerButton;
import Model.SpaceRunnerSubscene;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 *
 * @author Julian
 */
public class ViewManager {
    
    private final static int HEIGHT = 768;
    private final static int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage; 
    
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    
    private SpaceRunnerSubscene creditsListSubScene;
    private SpaceRunnerSubscene helpSubScene;
    private SpaceRunnerSubscene scoreSubScene;
    private SpaceRunnerSubscene shipChooserSubScene;
    
    private SpaceRunnerSubscene sceneToHide;
    
    List <SpaceRunnerButton> menuButtons = new ArrayList<>();
    
    
    
    public ViewManager(){
        mainPane =  new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScene();
        createButtons();
        createBackground();
        createLogo();
 
    }
    
    private void showSubScene(SpaceRunnerSubscene spaceRunnerSubscene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        spaceRunnerSubscene.moveSubScene();
        sceneToHide =   spaceRunnerSubscene;
    }
    
    public void createSubScene(){
        creditsListSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(creditsListSubScene);
        
        helpSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(helpSubScene);
        
        scoreSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(scoreSubScene);
        
        createShipChooserSubScene();
    }
    
    private void createShipChooserSubScene() {
        shipChooserSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(shipChooserSubScene);
        
        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(20);
        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);
    }
    
    public Stage getMainStage(){
        return mainStage;
    }
    
    private void addMenuButton (SpaceRunnerButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button); 
    }
    
    private void createButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }
    
    private void createStartButton (){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);
        
        startButton.setOnAction(event -> showSubScene(shipChooserSubScene));
    
    }
    
    private void createScoresButton (){
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);
        
        scoresButton.setOnAction(event -> showSubScene(scoreSubScene));
    }
    
    private void createHelpButton (){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
        
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }
    
    private void createCreditsButton (){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
        
        creditsButton.setOnAction(event -> showSubScene(creditsListSubScene));
    }
    
    private void createExitButton (){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);
        
        exitButton.setOnAction((event) -> {
            mainStage.close();
        });
    }
    
    private void createBackground(){
        Image backgroundImage = new Image("file:src/View/resources/lightblue.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        
        mainPane.setBackground(new Background(background));
        
    }
    
    private void createLogo(){
        ImageView logo = new ImageView("file:src/View/resources/space_runner.png");
        logo.setLayoutX(400);
        logo.setLayoutY(60);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });
        
        logo.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);  
    }

    
}


