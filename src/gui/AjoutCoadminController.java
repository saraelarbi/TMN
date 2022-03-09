/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.entities.Coadmin;
import edu.esprit.entities.Fonction;
import edu.esprit.services.CoadminCRUD;
import edu.esprit.services.FonctionCRUD;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.util.Date;*
import java.sql.Date;
import java.lang.NullPointerException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import edu.esprit.utils.MyConnection;
import edu.esprit.utils.NavigationEntreInterfaces;
import static edu.esprit.utils.PatternEmail.validate;
import java.io.IOException;
//import java.awt.HeadlessException;
//import java.io.FileOutputStream;
//import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.time.LocalDate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
//import javax.management.Notification;
//import javax.swing.JOptionPane;
//import javax.swing.event.DocumentListener;
//import javax.swing.event.UndoableEditListener;
//import javax.swing.text.AttributeSet;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.Document;
//import javax.swing.text.Position;
//import javax.swing.text.Segment;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class AjoutCoadminController implements Initializable {

    @FXML
    private TextField TextField_nom;
    @FXML
    private TextField TextField_ft;
    @FXML
    private TextField TextField_prenom;
    @FXML
    private TextField TextField_email;
    @FXML
    private TextField TextField_adresse;
    @FXML
    private TextField chercher;
    @FXML
    private TextField TextField_password;
    private Connection cnx;
    @FXML
    private DatePicker DatePicker_Datenaissance;
    @FXML
    private TableView<Coadmin> AffichageCoadmin;
    @FXML
    private TableView<Fonction> AffichageFonction;
    @FXML
    private TableColumn<Fonction, Integer> afficheidfonction;
    @FXML
    private TableColumn<Fonction, String> afficheft;
    @FXML
    private TableColumn<Coadmin, Integer> afficheidC;
    @FXML
    private TableColumn<Coadmin, String> affichenom;
    @FXML
    private TableColumn<Coadmin, String> afficheprenom;
    @FXML
    private TableColumn<Coadmin, Date> affichedatenaissance;
    @FXML
    private TableColumn<Coadmin, String> afficheemail;
    @FXML
    private TableColumn<Coadmin, String> afficheadresse;
    @FXML
    private TableColumn<Coadmin, String> affichefonction;
    @FXML
    private ComboBox<String> combo;
    private String str2;
    private Fonction selectedFunction;
    private Coadmin selectedcoadmin;

    CoadminCRUD cc = new CoadminCRUD();
    int num = -1;
    FilteredList<Coadmin> filter = new FilteredList<>(CoadminList(), e -> true);
    SortedList<Coadmin> sort = new SortedList<>(filter);
    String path = "C:\\Users\\PC\\Desktop\\TMN\\TMN\\Musique\\tmn.mp3";
     Media media = new Media(new File(path).toURI().toString());
     MediaPlayer mediaPlayer = new MediaPlayer(media);
    @FXML
    private AnchorPane AnchorPane_Coadmin;
    @FXML
    private Button AjouterCoadmin;
    @FXML
    private Button ModifierCoadmin;
    @FXML
    private Button SupprimerCoadmin;
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
    @FXML
    private Button musique;
    @FXML
    private Button pause;
    @FXML
    private Button Retour;
    @FXML
    private AnchorPane AnchorPane_Fonction;
    @FXML
    private Button AjouterFonction;
    @FXML
    private Button ModifierFonction;
    @FXML
    private Button SupprimerFonction;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AffichageCoadmin();
        AffichageFonction();
        AffichageFonction.setOnMouseClicked((MouseEvent event) -> {
        this.selectFonction(event);
    });
        
    }

    public ObservableList<Coadmin> CoadminList() {

        cnx = MyConnection.getInstance().getCnx();

        ObservableList<Coadmin> CoadminList = FXCollections.observableArrayList();

        String req = "SELECT * FROM coadmin";

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            Coadmin coadmin;

            while (rs.next()) {

                coadmin = new Coadmin(rs.getInt("idC"), rs.getInt("id_fonction"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"), rs.getDate("Datenaissance"), rs.getString("password"));
                CoadminList.add(coadmin);

            }

        } catch (SQLException e) {
        }

        return CoadminList;
    }
    @FXML
    public void AjouterCoadmin(ActionEvent event) {
        Coadmin c = new Coadmin();
        FonctionCRUD f = new FonctionCRUD();

        String str2 = DatePicker_Datenaissance.getValue().toString();

        c.setNom(TextField_nom.getText());
        c.setEmail(TextField_email.getText());
        c.setAdresse(TextField_adresse.getText());
        c.setPrenom(TextField_prenom.getText());
        c.setDatenaissance(Date.valueOf(str2));
        System.out.println(combo.getValue());
        c.setId_fonction(f.getid(combo.getValue()));
        c.setPassword(TextField_password.getText());
        if (validateInputs()) {
            cc.ajouterCoadmin2(c);
            Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajout Coadmin")
                    .text("Coadmin Ajouté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Coadmin ajouté avec succes!");
            alert.showAndWait();
            AffichageCoadmin();
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
        }   else if (DatePicker_Datenaissance.getValue().isAfter(LocalDate.now())) {
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
        }
        else if ((TextField_adresse.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre adresse");
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
        AffichageCoadmin();
        return true;
    }
   
    @FXML
    public void ModifierCoadmin(ActionEvent event) {
        Coadmin c = new Coadmin();
        FonctionCRUD f = new FonctionCRUD();
        CoadminCRUD co = new CoadminCRUD();
        c.setIdC(this.selectedcoadmin.getIdC());
        c.setNom(TextField_nom.getText());
        c.setPrenom(TextField_prenom.getText());
        c.setEmail(TextField_email.getText());
        c.setAdresse(TextField_adresse.getText());
        c.setDatenaissance(java.sql.Date.valueOf(DatePicker_Datenaissance.getEditor().getText()));
        c.setId_fonction(f.getid(combo.getValue()));
        c.setPassword(TextField_password.getText());
        co.modifier(c);
        Image img = new Image("/tmn2.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Modifier Coadmin")
                .text("Coadmin modifié").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        AffichageCoadmin();
    }
    @FXML
    public void SupprimerCoadmin(ActionEvent event) {
        Coadmin ca = AffichageCoadmin.getSelectionModel().getSelectedItem();
        cc.supprimer(ca.getIdC());
        Image img = new Image("/tmn2.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Suppression Coadmin")
                .text("Coadmin supprimé").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        AffichageCoadmin();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Coadmin supprimé avec succes!");
        alert.showAndWait();
    }

    public ObservableList<Fonction> FonctionList() {

        cnx = MyConnection.getInstance().getCnx();

        ObservableList<Fonction> TypepubList = FXCollections.observableArrayList();

        String req = "SELECT * FROM fonction";

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            Fonction fonction;

            while (rs.next()) {
                System.out.println(rs.getInt("id"));

                fonction = new Fonction(rs.getInt("id"), rs.getString("fonction"));

                TypepubList.add(fonction);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return TypepubList;
    }
    @FXML
    public void AffichageCoadmin() {
        ObservableList<Coadmin> CoadminList = CoadminList();
               AffichageCoadmin.setItems(CoadminList);


        afficheidC.setCellValueFactory(new PropertyValueFactory<>("idC"));
        affichenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        afficheprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        affichedatenaissance.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        afficheemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        afficheadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        FonctionCRUD f = new FonctionCRUD();
        affichefonction.setCellValueFactory(new PropertyValueFactory<>("id_fonction"));
        ObservableList<Fonction> fcts = FonctionList();
        ObservableList<String> noms = FXCollections.observableArrayList();
        for(int i = 0; i<fcts.size();i++){
            noms.add(fcts.get(i).getFonction());
        }
       combo.setItems(noms);
       AffichageCoadmin.setItems(CoadminList);
    }
    @FXML
    public void Chercher(ActionEvent event) {
        AffichageCoadmin.setItems(sort);
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });
            sort.comparatorProperty().bind(AffichageCoadmin.comparatorProperty());
            AffichageCoadmin.setItems(sort);
        });
    }
    @FXML
    public void selectCoadmin() {
        FonctionCRUD f = new FonctionCRUD();
        Coadmin coadmin = AffichageCoadmin.getSelectionModel().getSelectedItem();
        this.selectedcoadmin = coadmin;
        int num = AffichageCoadmin.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        TextField_nom.setText(String.valueOf(affichenom.getCellData(num)));
        TextField_email.setText(String.valueOf(afficheemail.getCellData(num)));
        TextField_prenom.setText(String.valueOf(afficheprenom.getCellData(num)));
        TextField_adresse.setText(String.valueOf(afficheadresse.getCellData(num)));
        DatePicker_Datenaissance.getEditor().setText(String.valueOf(coadmin.getDatenaissance()));
        TextField_password.setText(coadmin.getPassword());
        combo.setValue(f.gettype(coadmin.getId_fonction()));
    }
    @FXML
    public void AjouterFonction(ActionEvent event) {
        Fonction f = new Fonction();
        FonctionCRUD ft = new FonctionCRUD();
        f.setFonction(TextField_ft.getText());
        ft.ajouterFonction(f);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Type pub ajoutée");
        AffichageFonction();
    }
    @FXML
    public void ModifierFonction(ActionEvent event) {
        Fonction f = new Fonction();
        FonctionCRUD ft = new FonctionCRUD();
        System.out.println(this.selectedFunction.getId());
        f.setId(this.selectedFunction.getId());
        f.setFonction(TextField_ft.getText());
        ft.modifierFonction(f);
        AffichageFonction();
    }
    @FXML
    public void SupprimerFonction(ActionEvent event) {
        Fonction fonction = AffichageFonction.getSelectionModel().getSelectedItem();
        FonctionCRUD tp = new FonctionCRUD();
        tp.supprimerFonction(fonction.getId());
        AffichageFonction();
    }
@FXML
    public void AffichageFonction() {
        ObservableList<Fonction> Fonctionlist = FonctionList();
        afficheidfonction.setCellValueFactory(new PropertyValueFactory<>("id"));
        afficheft.setCellValueFactory(new PropertyValueFactory<>("fonction"));
        AffichageFonction.setItems(Fonctionlist);
    }
    public void selectFonction(MouseEvent event) {
         this.selectedFunction = AffichageFonction.getSelectionModel().getSelectedItem();
         Fonction type = selectedFunction;
        int num = AffichageFonction.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        FonctionCRUD ft = new FonctionCRUD();
        TextField_ft.setText(type.getFonction());
    }
        @FXML
    private void Musique(ActionEvent event) {
        mediaPlayer.play();
        
    }
     @FXML
    private void Pause(ActionEvent event) {
                mediaPlayer.pause();
    }
    @FXML
    private void Retour(ActionEvent event) throws IOException {
                NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Menu.fxml");
    }
    @FXML
    private void Retour1(ActionEvent event) throws IOException {
                NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/gui/Menu.fxml");
    }

    @FXML
    private void Coadmin(ActionEvent event) {
        
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
