/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import static br.com.fatec.database.Database.connect;
import br.com.fatec.model.Cadastrar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Larica
 */
public class LoginDAO {

    //variaveis auxiliares
    private Cadastrar cadastro;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    
    public void insertlogin(Cadastrar dado) throws SQLException{
        try (Connection conn = connect()) {
             
            
            String SQL = "INSERT INTO TBL_LOGIN (email, senha, nome) "
                    + "VALUES(?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, dado.getEmail());
            pstmt.setString(2, dado.getSenha());
            pstmt.setString(3, dado.getNome());
            
            //executa comando
            pstmt.executeUpdate();
            
            //fecha conexão
            conn.close();
        }
    }
    
    
    public int verificaLogin(String email, String senha) throws SQLException {
        int rowCount = 0;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM LOGIN WHERE cpf = ? AND SENHA = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, email);
                pstmt.setString(2, senha);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    rowCount = rs.getInt(1);
                }
            }
        }

        return rowCount;
    }
    
    public void selectNomeCelular(String email) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "SELECT cpf FROM login WHERE cpf = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String nome = rs.getString("cpf");
                    String celular = rs.getString("cpf");

                    // Realiza a inserção na tabela TBL_LOG_ACESSOS
                    //insertIntoLogAcessos(conn, email, nome, celular);
                }
            }
        }
    }

    private void insertIntoLogAcessos(Connection conn, String email, String nome, String celular) throws SQLException {
        String insertSQL = "INSERT INTO TBL_LOG_ACESSOS (EMAIL, NOME_CLIENTE, NUMERO_CLIENTE) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, nome);
            pstmt.setString(3, celular);
            pstmt.executeUpdate();
        }
    }   
}



