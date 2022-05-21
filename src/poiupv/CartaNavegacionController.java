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
import static javafx.beans.binding.Bindings.when;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CartaNavegacionController implements Initializable {

    @FXML
    private MenuItem informacionfxID;
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
    
    double inicioXTrans, inicioYTrans, baseX, baseY;
    
    Color color;
    
    Image transportador = new Image(getClass().getResourceAsStream("/resources/transportador.png"));
    
    
    @FXML
    private ImageView imagenfxID;
    @FXML
    private ColorPicker colorfxID;
    @FXML
    private Button noDibujarfxID;
    @FXML
    private CheckBox trasnportadorfxID;
    @FXML
    private ImageView cuadradofxID;

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
        
        trasnportadorfxID.setSelected(false);
        cuadradofxID.setVisible(false);
        cuadradofxID.setOpacity(0.7);
        
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.setHeaderText("Como Usar la carta de navegación");
        alert.setTitle("Información");
        alert.setContentText("Para dibujar en la carta de navegación pulse el boton a la izquierda del objeto que desea dibujar: \n" + "- No Seleccionar: Utilice este boton para moverte libremente por la carta sin dibujar nada. \n" +
                "- Punto: Pulse sobre la carta para dibujar el punto. \n" + "- Linea: Pulse (punto inicial) y arrastre (hasta punto final deseado) sobre la carta. \n" + 
                "- Arco: Pulse (centro del arco) y arrastre (diametro deseado) sobre la carta. \n" + "- Texto: Pulse sobre la carta donde desea escribir el texto. \n" + 
                "Menu Herramientas: \n" + "- Cambiar Color Marca: Seleccione el color de la siguiente marca a dibujar \n" + "- Eliminar Marca: Eliminas la ultima marca introducida en la carta de navegación \n" + 
                "- Limpiar Carta: Eliminas todos los objetos introducidos en la carta \n" + "Para aumentar o reducir el tamaño de la carta puede seleccionar tant los botones de '+' y '-' como utilizar el slider de laparte superior derecha dela ventana");
        alert.showAndWait();
    }

    @FXML
    private void bLimpiar(ActionEvent event) {
        for(int i = zoomGroup.getChildren().size() - 1; i > 0; i--){
            zoomGroup.getChildren().remove(i);
        }
        
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
            zoomGroup.getChildren().add(texto);
            texto.setLayoutX(event.getX());
                texto.setLayoutY(event.getY());
                texto.requestFocus();
                
            texto.setOnAction(e -> {
            Text textoT = new Text(texto.getText());
            textoT.setX(texto.getLayoutX());
            textoT.setY(texto.getLayoutY());
            textoT.setStyle("-fx-font-family: Gafata; -fx-font-size: 40;");
            textoT.setFill(color);
            zoomGroup.getChildren().add(textoT);
            zoomGroup.getChildren().remove(texto);
            e.consume();
        });
                
            
        }
    }

    @FXML
    private void bColor(ActionEvent event) {
        color = colorfxID.getValue();
        
    }

    @FXML
    private void bSalirMouse(MouseEvent event) {
       // pintar = 0;
    }

    @FXML
    private void bNoDibujar(ActionEvent event) {
        pintar = 0;
    }

    @FXML
    private void bTransportador(ActionEvent event) {
        if(trasnportadorfxID.isSelected()){
            cuadradofxID.setVisible(true);
        }else{
            cuadradofxID.setVisible(false);
        }
    }

    //Mouse Released
    @FXML
    private void finTraslacion(MouseEvent event) {
    }

    //Mouse Dragged
    @FXML
    private void traslacion(MouseEvent event) {
        double despX = event.getSceneX() - inicioXTrans;
        double despY = event.getSceneY() - inicioYTrans;
        
        cuadradofxID.setTranslateX(baseX + despX);
        cuadradofxID.setTranslateY(baseY + despY);
        
        event.consume();
        
    }

    //Mouse Pressed
    @FXML
    private void inicioTraslacion(MouseEvent event) {
        inicioXTrans = event.getSceneX();
        inicioYTrans = event.getSceneY();
        
        baseX = cuadradofxID.getTranslateX();
        baseY = cuadradofxID.getTranslateY();
        
        event.consume();
    }
    
}