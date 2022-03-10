/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Commentaire;
import entities.Blog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author bolba
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label commentaire;
    private Commentaire comm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Commentaire comen) {
        comm = comen;
        commentaire.setText(comm.getDescription().toString());
       
    }
    
}
