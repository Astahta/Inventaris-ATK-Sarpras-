/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.models;

import inventaris.atk.*;
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
public class InventarisAtkModel {

    /**
     * @param args the command line arguments
     */
     private static Connection conn = DatabaseConnector.connect();
     private DefaultTableModel atk = new DefaultTableModel(new Object[]{"No","ID ATK", "Nama ATK", "Stok"},0);
     public DefaultTableModel getTableModel() {
        return atk;
    }
    
    public void initModel() {
         try {
             atk.setRowCount(0);
             String sql = "SELECT * FROM ATK";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] o = new Object[4];
                 o[0]=i;
                 o[1]=rs.getInt("id_atk");
                 o[2]=rs.getString("nama_atk");
                 o[3]=rs.getInt("stok");
                 i++;
                 atk.addRow(o); 
             }

         } catch (SQLException ex) {
             Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public boolean addAtk(String nama, int stok ){
        try {
            String sql = "INSERT INTO ATK (nama_atk, stok) VALUES (?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setInt(2, stok);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editAtk(String nama, int stok, int id){
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ATK SET  nama_atk = ?, stok = ? WHERE id_atk=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setInt(2, stok);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    public boolean deleteAtk(int id ){
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM ATK WHERE id_atk=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }

}
