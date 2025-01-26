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
    BLUE("file:src/View/resources/shipchooser/blue_ship.png","file:src/View/resources/shipchooser/blue_life.png"),
    GREEN("file:src/View/resources/shipchooser/green_ship.png", "file:src/View/resources/shipchooser/green_life.png"),
    ORANGE("file:src/View/resources/shipchooser/orange_ship.png", "file:src/View/resources/shipchooser/orange_life.png"),
    RED("file:src/View/resources/shipchooser/red_ship.png", "file:src/View/resources/shipchooser/red_life.png");
    
    private String urlShip;//Guardamos el png del barco
    private String urlLife;//Guardamos el png de las vidas
    
    
    private Ship(String urlShip, String urlLife){
        this.urlShip = urlShip; 
        this.urlLife = urlLife;
    }

    public String getUrlShip() {
        return urlShip;
    }
    
    public String getUrlLife() {
        return urlLife;
    }
    
}
