package inventaris.atk.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;

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
    public boolean addPengadaan(int stok, int status, String tanggal_pesan, int id_pemasok, int id_atk) {
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
    
    public boolean addKedatangan(int id_atk, String tanggal_pesan, int id_pemasok, int stok){
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ATK SET  tanggal_kedatangan = ?, status = ?, stok = ? WHERE id_atk=?, tanggal_pesan=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, ta);
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
    
       public Vector<String> getATKName(){
        Vector<String> atkname = new Vector<String>();
        for(int i=0; i<atk.getRowCount(); i++) {
            atkname.add((String) atk.getValueAt(i,2));
        }
        return atkname;  
    }
    
    public boolean getPengadaan(int id_atk, String tanggal_pesan, int id_pemasok){
        try {
            String sql = "SELECT (askcdhasjkdh Pengadaan (stok, status, date, id_pemasok, id_atk) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, stok);
            dbStatement.setInt(2, status);
            dbStatement.setString(3, date);
            dbStatement.setInt(4, id_pemasok);
            dbStatement.setInt(5, id_atk);
            dbStatement.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
