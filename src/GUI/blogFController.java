/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Blog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.BlogCRUD;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class blogFController implements Initializable {
    private final List<Blog> blog = new ArrayList<>();
    BlogCRUD ps = new BlogCRUD();

    @FXML
    private AnchorPane blogs;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 blog.addAll(ps.Afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < blog.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("UnBlog.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                UnBlogController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.Afficher_Produit_Front(blog.get(i));
                if (column == 2) {
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
      
    }
        }    
    

