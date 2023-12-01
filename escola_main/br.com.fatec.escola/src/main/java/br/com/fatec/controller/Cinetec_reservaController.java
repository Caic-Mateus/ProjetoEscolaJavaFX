/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.reservaDAO;
import br.com.fatec.SendSmsBasic;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class Cinetec_reservaController implements Initializable {

    @FXML
    private Button btnSMS;
    @FXML
    private Hyperlink btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSMS(ActionEvent event) throws IOException, SQLException {
        
        reservaDAO dao = new reservaDAO();
    
        String[] vendaInfo = dao.getUltimaVenda();
    
        if (vendaInfo != null) {
            String numeroCliente = vendaInfo[0];
            String nomeCliente = vendaInfo[1];
            String nomeFilme = vendaInfo[2];
            String hora = vendaInfo[3];
            String poltronas = vendaInfo[4];
            
           SendSmsBasic sendSmsBasic = new SendSmsBasic();
           sendSmsBasic.send(numeroCliente, nomeCliente, poltronas, nomeFilme, hora);
           msg_info("SMS enviado!");
        }
        
        
            

        }

    @FXML
    private void btnSair(ActionEvent event) throws IOException {
        loginTela log = new loginTela();
        log.start(new Stage());
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
    
        private void msg_info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
        
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/icon.png").toString()));   
        
        alerta.showAndWait(); //exibe mensagem
    }
}
