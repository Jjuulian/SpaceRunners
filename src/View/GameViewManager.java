/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Ship;
import Model.SmallInfoLabel;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.W;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author gassa
 */
public class GameViewManager {
    // Elementos principales de la vista del juego
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    
    // Tamaños de la ventana de juego
    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;
    
    // Referencia a la ventana del menú principal
    private Stage menuStage;
    
    // Referencia a la nave y sus propiedades
    private ImageView ship;
    
    // Variables para el movimiento del jugador
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private int angle;
    private AnimationTimer gameTimer; // Bucle de animación para el juego
    
    // Elementos adicionales (como meteoritos y estrellas)
    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "file:src/View/resources/lightblue.png"; // Imagen de fondo
    private final static String METEOR_BROWN_IMAGE = "file:src/View/resources/meteor_brown.png"; // Imagen meteorito marrón
    private final static String METEOR_GREY_IMAGE = "file:src/View/resources/meteor_grey.png"; // Imagen meteorito gris
    
    // Array de meteoritos
    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    private int meteorsCount = 3; // Cantidad de meteoritos
    private double meteorSpeed = 2.0;
    Random randomPositionGenerator; // Generador de posiciones aleatorias
    
    // Elementos del jugador
    private ImageView star; // Estrella que el jugador debe recoger
    private SmallInfoLabel pointsLabel; // Etiqueta de puntos
    private ImageView[] playerLifes; // Iconos de vida del jugador
    private int playerLife; // Número de vidas del jugador
    private int points; // Puntos acumulados
    private final static String GOLD_STAR_IMAGE = "file:src/View/resources/star_gold.png"; // Imagen de la estrella
    
    // Radios para los elementos
    private final static int STAR_RADIUS = 15;
    private final static int SHIP_RADIUS = 27;
    private final static int METEOR_RADIUS = 20;

    // Constructor
    public GameViewManager(){
        initializeStage(); // Inicializa la ventana del juego
        createKeyListeners(); // Configura los controles del teclado
        randomPositionGenerator = new Random(); // Inicializa el generador de números aleatorios
    }

