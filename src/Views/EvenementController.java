/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Evenement;
import Service.EvenementService;
//import static Views.AjoutEvenetFrontController.showAlert;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class EvenementController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextField Prix;
    @FXML
    private TextField Etat;
    @FXML
    private Button AjouterEvent;
    @FXML
    private Button ModifierEvent;
    @FXML
    private Button SupprimerEvent;
    @FXML
    private TableView<Evenement> AffichageEvent;
    @FXML
    private TextField Description;
    @FXML
    private Button button_inserer_image;
    @FXML
    private TextField Categorie;
    @FXML
    private TextField Nbr_Place;
    @FXML
    private TextField Image;
    @FXML
    private TableColumn<?, ?> Titre1;
    @FXML
    private TableColumn<?, ?> Categorie2;
    @FXML
    private TableColumn<?, ?> Description2;
    @FXML
    private TableColumn<?, ?> Prix2;
    @FXML
    private TableColumn<?, ?> Etat2;
    @FXML
    private TableColumn<?, ?> Nbr_Place2;
    int index = -1;
    private javafx.scene.image.Image image;
    private FileChooser filechooser;
    private File file;
    @FXML
    private Button ttt;
    @FXML
    private Button Rateeeee;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterEvent(ActionEvent event) {
        if (Titre.getText().isEmpty() || Categorie.getText().isEmpty() || Description.getText().isEmpty()
                || Image.getText().isEmpty() || Prix.getText().isEmpty()    ) {
        showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");           
        }else {
        Evenement P = new Evenement(Titre.getText(), Categorie.getText(), Description.getText(), Image.getText(), parseFloat(Prix.getText()),Entite.Etat.valueOf(Etat.getText()), Integer.parseInt(Nbr_Place.getText()));
        EvenementService Ps = new EvenementService();
        Ps.Ajouter(P);
        System.out.println("Evenement ajoutééé");
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
              }
    }

    public ObservableList<Evenement> getlist() throws SQLException {
        EvenementService Ps = new EvenementService();
        ObservableList<Evenement> listproduit = FXCollections.observableArrayList(Ps.Afficher());
        return listproduit;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Evenement> list = getlist();
        AffichageEvent.setItems(list);
        Titre1.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        Categorie2.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        Description2.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Prix2.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Etat2.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        Nbr_Place2.setCellValueFactory(new PropertyValueFactory<>("Nbr_Place"));

    }

    @FXML
    private void ModifierEvent(ActionEvent event) {
        Evenement Prod = new Evenement();
        Prod.setId_Evenement(AffichageEvent.getSelectionModel().getSelectedItem().getId_Evenement());
        Prod.setTitre(Titre.getText());
        Prod.setCategorie(Categorie.getText());
        Prod.setDescription(Description.getText());
        Prod.setImage(Image.getText());
        Prod.setPrix(parseFloat(Prix.getText()));
        Prod.setEtat(Entite.Etat.valueOf(Etat.getText()));
        Prod.setNbr_Place(Integer.parseInt(Nbr_Place.getText()));
        EvenementService ps = new EvenementService();
        ps.Modifier(Prod);
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) throws SQLException {
        EvenementService st = new EvenementService();
        st.Supprimer(AffichageEvent.getSelectionModel().getSelectedItem().getId_Evenement());
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
        AfficherTable();
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

//    @FXML
//    private void AffichageEvent(SortEvent<C> event) {
//    }
    @FXML
    private void getSelected() {
        index = AffichageEvent.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        Titre.setText((String) Titre1.getCellData(index));
        Categorie.setText((String) Categorie2.getCellData(index));
        Description.setText((String) Description2.getCellData(index));
        Prix.setText(String.valueOf(Prix2.getCellData(index)));
      //  Etat.setText((String) Etat2.getCellData(index));
        Nbr_Place.setText(String.valueOf(Nbr_Place2.getCellData(index)));
    }

    @FXML
    private void ReservationBB(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AffichageReservation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void Ratee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AffichageRate.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void recherche(KeyEvent event) throws SQLException {
        ObservableList<Evenement> list = getlist();
        FilteredList<Evenement> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (p.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(p.getPrix()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(AffichageEvent.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        AffichageEvent.setItems(sortedData);
    }
    
    
        public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    

}
