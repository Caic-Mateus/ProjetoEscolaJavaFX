/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CadastroProfessorDAO;
import br.com.fatec.model.CadProfessor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caicm
 */
public class Cadastro_ProfessorController implements Initializable {

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

    private CadProfessor cadProfessor = new CadProfessor();
    @FXML
    private Button btnVoltar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarProfessor(ActionEvent event) throws SQLException{
        cadProfessor = moveViewToModel();
        
        if(!todosCamposPreenchidos()){
            return;
        }
        
        // Verifica se o e-mail é válido
        if (!isValidEmail(txt_email.getText())) {
            msg_alert("O e-mail informado não é válido.");
            return;  // Interrompe a execução do método
        };;
        
        
        //VERIFICANDO      
        CadastroProfessorDAO ProfDAO = new CadastroProfessorDAO();

        boolean contaJaExiste = ProfDAO.contaExiste(cadProfessor);
        
        if(contaJaExiste){
            msg_alert("Já existe um fornecedor cadastrado com esse CNPJ.");
            limparCampos();
            
        } else{
            //GRAVANDO
            try {
                if(ProfDAO.insertCadastro(cadProfessor)){
                    msg_info("Cadastro concluido com sucesso!");  
                    limparCampos();
                }
            } catch (SQLException ex) {
                System.out.println("Deu erro: " + 
                        ex.getMessage());
            }
        }          
    }
    private void msg_info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
        
        alerta.setContentText("");
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/Logo.png").toString()));
                       
        alerta.showAndWait(); //exibe mensagem
    }
    private void limparCampos() {
        txt_nome.clear();
        txt_agencia.clear();
        txt_banco.clear();
        txt_conta.clear();
        txt_cpf.clear();
        txt_email.clear();
        txt_rg.clear();
        txt_salario.clear();
        txt_sexo.clear();
    }
        private void moveModelToView(String nome) {
        try {
            CadastroProfessorDAO ProfDAO = new CadastroProfessorDAO();
            CadProfessor cadProfessor = ProfDAO.buscarProfessor(nome);
            if (cadProfessor != null) {
                txt_nome.setText(String.valueOf(cadProfessor.getNome()));
                txt_agencia.setText(cadProfessor.getAgencia());
                txt_banco.setText(String.valueOf(cadProfessor.getBanco()));
                txt_conta.setText(cadProfessor.getConta());
                txt_cpf.setText(String.valueOf(cadProfessor.getCpf()));
                txt_email.setText(cadProfessor.getEmail());
                txt_rg.setText(String.valueOf(cadProfessor.getRg()));
                txt_salario.setText(cadProfessor.getSalario());
                txt_sexo.setText(String.valueOf(cadProfessor.getSexo()));
                
            } else {
                msg_alert("Professor não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private void msg_alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
        
        alerta.setContentText("");
        
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/Logo.png").toString()));
                       
        alerta.showAndWait(); //exibe mensagem
    }
        private CadProfessor moveViewToModel(){
        
        //CRIA O OBJETO CADASTRO - (MODEL)
        cadProfessor = new CadProfessor();
        cadProfessor.setNome(txt_nome.getText());
        cadProfessor.setAgencia(txt_agencia.getText());
        cadProfessor.setBanco(txt_banco.getText());
        cadProfessor.setConta(txt_conta.getText());
        cadProfessor.setCpf(txt_cpf.getText());
        cadProfessor.setEmail(txt_email.getText());
        cadProfessor.setRg(txt_rg.getText());
        cadProfessor.setSalario(txt_salario.getText());
        cadProfessor.setSexo(txt_sexo.getText());
        
        //Devolve o model
        return cadProfessor;     
    }
        private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txt_nome.getText().isEmpty()) {
            msg_alert("Preencha o campo Nome.");
            txt_nome.requestFocus();
            return false;
        }
        if (txt_cpf.getText().length() != 11) {
            msg_alert("CPF deve ter 11 digitos.");
            txt_cpf.requestFocus();
            return false;
        }
        if (txt_banco.getText().isEmpty()) {
            msg_alert("Preencha o nome do banco.");
            txt_banco.requestFocus();
            return false;
        }
        if (txt_conta.getText().isEmpty()) {
            msg_alert("Preencha o numero da conta.");
            txt_banco.requestFocus();
            return false;
        }
        if (txt_email.getText().isEmpty()) {
            msg_alert("Preencha o campo E-mail.");
            txt_email.requestFocus();
            return false;
        }
        if (txt_rg.getText().isEmpty()) {
            msg_alert("Preencha o RG.");
            txt_salario.requestFocus();
            return false;
        }
        if (txt_sexo.getText().length() !=1) {
            msg_alert("Preencha o sexo com M ou F.");
            txt_salario.requestFocus();
            return false;
        }
        if (txt_salario.getText().isEmpty()) {
            msg_alert("Preencha o valor do salário.");
            txt_salario.requestFocus();
            return false;
        }

        return true;
    }
    
    @FXML
    private void atualizarProfessor(ActionEvent event) {
        CadastroProfessorDAO ProfDAO = new CadastroProfessorDAO();
         
        cadProfessor = moveViewToModel();   
        try {
            if(ProfDAO.alterCadastro(cadProfessor)){
                msg_info("Cadastro alterado com sucesso!");
                limparCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Deu erro: " + 
                    ex.getMessage());
        }   
    }

    @FXML
    private void DeletarProfessor(ActionEvent event) {
        CadastroProfessorDAO ProfDAO = new CadastroProfessorDAO();
          
       cadProfessor = moveViewToModel();
        try {
            if(ProfDAO.removeCadastro(cadProfessor)){
                msg_info("Cadastro excluido.");
                limparCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Deu erro: " + 
                    ex.getMessage());
        } 
    }

    @FXML
    private void btn_buscar(ActionEvent event) {
        String cpfSelecionado = txt_cpf.getText();
        if (cpfSelecionado != null) {
            moveModelToView(cpfSelecionado);
        } else {
            msg_alert("Selecione algum dado.");
        }
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
        menuTela home = new menuTela();
        home.start(new Stage());
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    public boolean isValidEmail(String txt_email) {
        String regexPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(txt_email);
        return matcher.matches();
    }
}
