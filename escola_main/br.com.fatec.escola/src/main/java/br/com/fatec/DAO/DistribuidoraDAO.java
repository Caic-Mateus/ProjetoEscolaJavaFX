/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import static br.com.fatec.database.Database.connect;
import br.com.fatec.model.Distribuidora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Leonardo
 */
public class DistribuidoraDAO {
    //variaveis auxiliares
    private Distribuidora distribuidora;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    public boolean insertCadastro(Distribuidora dado) throws SQLException{
        boolean inseriu;
            
        try (Connection conn = connect()) {
             
            
            String SQL = "INSERT INTO TBL_CAD_DISTRIBUIDORA (nome, cnpj, responsavel, email, celular, whats) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, dado.getNome());
            pstmt.setString(2, dado.getCnpj());
            pstmt.setString(3, dado.getResponsavel());
            pstmt.setString(4, dado.getEmail());
            pstmt.setString(5, dado.getCelular());          
            pstmt.setString(6, dado.getWhats());
            
            //executa comando
            if(pstmt.executeUpdate() > 0)
                inseriu = true; //tudo certo com a inserção
            else
                inseriu = false; 
            
            //fecha conexão
            conn.close();
            return inseriu;
        }
    }
    
 
    public boolean alterCadastro(Distribuidora dado) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "UPDATE TBL_CAD_DISTRIBUIDORA SET nome = ?, cnpj = ?, responsavel = ?, email = ?, celular = ?, whats = ? WHERE cnpj = ?"; 
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            // associar os dados do objeto Distribuidora com o comando UPDATE
            pstmt.setString(1, dado.getNome());
            pstmt.setString(2, dado.getCnpj());
            pstmt.setString(3, dado.getResponsavel());
            pstmt.setString(4, dado.getEmail());
            pstmt.setString(5, dado.getCelular());
            pstmt.setString(6, dado.getWhats());
            pstmt.setString(7, dado.getCnpj());

            int res = pstmt.executeUpdate(); 

            conn.close();

            // devolve se funcionou ou não
            return res != 0;
        } 
    }
    
    public boolean removeCadastro(Distribuidora dado) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "DELETE FROM TBL_CAD_DISTRIBUIDORA WHERE cnpj = ?"; 
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, dado.getCnpj());

            int res = pstmt.executeUpdate(); 

            conn.close();

            return res != 0;
        } 
    }

    
    public boolean contaExiste(Distribuidora dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM TBL_CAD_DISTRIBUIDORA WHERE cnpj = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, dado.getCnpj());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int rowCount = rs.getInt(1);
                        System.out.println("Número de linhas encontradas: " + rowCount);
                        existe = rowCount > 0;
                    }
                }
            }
        conn.close();
        }

        return existe;
    }
    
    public void preencherComboBox(ComboBox<String> cbSelecionar) {
        try (Connection conn = connect()) {
            // Sua consulta SQL para selecionar os nomes
            String sqlQuery = "SELECT nome FROM TBL_CAD_DISTRIBUIDORA";

            try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        String nome = resultSet.getString("nome");
                        cbSelecionar.getItems().add(nome); // Adiciona cada nome ao ComboBox
                    }
                }
            }
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções, se necessário
        }
    }
    
    public Distribuidora buscarDistribuidora(String nome) throws SQLException {
        Distribuidora distri = null;

        try (Connection conn = connect()) {
            String sqlQuery = "SELECT nome, cnpj, responsavel, email, celular, whats FROM TBL_CAD_DISTRIBUIDORA WHERE nome = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, nome);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                distri = new Distribuidora();
                distri.setNome(rs.getString("nome"));
                distri.setCnpj(rs.getString("cnpj"));
                distri.setResponsavel(rs.getString("responsavel"));
                distri.setEmail(rs.getString("email"));
                distri.setCelular(rs.getString("celular"));
                distri.setWhats(rs.getString("whats"));
            }
        conn.close();
        }
        return distri;
    }
}
