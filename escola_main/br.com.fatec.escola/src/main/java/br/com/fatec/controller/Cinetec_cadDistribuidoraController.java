/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.DistribuidoraDAO;
import br.com.fatec.model.Distribuidora;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class Cinetec_cadDistribuidoraController implements Initializable {

    @FXML
    private Label lblNome;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_responsavel;
    @FXML
    private TextField txt_cnpj;
    @FXML
    private TextField txt_nome;
    @FXML
    private Button btn_cadastro;
    @FXML
    private TextField txt_celular;
    @FXML
    private TextField txt_whats;
    @FXML
    private Button btn_alterar;
    @FXML
    private Button btn_deletar;
    @FXML
    private ComboBox<String> cbSelecionar;
    @FXML
    private Button btn_exibir;
    
    
    private Distribuidora distribuidora = new Distribuidora();
    @FXML
    private Button btnVoltar;
    @FXML
    private Hyperlink btnSair;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         preencherComboBoxDoDAO();
    }    

    @FXML
    private void btn_cadastro(ActionEvent event) throws SQLException {
        
        distribuidora = moveViewToModel();
        
        if(!todosCamposPreenchidos()){
            return;
        }
        
        // Verifica se o e-mail é válido
        if (!isValidEmail(txt_email.getText())) {
            msg_alert("O e-mail informado não é válido.");
            return;  // Interrompe a execução do método
        }
        
        
        //VERIFICANDO      
        DistribuidoraDAO DistriDAO = new DistribuidoraDAO();

        boolean contaJaExiste = DistriDAO.contaExiste(distribuidora);
        
        if(contaJaExiste){
            msg_alert("Já existe um fornecedor cadastrado com esse CNPJ.");
            limparCampos();
            
        } else{
            //GRAVANDO
            try {
                if(DistriDAO.insertCadastro(distribuidora)){
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
    
    private Distribuidora moveViewToModel(){
        
        //CRIA O OBJETO CADASTRO - (MODEL)
        distribuidora = new Distribuidora();
        distribuidora.setNome(txt_nome.getText());
        distribuidora.setCnpj(txt_cnpj.getText());
        distribuidora.setResponsavel(txt_responsavel.getText());
        distribuidora.setEmail(txt_email.getText());
        distribuidora.setCelular(txt_celular.getText());
        distribuidora.setWhats(txt_whats.getText());
                   
        //Devolve o model
        return distribuidora;     
    }
    
    private void moveModelToView(String nome) {
        try {
            DistribuidoraDAO DistriDAO = new DistribuidoraDAO();
            Distribuidora distribuidora = DistriDAO.buscarDistribuidora(nome);
            if (distribuidora != null) {
                txt_nome.setText(String.valueOf(distribuidora.getNome()));
                txt_cnpj.setText(distribuidora.getCnpj());
                txt_responsavel.setText(String.valueOf(distribuidora.getResponsavel()));
                txt_email.setText(distribuidora.getEmail());
                txt_celular.setText(distribuidora.getCelular());
                txt_whats.setText(distribuidora.getWhats());
            } else {
                msg_alert("Produto não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isValidEmail(String txt_email) {
        String regexPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(txt_email);
        return matcher.matches();
    }
    
    public void preencherComboBoxDoDAO() {
        DistribuidoraDAO DistriDAO = new DistribuidoraDAO();
        DistriDAO.preencherComboBox(cbSelecionar);
    }

    @FXML
    private void btn_alterar(ActionEvent event) throws SQLException {
        DistribuidoraDAO DistriDAO = new DistribuidoraDAO();
         
        distribuidora = moveViewToModel();   
        try {
            if(DistriDAO.alterCadastro(distribuidora)){
                msg_info("Cadastro alterado com sucesso!");
                limparCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Deu erro: " + 
                    ex.getMessage());
        }   
    }

    @FXML
    private void btn_deletar(ActionEvent event) {
       DistribuidoraDAO DistriDAO = new DistribuidoraDAO();
          
       distribuidora = moveViewToModel();
        try {
            if(DistriDAO.removeCadastro(distribuidora)){
                msg_info("Cadastro excluido.");
                limparCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Deu erro: " + 
                    ex.getMessage());
        } 
    }
    
    @FXML
    private void btn_exibir(ActionEvent event) {
        String nomeSelecionado = cbSelecionar.getValue();
        if (nomeSelecionado != null) {
            moveModelToView(nomeSelecionado);
        } else {
            msg_alert("Selecione algum dado.");
        }
        
    }

    @FXML
    private void cbSelecionar(ActionEvent event) {
        
    
    }
    
    private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txt_nome.getText().isEmpty()) {
            msg_alert("Preencha o campo Nome.");
            txt_nome.requestFocus();
            return false;
        }
        if (txt_cnpj.getText().length() != 14) {
            msg_alert("O CNPJ deve ter 14 dígitos.");
            txt_cnpj.requestFocus();
            return false;
        }
        if (txt_responsavel.getText().isEmpty()) {
            msg_alert("Preencha o campo Responsável.");
            txt_responsavel.requestFocus();
            return false;
        }
        if (txt_email.getText().isEmpty()) {
            msg_alert("Preencha o campo E-mail.");
            txt_email.requestFocus();
            return false;
        }
        if (txt_celular.getText().length() != 11) {
            msg_alert("O número de celular deve ter 11 dígitos.");
            txt_celular.requestFocus();
            return false;
        }
        if (txt_whats.getText().length() != 11) {
            msg_alert("O número do WhatsApp deve ter 11 dígitos.");
            txt_whats.requestFocus();
            return false;
        }

        return true;
    }
    
    private void limparCampos() {
        txt_nome.clear();
        txt_cnpj.clear();
        txt_responsavel.clear();
        txt_email.clear();
        txt_celular.clear();
        txt_whats.clear();
        cbSelecionar.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
        menuTela menu = new menuTela();
        menu.start(new Stage());
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

  @FXML
    private void btnSair(ActionEvent event) throws IOException {
        loginTela log = new loginTela();
        log.start(new Stage());
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
} 
