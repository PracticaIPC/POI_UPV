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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;
import model.Session;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MostrarResultadosController implements Initializable {

    @FXML
    private ImageView avatarfxID;
    @FXML
    private Label usuariofxID;
    @FXML
    private DatePicker fecha;
    @FXML
    private TableView<Session> tablafxID;
    @FXML
    private TableColumn<Session, String> partidafxID;
    @FXML
    private TableColumn<Session, String> aciertosfxID;
    @FXML
    private TableColumn<Session, String> totalfxID;
    @FXML
    private Button volverfxID;
    Navegacion BaseDatos;
    
    @FXML
    private CheckBox todosfxID;

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
        
        usuariofxID.setText(BaseDatos.getUser(MostrarUsuarioController.user).getNickName());
        avatarfxID.setImage(BaseDatos.getUser(MostrarUsuarioController.user).getAvatar());
        
    }    

    @FXML
    private void bVolver(ActionEvent event) {
        Stage stage = (Stage) this.volverfxID.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void bTodos(ActionEvent event) {
        if(todosfxID.isSelected()){
            
        }else{
            
        }
        
    }
    
}
