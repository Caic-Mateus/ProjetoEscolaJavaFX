package br.com.fatec.DAO;

import static br.com.fatec.database.Database.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reservaDAO {
    public String[] getUltimaVenda() throws SQLException {
        String[] vendaInfo = new String[5]; // Array para armazenar as informações da venda
        
        try (Connection conn = connect()) {
            String query = "SELECT * FROM TBL_VENDAS ORDER BY ID_VENDAS DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    vendaInfo[0] = rs.getString("NUMERO_CLIENTE");
                    vendaInfo[1] = rs.getString("NOME_CLIENTE");
                    vendaInfo[2] = rs.getString("NOME_FILME");
                    vendaInfo[3] = rs.getString("HORA");
                    vendaInfo[4] = rs.getString("POLTRONAS");
                }
            }
        }
        
        return vendaInfo; 
    }
    
}
