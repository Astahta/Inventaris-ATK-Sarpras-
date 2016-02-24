package inventaris.atk.controllers;



import inventaris.atk.models.InventarisAtkModel;
import inventaris.atk.models.InventarisSupplierModel;
import inventaris.atk.models.InventarisUserModel;
import inventaris.atk.view.Inventaris;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FiqieUlya
 */
public class InventarisController {
    //private final Inventaris inventaris = new Inventaris();
    private final Inventaris frame;
    private final InventarisAtkModel atkModel = new InventarisAtkModel();
    private final InventarisSupplierModel supplierModel = new InventarisSupplierModel();
    private final InventarisUserModel userModel = new InventarisUserModel();
    
    public InventarisController(Inventaris frame) {
        this.frame = frame;
        frame.initAtkTable(atkModel.getTableModel());
        frame.initSupplierTable(supplierModel.getTableModel());
        frame.initUserTable(userModel.getTableModel());
    }
    public void changeFrame(JFrame frame){
        this.frame.setVisible(false);
        frame.setVisible(true);
    }
   
    public void openAtk() {
        atkModel.initModel();
        frame.changeScreen("ATK");
    }
    public void openUser() {
        userModel.initModel();
        frame.changeScreen("user");
    }
    public void openSupplier() {
        supplierModel.initModel();
        frame.changeScreen("supplier");
    }
    public void addUser(String nama, String id, String kategori){
        if(userModel.addUser(nama, kategori, id)){
            frame.showDialogBox("Input user berhasil", "success");
            userModel.initModel();
        }else{
            frame.showDialogBox("Input user gagal", "failed");
        }
    }
    public void updateUser(String nama,String id, String kategori){
        if(userModel.editUser(nama, kategori, id)){
            frame.showDialogBox("Update user berhasil", "success");
            userModel.initModel();
        }else{
            frame.showDialogBox("Update user gagal", "failed");
        }
    }
    public void deleteUser(String id){
        //int n = JOptionPane.showConfirmDialog(frame,"Apakah anda yakin akan menghapus data tersebut?","Konfirmasi Penghapusan",JOptionPane.YES_NO_OPTION);
        //if(n==YES_OPTION){
            if(userModel.deleteUser(id)){
                frame.showDialogBox("Delete user berhasil", "success");
                userModel.initModel();
            }else{
                frame.showDialogBox("Delete user gagal", "failed");
            }
        //}
        
    }
    public void addSupplier(String nama, String alamat, String telpon){
        if(supplierModel.addSupplier(nama, alamat, telpon)){
            frame.showDialogBox("Input user berhasil", "success");
            supplierModel.initModel();
        }else{
            frame.showDialogBox("Input user gagal", "failed");
        }
    }
    public void updateSupplier(String nama,String alamat, int id, String telpon){
        if(supplierModel.editSupplier(nama, alamat, id, telpon)){
            frame.showDialogBox("Edit supplier berhasil", "success");
            supplierModel.initModel();
        }else{
            frame.showDialogBox("Edit supplier gagal", "failed");
        }
    }
    public void deleteSupplier(int id){
        if(supplierModel.deleteSupplier(id)){
            frame.showDialogBox("Hapus supplier berhasil", "success");
            supplierModel.initModel();
        }else{
            frame.showDialogBox("Hapus supplier gagal", "failed");
        }
    }
    public void addAtk(String nama, int stok){
        if(atkModel.addAtk(nama, stok)){
            frame.showDialogBox("Input ATK berhasil", "success");
            atkModel.initModel();
        }else{
            frame.showDialogBox("Input ATK gagal", "failed");
        }
    }
    public void updateAtk(String nama, int stok, int id){
        if(atkModel.editAtk(nama, stok, id)){
            frame.showDialogBox("Edit ATK berhasil", "success");
            atkModel.initModel();
        }else{
            frame.showDialogBox("Edit ATK gagal", "failed");
        }
    }
    public void deleteAtk(int id){
        if(atkModel.deleteAtk(id)){
            frame.showDialogBox("Delete ATK berhasil", "success");
            atkModel.initModel();
        }else{
            frame.showDialogBox("Delete ATK gagal", "failed");
        }
    }
}
