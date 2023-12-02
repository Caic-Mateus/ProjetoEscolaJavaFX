/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CadAlunosDAO;
import br.com.fatec.model.CadAlunos;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author caicm
 */
public class BuscaAlunosController implements Initializable {

    @FXML
    private TextField txtBusca;
    @FXML
    private TableView<CadAlunos> tbLista;
    @FXML
    private TableColumn<?, ?> colCpf;
    @FXML
    private TableColumn<?, ?> colNome;
    @FXML
    private TableColumn<?, ?> colSexo;
    @FXML
    private TableColumn<?, ?> colRg;
    @FXML
    private TableColumn<?, ?> colResponsavel;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> ColClasse;
    @FXML
    private TableColumn<?, ?> colPeriodo;
    @FXML
    private TableColumn<?, ?> colRa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Hyperlink btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         CadAlunosDAO AlunosDAO = new CadAlunosDAO(); // Inicializa o DAO
        // Configura as colunas para exibição dos dados
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        colEmail.setCellFactory(column -> new WrappingTextCell<>());
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        ColClasse.setCellValueFactory(new PropertyValueFactory<>("identificacao"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        colRa.setCellValueFactory(new PropertyValueFactory<>("ra"));


        // Chama o método para carregar os dados do banco na TableView
        try {
            Collection<CadAlunos> alunos = AlunosDAO.lista(null); // Pode passar um critério de filtro, se necessário
            tbLista.setItems(FXCollections.observableArrayList(alunos));
        } catch (SQLException e) {
            e.printStackTrace();
            // Lida com exceções, se necessário
        }
       
        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            ObservableList<CadAlunos> alunos;

            if (newValue == null || newValue.isEmpty()) {
                alunos = FXCollections.observableArrayList(AlunosDAO.lista("")); // Busca todos os alunos se o campo estiver vazio
            } else {
                alunos = FXCollections.observableArrayList(AlunosDAO.lista("nome LIKE '%" + newValue + "%'")); // Busca alunos baseado no texto do campo
            }

            tbLista.setItems(alunos);

        } catch (SQLException ex) {
            Logger.getLogger(Cinetec_listaController.class.getName()).log(Level.SEVERE, "Erro ao buscar filmes", ex);
        }
    });
    }    

    @FXML
    private void tbLista(SortEvent<CadAlunos> event) {
       
    }

    public class WrappingTextCell<CadAlunos, String> extends TableCell<CadAlunos, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                tbLista.setFixedCellSize(80); // Define a altura fixa da célula
                setText(item.toString());
                setWrapText(true); // Habilita a quebra de texto
            }
        }
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }

    @FXML
    private void btnSair(ActionEvent event) {
    }
    
}
