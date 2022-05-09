/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.Navegacion;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TestController implements Initializable {

    @FXML
    private ImageView avatatfxID;
    @FXML
    private Label usuariofxID;
    @FXML
    private Menu cambiarUsuariofxID;
    @FXML
    private Menu cartaNavegacionfxID;
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
    private Menu FinalizarfxID;
    @FXML
    private Menu infofxID;
    @FXML
    private Button comprobarfxID;
    @FXML
    private Label resultfxID;

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
    private void bCambiarUsuario(ActionEvent event) {
    }

    @FXML
    private void bCartaNavegación(ActionEvent event) {
    }

    @FXML
    private void bSiguientePregunta(ActionEvent event) {
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
    private void bFinalizar(ActionEvent event) {
    }

    @FXML
    private void bInfo(ActionEvent event) {
    }

    @FXML
    private void bComprobar(ActionEvent event) {
        if(resultado == false){
            resultfxID.setText("Incorrecto");
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
    
}
