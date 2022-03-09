/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.utils.NavigationEntreInterfaces;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PreAcceuilController implements Initializable {

    @FXML
    private AnchorPane AnchorPane_Coadmin;
    @FXML
    private Button login;
    @FXML
    private Button CreerCompte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void Login(ActionEvent event) throws IOException {
         NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Login.fxml");
    }

    @FXML
    private void CreerCompte(ActionEvent event) throws IOException {
         NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Creercompte.fxml");
    }
}
