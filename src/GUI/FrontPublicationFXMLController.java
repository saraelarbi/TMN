/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.Publication;
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
import services.ServicePublication;

/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class FrontPublicationFXMLController implements Initializable {
     private final List<Publication> publication = new ArrayList<>();
    ServicePublication sPub = new ServicePublication();
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane AnchorPane_publications;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        publication.addAll(sPub.afficherPB());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < publication.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("OnePublicationFXML.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                OnePublicationFXMLController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.setData(publication.get(i));
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
