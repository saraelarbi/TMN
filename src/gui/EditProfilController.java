/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.nio.sctp.Notification;
import edu.esprit.entities.User;
import edu.esprit.services.UserCRUD;
import gui.LoginController;
import edu.esprit.utils.MyConnection;
import edu.esprit.utils.NavigationEntreInterfaces;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.util.Duration;
import javax.swing.JFrame;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EditProfilController implements Initializable {

    @FXML
    private AnchorPane AnchorPane_Listuser;
    @FXML
    private Button supprimecompte;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private DatePicker date;
    @FXML
    private TextField adresse;
    @FXML
    private TextField num;
    private Connection cnx;
    @FXML
    private PasswordField password;
    @FXML
    private Button enregistrer;
        UserCRUD us = new UserCRUD();
    public int id_user;
    @FXML
    private Button editprofil;
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
    private Button deconnceter;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            
            UserCRUD us= new UserCRUD();
            User u=us.getUserlogged();
            this.id_user = u.getIdU();
            nom.setText(u.getNom());
            prenom.setText(u.getPrenom());
            email.setText(u.getEmail());
            date.getEditor().setText(String.valueOf(u.getDatenaissance()));
            adresse.setText(u.getAdresse());
            num.setText(Integer.toString(u.getNumtel()));
            password.setText(u.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(EditProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<User> UserList() {

        cnx = MyConnection.getInstance().getCnx();

        ObservableList<User> UserList = FXCollections.observableArrayList();

        String req = "SELECT * FROM user";

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            User user;

            while (rs.next()) {
                user = new User(rs.getInt("idU"), rs.getString("nom"), rs.getString("prenom"),  rs.getInt("numtel"), rs.getString("email"), rs.getString("adresse"), rs.getDate("Datenaissance"), rs.getString("password"));
                UserList.add(user);

            }

        } catch (SQLException e) {
        }

        return UserList;
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException, IOException {

        User user = us.getUserlogged();
        int id_log = user.getIdU();
        
        
        us.supprimer(user.getIdU());
         Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("A bientot!");
            alert2.setContentText("Vous avez supprimer votre compte");
            alert2.setHeaderText(null);
            alert2.show();
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "Login", "/gui/Acceuil.fxml");
        UserCRUD us = new UserCRUD();
        us.loggedOut();
        
     
    }

    @FXML
    private void Enregistrer(ActionEvent event) {
        
        User u = new User();
        UserCRUD us = new UserCRUD();  
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setEmail(email.getText());
        u.setAdresse(adresse.getText());
        u.setNumtel(Integer.parseInt(num.getText()));
        u.setDatenaissance(java.sql.Date.valueOf(date.getEditor().getText()));
        //date.getEditor().setText(String.valueOf(u.getDatenaissance()));
        u.setPassword(password.getText());
        u.setIdU(this.id_user);
        us.modifier(u);
         Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Success");
            alert2.setContentText("Vous avez modifié votre profil");
            alert2.setHeaderText(null);
            alert2.show();
        Image img = new Image("/tmn2.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Modifier Coadmin")
                .text("vous avez modifié votre profil").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void Editprofil(ActionEvent event) {
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
    private void Decoonecter(ActionEvent event) throws IOException {
         NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Login.fxml");
    }
      

   
}
