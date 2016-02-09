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
    private DefaultTableModel booking = new DefaultTableModel(new Object[]{"No", "Nama Pengguna", "Tanggal Pesan", "Banyak Jenis"},0);
    private DefaultTableModel detail = new DefaultTableModel(new Object[]{"No", "Jenis ATK", "Jumlah"},0);
    public DefaultTableModel getTableModel() {
        return booking;
    }
    
    public DefaultTableModel getDetailModel() {
        return detail;
    }
    
    public void initDetail(String nama, String tanggal){
        try {
            detail.setRowCount(0);
            String sql ="SELECT a.nama_atk as atk,  b.jumlah as jumlah  FROM Booking b NATURAL JOIN ATK a NATURAL JOIN Pengguna p WHERE p.nama_pengguna= ? AND b.tanggal_pemesanan = ? ";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setString(2, tanggal);
            ResultSet rs = dbStatement.executeQuery();
            int i=1;
            
            while (rs.next()) {
                 Object[] o = new Object[3];
                 o[0]=i;
                 o[1]=rs.getString("atk");
                 o[2]=rs.getInt("jumlah");
                 detail.addRow(o); 
                 i++;
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(PemakaianModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initModel() {
         try {
             booking.setRowCount(0);
             String sql = "SELECT p.nama_pengguna as pengguna, b.tanggal_pemesanan as tanggal, Count(*) as jumlah  FROM Booking b NATURAL JOIN ATK a NATURAL JOIN Pengguna p WHERE tanggal_pemesanan > date('NOW') GROUP BY pengguna, tanggal";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] o = new Object[4];
                 o[0]=i;
                 o[1]=rs.getString("pengguna");
                 o[2]=rs.getString("tanggal");
                 o[3]=rs.getInt("jumlah");
                 booking.addRow(o); 
                 i++;
             }

         } catch (SQLException ex) {
             Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
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
