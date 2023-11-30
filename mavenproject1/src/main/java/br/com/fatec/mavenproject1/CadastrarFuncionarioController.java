/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.mavenproject1;

import br.com.fatec.model.CadastroProfessor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    private CadastroProfessor cadastro = new CadastroProfessor(); //indica nosso model

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarProfessor(ActionEvent event) {
        cadastro = moveViewToModel();
        if(!todosCamposPreenchidos()){
            return;
        }
    }

    @FXML
    private void atualizarProfessor(ActionEvent event) {
    }

    @FXML
    private void DeletarProfessor(ActionEvent event) {
    }
    private CadastroProfessor moveViewToModel(){
        
        //CRIA O OBJETO CADASTRO - (MODEL)
        cadastro = new CadastroProfessor();
        cadastro.setNome(txt_nome.getText());
        cadastro.setAgencia(txt_agencia.getText());
        cadastro.setBanco(txt_banco.getText());
        cadastro.setCpf(txt_cpf.getText());
        cadastro.setEmail(txt_email.getText());
        cadastro.setRg(txt_rg.getText());
        cadastro.setSalario(txt_salario.getText());
        cadastro.setSexo(txt_sexo.getText());
             
        //Devolve o model
        return cadastro;     
    }
    private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txt_nome.getText().isEmpty()) {
            msg_alert("Preencha o campo Nome.");
            txt_nome.requestFocus();
            return false;
        }
        if (txt_salario.getText().isEmpty()) {
            msg_alert("Preencha o campo Salario.");
            txt_salario.requestFocus();
            return false;
        }
        if (txt_cpf.getText().length() != 11) {
            msg_alert("O CPF deve ter 11 dígitos.");
            txt_cpf.requestFocus();
            return false;
        }
        if (txt_agencia.getText().length() != 11) {
            msg_alert("O número de celular deve ter 11 dígitos.");
            txt_agencia.requestFocus();
            return false;
        }
                if (txt_email.getText().isEmpty()) {
            msg_alert("Preencha o campo E-mail.");
            txt_email.requestFocus();
            return false;
        }
       
        return true;
    }
    private void msg_alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
               
        alerta.showAndWait(); //exibe mensagem
    }
}
