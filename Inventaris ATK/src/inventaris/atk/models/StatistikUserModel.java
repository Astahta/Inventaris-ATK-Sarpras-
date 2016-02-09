package inventaris.atk.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class StatistikUserModel {
    private static Connection conn = DatabaseConnector.connect();
    private DefaultTableModel statistikUser = new DefaultTableModel(new Object[]{"Bulan","ATK", "Pemakaian"},0);
    
    public DefaultTableModel getTableModel() {
        return statistikUser;
    }

    public void getAtSpesificUser(String kategori, int year) {
        try {
            statistikUser.setRowCount(0);
            String sql =  "SELECT strftime('%m', tanggal_pemakaian) as month, ATK.nama_atk, SUM(Pemakaian.jumlah) as jumlah " 
                        + "FROM Pemakaian NATURAL JOIN ATK NATURAL JOIN Pengguna "
                        + "WHERE Pengguna.Kategori = '" + kategori + "'"
                        +   " AND strftime('%Y', tanggal_pemakaian) = '" + Integer.toString(year) + "'"
                        + "GROUP BY ATK.id_atk;";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            while (rs.next()) {
                Object[] o = new Object[3];
                o[0]=rs.getInt("month");
                o[1]=rs.getString("nama_atk");
                o[2]=rs.getInt("jumlah");
                statistikUser.addRow(o); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
