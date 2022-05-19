/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAO;
import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * Proyecto Realizado por:
 * - Francisco Ramos Guardiola
 * - Álvaro Camino Tirapu
 * - Carmen Martínez Rodríguez
 * GRUPO: 2F - L1
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
    private TextField contraseñafxID;
    @FXML
    private Label registrofxID;
    @FXML
    private Label muestrafxID;
    
    Navegacion BaseDatos;
    User usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        muestrafxID.setTextFill(Color.GRAY);
        registrofxID.setTextFill(Color.rgb(107, 207, 176));
        muestrafxID.setVisible(false);
        
        try {
            BaseDatos = Navegacion.getSingletonNavegacion();
            //BaseDatos.registerUser("Fran", "fran@upv.es", "hola21", LocalDate.MIN);
            // TODO
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void bMostrar(ActionEvent event) {
        if(mostrarfxID.isSelected()){
           muestrafxID.setVisible(true);
           muestrafxID.textProperty().bind(contraseñafxID.textProperty());
        }else if(!mostrarfxID.isSelected()){
            muestrafxID.setVisible(false);
        }
            
    }




    @FXML
    private void bCancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
        Parent root = loader.load();
        
        PantallaInicialController controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inicio");
        
        Stage myStage = (Stage) this.cancelarfxID.getScene().getWindow();
        myStage.close();
    }

    @FXML
    private void bContinuar(ActionEvent event) throws IOException {
        if((BaseDatos.loginUser(usuariofxID.getText(), contraseñafxID.getText())) != null){
            
            MostrarUsuarioController.user = usuariofxID.getText();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarUsuario.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Perfil de Usuario");
        
            Stage myStage = (Stage) this.continuarfxID.getScene().getWindow();
            myStage.close();
            
            
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Fallo Inicio Sesión");
            alert.setContentText("Usuario o Contraseña incorrecto");
            alert.showAndWait();
        }
    }

    @FXML
    private void finInicio(MouseEvent event) {
        registrofxID.setTextFill(Color.rgb(38, 159, 121));
    }

    @FXML
    private void arrastreInicio(MouseEvent event) {
        registrofxID.setTextFill(Color.rgb(36, 73, 62));
    }

    @FXML
    private void bRegistro(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inicio Sesión");
        
        Stage myStage = (Stage) this.registrofxID.getScene().getWindow();
            myStage.close();
    }





    
}
