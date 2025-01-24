/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Julian
 */
public enum Ship {
    BLUE("file:src/View/resources/shipchooser/blue_ship.png"),
    GREEN("file:src/View/resources/shipchooser/green_ship.png"),
    ORANGE("file:src/View/resources/shipchooser/orange_ship.png"),
    RED("file:src/View/resources/shipchooser/red_ship.png");
    
    private String urlShip;
    
    
    private Ship(String urlShip){
        this.urlShip = urlShip; 
    }

    public String getUrlShip() {
        return urlShip;
    }
    
}
