/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import static br.com.fatec.database.Database.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo
 */
public class PoltronaDAO {
    
    public boolean insertVendas(String poltronasConcatenadas, String hora, String nomeFilme) throws SQLException {
        boolean inseriu = false;

        try (Connection conn = connect()) {
            // Busca o registro mais recente na TBL_LOG_ACESSOS
            String logAcessosSQL = "SELECT NOME_CLIENTE, NUMERO_CLIENTE FROM TBL_LOG_ACESSOS ORDER BY DATA_LOG DESC, HORA_LOG DESC LIMIT 1";
            try (PreparedStatement logAcessosStmt = conn.prepareStatement(logAcessosSQL)) {
                ResultSet logAcessosRs = logAcessosStmt.executeQuery();

                if (logAcessosRs.next()) {
                    String nomeCliente = logAcessosRs.getString("NOME_CLIENTE");
                    String numeroCliente = logAcessosRs.getString("NUMERO_CLIENTE");

                    // Insere na tabela de vendas utilizando as informações mais recentes da TBL_LOG_ACESSOS
                    String vendasSQL = "INSERT INTO TBL_VENDAS (NUMERO_CLIENTE, NOME_CLIENTE, NOME_FILME, HORA, POLTRONAS) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement vendasStmt = conn.prepareStatement(vendasSQL)) {
                        vendasStmt.setString(1, numeroCliente);
                        vendasStmt.setString(2, nomeCliente);
                        vendasStmt.setString(3, nomeFilme);
                        vendasStmt.setString(4, hora);
                        vendasStmt.setString(5, poltronasConcatenadas);

                        // Executa o comando de inserção
                        inseriu = vendasStmt.executeUpdate() > 0;
                    }
                }
            }
        }

        return inseriu;
    }

        
        
        public List<String> getPoltronasCompradas(String hora) throws SQLException {
            List<String> poltronasCompradas = new ArrayList<>();

            try (Connection conn = connect()) {
                String SQL = "SELECT poltronas FROM TBL_VENDAS WHERE hora = ?";
                PreparedStatement pstmt = conn.prepareStatement(SQL);

               
                pstmt.setString(1, hora);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String poltronas = rs.getString("poltronas");
                    // Adicione as poltronas compradas à lista
                    poltronasCompradas.add(poltronas);
                }
                
                conn.close();
            }

            return poltronasCompradas;
        }
}
