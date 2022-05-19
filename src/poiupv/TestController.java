/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.Session;

/**
 * FXML Controller class
 *
 * Proyecto Realizado por:
 * - Francisco Ramos Guardiola
 * - Álvaro Camino Tirapu
 * - Carmen Martínez Rodríguez
 * GRUPO: 2F - L1
 */
public class TestController implements Initializable {

    @FXML
    private ImageView avatatfxID;
    @FXML
    private Label usuariofxID;
    @FXML
    private MenuItem cambiarUsuariofxID;
    @FXML
    private MenuItem cartaNavegacionfxID;
    @FXML
    private Button siguientePreguntafxID;
    @FXML
    private Label preguntafxID;
    @FXML
    private CheckBox resp1fxID;
    @FXML
    private CheckBox resp2fxID;
    @FXML
    private CheckBox resp3fxID;
    @FXML
    private CheckBox resp4fxID;
    
    Navegacion BaseDatos;
    
    boolean resultado;
    
    Random rnd = new Random();
    int  i = rnd.nextInt(17) + 1;
    @FXML
    private MenuItem FinalizarfxID;
    @FXML
    private MenuItem infofxID;
    @FXML
    private Button comprobarfxID;
    @FXML
    private Label resultfxID;
    
    int aciertos = 0;
    int fallos = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            BaseDatos = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usuariofxID.setText(BaseDatos.getUser(MostrarUsuarioController.user).getNickName());
        avatatfxID.setImage(BaseDatos.getUser(MostrarUsuarioController.user).getAvatar());
        
