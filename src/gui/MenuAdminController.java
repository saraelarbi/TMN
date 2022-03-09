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

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MenuAdminController implements Initializable {

    @FXML
    private Button Coadmin;
    @FXML
    private Button shop;
    @FXML
    private Button evenement;
    @FXML
    private Button forum;
    @FXML
    private Button news;
    @FXML
    private Button podcast;
    @FXML
    private Button Listusers;
    @FXML
    private Button deconnceter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Coadmin(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/AjoutCoadmin.fxml");
    }

    @FXML
    private void Shop(ActionEvent event) {
    }

    @FXML
    private void Evenement(ActionEvent event) {
    }

    @FXML
    private void Forum(ActionEvent event) {
    }

    @FXML
    private void News(ActionEvent event) {
    }

    @FXML
    private void Podcast(ActionEvent event) {
    }

    @FXML
    private void Listusers(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/ListUser.fxml");
    }

    @FXML
    private void Decoonecter(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Login.fxml");
    }
    
}
