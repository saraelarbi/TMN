/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Publication;
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
 * @author ffsga
 */
public class OnePublicationFXMLController implements Initializable {

    @FXML
    private ImageView image_PB;
    @FXML
    private Label titre_PB;
    @FXML
    private Label source_PB;
    @FXML
    private Button reclamer_PB;
    @FXML
    private Label Date_PB;
    @FXML
    private Label Desc_PB;
    @FXML
    private Label categorie_PB;
    
    private Publication pubs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    
    public void setData(Publication Pub) {
        pubs = Pub;
        Date_PB.setText(Pub.getDate_Pub().toString());
        titre_PB.setText(Pub.getTitre_Pub());
        Desc_PB.setText(Pub.getDesc_Pub());
        source_PB.setText(Pub.getSource_Pub());
        categorie_PB.setText(Pub.getCategorie_Pub());
        String picture ="file:" +  Pub.getImage_Pub();
        Image image1 = new Image(picture, 250, 180, false, true);
        image_PB.setImage(image1);
        
       
        FrontReclamationFXMLController.getIdPB(Pub.getIdPub());
    }



    @FXML
    private void Faire_Reclamation(ActionEvent event) throws IOException {
        int x = FrontReclamationFXMLController.getIdPB(pubs.getIdPub());
        System.out.println(x);
        Parent root = FXMLLoader.load(getClass().getResource("FrontReclamationFXML.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();    
    }
    
}