        preguntafxID.setText(BaseDatos.getProblems().get(i).getText());
        resp1fxID.setText(BaseDatos.getProblems().get(i).getAnswers().get(0).getText());
        resp2fxID.setText(BaseDatos.getProblems().get(i).getAnswers().get(1).getText());
        resp3fxID.setText(BaseDatos.getProblems().get(i).getAnswers().get(2).getText());
        resp4fxID.setText(BaseDatos.getProblems().get(i).getAnswers().get(3).getText());
        
        

        
    }    



    @FXML
    private void bCartaNavegación(ActionEvent event) throws IOException {
        Session sesion = new Session(LocalDateTime.now(), aciertos, fallos);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PoiUPVApp.class.getResource("CartaNavegacion.fxml"));
        Parent root = (Parent) loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Carta de Navegacion");
            
        
    }

    @FXML
    private void bSiguientePregunta(ActionEvent event) {
        resp1fxID.setSelected(false);
        resp2fxID.setSelected(false);
        resp3fxID.setSelected(false);
        resp4fxID.setSelected(false);
        
        comprobarfxID.setDisable(false);
        
        resp1fxID.setTextFill(Color.BLACK);
        resp2fxID.setTextFill(Color.BLACK);
        resp3fxID.setTextFill(Color.BLACK);
        resp4fxID.setTextFill(Color.BLACK);
        int j = rnd.nextInt(17)  +1;
        
        if(j == i){
            j = rnd.nextInt(17) + 1;
        }else{
            preguntafxID.setText(BaseDatos.getProblems().get(j).getText());
            resp1fxID.setText(BaseDatos.getProblems().get(j).getAnswers().get(0).getText());
            resp2fxID.setText(BaseDatos.getProblems().get(j).getAnswers().get(1).getText());
            resp3fxID.setText(BaseDatos.getProblems().get(j).getAnswers().get(2).getText());
            resp4fxID.setText(BaseDatos.getProblems().get(j).getAnswers().get(3).getText());
        }
        
        
        
    }

    @FXML
    private void bResp1(ActionEvent event) {
        if(resp1fxID.isSelected()){
            resp2fxID.setSelected(false);
            resp3fxID.setSelected(false);
            resp4fxID.setSelected(false);
            
            resultado = BaseDatos.getProblems().get(i).getAnswers().get(0).getValidity();
        }
        
    }

    @FXML
    private void bResp2(ActionEvent event) {
        if(resp2fxID.isSelected()){
            resp1fxID.setSelected(false);
            resp3fxID.setSelected(false);
            resp4fxID.setSelected(false);
            
            resultado = BaseDatos.getProblems().get(i).getAnswers().get(1).getValidity();
        }
    }

    @FXML
    private void bResp3(ActionEvent event) {
        if(resp3fxID.isSelected()){
            resp2fxID.setSelected(false);
            resp1fxID.setSelected(false);
            resp4fxID.setSelected(false);
            
            resultado = BaseDatos.getProblems().get(i).getAnswers().get(2).getValidity();
        }
    }

    @FXML
    private void bResp4(ActionEvent event) {
        if(resp4fxID.isSelected()){
            resp2fxID.setSelected(false);
            resp3fxID.setSelected(false);
            resp1fxID.setSelected(false);
            
            resultado = BaseDatos.getProblems().get(i).getAnswers().get(3).getValidity();
            
        }
    }

    @FXML
    private void bFinalizar(ActionEvent event) throws NavegacionDAOException, IOException {
        Session sesion = new Session(LocalDateTime.now(), aciertos, fallos);
        BaseDatos.getUser(MostrarUsuarioController.user).addSession(sesion);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarUsuario.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("ModificarUsuario");
            
            Stage myStage = (Stage) this.comprobarfxID.getScene().getWindow();
            myStage.close();
        
            /*FXMLLoader asd = new FXMLLoader(getClass().getResource("Test.fxml"));
            Parent root2 = asd.load();
            
            Scene scene2 = new Scene(root2);
            Stage stage2 = new Stage();
            stage2.close();*/
    }

    @FXML
    private void bInfo(ActionEvent event) {
    }

    @FXML
    private void bComprobar(ActionEvent event) {
        if(resultado == false){
            resultfxID.setText("Incorrecto");
            fallos += 1;
            if(resp4fxID.isSelected()){
                resp4fxID.setTextFill(Color.RED);
                if(BaseDatos.getProblems().get(i).getAnswers().get(0).getValidity() == true){
                    resp1fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(1).getValidity() == true){
                    resp2fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(2).getValidity() == true){
                    resp3fxID.setTextFill(Color.GREEN);
                }
            }
            if(resp1fxID.isSelected()){
                resp1fxID.setTextFill(Color.RED);
                if(BaseDatos.getProblems().get(i).getAnswers().get(3).getValidity() == true){
                    resp4fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(1).getValidity() == true){
                    resp2fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(2).getValidity() == true){
                    resp3fxID.setTextFill(Color.GREEN);
                }
            }
            if(resp2fxID.isSelected()){
                resp2fxID.setTextFill(Color.RED);
                if(BaseDatos.getProblems().get(i).getAnswers().get(0).getValidity() == true){
                    resp1fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(3).getValidity() == true){
                    resp4fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(2).getValidity() == true){
                    resp3fxID.setTextFill(Color.GREEN);
                }
            }
            if(resp3fxID.isSelected()){
                resp3fxID.setTextFill(Color.RED);
                if(BaseDatos.getProblems().get(i).getAnswers().get(0).getValidity() == true){
                    resp1fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(1).getValidity() == true){
                    resp2fxID.setTextFill(Color.GREEN);
                }else if(BaseDatos.getProblems().get(i).getAnswers().get(3).getValidity() == true){
                    resp4fxID.setTextFill(Color.GREEN);
                }
            }
        }else{
            resultfxID.setText("Correcto");
            aciertos += 1;
            if(resp4fxID.isSelected()){
                resp4fxID.setTextFill(Color.GREEN);
            }
            if(resp1fxID.isSelected()){
                resp1fxID.setTextFill(Color.GREEN);
            }
            if(resp2fxID.isSelected()){
                resp2fxID.setTextFill(Color.GREEN);
            }
            if(resp3fxID.isSelected()){
                resp3fxID.setTextFill(Color.GREEN);
            }
        }
        
        comprobarfxID.setDisable(true);
    }

    @FXML
    private void bCambiarUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Iniciar Sesion");
            
            Stage myStage = (Stage) this.comprobarfxID.getScene().getWindow();
            myStage.close();
    }
    
}
