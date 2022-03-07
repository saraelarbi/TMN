/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import entities.Produit;
import entities.testmail;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.CommandeCRUD;
import services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class CommandeFController implements Initializable {

    @FXML
    private TextField quantite;
    @FXML
    private ImageView image;
    @FXML
    private ComboBox<String> methode;
    @FXML
    private Button Coommander;
private static int idd;
    private Produit Prod;
    private Produit p;
    public static float prix ; 

    public static int getIdd(int id) {
        idd = id;
        return idd;
    }
    ProduitCRUD ps = new ProduitCRUD() ; 
    CommandeCRUD Cs = new CommandeCRUD() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        methode.getItems().addAll("Espece", "Cheque","En ligne") ;                       
       Prod = ps.TrouverById(idd) ;
      
       
    }    
   

    @FXML
    private void Commander(ActionEvent event) throws IOException {
       
        int qtite = Integer.parseInt(quantite.getText());
                 Commande C1= new Commande(qtite, methode.getValue(),(qtite*Prod.getPrix())) ;
     //  Commande C2 = new Commande(Integer.parseInt(nbField.getText()), Date.valueOf(dataField.getValue()), methodeField.getValue(), Integer.parseInt(nbField.getText())*Prod.getPrix()) ;
     //   System.out.println("aaaaaaaaaaaaa" + Prod.getId());
//     Commande C  = new Commande(1, Date.valueOf(dataField.getValue()), "aaa", 22) ;
      
        Cs.Ajouter2(C1,Prod.getIdProduit());
        Prod.setStock(Prod.getStock() -Integer.parseInt(quantite.getText()) );
     
        ps.Modifier(Prod);
        System.out.println("Commande ajoutée");
        prix = C1.getTotal() ; 
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Commmande validée!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       
       
          if(Prod.getStock() < 10){
        testmail M = new testmail() ; 
        M.sendEmail("sara.elarbi@esprit.tn",Prod.getNom(), "Ce produit va bientot s'épuiser!");
        }
         
        if ("En ligne".equals(C1.getMethode_de_paiement())){
              
        Parent root = FXMLLoader.load(getClass().getResource("PaiementEnLigne.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
          }
              
    }
    

    }
    