    // Método que inicializa la ventana del juego
    private void initializeStage() {
        gamePane = new AnchorPane(); // Pane principal para los elementos
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT); // Configuración de la escena
        gameStage = new Stage(); // Crear la ventana
        gameStage.setScene(gameScene); // Asignar la escena al escenario
    }
    
    // Método que crea un nuevo juego
    public void createNewGame(Stage menuStage, Ship choosenShip){
        this.menuStage = menuStage; // Almacena la ventana del menú
        this.menuStage.hide(); // Oculta el menú
        createBackground(); //Crea el fondo
        createShip(choosenShip); // Crea la nave
        createGameElements(choosenShip); // Crea los elementos del juego (meteoros, estrellas, etc.)
        createGameLoop(); // Inicia el bucle de animación
        gameStage.show(); // Muestra la ventana del juego
    }
    
    // Método que crea los elementos adicionales en el juego
    private void createGameElements(Ship choosenShip){
        playerLife = 2; // Establece el número de vidas iniciales
        star = new ImageView(GOLD_STAR_IMAGE); // Crea la estrella
        setNewElementPosition(star); // Posiciona la estrella aleatoriamente
        gamePane.getChildren().add(star); // Añade la estrella a la escena
        pointsLabel = new SmallInfoLabel("POINTS : 00"); // Crea la etiqueta de puntos
        pointsLabel.setLayoutX(460); // Posición de la etiqueta de puntos
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel); // Añade la etiqueta de puntos a la escena
        playerLifes = new ImageView[3]; // Crea los íconos de vida
        
        // Añade los íconos de vida a la escena
        for (int i = 0; i < playerLifes.length; i++) {
            playerLifes[i] = new ImageView(choosenShip.getUrlLife()); // Crea cada ícono de vida
            playerLifes[i].setLayoutX(455+(i*50)); // Posiciona cada ícono de vida
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]); // Añade cada ícono de vida a la escena
        }
        
        // Crea meteoritos marrones
        brownMeteors = new ImageView[meteorsCount];
        for (int i = 0; i< brownMeteors.length; i++){
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }
        
        // Crea meteoritos grises
        greyMeteors = new ImageView[meteorsCount];
        for (int i = 0; i< greyMeteors.length; i++){
            greyMeteors[i] = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);
        }
    }
    
    // Método para mover los elementos del juego
    private void moveGameElements(){
        star.setLayoutY(star.getLayoutY() + 2); // Mueve la estrella hacia abajo
        
        // Mueve los meteoritos marrones
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + meteorSpeed);
            brownMeteors[i].setRotate(brownMeteors[i].getRotate() + 2); // Rota el meteorito
        }
        
        // Mueve los meteoritos grises
        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + meteorSpeed);
            greyMeteors[i].setRotate(greyMeteors[i].getRotate() + 2); // Rota el meteorito
        }
    }
    
    // Método que incrementa la velocidad de los meteoritos
    private void increaseMeteorSpeed(){
        meteorSpeed+=0.00001;// Incrementa la velocidad de los meteoritos en cada ciclo
    }
    
    // Método que verifica si un elemento ha llegado al final de la pantalla y lo reposiciona
    private void checkAndRelocate(){
        if(star.getLayoutY() > 900){ // Si la estrella ha salido de la pantalla
            setNewElementPosition(star); // Reposiciona la estrella
        }
        
        // Verifica los meteoritos marrones
        for (int i = 0; i < brownMeteors.length; i++) {
            if (brownMeteors[i].getLayoutY() > 900) {
                setNewElementPosition(brownMeteors[i]); // Reposiciona el meteorito marrón
            }
        }
        
        // Verifica los meteoritos grises
        for (int i = 0; i < greyMeteors.length; i++) {
            if (greyMeteors[i].getLayoutY() > 900) {
                setNewElementPosition(greyMeteors[i]); // Reposiciona el meteorito gris
            }
        }
    }
    
    // Método que posiciona un elemento de manera aleatoria
    private void setNewElementPosition (ImageView image){
        image.setLayoutX(randomPositionGenerator.nextInt(550)); // Genera una posición X aleatoria
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600)); // Genera una posición Y aleatoria
    }
    
    // Método que crea la nave del jugador
    private void createShip(Ship chosenShip){
        ship = new ImageView(chosenShip.getUrlShip()); // Crea la nave con la imagen correspondiente
        ship.setLayoutX(GAME_WIDTH / 2); // Posición X de la nave
        ship.setLayoutY(GAME_HEIGHT - 90); // Posición Y de la nave
        gamePane.getChildren().add(ship); // Añade la nave a la escena
    }

    // Método que crea los eventos de teclado para controlar la nave
    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.D) {
                    isRightKeyPressed = true;
                } else if (event.getCode() == KeyCode.W) {
                    isUpKeyPressed = true;
                } else if (event.getCode() == KeyCode.S) {
                    isDownKeyPressed = true;
                }
            }
        });

        // Evento para cuando se sueltan las teclas
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.D) {
                    isRightKeyPressed = false;
                } else if (event.getCode() == KeyCode.W) {
                    isUpKeyPressed = false;
                } else if (event.getCode() == KeyCode.S) {
                    isDownKeyPressed = false;
                }
            }
        });
    }
    
    // Método que crea el bucle de animación del juego
    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground(); // Mueve el fondo
                moveGameElements(); // Mueve los elementos del juego
                checkAndRelocate(); // Verifica y reposiciona los elementos si es necesario
                checkCollision(); // Verifica si hay colisiones
                moveShip(); // Mueve la nave
                increaseMeteorSpeed(); // Aumenta la velocidad de los meteoritos
            }
        };
        gameTimer.start(); // Inicia el bucle de animación
    }
    
    // Método para mover la nave según las teclas presionadas
    private void moveShip() {
        // Movimiento hacia la izquierda y derecha (rotación)
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle); // Mantiene la rotación constante
            if (ship.getLayoutX() > -20) { // Límite para que no se salga de la pantalla
                ship.setLayoutX(ship.getLayoutX() - 2); // Movimiento hacia la izquierda
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle); // Mantiene la rotación constante
            if (ship.getLayoutX() < 550) { // Límite para que no se salga de la pantalla
                ship.setLayoutX(ship.getLayoutX() + 2); // Movimiento hacia la derecha
            }
        }

        // Restablece la rotación si no se presionan las teclas izquierda/derecha
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }
        
        // Movimiento hacia arriba
        if (isUpKeyPressed && !isDownKeyPressed) {
            if (ship.getLayoutY() > -20) {
                ship.setLayoutY(ship.getLayoutY() - 2); // Movimiento hacia arriba
            }
        }

        // Movimiento hacia abajo
        if (isDownKeyPressed && !isUpKeyPressed) {
            if (ship.getLayoutY() < 770) {
                ship.setLayoutY(ship.getLayoutY() + 2); // Movimiento hacia abajo
            }
        }
    }
    private void createBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        
        for (int i = 0; i < 12; i++){
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i% 3, i/3);
            GridPane.setConstraints(backgroundImage2, i% 3, i/3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        
        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }
    
    // Método que mueve el fondo del juego
    private void moveBackground(){
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
        
        // Reposiciona el fondo cuando se sale de la pantalla
        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }
    
    // Método que verifica las colisiones entre la nave, la estrella y los meteoritos
    private void checkCollision(){
        // Verifica colisión con la estrella
        if (SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX()+49, star.getLayoutX()+15, ship.getLayoutY()+37, star.getLayoutY()+15)) {
            setNewElementPosition(star);
            points++; // Incrementa los puntos
            String textToSet = "POINTS : ";
            if (points < 10){
                textToSet = textToSet + "0";
            }
            pointsLabel.setText(textToSet + points); // Actualiza la etiqueta de puntos
        }
        
        // Verifica colisión con los meteoritos marrones
        for (int i = 0; i < brownMeteors.length; i++) {
            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX()+49, brownMeteors[i].getLayoutX()+20, ship.getLayoutY()+37, brownMeteors[i].getLayoutY()+20)){
                removeLife(); // Resta una vida
                setNewElementPosition(brownMeteors[i]); // Reposiciona el meteorito
            }
        }
        
        // Verifica colisión con los meteoritos grises
        for (int i = 0; i < greyMeteors.length; i++) {
            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX()+49, greyMeteors[i].getLayoutX()+20, ship.getLayoutY()+37, greyMeteors[i].getLayoutY()+20)){
                removeLife(); // Resta una vida
                setNewElementPosition(greyMeteors[i]); // Reposiciona el meteorito
            }
        }
    }
    
    // Método que elimina una vida cuando se colisiona con un meteorito
    private void removeLife(){
        gamePane.getChildren().remove(playerLifes[playerLife]); // Elimina el ícono de vida
        playerLife--; // Resta una vida
        if (playerLife < 0) {
            gameStage.close(); // Cierra el juego
            gameTimer.stop(); // Detiene el bucle de animación
            menuStage.show(); // Muestra el menú
        }
    }
    
    // Método que calcula la distancia entre dos puntos (usado para colisiones)
    private double calculateDistance (double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); // Fórmula de distancia euclidiana
    }
}
