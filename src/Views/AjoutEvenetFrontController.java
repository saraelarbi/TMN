/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Evenement;
import Service.EvenementService;
import java.io.File;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjoutEvenetFrontController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextField Prix;
    @FXML
    private TextArea Description;
    @FXML
    private Button button_inserer_image;
    @FXML
    private TextField Categorie;
    @FXML
    private TextField Nbr_Place;
    @FXML
    private TextField Image;
    @FXML
    private Button ajouterBB;
    private javafx.scene.image.Image image;
    private FileChooser filechooser;
    private File file;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insertImage(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        button_inserer_image.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            if (file != null) {
                //String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                Image.setText(F);
                //     image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                //     img1.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }

    @FXML
    private void AjouterButton(ActionEvent event) {
         if (Titre.getText().isEmpty() || Categorie.getText().isEmpty() || Description.getText().isEmpty()
                || Image.getText().isEmpty() || Prix.getText().isEmpty()  ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
           
        } else if ( parseFloat(Prix.getText()) < 0    ) {
             showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Prix doit etre positive");
        }
        else {
        Evenement P = new Evenement(Titre.getText(), Categorie.getText(), Description.getText(), Image.getText(), parseFloat(Prix.getText()), Entite.Etat.Rejete, Integer.parseInt(Nbr_Place.getText()));
        EvenementService Ps = new EvenementService();
        Ps.Ajouter(P);
        System.out.println("Evenement ajoutééé");
         }
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
}
