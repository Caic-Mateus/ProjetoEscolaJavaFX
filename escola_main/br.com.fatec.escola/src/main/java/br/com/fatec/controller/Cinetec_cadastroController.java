/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CadastrarDAO;
import br.com.fatec.DAO.LoginDAO;
import br.com.fatec.model.Cadastrar;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Caic
 */
public class Cinetec_cadastroController implements Initializable {

    @FXML
    private Button btn_cadastro;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_apelido;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_celular;
    @FXML
    private DatePicker txt_bday;
    @FXML
    private ComboBox<String> cb_regiao;
    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_senha;
    @FXML
    private PasswordField txt_rsenha;
    @FXML
    private CheckBox chk_visualizar;
    @FXML
    private ImageView imgCadUsu;
    @FXML
    private Button btnVoltar;
    
    private Cadastrar cadastro = new Cadastrar(); //indica nosso model

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_regiao.getItems().addAll("Zona Norte","Zona Sul", "Zona Leste", "Zona Oeste", "Centro");
        
    }    
    
    @FXML
    private void btn_cadastro(ActionEvent event) throws SQLException, IOException {
            
        cadastro = moveViewToModel();

        if(!todosCamposPreenchidos()){
            return;
        }

        // Verifica se o e-mail é válido
        if (!isValidEmail(txt_email.getText())) {
            msg_alert("O e-mail informado não é válido.");
            return;  // Interrompe a execução do método
        }
        
        //VERIFICANDO
        LoginDAO LogDAO = new LoginDAO();
        CadastrarDAO CadDAO = new CadastrarDAO();

        boolean contaJaExiste = CadDAO.contaExiste(cadastro);
        
        if(contaJaExiste){
            msg_alert("Já existe uma conta cadastrada com esse e-mail.");
        } else{
            //GRAVANDO
            try {
                if(CadDAO.insertCadastro(cadastro)){
                    msg_info("Cadastro concluido com sucesso!\nAgora você já pode se logar normalmente.");   
                    LogDAO.insertlogin(cadastro);
                    limparCampos();
                    loginTela login = new loginTela();
                    login.start(new Stage());
                    Stage stage = (Stage) btn_cadastro.getScene().getWindow();
                    stage.close();
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
        
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/icon.png").toString()));
                       
        alerta.showAndWait(); //exibe mensagem
    }
    
    private void msg_alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
        
        alerta.setContentText("");
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/icon.png").toString()));
                       
        alerta.showAndWait(); //exibe mensagem
    }

    @FXML
    private void options(ActionEvent event) {
    }

    @FXML
    private void visualizar(ActionEvent event) {
        if(chk_visualizar.isSelected()){
    }
        
    }
    
    public boolean isValidEmail(String txt_email) {
        String regexPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(txt_email);
        return matcher.matches();
    }


    private Cadastrar moveViewToModel(){
        
        //CRIA O OBJETO CADASTRO - (MODEL)
        cadastro = new Cadastrar();
        cadastro.setNome(txt_nome.getText());
        cadastro.setApelido(txt_apelido.getText());
        cadastro.setCpf(txt_cpf.getText());
        cadastro.setCelular(txt_celular.getText());
        cadastro.setRegiao(cb_regiao.getValue());
        cadastro.setEmail(txt_email.getText());
        cadastro.setSenha(txt_senha.getText());
        cadastro.setRsenha(txt_rsenha.getText());
             
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
        if (txt_apelido.getText().isEmpty()) {
            msg_alert("Preencha o campo Apelido.");
            txt_apelido.requestFocus();
            return false;
        }
        if (txt_cpf.getText().length() != 11) {
            msg_alert("O CPF deve ter 11 dígitos.");
            txt_cpf.requestFocus();
            return false;
        }
        if (txt_celular.getText().length() != 11) {
            msg_alert("O número de celular deve ter 11 dígitos.");
            txt_celular.requestFocus();
            return false;
        }
        if (txt_bday.getValue() == null) {
            msg_alert("Selecione a data de nascimento.");
            txt_bday.requestFocus();
            return false;
        }
        if (cb_regiao.getValue() == null) {
            msg_alert("Selecione a região.");
            cb_regiao.requestFocus();
            return false;
        }
        if (txt_email.getText().isEmpty()) {
            msg_alert("Preencha o campo E-mail.");
            txt_email.requestFocus();
            return false;
        }
        if (txt_senha.getText().isEmpty()) {
            msg_alert("Preencha o campo Senha.");
            txt_senha.requestFocus();
            return false;
        }
        if (!txt_rsenha.getText().equals(txt_senha.getText())) {
            msg_alert("As senhas não coincidem.");
            txt_rsenha.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCampos() {
        txt_nome.clear();
        txt_apelido.clear();
        txt_cpf.clear();
        txt_bday.setValue(null);
        cb_regiao.getSelectionModel().clearSelection();
        txt_email.clear();
        txt_senha.clear();
        txt_rsenha.clear();
    }
    
    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
       loginTela log = new loginTela();
       log.start(new Stage());
       Stage stage = (Stage) btnVoltar.getScene().getWindow();
       stage.close();
        
    }
}

