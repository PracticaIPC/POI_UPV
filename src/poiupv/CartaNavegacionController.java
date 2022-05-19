/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.awt.Point;
import static java.lang.Double.valueOf;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CartaNavegacionController implements Initializable {

    @FXML
    private Menu informacionfxID;
    @FXML
    private MenuItem limpiarfxID;
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
    double inicioXArc;
    Circle circlePainting;
    TextField texto;
    Circle punto;
    
    Color color;
    
    
    
    
    @FXML
    private ImageView imagenfxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //imagenfxID = new ImageView(imagen);
        
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
        
        color = Color.RED;
        
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
        linePainting.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
                zoomGroup.getChildren().remove((Node)e.getSource());
                ev.consume();
            });
            menuContext.show(linePainting, e.getSceneX(), e.getSceneY());
            e.consume();
        });
        //==============================================================
        //Texto
        
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
        int i = zoomGroup.getChildren().size();
        zoomGroup.getChildren().remove(i-1);
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
        
        
        if(pintar == 1){
            
        }
        if(pintar == 2){
            linePainting.setEndX(event.getX());
                linePainting.setEndY(event.getY());
        }
        if(pintar == 3){
            double radio = Math.abs(event.getX() - inicioXArc);
                circlePainting.setRadius(radio);
                event.consume();
        }
        if(pintar == 4){
            
        }
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        linePainting = new Line(event.getX(), event.getY(), event.getX(), event.getY());
        linePainting.setFill(color);
        linePainting.setStroke(color);
        circlePainting = new Circle(1);
        circlePainting.setStroke(color);
        circlePainting.setFill(Color.TRANSPARENT);
        punto = new Circle();
        punto.setStroke(color);
        punto.setFill(color);
       // punto = new Point(event.getX(), event.getY());
        
        texto = new TextField();
        
        
        if(pintar == 1){
            zoomGroup.getChildren().add(punto);
            punto.setRadius(3);
            punto.setCenterX(event.getX());
            punto.setCenterY(event.getY());
        }
        if(pintar == 2){
            zoomGroup.getChildren().add(linePainting);
        }
        if(pintar == 3){
                zoomGroup.getChildren().add(circlePainting);
                
                circlePainting.setCenterX(event.getX());
                circlePainting.setCenterY(event.getY());
                inicioXArc = event.getX();
        }
        if(pintar == 4){
            texto.setLayoutX(event.getX());
                texto.setLayoutY(event.getY());
                texto.requestFocus();
                
            texto.setOnAction(e -> {
            Text textoT = new Text(texto.getText());
            textoT.setX(texto.getLayoutX());
            textoT.setY(texto.getLayoutY());
            //textoT.setStyle("-fx-font-family: Cafata; -fx-font-size: 40");
            zoomGroup.getChildren().add(textoT);
            zoomGroup.getChildren().remove(texto);
            e.consume();
        });
        }
    }
    
}