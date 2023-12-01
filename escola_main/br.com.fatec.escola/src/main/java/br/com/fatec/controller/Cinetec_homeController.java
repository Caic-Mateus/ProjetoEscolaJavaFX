/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;


import br.com.fatec.model.Home;
import br.com.fatec.model.Poltronas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class Cinetec_homeController implements Initializable {

    @FXML
    private Button btnUma;
    @FXML
    private Button btnOnze;
    @FXML
    private Button btnTres;
    @FXML
    private Button btnCinco;
    @FXML
    private Button btnDezEmeia;
    @FXML
    private Button btnSeteEDez;

    private Poltronas poltronas = new Poltronas();
    private Home home = new Home();
    @FXML
    private Hyperlink btnSair;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    
    private Home moveViewToModel(){ //leva da tela para o back
        //CRIA O OBJETO CADASTRO - (MODEL)
        home = new Home();
        
        home.setOnze(btnOnze.getText());
        home.setUma(btnUma.getText());
        home.setTres(btnTres.getText());
        home.setCinco(btnCinco.getText());
        home.setSete(btnSeteEDez.getText());
        home.setDez(btnDezEmeia.getText());

        return home;
    }
    
    @FXML
    private void btnOnze(ActionEvent event) throws IOException {
        String hora = btnOnze.getText(); // Obtém a hora do botão clicado
        String nomeFilme = "Elementos";
        poltronasTela p = new poltronasTela();
        p.receberHoraSelecionada(hora); // Passando a hora selecionada para o controlador das poltronas
        p.receberFilmeSelecionada(nomeFilme); // Definindo o nome do filme
        p.start(new Stage()); // Exibindo a tela após configurar a hora
        Stage stage = (Stage) btnUma.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void btnUma(ActionEvent event) throws IOException {
        String hora = btnUma.getText(); // Obtém a hora do botão clicado
        String nomeFilme = "Elementos";// Substitua por um nome de filme específico
        poltronasTela p = new poltronasTela();
        p.receberHoraSelecionada(hora); // Passando a hora selecionada para o controlador das poltronas
        p.receberFilmeSelecionada(nomeFilme); // Definindo o nome do filme
        p.start(new Stage()); // Exibindo a tela após configurar a hora
        Stage stage = (Stage) btnUma.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnTres(ActionEvent event) throws IOException {
        String hora = btnTres.getText(); // Obtém a hora do botão clicado
        String nomeFilme = "Guerra Mundial Z";
        poltronasTela p = new poltronasTela();
        p.receberHoraSelecionada(hora); // Passando a hora selecionada para o controlador das poltronas
        p.receberFilmeSelecionada(nomeFilme); // Definindo o nome do filme
        p.start(new Stage()); // Exibindo a tela após configurar a hora
        Stage stage = (Stage) btnUma.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnCinco(ActionEvent event) throws IOException {
        String hora = btnCinco.getText(); // Obtém a hora do botão clicado
        String nomeFilme = "Guerra Mundial Z";
        poltronasTela p = new poltronasTela();
        p.receberHoraSelecionada(hora); // Passando a hora selecionada para o controlador das poltronas
        p.receberFilmeSelecionada(nomeFilme); // Definindo o nome do filme
        p.start(new Stage()); // Exibindo a tela após configurar a hora
        Stage stage = (Stage) btnUma.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void btnDezEmeia(ActionEvent event) throws IOException {
        String hora = btnDezEmeia.getText(); // Obtém a hora do botão clicado
        String nomeFilme = "Django Livre";
        poltronasTela p = new poltronasTela();
        p.receberHoraSelecionada(hora); // Passando a hora selecionada para o controlador das poltronas
        p.receberFilmeSelecionada(nomeFilme); // Definindo o nome do filme
        p.start(new Stage()); // Exibindo a tela após configurar a hora
        Stage stage = (Stage) btnUma.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSeteEDez(ActionEvent event) throws IOException {
        String hora = btnSeteEDez.getText(); // Obtém a hora do botão clicado
        String nomeFilme = "Avatar: O caminho da Água";
        poltronasTela p = new poltronasTela();
        p.receberHoraSelecionada(hora); // Passando a hora selecionada para o controlador das poltronas
        p.receberFilmeSelecionada(nomeFilme); // Definindo o nome do filme
        p.start(new Stage()); 
        Stage stage = (Stage) btnUma.getScene().getWindow();
        stage.close();
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

    @FXML
    private void btnSair(ActionEvent event) throws IOException {
        loginTela log = new loginTela();
        log.start(new Stage());
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}
