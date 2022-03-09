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
import edu.esprit.utils.MyConnection;
import edu.esprit.utils.NavigationEntreInterfaces;
import static edu.esprit.utils.PatternEmail.validate;
import edu.esprit.utils.mdpCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
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
    private void Connecter(ActionEvent event) throws SQLException ,  IOException {
        
        String mail = email.getText(); 
        String pass = password.getText();
        if(mail.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs");
    }
       
       
        else if (validateInputs()) 
          //  if (mdpCrypt.checkpw(pass, pass+))
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
                          NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Menu.fxml");
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
                     NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/MenuAdmin.fxml");
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
                     NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/MenuCoadmin.fxml");
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
                    nav.navigate(event, "TMN", "/gui/preAcceuil.fxml");
      
}
    @FXML
    private void recupmdp(ActionEvent event) throws SQLException {
        if (email.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre email");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else if (validate(email.getText()))
        {UserCRUD usr = new UserCRUD();

            User user = new User();
            user = usr.TrouverUserParEmail(email.getText());
            if (user != null) {
                String plainpassword = getSaltString();
                System.out.println("Le nouveau mot de passe de " + user.getEmail() + " est " + plainpassword);

                usr.changePassword(plainpassword, email.getText());
                mail m = new mail();
                String body="Bonjour Mme/mr "+ user.getNom()+"\n"
                        + "Votre nouveau mot de passe est "+plainpassword;
                m.sendEmail(email.getText(), "Récupérer mot de passe", body);
                email.setVisible(false);
                System.out.println("Mot de passe envoyé par email");
                 Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Mot de passe envoyé par email");
            alert1.setHeaderText(null);
            alert1.show();
                
            } else {
                System.out.println("Utilisateur introuvable");
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Utilisateur introuvable");
            alert1.setHeaderText(null);
            alert1.show();
            }

}}
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
