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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FiqieUlya
 */
public class InventarisSupplierModel {
     private static Connection conn = DatabaseConnector.connect();
     private DefaultTableModel supplier = new DefaultTableModel(new Object[]{"No","ID Pemasok", "Nama Pemasok", "Alamat", "Nomor Telpon"},0);
     public DefaultTableModel getTableModel() {
        return supplier;
    }
    
    public void initModel() {
         try {
             supplier.setRowCount(0);
             String sql = "SELECT * FROM Pemasok";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] o = new Object[5];
                 o[0]=i;
                 o[1]=rs.getString("id_pemasok");
                 o[2]=rs.getString("nama_pemasok");
                 o[3]=rs.getString("alamat");
                 o[4]=rs.getString("no_telpon");
                 i++;
                 supplier.addRow(o); 
             }

         } catch (SQLException ex) {
             Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public boolean addSupplier(String nama, String alamat, String telpon ){
        try {
            String sql = "INSERT INTO Pemasok (nama_pemasok, alamat, no_telpon) VALUES (?, ?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setString(2, alamat);
            dbStatement.setString(3, telpon);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editSupplier(String nama, String alamat, int id, String telpon ){
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE Pemasok SET  nama_pemasok = ?, alamat = ?, no_telpon= ? WHERE id_pemasok=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setString(2, alamat);
            dbStatement.setString(3, telpon);
            dbStatement.setInt(4, id);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    public boolean deleteSupplier(int id ){
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Pemasok WHERE id_pemasok=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    
    public Vector<String> getSupplierName(){
        Vector<String> suppliername = new Vector<String>();
        for(int i=0; i<supplier.getRowCount(); i++) {
            suppliername.add((String) supplier.getValueAt(i,2));
        }
        return suppliername;  
    }
}
