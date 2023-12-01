/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.model.Poltronas;
import br.com.fatec.DAO.PoltronaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class Cinetec_poltronasController implements Initializable {

    @FXML
    private CheckBox A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, 
                    B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, 
                    C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, 
                    D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, 
                    E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, 
                    F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, 
                    G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, 
                    H1, H2, H3, H4, H5, H6, H7, H8, H9, H10;

    @FXML
    private Button btn_prosseguir;
    
    private Poltronas poltronas = new Poltronas();
    
    private String horaSelecionada;
    private String nomeFilme;
    @FXML
    private Button btnVoltar;
    @FXML
    private Hyperlink btnSair;
    
    public void receberHoraSelecionada(String hora) {
        this.horaSelecionada = hora;
    }
    
    public void receberFilmeSelecionada(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  

    public void setHoraSelecionada(String hora, String nomeFilme) {
        this.horaSelecionada = hora;
        this.nomeFilme = nomeFilme;
        mCarregaPoltronas();
    }


    private void mCarregaPoltronas() {
        try {
            PoltronaDAO poltronasDAO = new PoltronaDAO();
            List<String> poltronasCompradas = poltronasDAO.getPoltronasCompradas(this.horaSelecionada);
            System.out.println(horaSelecionada);
            System.out.println(nomeFilme);
            List<CheckBox> checkBoxes = getCheckBoxes();

            for (CheckBox checkBox : checkBoxes) {
                String idCheckBox = checkBox.getId();
                String poltronasString = String.join(", ", poltronasCompradas);
                poltronasString = poltronasString.replace("[", "").replace("]", "");

                if (poltronasString.contains(idCheckBox)) {
                    checkBox.setDisable(true);
                    checkBox.setStyle("-fx-background-color: #ff0000;"); // Define a cor de fundo vermelha para as que ja estao compradas
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cinetec_poltronasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btn_prosseguir(ActionEvent event) throws SQLException, IOException {
    moveViewToModel(poltronas);

    List<String> poltronasSelecionadas = getPoltronasSelecionadas();

        if (poltronasSelecionadas.isEmpty()) {
            msg_info("Você não selecionou nenhuma poltrona.");
            
        } else {
            String poltronasConcatenadas = String.join(", ", poltronasSelecionadas);
            msg_info("Você selecionou as poltronas: " + poltronasConcatenadas);
            
            //GRAVANDO COMPRA
            PoltronaDAO poltronas = new PoltronaDAO();
            poltronas.insertVendas(poltronasConcatenadas, horaSelecionada, nomeFilme);
            
            
            //CHAMA TELA RESERVA EFETUADA
            reservaTela reserva = new reservaTela();
            reserva.start(new Stage());
            Stage stage = (Stage) btn_prosseguir.getScene().getWindow();
            stage.close();
        }  
    }
    
    private List<String> getPoltronasSelecionadas() {
    List<String> poltronasSelecionadas = new ArrayList<>();
    char row = 'A';

    
        for (char i = row; i <= 'H'; i++) {
            for (int j = 1; j <= 10; j++) {
                String checkBoxId = i + "" + j; 
                CheckBox checkBox = findCheckBoxById(checkBoxId); // Encontra o CheckBox pelo ID

                if (checkBox != null && checkBox.isSelected()) {
                    poltronasSelecionadas.add(checkBoxId.toUpperCase());
                }
            }
        }

        return poltronasSelecionadas;
    }

    private CheckBox findCheckBoxById(String id) {       
        // Mapeamento dos IDs para os CheckBoxes
        Map<String, CheckBox> checkBoxMap = new HashMap<>();
        
        checkBoxMap.put("A1", A1);checkBoxMap.put("A2", A2);checkBoxMap.put("A3", A3);checkBoxMap.put("A4", A4);checkBoxMap.put("A5", A5);checkBoxMap.put("A6", A6);checkBoxMap.put("A7", A7);checkBoxMap.put("A8", A8);checkBoxMap.put("A9", A9);checkBoxMap.put("A10", A10);
        
        checkBoxMap.put("B1", B1); checkBoxMap.put("B2", B2); checkBoxMap.put("B3", B3); checkBoxMap.put("B4", B4); checkBoxMap.put("B5", B5); checkBoxMap.put("B6", B6); checkBoxMap.put("B7", B7); checkBoxMap.put("B8", B8); checkBoxMap.put("B9", B9); checkBoxMap.put("B10", B10);

        checkBoxMap.put("C1", C1); checkBoxMap.put("C2", C2); checkBoxMap.put("C3", C3); checkBoxMap.put("C4", C4); checkBoxMap.put("C5", C5); checkBoxMap.put("C6", C6); checkBoxMap.put("C7", C7); checkBoxMap.put("C8", C8); checkBoxMap.put("C9", C9); checkBoxMap.put("C10", C10);

        checkBoxMap.put("D1", D1); checkBoxMap.put("D2", D2); checkBoxMap.put("D3", D3); checkBoxMap.put("D4", D4); checkBoxMap.put("D5", D5); checkBoxMap.put("D6", D6); checkBoxMap.put("D7", D7); checkBoxMap.put("D8", D8); checkBoxMap.put("D9", D9); checkBoxMap.put("D10", D10);

        checkBoxMap.put("E1", E1); checkBoxMap.put("E2", E2); checkBoxMap.put("E3", E3); checkBoxMap.put("E4", E4); checkBoxMap.put("E5", E5); checkBoxMap.put("E6", E6); checkBoxMap.put("E7", E7); checkBoxMap.put("E8", E8); checkBoxMap.put("E9", E9); checkBoxMap.put("E10", E10);

        checkBoxMap.put("F1", F1); checkBoxMap.put("F2", F2); checkBoxMap.put("F3", F3); checkBoxMap.put("F4", F4); checkBoxMap.put("F5", F5); checkBoxMap.put("F6", F6); checkBoxMap.put("F7", F7); checkBoxMap.put("F8", F8); checkBoxMap.put("F9", F9); checkBoxMap.put("F10", F10);

        checkBoxMap.put("G1", G1); checkBoxMap.put("G2", G2); checkBoxMap.put("G3", G3); checkBoxMap.put("G4", G4); checkBoxMap.put("G5", G5); checkBoxMap.put("G6", G6); checkBoxMap.put("G7", G7); checkBoxMap.put("G8", G8); checkBoxMap.put("G9", G9); checkBoxMap.put("G10", G10);

        checkBoxMap.put("H1", H1); checkBoxMap.put("H2", H2); checkBoxMap.put("H3", H3); checkBoxMap.put("H4", H4); checkBoxMap.put("H5", H5); checkBoxMap.put("H6", H6); checkBoxMap.put("H7", H7); checkBoxMap.put("H8", H8); checkBoxMap.put("H9", H9); checkBoxMap.put("H10", H10);
          
        return checkBoxMap.get(id);
    }
    
    private List<CheckBox> getCheckBoxes() {
        List<CheckBox> checkBoxes = new ArrayList<>();
        
        checkBoxes.add(A1); checkBoxes.add(A2); checkBoxes.add(A3); checkBoxes.add(A4); checkBoxes.add(A5); checkBoxes.add(A6); checkBoxes.add(A7); checkBoxes.add(A8); checkBoxes.add(A9); checkBoxes.add(A10);
        
        checkBoxes.add(B1); checkBoxes.add(B2); checkBoxes.add(B3); checkBoxes.add(B4); checkBoxes.add(B5); checkBoxes.add(B6); checkBoxes.add(B7); checkBoxes.add(B8); checkBoxes.add(B9); checkBoxes.add(B10);

        checkBoxes.add(C1); checkBoxes.add(C2); checkBoxes.add(C3); checkBoxes.add(C4); checkBoxes.add(C5); checkBoxes.add(C6); checkBoxes.add(C7); checkBoxes.add(C8); checkBoxes.add(C9); checkBoxes.add(C10);

        checkBoxes.add(D1); checkBoxes.add(D2); checkBoxes.add(D3); checkBoxes.add(D4); checkBoxes.add(D5); checkBoxes.add(D6); checkBoxes.add(D7); checkBoxes.add(D8); checkBoxes.add(D9); checkBoxes.add(D10);

        checkBoxes.add(E1); checkBoxes.add(E2); checkBoxes.add(E3); checkBoxes.add(E4); checkBoxes.add(E5); checkBoxes.add(E6); checkBoxes.add(E7); checkBoxes.add(E8); checkBoxes.add(E9); checkBoxes.add(E10);

        checkBoxes.add(F1); checkBoxes.add(F2); checkBoxes.add(F3); checkBoxes.add(F4); checkBoxes.add(F5); checkBoxes.add(F6); checkBoxes.add(F7); checkBoxes.add(F8); checkBoxes.add(F9); checkBoxes.add(F10);

        checkBoxes.add(G1); checkBoxes.add(G2); checkBoxes.add(G3); checkBoxes.add(G4); checkBoxes.add(G5); checkBoxes.add(G6); checkBoxes.add(G7); checkBoxes.add(G8); checkBoxes.add(G9); checkBoxes.add(G10);

        checkBoxes.add(H1); checkBoxes.add(H2); checkBoxes.add(H3); checkBoxes.add(H4); checkBoxes.add(H5); checkBoxes.add(H6); checkBoxes.add(H7); checkBoxes.add(H8); checkBoxes.add(H9); checkBoxes.add(H10);

        return checkBoxes;
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
    
    private void moveViewToModel(Poltronas poltronas) {
        poltronas.setPoltronasSelecionadas(getPoltronasSelecionadas());
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
        homeTela home = new homeTela();
        home.start(new Stage());
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
