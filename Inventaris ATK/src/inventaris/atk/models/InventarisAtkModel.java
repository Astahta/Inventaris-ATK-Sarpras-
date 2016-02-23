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
import java.util.Vector;
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
            String sql = "SELECT stok FROM ATK WHERE nama_atk = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, nama);
            ResultSet rs = dbStatement.executeQuery();
            if (rs.next()) {
                sql = "UPDATE ATK SET stok = ? WHERE nama_atk = ?";
                int new_stok = stok + rs.getInt("stok");
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, new_stok);
                dbStatement.setString(2, nama);
                dbStatement.executeUpdate(); 
            }
            else {       
                sql = "INSERT INTO ATK (nama_atk, stok) VALUES (?, ?)";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, nama);
                dbStatement.setInt(2, stok);
                dbStatement.executeUpdate();
            }
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
    
    public Vector<String> getATKName(){
        Vector<String> atkname = new Vector<String>();
        for(int i=0; i<atk.getRowCount(); i++) {
            atkname.add((String) atk.getValueAt(i,2));
        }
        return atkname;  
    }
    
    public Vector<String> getATKIdName(){
        Vector<String> atkidname = new Vector<String>();
        for(int i=0; i<atk.getRowCount(); i++) {
            atkidname.add(Integer.toString((Integer) atk.getValueAt(i, 1)) + " - " + (String) atk.getValueAt(i,2));
            //atkidname.add("bismillah");
        }
        return atkidname;  
    }
    
    /*public Vector<String> getATKIdName(){
        Vector<String> ATKIdName = new Vector<String>();
        for(int i=0; i<atk.getRowCount(); i++) {
            //Integer.toString((Integer) atk.getValueAt(i,1)) +  + (String) atk.getValueAt(i,2)
            ATKIdName.add(" - ");
        }
        return ATKIdName;
    }*/

}
