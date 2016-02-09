package inventaris.atk.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class StatistikPeriodeModel {
    private static Connection conn = DatabaseConnector.connect();
    private DefaultTableModel statistikPeriode = new DefaultTableModel(new Object[]{"Bulan","ATK", "Pemakaian"},0);
    
    public DefaultTableModel getTableModel() {
        return statistikPeriode;
    }
    
    public void getAtSpesificTime(int month, int year) {
        String _month, _year;
        if (month<9)
            _month = "0" + Integer.toString(month + 1);
        else
            _month = Integer.toString(month + 1);
        _year = Integer.toString(year);
        try {
            statistikPeriode.setRowCount(0);
            String sql = "SELECT strftime('%m', tanggal_pemakaian) as month, "
                        +   "ATK.nama_atk, "
                        +   "SUM(Pemakaian.jumlah) as jumlah "
                        + "FROM Pemakaian NATURAL JOIN ATK "
                        + "WHERE month = '" + _month + "'"
                        +   " AND strftime('%Y', tanggal_pemakaian) = '" + _year + "'"
                        + "GROUP BY ATK.id_atk;";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            while (rs.next()) {
                Object[] o = new Object[3];
                o[0]=rs.getInt("month");
                o[1]=rs.getString("nama_atk");
                o[2]=rs.getInt("jumlah");
                statistikPeriode.addRow(o); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventarisAtkModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
