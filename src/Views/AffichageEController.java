/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Evenement;
import Service.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AffichageEController implements Initializable {
    private final List<Evenement> Evenement = new ArrayList<>();
    EvenementService ps = new EvenementService();
    @FXML
    private AnchorPane produits;
    @FXML
    private GridPane grid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Evenement.addAll(ps.Afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Evenement.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Views/OneP.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                OnePController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.setData(Evenement.get(i));
                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
              //  GridPane.setMargin(anchorPane,Insets(35));           
               // GridPane.setMargin(anchorPane, new javafx.geometry.Insets(35));
               // GridPane.setPadding(new Insets(133, 10, 111, 10))
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        
    }    
    
}
}
