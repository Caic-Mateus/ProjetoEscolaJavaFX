/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import static br.com.fatec.database.Database.connect;
import br.com.fatec.model.CadAlunos;
import br.com.fatec.model.CadFilmes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.control.ComboBox;

/**
 *
 * @author caicm
 */
public class CadAlunosDAO {
    public boolean insertAluno(CadAlunos dado) throws SQLException{
        boolean inseriu;
            
        try (Connection conn = connect()) {
             
            
            String SQL = "INSERT INTO aluno (cpf, nome, sexo, responsavel, email, rg, identificacao, periodo, ra) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, dado.getCpf());
            pstmt.setString(2, dado.getNome());
            pstmt.setString(3, dado.getSexo());
            pstmt.setString(4, dado.getResponsavel());
            pstmt.setString(5, dado.getEmail());
            pstmt.setString(6, dado.getRg());
            pstmt.setString(7, dado.getIdentificacao());
            pstmt.setString(8, dado.getPeriodo());
            pstmt.setString(9, dado.getRa());
            

            if(pstmt.executeUpdate() > 0)
                inseriu = true; //tudo certo com a inserção
            else
                inseriu = false; 
            
            //fecha conexão
            conn.close();
            return inseriu;
        }
        
    }
    public CadAlunos buscarAluno(String cpf) throws SQLException {
        CadAlunos alunos = null;

        try (Connection conn = connect()) {
            String sqlQuery = "SELECT cpf, nome,sexo, responsavel, email, rg, identificacao, periodo, ra FROM aluno WHERE cpf = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, cpf);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                alunos = new CadAlunos();
                alunos.setCpf(rs.getString("cpf"));
                alunos.setNome(rs.getString("nome"));
                alunos.setSexo(rs.getString("sexo"));
                alunos.setResponsavel(rs.getString("responsavel"));
                alunos.setEmail(rs.getString("email"));
                alunos.setRg(rs.getString("rg"));
                alunos.setIdentificacao(rs.getString("identificacao"));
                alunos.setPeriodo(rs.getString("periodo"));
                alunos.setRa(rs.getString("ra"));
                
            }
        conn.close();
        }
        return alunos;
    }
    public boolean alterCadastro(CadAlunos dado) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "UPDATE aluno SET cpf = ?, nome = ?,sexo = ?, responsavel = ?, email = ?, rg = ?, identificacao = ?, periodo = ?, ra = ?"; 
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            // associar os dados do objeto Distribuidora com o comando UPDATE
            pstmt.setString(1, dado.getCpf());
            pstmt.setString(2, dado.getNome());
            pstmt.setString(3, dado.getSexo());
            pstmt.setString(4, dado.getResponsavel());
            pstmt.setString(5, dado.getEmail());
            pstmt.setString(6, dado.getRg());
            pstmt.setString(7, dado.getIdentificacao());
            pstmt.setString(8, dado.getPeriodo());
            pstmt.setString(9, dado.getRa());

            int res = pstmt.executeUpdate(); 

            conn.close();

            // devolve se funcionou ou não
            return res != 0;
        } 
    }
    public boolean removeCadastro(CadAlunos dado) throws SQLException {
        try (Connection conn = connect()) {
            String SQL = "DELETE FROM aluno WHERE cpf = ?"; 
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, dado.getCpf());

            int res = pstmt.executeUpdate(); 

            conn.close();

            return res != 0;
        } 
    }
     public Collection<CadAlunos> lista(String criterio) throws SQLException {
        ArrayList<CadAlunos> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno ";

        if (criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        CadAlunos alunos = new CadAlunos();
                        alunos.setCpf(rs.getString("cpf"));
                        alunos.setNome(rs.getString("nome"));
                        alunos.setSexo(rs.getString("sexo"));
                        alunos.setResponsavel(rs.getString("responsavel"));
                        alunos.setEmail(rs.getString("email"));
                        alunos.setRg(rs.getString("rg"));
                        alunos.setIdentificacao(rs.getString("identificacao"));
                        alunos.setPeriodo(rs.getString("periodo"));
                        alunos.setRa(rs.getString("ra"));


                        lista.add(alunos);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções, se necessário
        }

        return lista;
    }
    
    public void preencherComboBoxClasse(ComboBox<String> cb_Classe) {
        try (Connection conn = connect()) {
            // Sua consulta SQL para selecionar os nomes
            String sqlQuery = "SELECT identificacao FROM classe";

            try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        String identificacao = resultSet.getString("identificacao");
                        cb_Classe.getItems().add(identificacao); // Adiciona cada nome ao ComboBox
                    }
                }
            }
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções, se necessário
        }
    }

     public boolean alunoExiste(CadAlunos dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM aluno WHERE cpf = ?";
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
}
