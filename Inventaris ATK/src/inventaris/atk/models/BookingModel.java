/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.models;
import java.sql.Connection;
import java.sql.Date;
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

    public boolean addBooking(String userId, java.util.Date date, String namaATK, int jumlah) {
        try {
            int atkId=0;
            String sql = "SELECT id_atk FROM ATK WHERE nama_atk=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, namaATK);
            ResultSet result = dbStatement.executeQuery();
            if (result.next()){
                atkId= result.getInt("id_atk");
            }

            result.close();
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);
            
            sql = "INSERT INTO Booking (id_pengguna, tanggal_pemesanan, id_atk, jumlah) VALUES (?, ?, ?, ?)";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, userId);
            dbStatement.setString(2, time);
            dbStatement.setInt(3, atkId);
            dbStatement.setInt(4, jumlah);
            dbStatement.executeUpdate();
            
            dbStatement.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
