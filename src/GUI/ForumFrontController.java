/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Blog;
import entities.Forum;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ForumCrud;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class ForumFrontController implements Initializable {

    @FXML
    private TableView<Forum> tableview2;
    @FXML
    private TableColumn<?, ?> quest;
    @FXML
    private TableColumn<?, ?> etatt;
    @FXML
    private TableColumn<?, ?> likes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ForumCrud css = new ForumCrud();
        
        ArrayList<Forum> c2 = new ArrayList<>();
         c2 = (ArrayList<Forum>) css.Afficher();
        
        
         ObservableList<Forum> obs2 = FXCollections.observableArrayList(c2);
        tableview2.setItems(obs2);
        
        
         quest.setCellValueFactory(new PropertyValueFactory<>("questions"));
             etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));
        likes.setCellValueFactory(new PropertyValueFactory<>("nbre_likes"));
    }    
@FXML
    private void news(ActionEvent event) throws IOException {
       }
     @FXML
    private void evenement(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void shop(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void forum(ActionEvent event) throws IOException {
         NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/GUI/ForumFront.fxml");
      
    }
     @FXML
    private void podcast(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void blog(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "TMN", "/GUI/blogF.fxml");
      
    }
    @FXML
    private void select_forum(MouseEvent event) {
    }
    
}
