/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.models;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author zulvafachrina
 */
public class PemakaianModel {
    private static Connection conn = DatabaseConnector.connect();

    public boolean addPemakaian(String userId, String date, int atkId, int jumlah) {
        try {
            String sql = "INSERT INTO Pemakaian (id_pengguna, tanggal_pemakaian, id_atk, jumlah) VALUES (?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, userId);
            dbStatement.setString(2, date);
            dbStatement.setInt(3, atkId);
            dbStatement.setInt(4, jumlah);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
