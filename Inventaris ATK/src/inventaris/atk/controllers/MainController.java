/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.atk.controllers;

import inventaris.atk.view.MainMenu;
import javax.swing.JFrame;

/**
 *
 * @author FiqieUlya
 */
public class MainController {
    private final MainMenu mainMenu;
    
    public MainController(MainMenu frame){
        mainMenu = frame;
    }
    public void changeFrame(JFrame frame){
        mainMenu.setVisible(false);
        frame.setVisible(true);
    }
    
}
