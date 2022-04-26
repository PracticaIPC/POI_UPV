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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RegistroController implements Initializable {

    @FXML
    private Button cancelarfxID;
    @FXML
    private Button continuarfxID;
    @FXML
    private CheckBox mostrarfxID;
    @FXML
    private TextField usuariofxID;
    @FXML
    private TextField contraseñafxID;
    @FXML
    private TextField correofxID;
    @FXML
    private DatePicker fechafxID;
    @FXML
    private Button seleccionfxID;
    @FXML
    private ImageView imagenfxID;
    @FXML
    private Label InicioSesionfxID;
    @FXML
    private Label muestrafxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InicioSesionfxID.setTextFill(Color.rgb(107, 207, 176));
        muestrafxID.setTextFill(Color.GRAY);
        muestrafxID.setVisible(false);
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
    private void bMostrar(ActionEvent event) {
        if(mostrarfxID.isSelected()){
           muestrafxID.setVisible(true);
           muestrafxID.textProperty().bind(contraseñafxID.textProperty());
        }else if(!mostrarfxID.isSelected()){
            muestrafxID.setVisible(false);
        }
    }

    @FXML
    private void bFecha(ActionEvent event) {
    }

    @FXML
    private void bSelección(ActionEvent event) {
    }

    @FXML
    private void bInicioSesion(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inicio Sesión");
        
        Stage myStage = (Stage) this.InicioSesionfxID.getScene().getWindow();
            myStage.close();
        
    }

    
    

    @FXML
    private void finInicio(MouseEvent event) {
        InicioSesionfxID.setTextFill(Color.rgb(38, 159, 121));
    }

    @FXML
    private void arrastreInicio(MouseEvent event) {
        InicioSesionfxID.setTextFill(Color.rgb(36, 73, 62));
    }
    
}
