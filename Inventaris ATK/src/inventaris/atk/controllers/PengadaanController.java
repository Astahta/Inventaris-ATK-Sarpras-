/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.controllers;

import inventaris.atk.models.BookingModel;
import inventaris.atk.models.InventarisAtkModel;
import inventaris.atk.models.InventarisSupplierModel;
import inventaris.atk.models.PemakaianModel;
import inventaris.atk.models.PengadaanModel;
import inventaris.atk.view.Pemakaian;
import inventaris.atk.view.Pengadaan;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author FiqieUlya
 */
public class PengadaanController {
    private final Pengadaan frame;
    private final PengadaanModel pengadaanModel = new PengadaanModel();
    private final InventarisAtkModel atkModel = new InventarisAtkModel();
    private final InventarisSupplierModel supplierModel = new InventarisSupplierModel();
    private DefaultTableModel atkTable = new DefaultTableModel(new Object[]{"No","ID ATK", "Nama ATK", "Stok"},0);
    
    public PengadaanController(Pengadaan frame) {
        this.frame = frame;
        //frame.initFormTable(pengadaanModel.getFormTableModel());
        frame.initPengadaanTable(pengadaanModel.getPengadaanTableModel());
        frame.initKedatanganTable(pengadaanModel.getKedatanganTableModel());
    }
    
    public void prosesForm(int n){
        pengadaanModel.initFormModel(n);
        //frame.initFormTable(pengadaanModel.getFormTableModel());
    }
    
    public void openForm(int n) {
        pengadaanModel.initFormModel(n);
        frame.changeScreen("formPanel");
    }
    
    public void openKedatangan(){
        pengadaanModel.initKedatanganModel();
        frame.changeScreen("kedatanganPanel");
    }
    
    public void openView(){
        pengadaanModel.initPengadaanModel();
        frame.changeScreen("viewPanel");
    }
    
    public void createPengadaan(int stok, String tanggal_pesan, int id_pemasok, int id_atk) {
        if(pengadaanModel.createPengadaan(stok, tanggal_pesan, id_pemasok, id_atk)){
        }
        else{
            System.out.println("Input pengadaan gagal" + "- stok: -" + stok + "- id_pemasok: -" + id_pemasok + "- id_atk: -" + id_atk + "-");
        }
    }
    
    public void deletePengadaan(int id_atk, String tanggal_pesan, int id_pemasok){
        if(pengadaanModel.deletePengadaan(id_atk, tanggal_pesan, id_pemasok)){
        }
        else{
            System.out.println("Hapus pengadaan gagal");
        }
    }
    
    public void updatePengadaanAddKedatangan(int id_atk, String tanggal_pesan, int id_pemasok){
        if(pengadaanModel.updatePengadaanAddKedatangan(id_atk, tanggal_pesan, id_pemasok)){
        }
        else{
            System.out.println("Gagal update");
        }
    }
    public Vector<String> getATKName(){
        atkModel.initModel();
        return atkModel.getATKName();  
    }
    
    public Vector<String> getATKIdName(){
        atkModel.initModel();
        return atkModel.getATKIdName();  
    }
    
    public Vector<String> getSupplierName(){      
        supplierModel.initModel();
        return supplierModel.getSupplierName();  
    }
    
    public Vector<String> getSupplierIdName(){      
        supplierModel.initModel();
        return supplierModel.getSupplierIdName();  
    }
    
    
}
