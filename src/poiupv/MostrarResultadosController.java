/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<String, String> partidafxID;
    @FXML
    private TableColumn<String, String> aciertosfxID;
    @FXML
    private Button volverfxID;
    Navegacion BaseDatos;
    LocalDate local;
    
    @FXML
    private CheckBox todosfxID;
    
    private ObservableList <Session> partida = null; 
    
    LocalDateTime time;
    Session sesion = new Session(LocalDateTime.of(LocalDate.ofYearDay(2022, 100), LocalTime.of(12, 32)), 17, 8);
    @FXML
    private TableColumn<String, String> fallosfxID;
    private ArrayList<Session> array = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BaseDatos = Navegacion.getSingletonNavegacion();
            //BaseDatos.getUser(MostrarUsuarioController.user).addSession(sesion);
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(MostrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.partidafxID.setCellValueFactory(new PropertyValueFactory("timeStamp"));
        this.aciertosfxID.setCellValueFactory(new PropertyValueFactory("hits"));
        this.fallosfxID.setCellValueFactory(new PropertyValueFactory("faults"));
        usuariofxID.setText(BaseDatos.getUser(MostrarUsuarioController.user).getNickName());
        avatarfxID.setImage(BaseDatos.getUser(MostrarUsuarioController.user).getAvatar());
        tablafxID.setItems(partida);
        partida = FXCollections.observableArrayList(BaseDatos.getUser(MostrarUsuarioController.user).getSessions());
        this.tablafxID.setItems(partida);
        
        
        
    }    

    @FXML
    private void bVolver(ActionEvent event) {
        Stage stage = (Stage) this.volverfxID.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void bTodos(ActionEvent event) {
        
        if(todosfxID.isSelected()){
            tablafxID.setItems(partida);
            fecha.setDisable(true);
            
        }else{
            fecha.setValue(null);
            fecha.setDisable(false);        
        }
        
    }

    @FXML
    private void bFecha(ActionEvent event) {
        /*ArrayList<Session> prueba = new ArrayList<Session>();
        array = new ArrayList<> (BaseDatos.getUser(MostrarUsuarioController.user).getSessions());
        array.forEach((Session)->{
            if(Session.getLocalDate().compareTo(this.fecha.getValue()) >= 0){
            }
            //Collections.reverse(prueba);
            partida = FXCollections.observableArrayList(prueba);
        });*/
        ObservableList<Session> obje = FXCollections.observableArrayList();
        for(Session item : this.partida){
                    if(item.getLocalDate().compareTo(fecha.getValue()) >= 0){
                        
                        obje.add(item);
                        
                        
                    }else{
                       
                    }
                }
        
        tablafxID.setItems(obje);
    }
    
    private String escribirHora(LocalDateTime hora){
        if(hora.getMinute() < 10){
            return hora.getHour() + ":0" + hora.getMinute() ;
        }else{
        return hora.getHour() + ":" + hora.getMinute() ;}
    
    }
    
}
