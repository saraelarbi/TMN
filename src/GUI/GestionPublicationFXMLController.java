/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Publication;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import services.ServicePublication;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class GestionPublicationFXMLController implements Initializable {

    @FXML
    private TextField TextField_ID_PUB;
    @FXML
    private DatePicker DatePicker_Date_PUB;
    @FXML
    private TextField TextField_TITRE_PUB;
    @FXML
    private TextField TextField_SOURCE_PUB;
    @FXML
    private TextField TextField_CATEGORIE_PUB;
    @FXML
    private Button AjouterPublication;
    @FXML
    private Button ModifierPublication;
    @FXML
    private Button SupprimerPublication;
    @FXML
    private TableView<Publication> AffichagePublication;
    @FXML
    private TableColumn<Publication, Integer> idPub;
    @FXML
    private TableColumn<Publication, Date> date_Pub;
    @FXML
    private TableColumn<Publication, String> titre_Pub;
    @FXML
    private TableColumn<Publication, String> desc_Pub;
    @FXML
    private TableColumn<Publication, String> source_Pub;
    @FXML
    private TableColumn<Publication, String> categorie_Pub;
    @FXML
    private TableColumn<Publication, String> image_Pub;
    @FXML
    private TextField TextField_DESC_PUB;
    @FXML
    private Button button_inserer_image;

    private Connection cnx;
    @FXML
    private AnchorPane AnchorPane_Publication;
    @FXML
    private Label file_path;
    @FXML
    private ImageView imageview_Publication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField_ID_PUB.setDisable(true);
        AffichagePublication();
    }

    @FXML
    private void AjouterPublication(ActionEvent event) {

        if (DatePicker_Date_PUB.getEditor().getText().isEmpty()
                | TextField_TITRE_PUB.getText().isEmpty()
                | TextField_DESC_PUB.getText().isEmpty()
                | TextField_SOURCE_PUB.getText().isEmpty()
                | TextField_CATEGORIE_PUB.getText().isEmpty()
                | imageview_Publication.getImage() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
            String str2 = DatePicker_Date_PUB.getValue().toString();

            Date datepub1 = Date.valueOf(str2);
            ServicePublication sPub = new ServicePublication();
            Publication pb = new Publication(1, datepub1, TextField_TITRE_PUB.getText(), TextField_DESC_PUB.getText(), TextField_SOURCE_PUB.getText(), TextField_CATEGORIE_PUB.getText(), file_path.getText());
            sPub.ajouterPB(pb);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Success Publication Ajouté!");
                alert.showAndWait();

            AffichagePublication();
            clear();
        }
    }

    @FXML
    private void ModifierPublication(ActionEvent event) {

            if (DatePicker_Date_PUB.getEditor().getText().isEmpty()
                    | TextField_TITRE_PUB.getText().isEmpty()
                    | TextField_DESC_PUB.getText().isEmpty()
                    | TextField_SOURCE_PUB.getText().isEmpty()
                    | TextField_CATEGORIE_PUB.getText().isEmpty()
                    | imageview_Publication.getImage() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs!");
                alert.showAndWait();

            } else {

                 String str2 = DatePicker_Date_PUB.getValue().toString();
                 String str3 = TextField_ID_PUB.getText();
                 int idpb = parseInt(str3);
            Date datepub1 = Date.valueOf(str2);
            ServicePublication sPub = new ServicePublication();
            Publication pb = new Publication(idpb, datepub1, TextField_TITRE_PUB.getText(), TextField_DESC_PUB.getText(), TextField_SOURCE_PUB.getText(), TextField_CATEGORIE_PUB.getText(), file_path.getText());
            sPub.modifierPB(pb);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Success Publication Modifié!");
                alert.showAndWait();

                AffichagePublication();
                clear();

            }
        
    }

    @FXML
    private void SupprimerPublication(ActionEvent event) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous supprimer cette publication ?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {

                ServicePublication sPub = new ServicePublication();
                String idpb = TextField_ID_PUB.getText();
                sPub.supprimerPB(parseInt(idpb));
                

            } else {

                return;

            }

            AffichagePublication();
            clear();

        
    }

    public ObservableList<Publication> PublicationList() {

        cnx = MyDB.getInstance().getConnection();

        ObservableList<Publication> PublicationList = FXCollections.observableArrayList();

        String req = "SELECT * FROM publication";

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            Publication publication;

            while (rs.next()) {

                publication = new Publication(rs.getInt("idPub"), rs.getDate("date_Pub"), rs.getString("titre_Pub"), rs.getString("desc_Pub"), rs.getString("source_Pub"), rs.getString("categorie_Pub"), rs.getString("image_Pub"));

                PublicationList.add(publication);

            }

        } catch (SQLException e) {
        }

        return PublicationList;

    }

    @FXML
    public void AffichagePublication() {
        ObservableList<Publication> PublicationList = PublicationList();

        idPub.setCellValueFactory(new PropertyValueFactory<>("idPub"));
        date_Pub.setCellValueFactory(new PropertyValueFactory<>("date_Pub"));
        titre_Pub.setCellValueFactory(new PropertyValueFactory<>("titre_Pub"));
        desc_Pub.setCellValueFactory(new PropertyValueFactory<>("desc_Pub"));
        source_Pub.setCellValueFactory(new PropertyValueFactory<>("source_Pub"));
        categorie_Pub.setCellValueFactory(new PropertyValueFactory<>("categorie_Pub"));
        image_Pub.setCellValueFactory(new PropertyValueFactory<>("image_Pub"));

        AffichagePublication.setItems(PublicationList);

    }

    @FXML
    public void insertImage() {

        FileChooser open = new FileChooser();

        Stage stage = (Stage) AnchorPane_Publication.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            imageview_Publication.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    public void selectPublication() {

        Publication publication = AffichagePublication.getSelectionModel().getSelectedItem();

        int num = AffichagePublication.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        TextField_ID_PUB.setText(String.valueOf(publication.getIdPub()));
        //DatePicker_Date_PUB.setText(String.(publication.getDate_Pub()));
        DatePicker_Date_PUB.getEditor().setText(String.valueOf(publication.getDate_Pub()));
        TextField_TITRE_PUB.setText(publication.getTitre_Pub());
        TextField_DESC_PUB.setText(publication.getDesc_Pub());
        TextField_SOURCE_PUB.setText(publication.getSource_Pub());
        TextField_CATEGORIE_PUB.setText(publication.getCategorie_Pub());

        String picture = "file:" + publication.getImage_Pub();

        Image image = new Image(picture, 110, 110, false, true);

        imageview_Publication.setImage(image);

        String path = publication.getImage_Pub();

        file_path.setText(path);

    }

    public void clear() {

        TextField_ID_PUB.setText("");
        DatePicker_Date_PUB.getEditor().getText();
        TextField_TITRE_PUB.setText("");
        TextField_DESC_PUB.setText("");
        TextField_SOURCE_PUB.setText("");
        TextField_CATEGORIE_PUB.setText("");
        imageview_Publication.setImage(null);
        file_path.setText("");

    }

}
