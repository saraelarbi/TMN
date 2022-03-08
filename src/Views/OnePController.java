/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Evenement;
import Entite.Rate_Evenement;
import Service.EvenementService;
import Service.Rate_EvenementService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class OnePController implements Initializable {

    @FXML
    private ImageView ImageLab;
    @FXML
    private Label Titrelab;
    @FXML
    private Label Categorielab;
    @FXML
    private Label Descriptionlab;
    @FXML
    private Label Prixlab;
    @FXML
    private Rating rate;
    private Evenement Prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Evenement P) {
        Prod = P;

        Titrelab.setText(P.getTitre());
        Prixlab.setText(String.valueOf(P.getPrix()));
        Categorielab.setText(P.getCategorie());
        Descriptionlab.setText(P.getDescription());
       // Image image = new Image(getClass().getResourceAsStream(P.getImage()));
       // ImageLab.setImage(image);
        rate.setRating(P.getRate());
        ReserverController.getIdd(Prod.getId_Evenement());
        
        InputStream stream;
            try {
                String image2 = P.getImage().substring​(6,P.getImage().length());
                stream = new FileInputStream(image2);
                Image image = new Image(stream);
                ImageLab.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void Reserver(ActionEvent event) throws IOException {
        int x = ReserverController.getIdd(Prod.getId_Evenement());
        System.out.println(x);
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Reserver.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void Rating(ActionEvent event) {
        int x = ReserverController.getIdd(Prod.getId_Evenement());
        float Y = 0 ; 
        EvenementService Ps = new EvenementService();
        Rate_EvenementService Psss = new Rate_EvenementService();
        
        Evenement P = Ps.TrouverById(x);
        P.setRate((float) rate.getRating());
        Rate_Evenement RR = new Rate_Evenement(Prod.getId_Evenement(), (int) (float) rate.getRating()) ; 
        Psss.Ajouter(RR);
        
        // System.out.println((int) rate.getRating());
        //System.out.println("jdyyyyyyyyyyyyyyyd" + P.getAvis());
     //   Y = Ps.Rating(P);
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("merci pour voter").graphic(null).hideAfter(javafx.util.Duration.seconds(1))
               .position(Pos.CENTER)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
     //  avis.setText(String.valueOf(Y));
       rate.setRating(Y);
    }

}
