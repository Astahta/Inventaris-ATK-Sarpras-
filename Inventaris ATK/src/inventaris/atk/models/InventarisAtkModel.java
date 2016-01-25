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
     private DefaultTableModel atk = new DefaultTableModel(new Object[]{"No", "Nama ATK", "Stok"},0);
     public DefaultTableModel getTableModel() {
        return atk;
    }
    
    public void initModel() {
         try {
             atk.setRowCount(0);
             String sql = "SELECT * FROM ATK";
             PreparedStatement dbStatement = conn.prepareStatement(sql);
             ResultSet rs = dbStatement.executeQuery();
             while (rs.next()) {
                 Object[] o = new Object[3];
                 o[0]=rs.getInt("id_atk");
                 o[1]=rs.getString("nama_atk");
                 o[2]=rs.getInt("stok");

                 atk.addRow(o); 
             }

         } catch (SQLException ex) {
             Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}
