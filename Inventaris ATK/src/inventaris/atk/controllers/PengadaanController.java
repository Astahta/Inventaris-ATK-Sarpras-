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
import javax.swing.JFrame;
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
        frame.initListTable(pengadaanModel.getListTableModel());
    }
    
    public void changeFrame(JFrame frame){
        this.frame.setVisible(false);
        frame.setVisible(true);
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
    
    public void openList(){
        pengadaanModel.initListModel();
        frame.changeScreen("listPanel");
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
            frame.showDialogBox("Hapus", "Success");
            System.out.println("Hapus pengadaan gagal");
        }
    }
    
    public void updatePengadaanAddKedatangan(int id_atk, String tanggal_pesan, int id_pemasok){
        if(pengadaanModel.updatePengadaanAddKedatangan(id_atk, tanggal_pesan, id_pemasok)){
            frame.showDialogBox("Validasi", "Success");
        }
        else{
            frame.showDialogBox("Validasi", "Failed");
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
    
    public void validasi(int row){
        //String pengadaanModel.getKedatanganTableModel().getValueAt(row, 5);
        String tanggal_pesan= (String) pengadaanModel.getKedatanganTableModel().getValueAt(row, 5);
        Integer id_pemasok= (Integer) pengadaanModel.getKedatanganTableModel().getValueAt(row, 6);
        Integer id_atk= (Integer) pengadaanModel.getKedatanganTableModel().getValueAt(row, 7);
        updatePengadaanAddKedatangan(id_atk, tanggal_pesan, id_pemasok);
        openKedatangan();
    }
    
    public void hapusKedatangan(int row){
        String tanggal_pesan= (String) pengadaanModel.getKedatanganTableModel().getValueAt(row, 5);
        Integer id_pemasok= (Integer) pengadaanModel.getKedatanganTableModel().getValueAt(row, 6);
        Integer id_atk= (Integer) pengadaanModel.getKedatanganTableModel().getValueAt(row, 7);
        deletePengadaan(id_atk, tanggal_pesan, id_pemasok);
        openKedatangan();
    }
    
    public void hapusPengadaan(int row){
        String tanggal_pesan= (String) pengadaanModel.getPengadaanTableModel().getValueAt(row, 7);
        Integer id_pemasok= (Integer) pengadaanModel.getPengadaanTableModel().getValueAt(row, 8);
        Integer id_atk= (Integer) pengadaanModel.getPengadaanTableModel().getValueAt(row, 9);
        deletePengadaan(id_atk, tanggal_pesan, id_pemasok);
        openKedatangan();
    }
    
}
