package com.mycompany.ejerciciocomandasdesayunografico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Pablo Martínez y Santiago Ucero. Cesur 2ªDAM, Diseño de Interfaces
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stageAux; // Copia auxiliar de Stage, para uso en métodos menores

    @Override
    public void start(Stage stage) throws IOException {
        stageAux = stage;
        scene = new Scene(loadFXML("primary"), 1200, 720);
        stage.setScene(scene);
        stage.setTitle("Comandas Desayuno");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public boolean fullscreen(){
        stageAux.setFullScreen(!stageAux.isFullScreen());
        return stageAux.isFullScreen();
    }

}