/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import static br.com.fatec.database.Database.connect;
import br.com.fatec.model.CadProfessor;
import br.com.fatec.model.Distribuidora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.ComboBox;

/**
 *
 * @author caicm
 */
public class CadastroProfessorDAO {
    /*


/**
 *
 * @author caicm
 */
    private CadProfessor professor;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    public boolean insertCadastro(CadProfessor dado) throws SQLException{
        boolean inseriu;
            
        try (Connection conn = connect()) {
             
            
            String SQL = "INSERT INTO professor (cpf, nome, sexo, email, rg, salario, banco, agencia, conta) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, dado.getCpf());
            pstmt.setString(2, dado.getNome());
            pstmt.setString(3, dado.getSexo());
            pstmt.setString(4, dado.getEmail());
            pstmt.setString(5, dado.getRg());          
            pstmt.setString(6, dado.getSalario());
            pstmt.setString(7, dado.getBanco());
            pstmt.setString(8, dado.getAgencia());
            pstmt.setString(9, dado.getConta());
            
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
    
 
    public boolean alterCadastro(CadProfessor dado) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "UPDATE professor SET cpf = ?, nome = ?, sexo = ?, email = ?, rg = ?, salario = ?, banco = ?, agencia = ?, conta = ? where cpf = ?"; 
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            // associar os dados do objeto Distribuidora com o comando UPDATE
            pstmt.setString(1, dado.getCpf());
            pstmt.setString(2, dado.getNome());
            pstmt.setString(3, dado.getSexo());
            pstmt.setString(4, dado.getEmail());
            pstmt.setString(5, dado.getRg());          
            pstmt.setString(6, dado.getSalario());
            pstmt.setString(7, dado.getBanco());
            pstmt.setString(8, dado.getAgencia());
            pstmt.setString(9, dado.getConta());
            pstmt.setString(10, dado.getCpf());  
            
            int res = pstmt.executeUpdate(); 

            conn.close();

            // devolve se funcionou ou não
            return res != 0;
        } 
    }
    
    public boolean removeCadastro(CadProfessor dado) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "DELETE FROM professor WHERE cpf = ?"; 
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, dado.getCpf());

            int res = pstmt.executeUpdate(); 

            conn.close();

            return res != 0;
        } 
    }

    
    public boolean contaExiste(CadProfessor dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM professor WHERE cpf = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, dado.getCpf());
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
    
    public CadProfessor buscarProfessor(String cpf) throws SQLException {
        CadProfessor prof = null;

        try (Connection conn = connect()) {
            String sqlQuery = "SELECT nome, agencia, banco, conta, cpf, email, rg, salario, sexo FROM professor WHERE cpf = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, cpf);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                prof = new CadProfessor();
                prof.setNome(rs.getString("nome"));
                prof.setAgencia(rs.getString("agencia"));
                prof.setBanco(rs.getString("banco"));
                prof.setConta(rs.getString("conta"));
                prof.setCpf(rs.getString("cpf"));
                prof.setEmail(rs.getString("email"));
                prof.setRg(rs.getString("rg"));
                prof.setSalario(rs.getString("salario"));
                prof.setSexo(rs.getString("sexo"));
                
            }
        conn.close();
        }
        return prof;
    }
}


