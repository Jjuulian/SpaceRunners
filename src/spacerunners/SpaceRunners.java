/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package spacerunners;

import View.ViewManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Julian
 */
public class SpaceRunners extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try{
          ViewManager manager = new ViewManager();
          primaryStage = manager.getMainStage(); 
          primaryStage.show(); 
       }catch(Exception e){
           e.printStackTrace();
       }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
