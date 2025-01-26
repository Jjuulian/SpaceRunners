/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Clase ShipPicker que representa un selector de nave en la interfaz gráfica.
 * Se encarga de mostrar una imagen de un círculo que indica si la nave está seleccionada o no,
 * junto con la imagen de la nave que se puede elegir.
 * 
 * @author Julian
 */
public class ShipPicker extends VBox {
    
    // Imagen que se mostrará como círculo (para indicar si está seleccionada o no)
    private ImageView circleImage;
    // Imagen de la nave que representa el tipo de nave seleccionable
    private ImageView shipImage;
    
    // Rutas de las imágenes para los círculos (seleccionada o no seleccionada)
    private String circleNotChoosen = "file:src/View/resources/shipchooser/grey_circle.png";
    private String circleChoosen = "file:src/View/resources/shipchooser/red_choosen.png";
    
    // El objeto Ship que esta clase encapsula
    private Ship ship;
    
    // Bandera que indica si el círculo está seleccionado o no
    private boolean isCircleChoosen;
    
    /**
     * Constructor de ShipPicker. Inicializa los elementos visuales del selector de nave.
     * 
     * @param ship El objeto Ship que esta instancia de ShipPicker va a representar
     */
    public ShipPicker(Ship ship){
        // Inicializamos la imagen del círculo (inicialmente no está seleccionada)
        circleImage = new ImageView(circleNotChoosen);
        // Inicializamos la imagen de la nave basada en el objeto Ship pasado como parámetro
        shipImage = new ImageView(ship.getUrlShip());
        
        // Asignamos el objeto Ship a esta instancia
        this.ship = ship;
        // Inicializamos el estado del círculo como no seleccionado
        isCircleChoosen = false;
        
        // Establecemos la alineación y el espaciado del VBox que contendrá las imágenes
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        
        // Añadimos las imágenes al contenedor VBox
        this.getChildren().addAll(circleImage, shipImage);
    }
    
    /**
     * Método que devuelve el objeto Ship representado por esta instancia de ShipPicker.
     * 
     * @return El objeto Ship representado por este ShipPicker.
     */
    public Ship getShip(){
        return ship;
    }
    
    /**
     * Método que devuelve el estado de selección del círculo (si está seleccionado o no).
     * 
     * @return true si el círculo está seleccionado, false en caso contrario.
     */
    public boolean getIsCircleChoosen(){
        return isCircleChoosen;
    }
    
    /**
     * Método que cambia el estado de selección del círculo.
     * Si el círculo es seleccionado, se cambia la imagen a la imagen de "seleccionado",
     * de lo contrario se establece la imagen del círculo como no seleccionado.
     * 
     * @param isCircleChoosen El estado a establecer para el círculo (true = seleccionado, false = no seleccionado)
     */
    public void setCircleIsChoosen(boolean isCircleChoosen){
        // Actualizamos el estado interno de si el círculo está seleccionado
        this.isCircleChoosen = isCircleChoosen;
        
        // Se decide cuál imagen se debe establecer, según si el círculo está seleccionado o no
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        
        // Establecemos la imagen del círculo
        circleImage.setImage(new Image(imageToSet));
    }
}