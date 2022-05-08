/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CartaNavegacionController implements Initializable {

    @FXML
    private Menu informacionfxID;
    @FXML
    private Menu limpiarfxID;
    @FXML
    private MenuItem cambiarColorfxID;
    @FXML
    private MenuItem eliminarMarcafxID;
    @FXML
    private Slider zoomSliderfxID;
    @FXML
    private Button puntofxID;
    @FXML
    private Button lineafxID;
    @FXML
    private Button arcofxID;
    @FXML
    private Button textofxID;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private MenuButton menu_pin;
    @FXML
    private ListView<?> listafxID;
    @FXML
    private Label posicionfxID;
    
    Image imagen = new Image(getClass().getResourceAsStream("/resources/carta_nautica.jpg"));
    @FXML
    private ImageView imagenfxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenfxID = new ImageView(imagen);
        // TODO
    }    

    @FXML
    private void bInformacion(ActionEvent event) {
    }

    @FXML
    private void bLimpiar(ActionEvent event) {
    }

    @FXML
    private void bCambiarColor(ActionEvent event) {
    }

    @FXML
    private void bEliminarMarca(ActionEvent event) {
    }

    @FXML
    private void zoomOut(ActionEvent event) {
    }

    @FXML
    private void zoomIn(ActionEvent event) {
    }

    @FXML
    private void bPunto(ActionEvent event) {
    }

    @FXML
    private void bLinea(ActionEvent event) {
    }

    @FXML
    private void bArco(ActionEvent event) {
    }

    @FXML
    private void bTexto(ActionEvent event) {
    }
    
}
