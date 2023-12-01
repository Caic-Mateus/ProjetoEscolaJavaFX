/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.controller;


import br.com.fatec.Principal;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Leonardo
 */
public class poltronasTela extends Application{
    public static Stage tela;
    
    private String horaSelecionada;
    private String nomeFilme;

    public void receberHoraSelecionada(String hora) {
        this.horaSelecionada = hora;
    }
    
    public void receberFilmeSelecionada(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    
    // Esse método inicia a cena após a definição da hora selecionada
    public void iniciarCena() throws IOException {
        start(new Stage());
    }

    @Override
    public void start(Stage tela) throws IOException {
        setStage(tela);

        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("view/Cinetec_poltronas.fxml"));
        Parent root = fxmlLoader.load();
        Cinetec_poltronasController controller = fxmlLoader.getController();
        
        controller.setHoraSelecionada(this.horaSelecionada, this.nomeFilme); // Defina a hora selecionada antes de carregar as poltronas
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
        tela.getIcons().add(icon);
        tela.setTitle("Cinetec - Poltronas");
        tela.setScene(scene);
        tela.show();
    }
    
    public static void setStage(Stage t) {
        tela = t;
    }  
}
