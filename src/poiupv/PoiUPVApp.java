/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * Proyecto Realizado por:
 * - Francisco Ramos Guardiola
 * - Álvaro Camino Tirapu
 * - Carmen Martínez Rodríguez
 * GRUPO: 2F - L1
 */
public class PoiUPVApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PoiUPVApp.class.getResource("PantallaInicial.fxml"));
        Parent root = (Parent) loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
