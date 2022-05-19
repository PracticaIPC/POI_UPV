/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
public class MostrarUsuarioController implements Initializable {

    @FXML
    private Button cancelarfxID;
    @FXML
    private Button continuarfxID;
    @FXML
    private ImageView avatarfxID;
    @FXML
    private Label usuariofxID;
    
    public static String user;
    
    Navegacion BaseDatos;
    @FXML
    private Button modificarfxID;
    @FXML
    private Button resultadosfxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BaseDatos = Navegacion.getSingletonNavegacion();
            // TODO
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(MostrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        usuariofxID.setText(BaseDatos.getUser(user).getNickName());
        avatarfxID.setImage(BaseDatos.getUser(user).getAvatar());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Test.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Test");
        
            Stage myStage = (Stage) this.continuarfxID.getScene().getWindow();
            myStage.close();
    }

    @FXML
    private void bModificar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificarUsuario.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("ModificarUsuario");
        
            Stage myStage = (Stage) this.modificarfxID.getScene().getWindow();
            myStage.close();
    }

    @FXML
    private void bResultados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarResultados.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Resultados");
        
    }
    
}
