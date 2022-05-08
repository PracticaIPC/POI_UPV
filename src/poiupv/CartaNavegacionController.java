/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

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
    
    Line linePainting = new Line();
    
    Image imagen = new Image(getClass().getResourceAsStream("/resources/carta_nautica.jpg"));
    //----------------------------------------------------------------------------------------
    //hashmap para guardar los puntos de interes
    private final HashMap<String, Poi> hm = new HashMap<>();
    //========================================================================================
    //la variable zoomGroup se utiliza para dar soporte al zoom
    // el escalado se realiza sobre este nodo, al escalar el Group no mueve sus nodos
    private Group zoomGroup;
    
    int pintar;
    
    
    @FXML
    private ImageView imagenfxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenfxID = new ImageView(imagen);
        
        initData();
        
        zoomSliderfxID.setMin(0.5);
        zoomSliderfxID.setMax(1.5);
        zoomSliderfxID.setValue(1);
        zoomSliderfxID.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
        //=======================================================
        //Envuelve el contenido de scrollpane en un grupo para que
        //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);
        //======================================================
        
    }    
    
    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
        //========================================================
        //Linea
        
        //==============================================================
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
    void zoomOut(ActionEvent event) {
        double sliderVal = zoomSliderfxID.getValue();
        zoomSliderfxID.setValue(sliderVal + -0.1);
    }

    @FXML
    void zoomIn(ActionEvent event) {
        double sliderVal = zoomSliderfxID.getValue();
        zoomSliderfxID.setValue(sliderVal += 0.1);
    }

    @FXML
    private void bPunto(ActionEvent event) {
        pintar = 1;
        linePainting.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
                zoomGroup.getChildren().remove((Node) e.getSource());
                ev.consume();
            });
            menuContext.show(linePainting, e.getSceneX(), e.getSceneY());
            e.consume();
        });
    }

    @FXML
    private void bLinea(ActionEvent event) {
        pintar = 2;
    }

    @FXML
    private void bArco(ActionEvent event) {
        pintar = 3;
    }

    @FXML
    private void bTexto(ActionEvent event) {
        pintar = 4;
    }

    private void initData() {
        
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
        switch(pintar){
            case 1:
                System.out.println("Punto");
            case 2:
                linePainting.setEndX(event.getX());
                linePainting.setEndY(event.getY());
            case 3:
                System.out.println("Arco");
            case 4:
                System.out.println("Texto");
            }
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        switch(pintar){
            case 1:
                System.out.println("Punto");
            case 2:
                linePainting = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                zoomGroup.getChildren().add(linePainting);
            case 3:
                System.out.println("Arco");
            case 4:
                System.out.println("Texto");
            }
    }
    
}
