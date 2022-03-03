/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entities.Publication;
import entities.Reclamation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
    private Button Button_RecToExcel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField_ID_RECLAMATION.setDisable(true);
        AffichageReclamation();
        ProgressBarRec.setProgress(0);
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
    }

    @FXML
    private void ModifierReclamation(ActionEvent event) {
    }

    @FXML
    private void SupprimerReclamation(ActionEvent event) {
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
         ComboBox_idPub.setValue(reclamation.getIdPub());
        //ComboBox_idPub.setPromptText(""+reclamation.getIdPub());
        ComboBox_idPod.setValue(String.valueOf(reclamation.getIdPod()));
        ComboBox_idBlog.setValue(String.valueOf(reclamation.getIdBlog()));
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
        ComboBox_idPub.setPromptText("NULL");
        ComboBox_idPod.setPromptText("NULL");
        ComboBox_idBlog.setPromptText("NULL");
         
       // TextField_id_Pub.setText("");
        //TextField_id_Pod.setText("");
       // TextField_id_Blog.setText("");

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
            ProgressBarRec.setProgress(1);
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

    /*@FXML
    private void RecToExcel(ActionEvent event) throws IOException{
        ExcelFile file = new ExcelFile();
        ExcelWorksheet worksheet = file.addWorksheet("sheet");
        for (int row = 0; row < AffichageReclamation.getItems().size(); row++) {
            ObservableList cells = (ObservableList) AffichageReclamation.getItems().get(row);
            for (int column = 0; column < cells.size(); column++) {
                if (cells.get(column) != null)
                    worksheet.getCell(row, column).setValue(cells.get(column).toString());
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
        );
        File saveFile = fileChooser.showSaveDialog(AffichageReclamation.getScene().getWindow());

        file.save(saveFile.getAbsolutePath());
    } */

    @FXML
    private void RecToExcel(ActionEvent event) {
    }
    

}
