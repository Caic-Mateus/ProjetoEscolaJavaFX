/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author caicm
 */
public class CadastrarFuncionarioController implements Initializable {

    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_sexo;
    @FXML
    private TextField txt_rg;
    @FXML
    private TextField txt_salario;
    @FXML
    private TextField txt_banco;
    @FXML
    private TextField txt_agencia;
    @FXML
    private Button btn_cadastrar;
    @FXML
    private Button btn_atualizar;
    @FXML
    private Button btn_deletar;
    @FXML
    private TextField txt_conta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarProfessor(ActionEvent event) {
    }

    @FXML
    private void atualizarProfessor(ActionEvent event) {
    }

    @FXML
    private void DeletarProfessor(ActionEvent event) {
    }
    
}
