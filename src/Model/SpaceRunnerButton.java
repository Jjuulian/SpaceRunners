/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * Clase SpaceRunnerButton que extiende la clase Button de JavaFX.
 * Representa un botón personalizado con estilo y efectos visuales interactivos.
 * 
 * @author Julian
 */
public class SpaceRunnerButton extends Button {

    // Ruta del archivo de fuente personalizado para el botón
    private final String FONT_PATH= "src/model/resources/kenvector_future.ttf";
    // Estilo cuando el botón está presionado (fondo transparente y botón rojo presionado)
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('file:src/Model/resources/red_button_pressed.png');";
    // Estilo cuando el botón está libre (fondo transparente y botón rojo normal)
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('file:src/Model/resources/red_button.png');";
    
    /**
     * Constructor de SpaceRunnerButton.
     * Establece el texto, la fuente, el tamaño y el estilo inicial del botón.
     * También configura los listeners de eventos del botón.
     * 
     * @param text El texto que se mostrará en el botón.
     */
    public SpaceRunnerButton(String text){
        // Establecemos el texto que mostrará el botón
        setText(text);
        // Configuramos la fuente del texto en el botón
        setButtonFont();
        // Establecemos el tamaño preferido del botón
        setPrefWidth(190);
        setPrefHeight(49);
        // Aplicamos el estilo libre (el estado normal) al botón
        setStyle(BUTTON_FREE_STYLE);
        // Inicializamos los listeners de eventos (para la interacción con el ratón)
        initializeButtonListeners();
    }
    
    /**
     * Configura la fuente del texto en el botón.
     * Intenta cargar una fuente personalizada, y si no se encuentra, usa una fuente por defecto.
     */
    private void setButtonFont(){
        try{
            // Intentamos cargar la fuente desde el archivo especificado
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        }catch(FileNotFoundException e){
            // Si no se encuentra la fuente, usamos una fuente predeterminada (Verdana)
            setFont(Font.font("Verdana",23));
        }
    }
    
    /**
     * Cambia el estilo del botón cuando está presionado.
     * Establece el fondo del botón a "rojo presionado" y ajusta su altura y posición.
     */
    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_STYLE);  // Establece el estilo del botón presionado
        setPrefHeight(45);  // Reduce la altura del botón cuando se presiona
        setLayoutY(getLayoutY()+4);  // Baja el botón ligeramente al presionar
    }
    
    /**
     * Cambia el estilo del botón cuando se libera (se deja de presionar).
     * Vuelve al estilo inicial con fondo "rojo normal" y ajusta su altura y posición.
     */
    private void setButtonReleasedStyle(){
        setStyle(BUTTON_FREE_STYLE);  // Establece el estilo de botón normal
        setPrefHeight(49);  // Restaura la altura del botón
        setLayoutY(getLayoutY()-4);  // Sube el botón ligeramente al liberar
    }
    
    /**
     * Inicializa los listeners de eventos del botón.
     * Define la lógica para las acciones que ocurren al hacer clic y pasar el ratón por encima.
     */
    private void initializeButtonListeners(){
        
        // Listener para cuando se presiona el botón con el ratón
        setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                // Solo responde a la presión del botón principal del ratón (botón izquierdo)
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();  // Cambia el estilo a presionado
                }
            } 
        });
        
        // Listener para cuando se libera el botón del ratón
        setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                // Solo responde a la liberación del botón principal del ratón (botón izquierdo)
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonReleasedStyle();  // Cambia el estilo a libre
                }
            } 
        });
        
        // Listener para cuando el ratón entra en el área del botón
        setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());  // Aplica un efecto de sombra al pasar el ratón
            }
        });
        
        // Listener para cuando el ratón sale del área del botón
        setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);  // Elimina el efecto de sombra cuando el ratón sale
            }
        });
    } 
}
