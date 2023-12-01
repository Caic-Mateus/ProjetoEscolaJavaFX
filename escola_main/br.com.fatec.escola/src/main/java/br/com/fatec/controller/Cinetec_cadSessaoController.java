package br.com.fatec.controller;

import br.com.fatec.model.CadSessao;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Cinetec_cadSessaoController implements Initializable {

    @FXML
    private Button btn_cadastro;
    @FXML
    private TextField txt_filme;
    @FXML
    private TextField txt_nsala;
    @FXML
    private TextField txt_horaSessao;
    @FXML
    private TextField txt_filmeDuracao;
    @FXML
    private TextField txt_classificacao;
    @FXML
    private TextField txt_genero;
    @FXML
    private ComboBox<CadSessao> cbSelecionar;
    @FXML
    private Button btn_deletar;
    @FXML
    private Button btn_alterar;
    @FXML
    private Button btn_exibir;

    private ObservableList<CadSessao> sessoes = FXCollections.observableArrayList();
    @FXML
    private Button btnVoltar;
    @FXML
    private Hyperlink btnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Define a fonte de dados da ComboBox
        cbSelecionar.setItems(sessoes);
    }

    @FXML
    private void btn_cadastro(ActionEvent event) {
        if (todosCamposPreenchidos()) {
            CadSessao sessao = moveViewParaModel();
            sessoes.add(sessao);
            limparCampos();
            mensagem("Sessão cadastrada com sucesso!");
        } else {
            mensagem("Preencha todos os campos.");
        }
    }

    @FXML
    private void btn_deletar(ActionEvent event) {
        CadSessao selectedSessao = cbSelecionar.getValue();
        if (selectedSessao != null) {
            sessoes.remove(selectedSessao);
            limparCampos();
            mensagem("Sessão removida com sucesso!");
        } else {
            mensagem("Selecione uma sessão para excluir.");
        }
    }

    @FXML
    private void btn_alterar(ActionEvent event) {
        CadSessao selectedSessao = cbSelecionar.getValue();
        if (selectedSessao != null) {
            CadSessao novaSessao = moveViewParaModel();
            limparCampos();
            sessoes.set(sessoes.indexOf(selectedSessao), novaSessao);
            mensagem("Sessão alterada com sucesso!");
        } else {
            mensagem("Selecione uma sessão para alterar.");
        }
    }

    @FXML
    private void btn_exibir(ActionEvent event) {
        CadSessao selectedSessao = cbSelecionar.getValue();
        if (selectedSessao != null) {
            moveModelParaView(selectedSessao);
        } else {
            mensagem("Selecione uma sessão para exibir.");
        }
    }

    private CadSessao moveViewParaModel() {
        CadSessao sessao = new CadSessao();
        sessao.setNumeroSala(txt_nsala.getText());
        sessao.setCodigoFilme(txt_filme.getText());
        sessao.setHorarioSessao(txt_horaSessao.getText());
        sessao.setDuracaoFilme(txt_filmeDuracao.getText());
        sessao.setGenero(txt_genero.getText());
        sessao.setClassificacao(txt_classificacao.getText());
        return sessao;
    }

    private void moveModelParaView(CadSessao sessao) {
        txt_nsala.setText(sessao.getNumeroSala());
        txt_filme.setText(sessao.getCodigoFilme());
        txt_horaSessao.setText(sessao.getHorarioSessao());
        txt_filmeDuracao.setText(sessao.getDuracaoFilme());
        txt_genero.setText(sessao.getGenero());
        txt_classificacao.setText(sessao.getClassificacao());

        // Defina a seleção no ComboBox
        cbSelecionar.getSelectionModel().select(sessao);
    }

    private boolean todosCamposPreenchidos() {
        return !txt_nsala.getText().isEmpty()
                && !txt_filme.getText().isEmpty()
                && !txt_horaSessao.getText().isEmpty()
                && !txt_filmeDuracao.getText().isEmpty()
                && !txt_genero.getText().isEmpty()
                && !txt_classificacao.getText().isEmpty();
    }

    private void limparCampos() {
        txt_nsala.clear();
        txt_filme.clear();
        txt_horaSessao.clear();
        txt_filmeDuracao.clear();
        txt_genero.clear();
        txt_classificacao.clear();
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
        menuTela menu= new menuTela();
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
