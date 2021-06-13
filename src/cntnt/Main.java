package cntnt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    /*

    if Error: JavaFX runtime components are missing, and are required to run this application

    add this to VM Options in Run/Debug Configurations

    --module-path "PATH_TO\javafx-sdk-15.0.1\lib" --add-modules javafx.fxml,javafx.controls

    example: --module-path "B:\Programmieren\javafx-sdk-15.0.1\lib" --add-modules javafx.fxml,javafx.controls
     */


    final static int rectWidth = 3, camSpeed = 300, windowSize = 800;

    static int mapSize = 300, numberOfAsteroids = 500, minAsteroidSize = 50, maxAsteroidSize = 100;

    // standard values  mapSize = 300, numberOfAsteroids = 250, minAsteroidSize = 20, maxAsteroidSize = 200

    Pane root;
    static Map map;
    Camera cam;

    Label tut;

    Parent createContend(){
        root = new Pane();
        map = new Map();
        cam = new Camera(root);

        tut = new Label("Move map: A,S,D,W \n New map: Enter");
        tut.setTextFill(Color.RED);
        tut.setFont(new Font(50));
        tut.setTranslateX((double)windowSize/2 - 200);
        tut.setTranslateY((double)windowSize/2 - 200);


        map.mapGrid = map.generateMap(mapSize, numberOfAsteroids, minAsteroidSize, maxAsteroidSize);

        map.addMapToGroup(map.mapGrid);

        root.getChildren().add(map.mapGroup);

        cam.initTimeline();
        cam.timeline.play();

        root.getChildren().add(tut);

        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        return root;
    }

    static void reset(){
        map.mapGroup.getChildren().clear();
        map.mapGrid = map.generateMap(mapSize, numberOfAsteroids, (int)(Math.random() * minAsteroidSize)+1, (int)(Math.random() * maxAsteroidSize)+1);
        map.addMapToGroup(map.mapGrid);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("RVS-Poject 2");
        primaryStage.setScene(new Scene(createContend()));
        primaryStage.getScene().setFill(Color.BLACK);

        primaryStage.setWidth(windowSize);
        primaryStage.setHeight(windowSize);


        primaryStage.show();

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {

            tut.setVisible(false);

            if(e.getCode() == KeyCode.ENTER){
               reset();
            }else if(e.getCode() == KeyCode.A){
                cam.dx = camSpeed;
            }else if(e.getCode() == KeyCode.D){
                cam.dx = -camSpeed;
            }else if(e.getCode() == KeyCode.W){
                cam.dy = camSpeed;
            }else if(e.getCode() == KeyCode.S){
                cam.dy = -camSpeed;
            }else if(e.getCode() == KeyCode.ESCAPE){
                System.exit(500);
            }

        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {

            if((e.getCode() == KeyCode.A || e.getCode() == KeyCode.D)){
                cam.dx = 0;
            }else if((e.getCode() == KeyCode.W || e.getCode() == KeyCode.S)){
                cam.dy = 0;
            }

        });


    }



}
