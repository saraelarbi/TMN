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
import entities.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import org.controlsfx.control.Notifications;
import services.ServiceReclamation;
import services.mail;
import static services.mail.prepareMessage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class GestionReclamationFXMLController implements Initializable {

    @FXML
    private TextField TextField_ID_RECLAMATION;
    @FXML
    private Button AjouterReclamation;
    @FXML
    private Button ModifierReclamation;
    @FXML
    private Button SupprimerReclamation;
    @FXML
    private TableView<Reclamation> AffichageReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> idReclamation;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, Integer> idPub;
    @FXML
    private TableColumn<Reclamation, Integer> idPod;
    @FXML
    private TableColumn<Reclamation, Integer> idBlog;
    @FXML
    private TextField TextField_DESCRIPTION;
    @FXML
    private ImageView logoTMN;
    private TextField TextField_id_Pub;

    private Connection cnx;
    @FXML
    private TextField TextField_Recherche_Reclamation;
    @FXML
    private ProgressBar ProgressBarRec;
    @FXML
    private ComboBox ComboBox_idPub;
    @FXML
    private ComboBox ComboBox_idPod;
    @FXML
    private ComboBox ComboBox_idBlog;
    @FXML
    private RadioButton RadioButton_idPub;
    @FXML
    private RadioButton RadioButton_idPod;
    @FXML
    private RadioButton RadioButton_idBlog;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    @FXML
    private Button Button_RecToPDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField_ID_RECLAMATION.setDisable(true);
        AffichageReclamation();
        ProgressBarRec.setProgress(0);
        ComboBox_idPub.setDisable(true);
        ComboBox_idPod.setDisable(true);
        ComboBox_idBlog.setDisable(true);

    }
    private PreparedStatement prepare;
    private Statement statement;

    @FXML
    private void AjouterReclamation(ActionEvent event) throws SQLException {

        cnx = MyDB.getInstance().getConnection();
//        I HAVE 5 COLUMNS
        String sql = "INSERT INTO reclamation (description,idPub,idPod,idBlog) VALUES (?,?,?,?)";
        if (TextField_DESCRIPTION.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        }
        if (TextField_DESCRIPTION.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {

            // ServiceReclamation sRec = new ServiceReclamation();
            // Reclamation re = new Reclamation(1, TextField_DESCRIPTION.getText(), idPb, idPd, idBl);
            //StartProgrssBar();
            // sRec.ajouterRE(re);
            if (i1 == 1) {
                String str1 = (String) ComboBox_idPub.getValue();
                int idPb = parseInt(str1);

                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, TextField_DESCRIPTION.getText());
                prepare.setInt(2, idPb);
                prepare.setNull(3, Types.INTEGER);
                prepare.setNull(4, Types.INTEGER);
                prepare.executeUpdate();

            }

            if (i2 == 1) {
                String str2 = (String) ComboBox_idPod.getValue();
                int idPd = parseInt(str2);
                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, TextField_DESCRIPTION.getText());
                prepare.setNull(2, Types.INTEGER);
                prepare.setInt(3, idPd);
                prepare.setNull(4, Types.INTEGER);
                prepare.executeUpdate();
            }
            if (i3 == 1) {
                String str3 = (String) ComboBox_idBlog.getValue();
                int idBl = parseInt(str3);
                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, TextField_DESCRIPTION.getText());
                prepare.setNull(2, Types.INTEGER);
                prepare.setNull(3, Types.INTEGER);
                prepare.setInt(4, idBl);
                prepare.executeUpdate();
            }
            StartProgrssBar();
            Notificationmanager(3);
            ProgressBarRec.setProgress(0);
            AffichageReclamation();
            clear();

            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Success Publication Ajouté!");
            alert.showAndWait();
            
            String txt = "Bonjour  Vous avez une Reclamation ";
            String sub = "Nouvelle Reclamation ajouté";
            String destinataire = "mohamedaziz.snoussi@esprit.tn";
            mail m = new mail(txt, sub, destinataire);
            Message msg = prepareMessage(m.getSession(), m.getMail(), destinataire, txt, sub);
            System.out.println(msg);
            try {
                Transport.send(msg);
            } catch (MessagingException ex) {
                // Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("MAIL ENVOYEE");
            Notificationmanager(6);

           

        }
    }

    @FXML
    private void ModifierReclamation(ActionEvent event) throws SQLException, MessagingException {
        cnx = MyDB.getInstance().getConnection();
        String sql = "update reclamation set  description = ? , idPub = ? , idPod = ? , idBlog = ? where idReclamation = ?";
        if (TextField_DESCRIPTION.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        }
        if (TextField_DESCRIPTION.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
            
            String str4 = (String) TextField_ID_RECLAMATION.getText();
            int idRec = parseInt(str4);

            // ServiceReclamation sRec = new ServiceReclamation();
            // Reclamation re = new Reclamation(1, TextField_DESCRIPTION.getText(), idPb, idPd, idBl);
            //StartProgrssBar();
            // sRec.ajouterRE(re);
             if (i1 == 1) {
                String str1 = (String) ComboBox_idPub.getValue();
                int idPb = parseInt(str1);

                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, TextField_DESCRIPTION.getText());
                prepare.setInt(2, idPb);
                prepare.setNull(3, Types.INTEGER);
                prepare.setNull(4, Types.INTEGER);
                prepare.setInt(5, idRec);
                prepare.executeUpdate();

            }

            if (i2 == 1) {
                String str2 = (String) ComboBox_idPod.getValue();
                int idPd = parseInt(str2);
                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, TextField_DESCRIPTION.getText());
                prepare.setNull(2, Types.INTEGER);
                prepare.setInt(3, idPd);
                prepare.setNull(4, Types.INTEGER);
                prepare.setInt(5, idRec);
                prepare.executeUpdate();
            }
            if (i3 == 1) {
                String str3 = (String) ComboBox_idBlog.getValue();
                int idBl = parseInt(str3);
                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, TextField_DESCRIPTION.getText());
                prepare.setNull(2, Types.INTEGER);
                prepare.setNull(3, Types.INTEGER);
                prepare.setInt(4, idBl);
                prepare.setInt(5, idRec);
                prepare.executeUpdate();
            }

            Notificationmanager(4);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Success Publication Modifiee!");
            alert.showAndWait();

            ProgressBarRec.setProgress(0);
            AffichageReclamation();
            clear();
        }
    }

    @FXML
    private void SupprimerReclamation(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer cette Reclamation ?");

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.OK) {

            ServiceReclamation sRec = new ServiceReclamation();
            String idRec = TextField_ID_RECLAMATION.getText();
            StartProgrssBar();
            sRec.supprimerRE(parseInt(idRec));
            Notificationmanager(5);

        } else {

            return;

        }

        AffichageReclamation();
        clear();
        ProgressBarRec.setProgress(0);
    }

    @FXML
    private void selectReclamation() {
        Reclamation reclamation = AffichageReclamation.getSelectionModel().getSelectedItem();

        int num = AffichageReclamation.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        TextField_ID_RECLAMATION.setText(String.valueOf(reclamation.getIdReclamation()));
        TextField_DESCRIPTION.setText(reclamation.getDescription());
        ComboBox_idPub.setValue(String.valueOf(reclamation.getIdPub()));
        ComboBox_idPod.setValue(String.valueOf(reclamation.getIdPod()));
        ComboBox_idBlog.setValue(String.valueOf(reclamation.getIdBlog()));

         if((ComboBox_idPod.getValue()=="0")&&(ComboBox_idBlog.getValue()=="0")) {
             
        ComboBox_idPub.setValue(String.valueOf(reclamation.getIdPub()));
         ComboBox_idPod.setValue("NULL");
          ComboBox_idBlog.setValue("NULL");
        } 
           if((ComboBox_idPub.getValue()=="0")&&(ComboBox_idBlog.getValue()=="0")) {
        ComboBox_idPub.setValue("NULL");
         ComboBox_idPod.setValue(String.valueOf(reclamation.getIdPod()));
          ComboBox_idBlog.setValue("NULL");
        }
            if((ComboBox_idPub.getValue()=="0")&&(ComboBox_idPod.getValue()=="0")) {
        ComboBox_idPub.setValue("NULL");
        ComboBox_idPod.setValue("NULL");
         ComboBox_idBlog.setValue(String.valueOf(reclamation.getIdBlog()));
        } 
        //ComboBox_idPub.setPromptText(""+reclamation.getIdPub());
        //ComboBox_idPod.setValue(String.valueOf(reclamation.getIdPod()));
        //ComboBox_idBlog.setValue(String.valueOf(reclamation.getIdBlog()));
        // TextField_id_Pub.setText(String.valueOf(reclamation.getIdPub()));
        // TextField_id_Pod.setText(String.valueOf(reclamation.getIdPub()));
        // TextField_id_Blog.setText(String.valueOf(reclamation.getIdPub()));
    }

    public ObservableList<Reclamation> ReclamationList() {

        cnx = MyDB.getInstance().getConnection();

        ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();

        String req = "SELECT * FROM reclamation";

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            Reclamation reclamation;

            while (rs.next()) {

                reclamation = new Reclamation(rs.getInt("idReclamation"), rs.getString("Description"), rs.getInt("idPub"), rs.getInt("idPod"), rs.getInt("idBlog"));

                ReclamationList.add(reclamation);

            }

        } catch (SQLException e) {
        }

        return ReclamationList;

    }

    public void clear() {

        TextField_ID_RECLAMATION.setText("");
        TextField_DESCRIPTION.setText("");
        ComboBox_idPub.setValue("NULL");
        ComboBox_idPod.setValue("NULL");
        ComboBox_idBlog.setValue("NULL");

        ComboBox_idPub.setDisable(true);
        ComboBox_idPod.setDisable(true);
        ComboBox_idBlog.setDisable(true);
        RadioButton_idPod.setSelected(false);
        RadioButton_idPub.setSelected(false);
        RadioButton_idPod.setSelected(false);
        // TextField_id_Pub.setText("");
        //TextField_id_Pod.setText("");
        // TextField_id_Blog.setText("");
        i1=0;
        i2=0;
        i3=0;
    }

    @FXML
    private void AffichageReclamation() {
        ObservableList<Reclamation> ReclamationList = ReclamationList();

        idReclamation.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        idPub.setCellValueFactory(new PropertyValueFactory<>("idPub"));
        idPod.setCellValueFactory(new PropertyValueFactory<>("idPod"));
        idBlog.setCellValueFactory(new PropertyValueFactory<>("idBlog"));

        AffichageReclamation.setItems(ReclamationList);

        FilteredList<Reclamation> filteredData = new FilteredList<>(ReclamationList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        TextField_Recherche_Reclamation.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    /* } else if (publication.getDesc_Pub().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } */
                } else {
                    return false;
                }
            });
        });

        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(AffichageReclamation.comparatorProperty());
        AffichageReclamation.setItems(sortedData);

    }

    public void StartProgrssBar() {
        double ii = 0;

        do {

            ii += 0.001;
            ProgressBarRec.setProgress(ii);
        } while (1 > (ii));

    }

    @FXML
    public void getComboBox_idPub() {
        String sql_idPub = " select idPub from publication ";

        try {
            cnx = MyDB.getInstance().getConnection();
            PreparedStatement pstStn = cnx.prepareStatement(sql_idPub);
            ResultSet stnRS = pstStn.executeQuery(sql_idPub);

            while (stnRS.next()) {

                ComboBox_idPub.getItems().add(stnRS.getString("idPub"));

            }

            stnRS.close();
            pstStn.close();
            // cnx.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }

    }

    /* public void getComboBox_idPod() {
       

    } */
    @FXML
    private void getComboBox_idPod(Event event) {
        // String sql_idPod = " select id from podcast ";
        String sql_idPod = " select id from podcast ";

        try {
            cnx = MyDB.getInstance().getConnection();
            PreparedStatement pstStn = cnx.prepareStatement(sql_idPod);
            ResultSet stnRS = pstStn.executeQuery(sql_idPod);

            while (stnRS.next()) {

                ComboBox_idPod.getItems().add(stnRS.getString("id"));

            }

            stnRS.close();
            pstStn.close();
            // cnx.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
    }

    @FXML
    private void getComboBox_idBlog(Event event) {
        String sql_id_blog = " select id_blog from blog ";

        try {
            cnx = MyDB.getInstance().getConnection();
            PreparedStatement pstStn = cnx.prepareStatement(sql_id_blog);
            ResultSet stnRS = pstStn.executeQuery(sql_id_blog);

            while (stnRS.next()) {

                ComboBox_idBlog.getItems().add(stnRS.getString("id_blog"));

            }

            stnRS.close();
            pstStn.close();
            // cnx.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
    }

    @FXML
    private void RadioButton_checked(MouseEvent event) {

        i1 = 1;
        if (RadioButton_idPub.isPressed()) {

            RadioButton_idPod.setSelected(false);
            RadioButton_idBlog.setSelected(false);
            ComboBox_idPub.setDisable(false);
            ComboBox_idPod.setDisable(true);
            ComboBox_idBlog.setDisable(true);

            ComboBox_idPod.setValue("NULL");
            ComboBox_idBlog.setValue("NULL");
        }

    }

    @FXML
    private void RadioButton_checked1(MouseEvent event) {
        i2 = 1;
        if (RadioButton_idPod.isPressed()) {

            RadioButton_idPub.setSelected(false);
            RadioButton_idBlog.setSelected(false);
            ComboBox_idPod.setDisable(false);
            ComboBox_idPub.setDisable(true);
            ComboBox_idBlog.setDisable(true);
            ComboBox_idPub.setValue("NULL");

            ComboBox_idBlog.setValue("NULL");
        }
    }

    @FXML
    private void RadioButton_checked2(MouseEvent event) {
        i3 = 1;
        if (RadioButton_idBlog.isPressed()) {

            RadioButton_idPub.setSelected(false);
            RadioButton_idPod.setSelected(false);
            ComboBox_idBlog.setDisable(false);
            ComboBox_idPub.setDisable(true);
            ComboBox_idPod.setDisable(true);
            ComboBox_idPub.setValue("NULL");
            ComboBox_idPod.setValue("NULL");

        }
    }

    @FXML
    private void RecToPDF(ActionEvent event) {
        try {
            Reclamation reclamation = AffichageReclamation.getSelectionModel().getSelectedItem();
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/ffsga/Documents/NetBeansProjects/TMN/images/TMNreclamation.pdf"));
            document.open();

            document.open();

            Paragraph para = new Paragraph("Reclamations :\n\t");
            document.add(para);

            //simple paragraph
            //add table
            PdfPTable pdfPTable = new PdfPTable(5);

            PdfPCell pdfCell1 = new PdfPCell(new Phrase("idPub"));

            PdfPCell pdfCell2 = new PdfPCell(new Phrase("date_Pub"));
            PdfPCell pdfCell3 = new PdfPCell(new Phrase("titre_Pub"));
            PdfPCell pdfCell4 = new PdfPCell(new Phrase("desc_Pub"));
            PdfPCell pdfCell50 = new PdfPCell(new Phrase("source_Pub"));

            pdfPTable.addCell(pdfCell1);
            pdfPTable.addCell(pdfCell2);
            pdfPTable.addCell(pdfCell3);
            pdfPTable.addCell(pdfCell4);
            pdfPTable.addCell(pdfCell50);

            pdfPTable.addCell("" + reclamation.getIdReclamation() + "");
            pdfPTable.addCell("" + reclamation.getDescription() + "");
            pdfPTable.addCell("" + reclamation.getIdPub() + "");
            pdfPTable.addCell("" + reclamation.getIdPod() + "");
            pdfPTable.addCell("" + reclamation.getIdBlog() + "");

            document.add(pdfPTable);
            // document.add(image);
            document.close();
            Notificationmanager(7);

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

}
