/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CadFilmesDAO;
import br.com.fatec.model.CadFilmes;
import java.io.IOException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class Cinetec_listaController implements Initializable {

    @FXML
    private TableView<CadFilmes> tbLista;
    @FXML
    private TableColumn<CadFilmes, Integer> colNome;
    @FXML
    private TableColumn<CadFilmes, String> colGenero;
    @FXML
    private TableColumn<CadFilmes, String> colClassificacao;
    @FXML
    private TableColumn<CadFilmes, String> colSinopse;
    @FXML
    private TableColumn<CadFilmes, String> colDistribuidora;
    @FXML
    private TableColumn<CadFilmes, String> colImagem;
    @FXML
    private TextField txtBusca;
    
     private CadFilmesDAO filmesDAO;
    @FXML
    private Button btnVoltar;
    @FXML
    private Hyperlink btnSair;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmesDAO = new CadFilmesDAO(); // Inicializa o DAO

        // Configura as colunas para exibição dos dados
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colClassificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        colSinopse.setCellFactory(column -> new WrappingTextCell<>());
        colSinopse.setCellValueFactory(new PropertyValueFactory<>("sinopse"));
        colDistribuidora.setCellValueFactory(new PropertyValueFactory<>("distribuidora"));
        colImagem.setCellFactory(column -> new ImageTableCell<>());
        colImagem.setCellValueFactory(new PropertyValueFactory<>("image"));


        // Chama o método para carregar os dados do banco na TableView
        try {
            Collection<CadFilmes> filmes = filmesDAO.lista(null); // Pode passar um critério de filtro, se necessário
            tbLista.setItems(FXCollections.observableArrayList(filmes));
        } catch (SQLException e) {
            e.printStackTrace();
            // Lida com exceções, se necessário
        }
       
        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            ObservableList<CadFilmes> filmes;

            if (newValue == null || newValue.isEmpty()) {
                filmes = FXCollections.observableArrayList(filmesDAO.lista("")); // Busca todos os filmes se o campo estiver vazio
            } else {
                filmes = FXCollections.observableArrayList(filmesDAO.lista("nome LIKE '%" + newValue + "%'")); // Busca filmes baseado no texto do campo
            }

            tbLista.setItems(filmes);

        } catch (SQLException ex) {
            Logger.getLogger(Cinetec_listaController.class.getName()).log(Level.SEVERE, "Erro ao buscar filmes", ex);
        }
    });

    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
       menuTela menu = new menuTela();
       menu.start(new Stage());
       Stage stage = (Stage) btnVoltar.getScene().getWindow();
       stage.close();
        
    }

    @FXML
    private void tbLista(SortEvent<CadFilmes> event) {
    }
        

    public class ImageTableCell<CadFilmes> extends TableCell<CadFilmes, String> {
        private final ImageView imageView = new ImageView();

        public ImageTableCell() {
            setGraphic(imageView);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(String imagePath, boolean empty) {
            super.updateItem(imagePath, empty);

            if (empty || imagePath == null || imagePath.isEmpty()) {
                imageView.setImage(null);
                imageView.setFitWidth(60); // Define a largura da imagem
                imageView.setFitHeight(60); // Define a altura da imagem
            } else {
                Image image = new Image("file:" + imagePath);
                imageView.setImage(image);
                imageView.setFitWidth(60); // Define a largura da imagem
                imageView.setFitHeight(60); // Define a altura da imagem
            }
        }
    }


    public class WrappingTextCell<CadFilmes, String> extends TableCell<CadFilmes, String> {
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
    private void btnSair(ActionEvent event) throws IOException {
        loginTela log = new loginTela();
        log.start(new Stage());
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
    

   
    
}
