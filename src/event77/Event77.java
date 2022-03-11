/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event77;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Event77 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
  //Parent root = FXMLLoader.load(getClass().getResource("/Views/AjoutEvenetFront.fxml")); //ajouter front
  //Parent root = FXMLLoader.load(getClass().getResource("/Views/AffichageE.fxml")); //affciher front
  //Parent root = FXMLLoader.load(getClass().getResource("/Views/Evenement.fxml")); // ajouter + affciher back
      //Parent root = FXMLLoader.load(getClass().getResource("/Views/AffichageReservation.fxml"));
     //  Parent root = FXMLLoader.load(getClass().getResource("/Views/AffichageRate.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
