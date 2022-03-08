/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Evenement;
import Entite.Reservation;
import Service.EvenementService;
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
public class AffichageReservationController implements Initializable {

    @FXML
    private TableColumn<?, ?> idres;
    @FXML
    private TableColumn<?, ?> dateres;
    @FXML
    private TableColumn<?, ?> evenetid;
    @FXML
    private Button Buttonttt;
    @FXML
    private TableView<Reservation> tableee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

        public ObservableList<Reservation> getlist() throws SQLException {
        ReservationService Ps = new ReservationService();
        ObservableList<Reservation> listReservation = FXCollections.observableArrayList(Ps.Afficher());
        return listReservation;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Reservation> list = getlist();
        tableee.setItems(list);
        idres.setCellValueFactory(new PropertyValueFactory<>("Id_Reservation"));
        dateres.setCellValueFactory(new PropertyValueFactory<>("Date"));
        evenetid.setCellValueFactory(new PropertyValueFactory<>("idevent"));
     
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
                 Parent root = FXMLLoader.load(getClass().getResource("/Views/Evenement.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
    }
    
}
