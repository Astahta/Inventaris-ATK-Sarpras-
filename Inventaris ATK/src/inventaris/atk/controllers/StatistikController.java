package inventaris.atk.controllers;

import inventaris.atk.models.StatistikPeriodeModel;
import inventaris.atk.models.StatistikUserModel;
import inventaris.atk.view.Statistik;
import javax.swing.JFrame;

public class StatistikController {
    private final Statistik frame;
    private final StatistikPeriodeModel periodeModel = new StatistikPeriodeModel();
    private final StatistikUserModel userModel = new StatistikUserModel();
    
    public StatistikController(Statistik frame) {
        this.frame = frame;
        frame.initPeriodeTable(periodeModel.getTableModel());
        frame.initUserTable(userModel.getTableModel());
    }
    public void changeFrame(JFrame frame){
        this.frame.setVisible(false);
        frame.setVisible(true);
    }
    public void openPeriode(int month, int year) {
        periodeModel.getAtSpesificTime(month, year);
        frame.changeScreen("Periode");
    }
    
    public void openUser(String kategori, int year) {
        userModel.getAtSpesificUser(kategori, year);
        frame.changeScreen("User");
    }
    
    public void showAtSpesificTime(int month, int year) {
        periodeModel.getAtSpesificTime(month, year);
    }
    
    public void showAtSpesificUser(String kategori, int year) {
        userModel.getAtSpesificUser(kategori, year);
    }
}
