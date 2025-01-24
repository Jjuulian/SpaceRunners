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
 *
 * @author Julian
 */
public class ShipPicker extends VBox {
    
    private ImageView circleImage;
    private ImageView shipImage;
    
    private String circleNotChoosen = "file:src/View/resources/shipchooser/grey_circle.png";
    private String circleChoosen = "file:src/View/resources/shipchooser/red_choosen.png";
    
    private Ship ship;
    
    private boolean isCircleChoosen;
    
    public ShipPicker(Ship ship){
        circleImage=new ImageView(circleNotChoosen);
        shipImage = new ImageView(ship.getUrlShip());
        
        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(circleImage,shipImage);
        
    }
    
    public Ship getShip(){
        return ship;
    }
    
    public boolean getIsCircleChoosen(){
        return isCircleChoosen;
    }
    
    public  void setCircleIsChoosen(boolean isCircleChoosen){
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
        
    }
    
    
    
    
    
}
