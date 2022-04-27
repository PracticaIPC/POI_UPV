/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author User
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
    private void bCancelar(ActionEvent event) {
    }

    @FXML
    private void bContinuar(ActionEvent event) {
    }
    
}
