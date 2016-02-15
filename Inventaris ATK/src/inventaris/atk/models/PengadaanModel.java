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
    private DefaultTableModel form = new DefaultTableModel(new Object[]{"No", "Nama ATK", "Jumlah"},0);
    private DefaultTableModel pengadaan = new DefaultTableModel(new Object[]{"No","Jumlah", "Status", "Tanggal Pesan", "Tanggal Datang", "Nama Pemasok", "Nama ATK", "Tanggal Pesan Asli", "ID Pemasok", "ID ATK"},0);
    private DefaultTableModel kedatangan = new DefaultTableModel(new Object[]{"No","Jumlah", "Tanggal Pesan", "Nama Pemasok", "Nama ATK", "Tanggal Pesan Asli", "ID Pemasok", "ID ATK"},0);
    public DefaultTableModel getFormTableModel() {
        return form;
    }
    
    public DefaultTableModel getPengadaanTableModel() {
        return pengadaan;
    }
    
    public DefaultTableModel getKedatanganTableModel() {
        return kedatangan;
    }
    
    public void initFormModel(int n) {
         try {
             form.setRowCount(0);
             int i=1;
             while (i <= n) {
                 Object[] object = new Object[3];
                 object[0]=i;
                 object[1]="";
                 object[2]="";
                 i++;
                 form.addRow(object); 
             }

         } catch (Exception ex) {
         }
    }
    
    public void initPengadaanModel() {
         try {
             pengadaan.setRowCount(0);
             String sql = "SELECT pengadaan.stok, status, tanggal_pesan, tanggal_kedatangan, nama_pemasok, nama_atk, pengadaan.id_pemasok, pengadaan.id_atk FROM pengadaan INNER JOIN pemasok on pengadaan.id_pemasok = pemasok.id_pemasok INNER JOIN atk ON pengadaan.id_atk = atk.id_atk";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] object = new Object[10];
                 object[0]=i;
                 object[1]=rs.getInt("stok");
                 if(rs.getInt("status") == 1){
                     object[2]="sudah datang";
                 }
                 else{
                     object[2]="belum datang";
                 }
                 
                 String[] array_tanggal_pesan = ((rs.getString("tanggal_pesan")).split("\\ ", -1));
                 object[3]=array_tanggal_pesan[0];
                 //rs.getString("tanggal_kedatangan");
                 object[4]=rs.getString("tanggal_kedatangan");
                 object[5]=rs.getString("nama_pemasok");
                 object[6]=rs.getString("nama_atk");
                 object[7]=rs.getString("tanggal_pesan");
                 object[8]=rs.getInt("id_pemasok");
                 object[9]=rs.getInt("id_atk");
                 /*object[4]="empat";
                 object[5]="lima";
                 object[6]="enam";
                 object[3]=rs.getString("tanggal_pesan");
                 object[5]=rs.getInt("id_pemasok");
                 object[6]=rs.getInt("id_atk");*/
                 i++;
                 pengadaan.addRow(object); 
             }

         } catch (SQLException ex) {
             Logger.getLogger(PengadaanModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void initKedatanganModel() {
         try {
             kedatangan.setRowCount(0);
             //String sql = "SELECT stok, tanggal_pesan, nama_pemasok, nama_atk, id_pemasok, id_atk FROM pengadaan NATURAL JOIN pemasok NATURAL JOIN atk WHERE status = 0";
             //String sql = "SELECT stok, tanggal_pesan, id_pemasok, id_atk FROM pengadaan WHERE status = 0";
             String sql = "SELECT pengadaan.stok, tanggal_pesan, nama_pemasok, nama_atk, pengadaan.id_pemasok, pengadaan.id_atk FROM pengadaan INNER JOIN pemasok on pengadaan.id_pemasok = pemasok.id_pemasok INNER JOIN atk ON pengadaan.id_atk = atk.id_atk WHERE status = 0";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] object = new Object[8];
                 object[0]=i;
                 object[1]=rs.getInt("stok");
                 String[] array_tanggal_pesan = ((rs.getString("tanggal_pesan")).split("\\ ", -1));
                 object[2]=array_tanggal_pesan[0];
                 object[3]=rs.getString("nama_pemasok");
                 object[4]=rs.getString("nama_atk");
                 object[5]=rs.getString("tanggal_pesan");
                 object[6]=rs.getInt("id_pemasok");
                 object[7]=rs.getInt("id_atk");
                 
                 
                 
                 
                 i++;
                 kedatangan.addRow(object); 
             }

         } catch (SQLException ex) {
             Logger.getLogger(PengadaanModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public boolean createPengadaan(int stok, String tanggal_pesan, int id_pemasok, int id_atk) {
        try {
            String sql = "INSERT INTO Pengadaan (stok, status, tanggal_pesan, id_pemasok, id_atk) VALUES (?, ?, "+tanggal_pesan+", ?, ?)";
            System.out.println("sql: " + sql);
            
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, stok); System.out.println("stok: " + stok);
            dbStatement.setInt(2, 0); System.out.println("status: " + "0");
            dbStatement.setInt(3, id_pemasok); System.out.println("id_pemasok: " + id_pemasok);
            dbStatement.setInt(4, id_atk); System.out.println("id_atk: " + id_atk);
            dbStatement.executeUpdate(); System.out.println("jalankan");
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updatePengadaanAddKedatangan(int id_atk, String tanggal_pesan, int id_pemasok){
        try {
            Statement stmt = conn.createStatement();
            //String sql = "UPDATE Pengadaan SET tanggal_kedatangan = ?, status = ? WHERE id_atk=? AND tanggal_pesan=? AND id_pemasok = ?";
            String sql = "UPDATE Pengadaan SET tanggal_kedatangan = DATETIME('now'), status = 1 WHERE id_atk= "+id_atk+" AND tanggal_pesan='"+tanggal_pesan+"' AND id_pemasok = "+id_pemasok;
            System.out.println("sql baru: " + sql);
            
            /*
            UPDATE ATK SET  tanggal_kedatangan = DATETIME('now'), status = 1 WHERE id_atk=2, tanggal_pesan=2016-02-13 06:46:06;
            UPDATE Pengadaan SET tanggal_kedatangan = DATETIME('now'), status = 1 WHERE tanggal_pesan='2016-02-13 06:46:06' AND id_atk = 3 AND id_pemasok = 2
            */
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            /*dbStatement.setString(1, "DATETIME('now')");
            dbStatement.setInt(2, 1);
            dbStatement.setInt(3, id_atk);
            dbStatement.setString(4, tanggal_pesan);
            dbStatement.setInt(3, id_pemasok);*/
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
    
    public boolean deletePengadaan(int id_atk, String tanggal_pesan, int id_pemasok){
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Pengadaan WHERE id_atk = ? AND tanggal_pesan = ? AND id_pemasok=?";
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
