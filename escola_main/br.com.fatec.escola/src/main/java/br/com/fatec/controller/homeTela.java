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
public class homeTela extends Application{
    public static Stage tela;
    
    @Override
    public void start(Stage tela) throws IOException {
        setStage(tela);
        
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("view/Cinetec_home.fxml"));
        Parent root = fxmlLoader.load();
       // Cinetec_cadastroController controler = fxmlLoader.getController();
        
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
        tela.getIcons().add(icon);
        tela.setTitle("Cinetec - Filmes");
        tela.setScene(scene);
        tela.show();        

    }
    
    public static void setStage(Stage t) {
        tela = t;
    }
    
}
