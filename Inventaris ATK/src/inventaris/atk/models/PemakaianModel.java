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
 * @author zulvafachrina
 */
public class PemakaianModel {
    private static Connection conn = DatabaseConnector.connect();
    private DefaultTableModel pemakaian = new DefaultTableModel(new Object[]{"No","Nama Pengguna", "Tanggal Pemakaian", "Banyak Jenis", "ID Pengguna", "Tanggal Pemakaian Asli"},0);
    private DefaultTableModel detail = new DefaultTableModel(new Object[]{"No", "Jenis ATK", "Jumlah", "ID ATK"},0);
    public DefaultTableModel getTableModel() {
        return pemakaian;
    }
    public DefaultTableModel getDetailModel() {
        return detail;
    }
    
    public void initDetail(String nama, String tanggal){
        try {
            detail.setRowCount(0);
            String sql ="SELECT a.nama_atk as atk,  p.jumlah as jumlah, p.id_atk as id  FROM Pemakaian p NATURAL JOIN ATK a NATURAL JOIN Pengguna p WHERE p.nama_pengguna= ? AND p.tanggal_pemakaian = ? ";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            dbStatement.setString(2, tanggal);
            ResultSet rs = dbStatement.executeQuery();
            int i=1;
            
            while (rs.next()) {
                 Object[] o = new Object[4];
                 o[0]=i;
                 o[1]=rs.getString("atk");
                 o[2]=rs.getInt("jumlah");
                 o[3]=rs.getInt("id");
                 detail.addRow(o); 
                 i++;
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(PemakaianModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void emptyDetail(){
        while(detail.getRowCount()>0){
            detail.removeRow(0);
        }
    }
    
    public void initModel() {
         try {
             pemakaian.setRowCount(0);
             String sql = "SELECT p.nama_pengguna as pengguna,  p.tanggal_pemakaian as tanggal, p.id_pengguna as id, count(*) as jumlah  FROM Pemakaian p NATURAL JOIN ATK a NATURAL JOIN Pengguna p GROUP BY pengguna, tanggal ";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             int i=1;
             while (rs.next()) {
                 Object[] o = new Object[6];
                 o[0]=i;
                 o[1]=rs.getString("pengguna");
                 o[2]=rs.getString("tanggal");
                 o[3]=rs.getInt("jumlah");
                 o[4]=rs.getInt("id");
                 o[5]=rs.getString("tanggal");
                 pemakaian.addRow(o); 
                 i++;
             }

         } catch (SQLException ex) {
             Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public boolean addPemakaian(String userId, java.util.Date date, String namaATK, int jumlah) {
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
            
            sql = "INSERT INTO Pemakaian (id_pengguna, tanggal_pemakaian, id_atk, jumlah) VALUES (?, ?, ?, ?)";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, userId);
            dbStatement.setString(2, time);
            dbStatement.setInt(3, atkId);
            dbStatement.setInt(4, jumlah);
            dbStatement.executeUpdate();
            
            sql = "UPDATE ATK SET stok = stok - ? WHERE id_atk=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, jumlah);
            dbStatement.setInt(2, atkId);
            dbStatement.executeUpdate();
            
            dbStatement.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAvailable(String atkName, int jumlahpesanan) {
        try {
            int jumlah=0;
            String sql = "SELECT stok FROM ATK WHERE nama_atk=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, atkName);
            ResultSet result = dbStatement.executeQuery();
            if (result.next()){
                jumlah= result.getInt("stok");
            }

            result.close();
            dbStatement.close();
            
            if(jumlah>=jumlahpesanan) {
                return true;
            }
            else {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
              
    }    
    
    public boolean deletePemakaian(int id_pengguna, String tanggal_pemakaian, int id_atk){
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Pemakaian WHERE id_pengguna = ? AND tanggal_pemakaian = ? AND id_atk=?";
            System.out.println(sql+" "+id_pengguna+" "+tanggal_pemakaian+" "+id_atk);
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id_pengguna);
            dbStatement.setString(2, tanggal_pemakaian);
            dbStatement.setInt(3, id_atk);
            dbStatement.executeUpdate();
            
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
}
