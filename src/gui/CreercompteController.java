/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.entities.User;
import edu.esprit.services.UserCRUD;
import edu.esprit.utils.NavigationEntreInterfaces;
import edu.esprit.utils.PatternEmail;
import static edu.esprit.utils.PatternEmail.validate;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CreercompteController implements Initializable {

    @FXML
    private TextField TextField_nom;
    @FXML
    private DatePicker DatePicker_Datenaissance;
    @FXML
    private TextField TextField_prenom;
    @FXML
    private TextField TextField_adresse;
    @FXML
    private PasswordField TextField_password;
    @FXML
    private TextField TextField_email;
    @FXML
    private TextField TextField_tel;
    @FXML
    private AnchorPane AnchorPane_User;
    @FXML
    private Button AjouterUser;
     UserCRUD uu = new UserCRUD();
     String path = "C:\\Users\\PC\\Desktop\\TMN\\TMN\\Musique\\tmn.mp3";
     Media media = new Media(new File(path).toURI().toString());
     MediaPlayer mediaPlayer = new MediaPlayer(media);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterUser(ActionEvent event) throws IOException {
        User u =  new User();
        String str2 = DatePicker_Datenaissance.getValue().toString();
        
       u.setNom(TextField_nom.getText());
       u.setEmail( TextField_email.getText());
       u.setAdresse(TextField_adresse.getText());
       u.setPrenom(TextField_prenom.getText());
       u.setNumtel (Integer.parseInt(TextField_tel.getText()));
       u.setDatenaissance (Date.valueOf(str2));
       u.setPassword(TextField_password.getText());
        if (validateInputs()) {
        uu.ajouterUser2(u);

     Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Alert");
      alert.setHeaderText(null);
      alert.setContentText("Compte crée avec succes!");
      alert.showAndWait();
      Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title(" Connexion ")
                    .text("Votre compte est crée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
       NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Login.fxml");
        }
      
    }
    
    private boolean validateInputs() {
        if (TextField_nom.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre nom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((TextField_prenom.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre prenom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
           
         } else if (DatePicker_Datenaissance.getValue().isAfter(LocalDate.now())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre date de naissance");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
            
        } else if ((TextField_email.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
            
          } else if (!(validate(TextField_email.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
         
          } else if ((TextField_adresse.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre adresse");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
          
          
          }else if (TextField_tel.getText().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre numero");
            alert2.setHeaderText(null);
            alert2.show();
            return false;  
        
        }
            else if (TextField_tel.getText().length()!= 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Le numéro doit étre égale à 8 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;  
        
        } else if ((TextField_password.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;

        } else if (TextField_password.getText().length() < 6) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
       
  
       
        }

        return true;
    }
     @FXML
    private void Retour(ActionEvent event) throws IOException {
    NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/preAcceuil.fxml");
      
}
    @FXML
    private void Musique(ActionEvent event) {
        mediaPlayer.play();
        
    }
     @FXML
    private void Pause(ActionEvent event) {
                mediaPlayer.pause();
    }


    
}
