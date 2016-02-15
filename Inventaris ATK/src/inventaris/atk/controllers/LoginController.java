package inventaris.atk.controllers;

import inventaris.atk.view.Login;
import javax.swing.JFrame;

public class LoginController {
    private final Login login;
    
    public LoginController(Login frame){
        login = frame;
    }
    
    public Boolean checkPassword(char[] pass) {
        String word = "inipassword";
        return (word.equals(new String(pass)));
    }
    
    public void changeFrame(JFrame frame){
        login.setVisible(false);
        frame.setVisible(true);
    }
}
