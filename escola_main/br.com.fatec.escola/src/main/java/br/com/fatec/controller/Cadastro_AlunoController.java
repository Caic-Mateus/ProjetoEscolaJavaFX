/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CadAlunosDAO;
import br.com.fatec.model.CadAlunos;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caicm
 */
public class Cadastro_AlunoController implements Initializable {

    @FXML
    private TextField txt_cpf;
    @FXML
    private ComboBox<String> cb_Classe;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_responsavel;
    @FXML
    private TextField txt_sexo;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_rg;
    @FXML
    private TextField txt_periodo;
    @FXML
    private TextField txt_ra;
    @FXML
    private Button btn_cadastrar;
    @FXML
    private Button btn_atualizar;
    @FXML
    private Button btn_deletar;
    private CadAlunos cadAlunos = new CadAlunos();
    @FXML
    private Button btn_Buscar;
    @FXML
    private Button btnVoltar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preencherComboBoxDoDAO();
    }    
    public void preencherComboBoxDoDAO() {
        CadAlunosDAO cadAlunosDAO = new CadAlunosDAO();
        cadAlunosDAO.preencherComboBoxClasse(cb_Classe);
    }

    @FXML
    private void btn_cadastrar(ActionEvent event) throws SQLException {
        if (!todosCamposPreenchidos()) {
            return;
        }

        cadAlunos = moveViewToModel();

        CadAlunosDAO alunosDAO = new CadAlunosDAO();

        boolean alunoExiste = alunosDAO.alunoExiste(cadAlunos);

        if(alunoExiste) {
            msg_alert("Não é possível cadastrar mais de um aluno\ncom o mesmo cpf.");
            limparCampos();
        } else {
            
                if(alunosDAO.insertAluno(cadAlunos)){
                    msg_info("Aluno cadastrado com sucesso!");
                limparCampos();}
                else{
                    msg_info("Erro ao cadastrar Aluno!");
                }
            
        }
    }
    @FXML
    private void btn_atualizar(ActionEvent event) {
        CadAlunosDAO AlunosDAO = new CadAlunosDAO();
         
        cadAlunos = moveViewToModel();   
        try {
            if(AlunosDAO.alterCadastro(cadAlunos)){
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
        CadAlunosDAO AlunosDAO = new CadAlunosDAO();
         
        cadAlunos = moveViewToModel();   
        try {
            if(AlunosDAO.removeCadastro(cadAlunos)){
                msg_info("Cadastro excluido.");
                limparCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Deu erro: " + 
                    ex.getMessage());
        } 
    }
    private CadAlunos moveViewToModel(){ //leva da tela para o back
        //CRIA O OBJETO CADASTRO - (MODEL)
        cadAlunos = new CadAlunos();
        cadAlunos.setCpf(txt_cpf.getText());
        cadAlunos.setNome(txt_nome.getText());
        cadAlunos.setSexo(txt_sexo.getText());
        cadAlunos.setResponsavel(txt_responsavel.getText());
        cadAlunos.setEmail(txt_email.getText());
        cadAlunos.setRg(txt_rg.getText());
        cadAlunos.setIdentificacao(cb_Classe.getValue());
        cadAlunos.setPeriodo(txt_periodo.getText());
        cadAlunos.setRa(txt_ra.getText());
        

        return cadAlunos;
    }
    
    private void moveModelToView(String cpf) { //leva o back para a tela
        try {
            CadAlunosDAO alunosDAO = new CadAlunosDAO();
            CadAlunos alunos = alunosDAO.buscarAluno(cpf);
            if (alunos != null) {
                txt_cpf.setText(String.valueOf(alunos.getCpf()));
                txt_nome.setText(String.valueOf(alunos.getNome()));
                txt_sexo.setText(String.valueOf(alunos.getSexo()));
                txt_responsavel.setText(String.valueOf(alunos.getResponsavel()));
                txt_email.setText(String.valueOf(alunos.getEmail()));
                txt_rg.setText(String.valueOf(alunos.getRg()));
                cb_Classe.setValue(alunos.getIdentificacao());
                txt_periodo.setText(String.valueOf(alunos.getPeriodo()));
                txt_ra.setText(String.valueOf(alunos.getRa()));
                
                

                // Carregar a imagem usando o caminho e definir no ImageView
            } else {
                msg_alert("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/icon.png").toString()));
        
        alerta.showAndWait(); //exibe mensagem
    }
    private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txt_nome.getText().isEmpty()) {
            msg_alert("Preencha o campo Nome.");
            txt_nome.requestFocus();
            return false;
        }
        

        return true;
    }
    
    private void limparCampos() {
        txt_nome.clear();
        txt_cpf.clear();
        txt_sexo.clear();
        txt_responsavel.clear();
        txt_email.clear();
        txt_rg.clear();
        txt_periodo.clear();
        txt_ra.clear();


    }

    @FXML
    private void btn_Buscar(ActionEvent event) {
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
}
