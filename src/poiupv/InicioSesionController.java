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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class InicioSesionController implements Initializable {

    @FXML
    private CheckBox mostrarfxID;
    @FXML
    private TextField usuariofxID;
    @FXML
    private Button cancelarfxID;
    @FXML
    private Button continuarfxID;
    @FXML
    private DatePicker fechafxID;
    @FXML
    private Button seleccionfxID;
    @FXML
    private ImageView imagenfxID;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bMostrar(ActionEvent event) {
    }


    @FXML
    private void bUsuario(ActionEvent event) {
    }


    @FXML
    private void bCancelar(ActionEvent event) {
    }

    @FXML
    private void bContinuar(ActionEvent event) {
    }

    @FXML
    private void bFecha(ActionEvent event) {
    }

    @FXML
    private void bSelección(ActionEvent event) {
    }

    
}
