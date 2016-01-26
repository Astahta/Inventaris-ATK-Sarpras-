package inventaris.atk.controllers;



import inventaris.atk.models.InventarisAtkModel;
import inventaris.atk.models.InventarisSupplierModel;
import inventaris.atk.models.InventarisUserModel;
import inventaris.atk.view.Inventaris;

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
        if(userModel.addUser(nama, kategori, id))
            userModel.initModel();
        else{
            System.out.println("Input user gagal");
        }
    }
    public void updateUser(String nama,String id, String kategori){
        if(userModel.editUser(nama, kategori, id)){
            userModel.initModel();
        }else{
            System.out.println("Edit user gagal");
        }
    }
    public void deleteUser(String id){
        if(userModel.deleteUser(id)){
            userModel.initModel();
        }else{
            System.out.println("Delete gagal");
        }
    }
}
