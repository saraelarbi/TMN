/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.entities.User;

import edu.esprit.services.UserCRUD;
import edu.esprit.utils.NavigationEntreInterfaces;
import static edu.esprit.utils.PatternEmail.validate;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RecupmdpController implements Initializable {

    @FXML
    private AnchorPane AnchorPane_Login;
    @FXML
    private Button ok;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ok(ActionEvent event) throws SQLException, IOException {
        if (validate(email.getText())) {
            UserCRUD usr = new UserCRUD();
            User user = new User();
            user = usr.TrouverUserParEmail(email.getText());
            if (user != null) {
                String plainpassword = getSaltString();
                System.out.println("Le nouveau mot de passe de " + user.getEmail() + "est " + plainpassword);

                usr.changePassword(plainpassword, email.getText());
               
                String body="Bonjour Mme/mr "+ user.getNom()+"\n"
                        + "Votre nouveau mot de passe est "+plainpassword;
                
                email.setVisible(false);
                System.out.println("Mot de passe envoyé par email");
            } else {
                System.out.println("Utilisateur introuvable");
            }

        }
       // NavigationEntreInterfaces nav= new NavigationEntreInterfaces();
         //   nav.navigate(event, "Login", "/gui/Login.fxml");
    }
    
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8 && salt.length() > 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    }
    

