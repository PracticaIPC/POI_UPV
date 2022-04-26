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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    private TextField contraseñafxID;
    @FXML
    private Label registrofxID;
    @FXML
    private Label muestrafxID;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        muestrafxID.setTextFill(Color.GRAY);
        registrofxID.setTextFill(Color.rgb(107, 207, 176));
        muestrafxID.setVisible(false);
        // TODO
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
        stage.setTitle("Pizarra de Navegación");
        
        Stage myStage = (Stage) this.cancelarfxID.getScene().getWindow();
        myStage.close();
    }

    @FXML
    private void bContinuar(ActionEvent event) {
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
