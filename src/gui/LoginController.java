/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.entities.Coadmin;
import edu.esprit.entities.User;
import edu.esprit.services.CoadminCRUD;
import edu.esprit.services.UserCRUD;
import edu.esprit.services.mail;
import static edu.esprit.services.mail.prepareMessage;
import edu.esprit.utils.MyConnection;
import edu.esprit.utils.NavigationEntreInterfaces;
import static edu.esprit.utils.PatternEmail.validate;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.management.Notification;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginController implements Initializable {
  @FXML
  private TextField email;
  @FXML
  private TextField password;
  @FXML
  private Button connecter;
  @FXML
  
  PreparedStatement pst;
  ResultSet rs;
      UserCRUD us = new UserCRUD();

   Connection cnx;
   public LoginController() {
       cnx = MyConnection.getInstance().getCnx();
   }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       
        
    
    @FXML
    private void Connecter(ActionEvent event) throws SQLException {
        
        String mail = email.getText(); 
        String pass = password.getText();
        if(mail.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs");
    }
       
       
        else if (validateInputs()) 
        {  
                try {
                    Class.forName("com.mysql.jdbc.Driver");
            
                    pst = cnx.prepareStatement("select * from user where email=? and password=?");
                    pst.setString(1, mail);
                    pst.setString(2, pass);
                    rs = pst.executeQuery();
                    User u = us.searchByPseudoPassU(mail, pass);
                    if(rs.next())
                    {  us.loggedIn(u);
                        JOptionPane.showMessageDialog(null, "Connecté avec succes ");
                        Image img = new Image("/tmn2.jpg");
                      Notifications notificationBuilder = Notifications.create()
                    .title(" Connexion ")
                    .text("Vous etes connecté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
                    notificationBuilder.darkStyle();
                      notificationBuilder.show();
                    }
                    else 
                    {
                        pst = cnx.prepareStatement("select * from admin where email = ? and password = ?");
                        pst.setString(1 , mail);
                        pst.setString(2 , pass);
                        rs = pst.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Connecté avec succes comme Admin");
                            Image img = new Image("/tmn2.jpg");
                    Notifications notificationBuilder = Notifications.create()
                    .title("Connexion ")
                    .text("Vous etes connecté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
                     notificationBuilder.darkStyle();
                     notificationBuilder.show();
                        }
                    
                    else 
                    {
                        pst = cnx.prepareStatement("select * from coadmin where email = ? and password = ?");
                        pst.setString(1 , mail);
                        pst.setString(2 , pass);
                        rs = pst.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Connecté avec succes comme CO-Admin");
                            Image img = new Image("/tmn2.jpg");
                    Notifications notificationBuilder = Notifications.create()
                    .title("Connexion ")
                    .text("Vous etes connecté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
                     notificationBuilder.darkStyle();
                     notificationBuilder.show();
                        }
                        else{                  
                            JOptionPane.showMessageDialog(null, "Connexion echoué");
                        email.setText("");
                        password.setText("");
                        email.requestFocus();;
                        }
                        
                    }}
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
    }
    
    
    
    private boolean validateInputs() throws SQLException {

        if (email.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre email");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        else if (!(validate(email.getText())))
        {Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;}
        
        if (password.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre mot de passe");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        
        return true;

    }
    @FXML
    private void Retour(ActionEvent event) throws IOException {
    NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Acceuil.fxml");
      
}
    @FXML
    private void recupmdp(ActionEvent event) {
        if (email.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre email");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else if (!(validate(email.getText())))
        {Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            }
        
        else {
        String txt = "Bonjour voici votre mdp  ";
            String sub = "Recup mdp";
            String destinataire = "ghazi.benjamaa@esprit.tn";
            mail m = new mail(txt, sub, destinataire);
            Message msg = prepareMessage(m.getSession(), m.getMail(), destinataire, txt, sub);
            System.out.println(msg);
            try {
                Transport.send(msg);
            } catch (MessagingException ex) {
                // Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("MAIL ENVOYE");
}}
   
}
