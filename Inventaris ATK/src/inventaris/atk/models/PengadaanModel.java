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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chairuniaulianusapati
 */
public class PengadaanModel {
    private static Connection conn = DatabaseConnector.connect();
    /*[stok] INTEGER, 
    [status] INTEGER, 
    [tanggal_pesan] DATETIME, 
    [tanggal_kedatangan] DATETIME, 
    [id_pemasok] INTEGER REFERENCES Pemasok([id_pemasok]) ON DELETE CASCADE ON UPDATE CASCADE, 
    [id_atk] INTEGER REFERENCES ATK([id_atk]) ON DELETE CASCADE ON UPDATE CASCADE,*/
    private DefaultTableModel pengadaan = new DefaultTableModel(new Object[]{"No","Jumlah", "Status", "Tanggal Pesan", "Tanggal Datang", "ID Pemasok", "ID ATK"},0);
    public DefaultTableModel getTableModel() {
        return pengadaan;
    }
    
    public void initModel() {
         try {
             pengadaan.setRowCount(0);
             String sql = "SELECT * FROM pengadaan";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] object = new Object[7];
                 object[0]=i;
                 object[1]=rs.getInt("stok");
                 object[2]=rs.getInt("status");
                 object[3]=rs.getString("tanggal_pesan");
                 object[4]=rs.getString("tanggal_kedatangan");
                 object[5]=rs.getInt("id_pemasok");
                 object[6]=rs.getInt("id_atk");
                 i++;
                 pengadaan.addRow(object); 
             }

         } catch (SQLException ex) {
             Logger.getLogger(PengadaanModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public boolean createPengadaan(int stok, int status, String tanggal_pesan, int id_pemasok, int id_atk) {
        try {
            String sql = "INSERT INTO Pengadaan (stok, tanggal_pesan, id_pemasok, id_atk) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, stok);
            dbStatement.setInt(2, 0);
            dbStatement.setString(3, tanggal_pesan);
            dbStatement.setInt(4, id_pemasok);
            dbStatement.setInt(5, id_atk);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updatePengadaanAddKedatangan(int id_atk, String tanggal_pesan, int id_pemasok, int stok){
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ATK SET  tanggal_kedatangan = ?, status = ?, stok = ? WHERE id_atk=?, tanggal_pesan=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, "now");
            dbStatement.setInt(2, 1);
            dbStatement.setInt(3, stok);
            dbStatement.setInt(4, id_atk);
            dbStatement.setString(5, tanggal_pesan);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    
    /*public boolean updatePengadaan(int id_atk, String tanggal_pesan, int id_pemasok, int stok){
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ATK SET  tanggal_kedatangan = ?, status = ?, stok = ? WHERE id_atk=?, tanggal_pesan=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, "now");
            dbStatement.setInt(2, 1);
            dbStatement.setInt(3, stok);
            dbStatement.setInt(4, id_atk);
            dbStatement.setString(5, tanggal_pesan);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }*/
    
    public boolean deleteKedatangan(int id_atk, String tanggal_pesan, int id_pemasok){
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM ATK WHERE id_atk=?, tanggal_pesan=?, id_pemasok=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id_atk);
            dbStatement.setString(2, tanggal_pesan);
            dbStatement.setInt(3, id_pemasok);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }  
    }
    
    /*public Vector<String> getATKName(){
        Vector<String> atkname = new Vector<String>();
        for(int i=0; i<atk.getRowCount(); i++) {
            atkname.add((String) atk.getValueAt(i,2));
        }
        return atkname;  
    }*/
    
    
}
