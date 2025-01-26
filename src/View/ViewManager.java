/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.InfoLabel;
import Model.Ship;
import Model.ShipPicker;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Julian
 */
public class ViewManager {
    
    // Constantes para el tamaño de la ventana principal
    private final static int HEIGHT = 768;
    private final static int WIDTH = 1024;
    
    // Atributos principales de la interfaz
    private AnchorPane mainPane; 
    private Scene mainScene; 
    private Stage mainStage; 
    
    // Constantes para la posición inicial de los botones del menú
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    
    // Subpantallas para mostrar diferentes secciones
    private SpaceRunnerSubscene creditsListSubScene;
    private SpaceRunnerSubscene helpSubScene;
    private SpaceRunnerSubscene scoreSubScene;
    private SpaceRunnerSubscene shipChooserSubScene;
    
    private SpaceRunnerSubscene sceneToHide;
    
    // Lista para almacenar los botones del menú
    List <SpaceRunnerButton> menuButtons = new ArrayList<>();
    
    // Lista para los seleccionadores de naves
    List<ShipPicker> shipList;
    
    private Ship choosenShip; // Nave seleccionada por el jugador
    
    
    public ViewManager(){
        // Inicializamos la interfaz gráfica
        mainPane =  new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        
        // Creamos las subpantallas y los botones del menú
        createSubScene();
        createButtons();
        createBackground();
        createLogo();
    }
    
    // Método para mostrar una subpantalla específica
    private void showSubScene(SpaceRunnerSubscene spaceRunnerSubscene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene(); // Ocultamos la subpantalla actual
        }
        spaceRunnerSubscene.moveSubScene(); // Mostramos la nueva subpantalla
        sceneToHide =   spaceRunnerSubscene; // Establecemos la nueva subpantalla como la activa
    }
    
    // Método para crear todas las subpantallas
    public void createSubScene(){
        creditsListSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(creditsListSubScene);
        
        helpSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(helpSubScene);
        
        scoreSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(scoreSubScene);
        
        createShipChooserSubScene(); // Subpantalla para elegir la nave
    }
    
    // Método para crear la subpantalla de selección de nave
    private void createShipChooserSubScene() {
        shipChooserSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(shipChooserSubScene);
        
        // Etiqueta para indicar que el jugador debe elegir una nave
        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(130);
        chooseShipLabel.setLayoutY(25);
        shipChooserSubScene.getPane().getChildren().addAll(chooseShipLabel, createShipsToChoose(), createButtonToStart());
    }
    
    // Método para crear la lista de naves que el jugador puede seleccionar
    private HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        
        // Lista para almacenar los seleccionadores de naves
        shipList = new ArrayList<>();
        
        // Creamos un seleccionador de nave para cada nave disponible
        for (Ship ship : Ship.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipList.add(shipToPick);
            box.getChildren().add(shipToPick);
            
            // Acción cuando el jugador hace clic en un seleccionador de nave
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    // Desmarcamos todas las naves seleccionadas
                    for(ShipPicker ship : shipList){
                        ship.setCircleIsChoosen(false);
                    }
                    // Marcamos la nave seleccionada
                    shipToPick.setCircleIsChoosen(true);
                    choosenShip = shipToPick.getShip(); // Guardamos la nave seleccionada
                }
            });
        }
        
        // Configuramos la posición de la lista de naves
        box.setLayoutX(130);
        box.setLayoutY(130);
        return box;
    }
    
    // Método para crear el botón de inicio del juego
    private SpaceRunnerButton createButtonToStart(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        
        // Acción cuando el jugador presiona el botón de inicio
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                // Si el jugador ha seleccionado una nave, inicia el juego
                if (choosenShip != null){
                    GameViewManager gameManager  = new GameViewManager();
                    gameManager.createNewGame(mainStage, choosenShip);
                }
            }
        
        });
        
        return startButton;
    }
    
    // Método para obtener la escena principal (stage)
    public Stage getMainStage(){
        return mainStage;
    }
    
    // Método para agregar un botón al menú principal
    private void addMenuButton (SpaceRunnerButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button); 
    }
    
    // Método para crear todos los botones del menú principal
    private void createButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }
    
    // Método para crear el botón "Jugar"
    private void createStartButton (){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);
        
        // Acción cuando el jugador presiona "Jugar"
        startButton.setOnAction(event -> showSubScene(shipChooserSubScene));
    }
    
    // Método para crear el botón "Puntuaciones"
    private void createScoresButton (){
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);
        
        // Acción cuando el jugador presiona "Puntuaciones"
        scoresButton.setOnAction(event -> showSubScene(scoreSubScene));
    }
    
    // Método para crear el botón "Ayuda"
    private void createHelpButton (){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
        
        // Acción cuando el jugador presiona "Ayuda"
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }
    
    // Método para crear el botón "Créditos"
    private void createCreditsButton (){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
        
        // Acción cuando el jugador presiona "Créditos"
        creditsButton.setOnAction(event -> showSubScene(creditsListSubScene));
    }
    
    // Método para crear el botón "Salir"
    private void createExitButton (){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);
        
        // Acción cuando el jugador presiona "Salir"
        exitButton.setOnAction((event) -> {
            mainStage.close(); // Cierra la ventana del juego
        });
    }
    
    // Método para crear el fondo de la ventana principal
    private void createBackground(){
        Image backgroundImage = new Image("file:src/View/resources/lightblue.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        
        mainPane.setBackground(new Background(background)); // Asigna el fondo a la escena
    }
    
    // Método para crear el logo del juego
    private void createLogo(){
        ImageView logo = new ImageView("file:src/View/resources/space_runner.png");
        logo.setLayoutX(400);
        logo.setLayoutY(60);

        // Efecto al pasar el mouse sobre el logo
        logo.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow()); // Agrega sombra al logo
            }
        });
        
        // Elimina el efecto al salir el mouse
        logo.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null); // Elimina la sombra
            }
        });
        
        // Añade el logo a la escena principal
        mainPane.getChildren().add(logo);  
    }
}


