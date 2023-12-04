/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.database.Database;
import static br.com.fatec.database.Database.connect;
import br.com.fatec.model.Cadastrar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;


/**
 *
 * @author Caic
 */
public class CadastrarDAO {

    //variaveis auxiliares
    private Cadastrar cadastro;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    public boolean insertCadastro(Cadastrar dado) throws SQLException{
        boolean inseriu;
            
        try (Connection conn = connect()) {
             
            
            String SQL = "INSERT INTO TBL_CADASTRO (nome, apelido, cpf, celular, regiao, email, senha) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, dado.getNome());
            pstmt.setString(2, dado.getApelido());
            pstmt.setString(3, dado.getCpf());
            pstmt.setString(4, "55" + dado.getCelular());
            pstmt.setString(5, dado.getRegiao());
            pstmt.setString(6, dado.getEmail());
            pstmt.setString(7, dado.getSenha());
            
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
    
    public boolean contaExiste(Cadastrar dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM TBL_LOGIN WHERE email = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, dado.getEmail());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int rowCount = rs.getInt(1);
                        System.out.println("Número de linhas encontradas: " + rowCount);
                        existe = rowCount > 0;
                    }
                }
            }
        }

        return existe;
    }

}
