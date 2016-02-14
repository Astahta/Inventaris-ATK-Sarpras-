/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.controllers;
import inventaris.atk.models.BookingModel;
import inventaris.atk.models.InventarisAtkModel;
import inventaris.atk.models.PemakaianModel;
import inventaris.atk.view.Pemakaian;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FiqieUlya
 */
public class PemakaianController {
    private final Pemakaian frame;
    private final BookingModel bookingModel = new BookingModel();
    private final PemakaianModel pemakaianModel = new PemakaianModel();
    private final InventarisAtkModel atkModel = new InventarisAtkModel();
    private DefaultTableModel atkTable = new DefaultTableModel(new Object[]{"No","ID ATK", "Nama ATK", "Stok"},0);
    
    public PemakaianController(Pemakaian frame) {
        this.frame = frame;
        frame.initBookingTable(bookingModel.getTableModel());
        frame.initPemakaianTable(pemakaianModel.getTableModel());
        frame.initDetailPemakaianTable(pemakaianModel.getDetailModel());
        frame.initDetailBookingTable(bookingModel.getDetailModel());
    }
    public void changeFrame(JFrame frame){
        this.frame.setVisible(false);
        frame.setVisible(true);
    }
    
    public void viewDetailPemakaian(String nama, String tanggal){
        pemakaianModel.initDetail(nama, tanggal);
    }
    
    public void viewDetailBooking(String nama, String tanggal){
        bookingModel.initDetail(nama, tanggal);
    }
    
    public void openPemakaian() {
        frame.changeScreen("pemakaian");
        pemakaianModel.initModel();
    }
    
    public void openBooking(){
        frame.changeScreen("booking");
        bookingModel.initModel();
    }
    
    public void addPemakaian(String userId, java.util.Date date, String atkName, int jumlah){
        if(pemakaianModel.addPemakaian(userId, date, atkName, jumlah)){
            pemakaianModel.initModel();
        }
        else{
            System.out.println("Input pemakaian gagal");
        }
    }
    
    public void addBooking(String userId, java.util.Date date, String atkName, int sum){
        if(bookingModel.addBooking(userId, date, atkName, sum)){
            bookingModel.initModel();
        }
        else{
            System.out.println("Input pemakaian gagal");
        }
    }
    
    public Vector<String> getATKName(){      
        atkModel.initModel();
        return atkModel.getATKName();  
    }

    public boolean isPemakaianAvailable(String atkName, int sum) {
        if(pemakaianModel.isAvailable(atkName, sum)){
            return true;
        }
        else{
            return false;
        }
    }
    
        
}
