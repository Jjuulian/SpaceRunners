/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

/**
 *
 * @author Julian
 */
public class InfoLabel extends Label {
    
    private final static String FONT_PATH= "file:src/model/resources/kenvector_future.ttf";
    
    private final static String BACKGROUND_IMAGE = "file:src/View/resources/red_small_panel.png";
    
    
    
    
    public InfoLabel(String text){
        
        setPrefWidth(380);
        setPrefHeight(49);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        
        BackgroundImage backGroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,380, 49,false,true),
                                            BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        
        setBackground(new Background(backGroundImage));
    }
    
    private void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(new File (FONT_PATH)),23));
        } catch (FileNotFoundException ex) {
            setFont(Font.font("Verdana",23));
        }
    }
    
}
