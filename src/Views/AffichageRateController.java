/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Rate_Evenement;
import Entite.Reservation;
import Service.Rate_EvenementService;
import Service.ReservationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AffichageRateController implements Initializable {

    @FXML
    private TableView<Rate_Evenement> EventTable;
    @FXML
    private TableColumn<?, ?> Idevent;
    @FXML
    private TableColumn<?, ?> Rate;
    @FXML
    private Button Bavkk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageRateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
        public ObservableList<Rate_Evenement> getlist() throws SQLException {
        Rate_EvenementService Ps = new Rate_EvenementService();
        ObservableList<Rate_Evenement> list = FXCollections.observableArrayList(Ps.Afficher());
        return list;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Rate_Evenement> list = getlist();
        EventTable.setItems(list);
        Idevent.setCellValueFactory(new PropertyValueFactory<>("Id_Evenement"));
        Rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));

     
    }
    @FXML
    private void backkk(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Views/Evenement.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
