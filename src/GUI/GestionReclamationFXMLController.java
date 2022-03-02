/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Publication;
import entities.Reclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TextField TextField_id_Pod;
    @FXML
    private TextField TextField_id_Blog;
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
    @FXML
    private TextField TextField_id_Pub;
    
    private Connection cnx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AffichageReclamation();
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
        TextField_id_Pub.setText(String.valueOf(reclamation.getIdPub()));
        TextField_id_Pod.setText(String.valueOf(reclamation.getIdPub()));
        TextField_id_Blog.setText(String.valueOf(reclamation.getIdPub()));

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

                reclamation = new Reclamation(rs.getInt("idReclamation"), rs.getString("Description"),rs.getInt("idPub"),rs.getInt("idPod"),rs.getInt("idBlog"));

                ReclamationList.add(reclamation);

            }

        } catch (SQLException e) {
        }

        return ReclamationList;

    }
    public void clear() {

        TextField_ID_RECLAMATION.setText("");
        TextField_DESCRIPTION.setText("");
        TextField_id_Pub.setText("");
        TextField_id_Pod.setText("");
        TextField_id_Blog.setText("");
       

    }

    @FXML
    private void AffichageReclamation() {
        ObservableList<Reclamation> ReclamationList = ReclamationList();

        idReclamation.setCellValueFactory(new PropertyValueFactory<>("idPub"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        idPub.setCellValueFactory(new PropertyValueFactory<>("idPub"));
        idPod.setCellValueFactory(new PropertyValueFactory<>("idPod"));
        idBlog.setCellValueFactory(new PropertyValueFactory<>("idBlog"));

        AffichageReclamation.setItems(ReclamationList);
    }

}
