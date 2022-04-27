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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;

import static model.User.checkEmail;
import static model.User.checkNickName;
import static model.User.checkPassword;

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
    
    Navegacion BaseDatos;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InicioSesionfxID.setTextFill(Color.rgb(107, 207, 176));
        muestrafxID.setTextFill(Color.GRAY);
        muestrafxID.setVisible(false);
        
        try {
            BaseDatos = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void bContinuar(ActionEvent event) throws NavegacionDAOException, IOException {
        if(BaseDatos.exitsNickName(usuariofxID.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario Ya Utilizado");
            alert.showAndWait();
        }
        
        if(checkNickName(usuariofxID.getText())&& checkPassword(contraseñafxID.getText()) && checkEmail(correofxID.getText())){
            if(imagenfxID.getImage() == null){
                BaseDatos.registerUser(usuariofxID.getText(), correofxID.getText(), contraseñafxID.getText(), fechafxID.getValue());
            }
            if(imagenfxID.getImage() != null){
                BaseDatos.registerUser(usuariofxID.getText(), correofxID.getText(), contraseñafxID.getText(), imagenfxID.getImage(), fechafxID.getValue());
            }
            
            MostrarUsuarioController.user = usuariofxID.getText();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarUsuario.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Pizarra de Navegación");
        
            Stage myStage = (Stage) this.continuarfxID.getScene().getWindow();
            myStage.close();
        }
        
        if(!checkNickName(usuariofxID.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Usuario no Válido");
                   alert.setTitle("Error");
                   alert.setContentText("Un usuario es valido si: \n" + "- tiene entre 6 y 15 caracteres \n"
                                        + "- contiene mayusculas y minusculas, números o los guiones '-' y '_'.");
                   alert.showAndWait();
        }
        
        if(!checkPassword(contraseñafxID.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Contraseña no Válida");
                   alert.setTitle("Error");
                   alert.setContentText("Una contraseña es valida si:\n" + "- contiene ente 8 y 20 caracteres\n" +
                                        "- contiene al menos una letra mayúscula\n" + "- contiene al menos una letra minúscula\n" +
                                        "- contiene al menos un dígito\n" + "- contiene un carácter especial del conjunto: !@#$%&*()-+=\n" +
                                        "- no contiene ningún espacio en blanco");
                   alert.showAndWait();
        }
        
        if(!checkEmail(correofxID.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Correo no Válido");
                   alert.setTitle("Error");
                   alert.setContentText("El dato proporcionado no corresponde con una cuenta de correo válida. Por ejemplo: \n" + "ejemplo@mail.com");
                   alert.showAndWait();
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
