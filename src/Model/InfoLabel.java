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

import javafx.geometry.Pos;
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

//Label personalizado para el Ship Chooser
public class InfoLabel extends Label {
    
    private final static String FONT_PATH= "file:src/model/resources/kenvector_future.ttf";
    
    private final static String BACKGROUND_IMAGE = "file:src/View/resources/red_small_panel.png";
    
    
    
    
    public InfoLabel(String text){
        // Configuración de tamaño
        setPrefSize(380, 49);
        setMinSize(380, 49);
        setMaxSize(380, 49);

        // Configuración del texto
        setText(text);
        setWrapText(true);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 10, 0, 10)); // Relleno opcional

        // Configuración de la fuente
        setLabelFont();

        // Configuración de la imagen de fondo
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(BACKGROUND_IMAGE),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false) // Ajuste automático
        );
        setBackground(new Background(backgroundImage));
    }
    
    private void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(new File (FONT_PATH)),23));
        } catch (FileNotFoundException ex) {
            setFont(Font.font("Verdana",23));
        }
    }
}
