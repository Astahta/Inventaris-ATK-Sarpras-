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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FiqieUlya
 */
public class InventarisUserModel {
     private static Connection conn = DatabaseConnector.connect();
     private DefaultTableModel user = new DefaultTableModel(new Object[]{"No","ID Pengguna", "Nama Pengguna", "Kategori"},0);
     public DefaultTableModel getTableModel() {
        return user;
    }
    
    public void initModel() {
         try {
             user.setRowCount(0);
             String sql = "SELECT * FROM Pengguna";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] o = new Object[4];
                 o[0]=i;
                 o[1]=rs.getString("id_pengguna");
                 o[2]=rs.getString("nama_pengguna");
                 o[3]=rs.getString("Kategori");

                 user.addRow(o); 
                 i++;
             }

         } catch (SQLException ex) {
             Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public boolean addUser(String nama, String kategori, String id ){
        try {
            String sql = "INSERT INTO Pengguna (id_pengguna, nama_pengguna, Kategori) VALUES (?, ?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, id);
            dbStatement.setString(2, nama);
            dbStatement.setString(3, kategori);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editUser(String nama, String kategori, String id ){
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE Pengguna SET  nama_pengguna = ?, Kategori = ? WHERE id_pengguna=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setString(2, kategori);
            dbStatement.setString(3, id);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    public boolean deleteUser(String id ){
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Pengguna WHERE id_pengguna=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, id);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
}
