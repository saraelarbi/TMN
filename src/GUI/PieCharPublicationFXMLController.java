/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class PieCharPublicationFXMLController implements Initializable {

    @FXML
    private PieChart piechart1;
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MyDB.getInstance().getConnection();
        stat();
    }

    private void stat() {

        try {

            String query = "SELECT COUNT(*),categorie_Pub FROM publication GROUP BY idPub";

            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString("categorie_Pub"), rs.getInt("COUNT(*)")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionPublicationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        piechart1.setTitle("**Statistiques nombres des catégories**");
        piechart1.setLegendSide(Side.LEFT);
        piechart1.setData(data);

    }

}
