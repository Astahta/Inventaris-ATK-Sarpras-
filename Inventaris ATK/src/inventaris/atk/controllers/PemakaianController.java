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
    }
    
    public void openPemakaian() {
        frame.changeScreen("pemakaian");
    }
    
    public void openBooking(){
        frame.changeScreen("booking");
    }
    
    public void addPemakaian(String userId, String date, int atkId, int jumlah){
        if(pemakaianModel.addPemakaian(userId, date, atkId, jumlah)){
        }
        else{
            System.out.println("Input pemakaian gagal");
        }
    }
    
    public void addBooking(String userId, String date, int atkId, int jumlah){
        if(bookingModel.addBooking(userId, date, atkId, jumlah)){
        }
        else{
            System.out.println("Input pemakaian gagal");
        }
    }
    
    public Vector<String> getATKName(){      
        atkModel.initModel();
        return atkModel.getATKName();  
    }
    
        
}
