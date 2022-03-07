/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Publication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
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
    @FXML
    private ImageView logoTMN;
    @FXML
    private TextField TextField_Recherche_Pub;
    @FXML
    private ProgressBar ProgressBar;
    @FXML
    private Button Button_PubToPDF;
    @FXML
    private Button button_podc;

    //ProgressBar proBar = new ProgressBar();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField_ID_PUB.setDisable(true);
        AffichagePublication();
        ProgressBar.setProgress(0);
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
            StartProgrssBar();
            sPub.ajouterPB(pb);
            
            
            Notificationmanager(0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Success Publication Ajouté!");
            alert.showAndWait();

            ProgressBar.setProgress(0);

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous Modifier cette publication ?");

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.OK) {
            ServicePublication sPub = new ServicePublication();
            String str2 = DatePicker_Date_PUB.getValue().toString();
            String str3 = TextField_ID_PUB.getText();
            int idpb = parseInt(str3);
            Date datepub1 = Date.valueOf(str2);
            
            
            Publication pb = new Publication(idpb, datepub1, TextField_TITRE_PUB.getText(), TextField_DESC_PUB.getText(), TextField_SOURCE_PUB.getText(), TextField_CATEGORIE_PUB.getText(), file_path.getText());
            StartProgrssBar();
            sPub.modifierPB(pb);
            Notificationmanager(1);
           

           
            AffichagePublication();
            clear();}
         ProgressBar.setProgress(0); 

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
            StartProgrssBar();
            sPub.supprimerPB(parseInt(idpb));
             Notificationmanager(2);

        } else {

            return;

        }

        AffichagePublication();
        clear();
        ProgressBar.setProgress(0);

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

        FilteredList<Publication> filteredData = new FilteredList<>(PublicationList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        TextField_Recherche_Pub.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(publication -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (publication.getTitre_Pub().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (publication.getDesc_Pub().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (publication.getSource_Pub().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (publication.getCategorie_Pub().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Publication> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(AffichagePublication.comparatorProperty());
        AffichagePublication.setItems(sortedData);

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
        DatePicker_Date_PUB.getEditor().setText("");
        TextField_TITRE_PUB.setText("");
        TextField_DESC_PUB.setText("");
        TextField_SOURCE_PUB.setText("");
        TextField_CATEGORIE_PUB.setText("");
        imageview_Publication.setImage(null);
        file_path.setText("");

    }

    public void StartProgrssBar() {
        double ii = 0;

        do {

            ii += 0.001;
            ProgressBar.setProgress(1);
        } while (1 > (ii));

    }

    @FXML
    private void ConvertToPDF(ActionEvent event) {
        try {
            Publication publication = AffichagePublication.getSelectionModel().getSelectedItem();
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/ffsga/Documents/NetBeansProjects/TMN/images/TMNpublication.pdf"));
            document.open();

            document.open();

            Paragraph para = new Paragraph("Publications :\n\t");
            document.add(para);

            //simple paragraph
            //add table
            PdfPTable pdfPTable = new PdfPTable(7);

            PdfPCell pdfCell1 = new PdfPCell(new Phrase("idPub"));

            PdfPCell pdfCell2 = new PdfPCell(new Phrase("date_Pub"));
            PdfPCell pdfCell3 = new PdfPCell(new Phrase("titre_Pub"));
            PdfPCell pdfCell4 = new PdfPCell(new Phrase("desc_Pub"));
            PdfPCell pdfCell50 = new PdfPCell(new Phrase("source_Pub"));
            PdfPCell pdfCell5 = new PdfPCell(new Phrase("categorie_Pub:"));
            PdfPCell pdfCell555 = new PdfPCell(new Phrase("image_Pub:"));

            pdfPTable.addCell(pdfCell1);
            pdfPTable.addCell(pdfCell2);
            pdfPTable.addCell(pdfCell3);
            pdfPTable.addCell(pdfCell4);
            pdfPTable.addCell(pdfCell50);
            pdfPTable.addCell(pdfCell5);
            pdfPTable.addCell(pdfCell555);
            pdfPTable.addCell("" + publication.getIdPub() + "");
            pdfPTable.addCell("" + publication.getDate_Pub() + "");
            pdfPTable.addCell(publication.getTitre_Pub());
            pdfPTable.addCell(publication.getDesc_Pub());
            pdfPTable.addCell(publication.getSource_Pub());
            pdfPTable.addCell(publication.getCategorie_Pub());
            pdfPTable.addCell(publication.getImage_Pub());
            document.add(pdfPTable);
            // document.add(image);
            document.close();

        } catch (DocumentException | FileNotFoundException Exception) {
            System.out.println(Exception);
        }

    }

    public void Notificationmanager(int mode) {
        Notifications not = Notifications.create().graphic(null).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked on notification");
            }
        });
        not.darkStyle();
        switch (mode) {
            case 0:

                not.title("Success");
                not.text("Publication ajouté");
                not.showInformation();
                break;
            case 1:

                not.title("Success ");
                not.text("Publication Modifié");
                not.showWarning();
                break;
            case 2:

                not.text("Publication supprimé");
                not.title("Success");
                not.showConfirm();
                break;
            case 3:

                not.text("Reclamation ajouté");
                not.title("Success");
                not.showInformation();
                break;
            case 4:

                not.text("Reclamation modifié");
                not.title("Success");
                not.showWarning();
                break;
            case 5:

                not.text("Reclamation supprimé");
                not.title("Success");
                not.showWarning();
                break;
            case 6:

                not.text("Mail envoyé");
                not.title("Mail");
                not.showWarning();
                break;
            case 7:

                not.text("Converted to PDF");
                not.title("PDF");
                not.showWarning();
                break;    
            default:

        }

    }
 private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private void link1(ActionEvent event) throws Exception {               
 
       try {
      /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/GestionReclamationFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
          // NewFXMain..close();  */
          
           root = FXMLLoader.load(getClass().getResource("../GUI/GestionReclamationFXML.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    } catch(IOException e) {
    }

    }

}
