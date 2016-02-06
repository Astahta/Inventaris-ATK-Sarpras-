/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zulvafachrina
 */
public class BookingModel {
    private static Connection conn = DatabaseConnector.connect();

    public boolean addBooking(String userId, String date, int atkId, int jumlah) {
        try {
            String sql = "INSERT INTO Booking (id_pengguna, tanggal_pemesanan, id_atk, jumlah) VALUES (?, ?)";
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
