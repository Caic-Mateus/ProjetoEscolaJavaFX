/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author caicm
 */
public class MenuController  {

    @FXML
    private Button cadastroSalaClick;
    @FXML
    private Button cadastroProfessorClick;

    /**
     * Initializes the controller class.
     */
  

    @FXML
    private void cadastroSalaClick(ActionEvent event) throws IOException {
        App.setRoot("cadastrarSala");
    }

    @FXML
    private void cadastroProfessorClick(ActionEvent event) throws IOException {
        App.setRoot("cadastrarFuncionario");
    }
    
}
