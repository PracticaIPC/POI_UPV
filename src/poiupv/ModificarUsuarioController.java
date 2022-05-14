/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;
import model.Session;

import static model.User.checkEmail;
import static model.User.checkNickName;
import static model.User.checkPassword;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModificarUsuarioController implements Initializable {

    @FXML
    private Button cancelarfxID;
    @FXML
    private Button continuarfxID;
    @FXML
    private Button informacionfxID;
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
    private Label muestrafxID;
    @FXML
    private CheckBox mostrarfxID;
    
    Navegacion BaseDatos;
    
    final FileChooser fileChooser = new FileChooser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BaseDatos = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(ModificarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        muestrafxID.setTextFill(Color.GRAY);
        muestrafxID.setVisible(false);
        
        usuariofxID.setText(BaseDatos.getUser(MostrarUsuarioController.user).getNickName());
        imagenfxID.setImage(BaseDatos.getUser(MostrarUsuarioController.user).getAvatar());
        contraseñafxID.setText(BaseDatos.getUser(MostrarUsuarioController.user).getPassword());
        fechafxID.setValue(BaseDatos.getUser(MostrarUsuarioController.user).getBirthdate());
        correofxID.setText(BaseDatos.getUser(MostrarUsuarioController.user).getEmail());
        usuariofxID.setEditable(false);
    }    

    @FXML
    private void bCancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarUsuario.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Perfil Usuario");
        
            Stage myStage = (Stage) this.continuarfxID.getScene().getWindow();
            myStage.close();
    }

    @FXML
      private void bContinuar(ActionEvent event) throws IOException, NavegacionDAOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarUsuario.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Perfil");
        
            BaseDatos.getUser(usuariofxID.getText()).setEmail(correofxID.getText());
            BaseDatos.getUser(usuariofxID.getText()).setPassword(contraseñafxID.getText());
            BaseDatos.getUser(usuariofxID.getText()).setAvatar(imagenfxID.getImage());
           BaseDatos.getUser(usuariofxID.getText()).setBirthdate(fechafxID.getValue());
           
            Stage myStage = (Stage) this.continuarfxID.getScene().getWindow();
            myStage.close();
    }

  

    @FXML
    private void bFecha(ActionEvent event) {
    }

    @FXML
    private void bSelección(ActionEvent event) throws FileNotFoundException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificarUsuario.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        File file = fileChooser.showOpenDialog(stage);
        
        if(file.isFile() && (file.getName().contains(".jpg") || file.getName().contains(".png") || file.getName().contains(".bmp") || file.getName().contains(".gif"))){
            Image avatar = new Image(new FileInputStream(file));
            imagenfxID.imageProperty().setValue(avatar);
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
    private void bInformacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText("hola");
                   alert.setTitle("adios");
                   alert.setContentText("Puede modificar todos los datos menos el nombre de usuario. \n" + 
                           "Solo modifique los datos que desea cambiar\n" + 
                           "Si no desea modificar algún dato déjelo como sale al iniciar la ventana");
                   alert.showAndWait();
    }
    
}
