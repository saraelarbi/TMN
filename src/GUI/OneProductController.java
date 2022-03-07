/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class OneProductController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label type;
    @FXML
    private Button Commander;
    private Produit Prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Produit P) {
        Prod = P;
        nom.setText(P.getNom());
        prix.setText(String.valueOf(P.getPrix()));
        type.setText(String.valueOf(P.getType()));
        String picture ="file:" +  P.getImage();
        Image imagee = new Image(picture, 110, 110, false, true);
        image.setImage(imagee);
        
       
        CommandeFController.getIdd(Prod.getIdProduit());
    }

    @FXML
    private void Passer_Commande(ActionEvent event) throws IOException {
        int x = CommandeFController.getIdd(Prod.getIdProduit());
        System.out.println(x);
        Parent root = FXMLLoader.load(getClass().getResource("CommandeF.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
