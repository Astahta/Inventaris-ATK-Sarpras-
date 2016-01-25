package inventaris.atk.controllers;



import inventaris.atk.models.InventarisAtkModel;
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
    
    public InventarisController(Inventaris frame) {
        this.frame = frame;
        frame.initAtkTable(atkModel.getTableModel());
    }
   
    public void openAtk() {
        atkModel.initModel();
        frame.changeScreen("ATK");
    }
    public void openUser() {
        
        frame.changeScreen("user");
    }
    public void openSupplier() {
        
        frame.changeScreen("supplier");
    }
}
