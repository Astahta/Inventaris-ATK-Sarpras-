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
}
