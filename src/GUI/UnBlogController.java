/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Blog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class UnBlogController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label desc;
    @FXML
    private Label date;
    @FXML
    private Label categ;
    Blog blog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void Afficher_Produit_Front(Blog b) {
        blog = b;
        desc.setText(b.getDescription());
        date.setText(String.valueOf(b.getDate_de_publication()));
        categ.setText(String.valueOf(b.getCategorie()));
        String picture ="file:" +  b.getimage();
        Image imagee = new Image(picture, 110, 110, false, true);
        image.setImage(imagee);
        
       
    }
    
}
