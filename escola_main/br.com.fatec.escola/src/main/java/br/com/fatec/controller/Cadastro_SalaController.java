/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.model.CadSala;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class Cadastro_SalaController implements Initializable {

    @FXML
    private TextField txt_computador;
    @FXML
    private TextField txt_capacidade;
    @FXML
    private Button btn_cadastrar;
    @FXML
    private Button btn_atualizar;
    @FXML
    private Button btn_deletar;
    @FXML
    private Button btn_buscarSala;
    @FXML
    private TextField txt_audio;
    @FXML
    private TextField txt_acessibilidade;
    @FXML
    private TextField txt_andar;
    
    @FXML
    private ComboBox<CadSala> cbSelecionar;
    private ObservableList<CadSala> salas = FXCollections.observableArrayList();
    
    @FXML
    private TextField txt_identificacao;
    @FXML
    private Button btnVoltar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbSelecionar.setItems(salas);
    }    

    @FXML
    private void cadastrarSala(ActionEvent event) {
        if (todosCamposPreenchidos()) {
            CadSala sala = moveViewParaModel();
            salas.add(sala);
            limparCampos();
            mensagem("Sessão cadastrada com sucesso!");
        } else {
            mensagem("Preencha todos os campos.");
        }
    }

    @FXML
    private void atualizarSala(ActionEvent event) {
        CadSala selectedSala = cbSelecionar.getValue();
        if (selectedSala != null) {
            CadSala novaSessao = moveViewParaModel();
            limparCampos();
            salas.set(salas.indexOf(selectedSala), novaSessao);
            mensagem("Sala alterada com sucesso!");
        } else {
            mensagem("Selecione uma sala para alterar.");
        }
    }

    @FXML
    private void DeletarSala(ActionEvent event) {
        CadSala selectedSala = cbSelecionar.getValue();
        if (selectedSala != null) {
            salas.remove(selectedSala);
            limparCampos();
            mensagem("Sala removida com sucesso!");
        } else {
            mensagem("Selecione uma sala para excluir.");
        }

    }

    @FXML
    private void btn_buscar(ActionEvent event) {
        CadSala salaSelecionada = cbSelecionar.getValue();
        if (salaSelecionada != null) {
            moveModelParaView(salaSelecionada);
        } else {
            mensagem("Selecione uma sessão para exibir.");
        }
    }
    
    private CadSala moveViewParaModel() {
        CadSala sala = new CadSala();
        sala.setIdentificacao(txt_identificacao.getText());
        sala.setAcessibilidade(txt_acessibilidade.getText());
        sala.setAndar(txt_andar.getText());
        sala.setAudio(txt_audio.getText());
        sala.setCapacidade(txt_capacidade.getText());
        sala.setComputador(txt_computador.getText());
        
        return sala;
    }

    private void moveModelParaView(CadSala sala) {
        txt_identificacao.setText(sala.getIdentificacao());
        txt_acessibilidade.setText(sala.getAcessibilidade());
        txt_andar.setText(sala.getAndar());
        txt_audio.setText(sala.getAudio());
        txt_capacidade.setText(sala.getCapacidade());
        txt_computador.setText(sala.getComputador());
        
        cbSelecionar.getSelectionModel().select(sala);
    }

    private boolean todosCamposPreenchidos() {
        return !txt_acessibilidade.getText().isEmpty()
                && !txt_andar.getText().isEmpty()
                && !txt_identificacao.getText().isEmpty()
                && !txt_audio.getText().isEmpty()
                && !txt_capacidade.getText().isEmpty()
                && !txt_computador.getText().isEmpty();
    }

    private void limparCampos() {
        txt_identificacao.clear();
        txt_acessibilidade.clear();
        txt_andar.clear();
        txt_audio.clear();
        txt_capacidade.clear();
        txt_computador.clear();
    }

    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
        
        // Get the Stage.
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/imagens/icon.png").toString()));
                
        alerta.showAndWait(); // exibe mensagem
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
        menuTela home = new menuTela();
        home.start(new Stage());
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    
}
