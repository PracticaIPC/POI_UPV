/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PantallaInicialController implements Initializable {

    @FXML
    private Button registrofxID;
    @FXML
    private Button incioSesionfxID;
    @FXML
    private Button invitadofxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bRegistro(ActionEvent event) throws IOException {
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("/poiupv/Registro.fxml"));
        Parent root = loadder.load();
        
        //RegistroController controlador = loadder.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inicio Sesión");
        
        Stage myStage = (Stage) this.registrofxID.getScene().getWindow();
        myStage.close();
    }

    @FXML
    private void bInicioSesion(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Parent root = loader.load();
        
        InicioSesionController controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inicio Sesión");
        
        Stage myStage = (Stage) this.incioSesionfxID.getScene().getWindow();
        myStage.close();
    }

    @FXML
    private void bInvitado(ActionEvent event) {
    }
    
}
