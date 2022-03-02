/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import entities.Produit;

import java.io.File;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CommandeCRUD;
import services.ProduitCRUD;
import utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class GestionPublicationFXMLController implements Initializable {

   /* @FXML
    private TextField TextField_numCMD;*/
    @FXML
    private TextField numCmdMod;
    @FXML
    private TextField TextField_totalCMD;
    @FXML
    private TextField TextField_QtiteCMD;
    @FXML
    private TextField TextField_idUcmd;
    @FXML
    private TextField TextField_idproduit;
    @FXML
    private Button AjouterCommande;
    @FXML
    private Button ModifierCommande;
    @FXML
    private Button SupprimerCommande;
    @FXML
    private TableView<Commande> AffichageCommande;
    @FXML
    private TableColumn<Commande, Integer> numCMD;
    @FXML
    private TableColumn<Commande, Float> total;
    @FXML
    private TableColumn<Commande, String> quantite;
    @FXML
    private TableColumn<Commande, Integer> idU;
    @FXML
    private TableColumn<Commande, Integer> idP;
        
    private Connection cnx;
    @FXML
    private AnchorPane AnchorPane_Commande;
   
    
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*TextField_numCMD.setDisable (true);*/
        AffichageCommande();
    }    

    @FXML
    private void AjouterCommande(ActionEvent event) {
          CommandeCRUD cmd = new CommandeCRUD();
            Commande cd = new Commande( Float.parseFloat(TextField_totalCMD.getText()), TextField_QtiteCMD.getText(), Integer.parseInt(TextField_idUcmd.getText()),Integer.parseInt(TextField_idproduit.getText()));
            cmd.Ajouter(cd);
            AffichageCommande();
            Refresh();

            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Success Commande Ajouté!");
                alert.showAndWait();
        
    }

    @FXML
    private void ModifierCommande(ActionEvent event) {
         String str1= numCmdMod.getText();
        int idcmd =parseInt(str1);
        
         CommandeCRUD cmd = new CommandeCRUD();
            Commande cd = new Commande(idcmd,Float.parseFloat(TextField_totalCMD.getText()), TextField_QtiteCMD.getText(),Integer.parseInt(TextField_idUcmd.getText()), Integer.parseInt(TextField_idproduit.getText()));
            cmd.Modifier(cd);
            AffichageCommande();
            Refresh();

             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Commande Modifié!");
                alert.showAndWait();
    }

    @FXML
    private void SupprimerCommande(ActionEvent event) {
          CommandeCRUD cmd = new CommandeCRUD();
                String idcmd = numCmdMod.getText();
                cmd.Supprimer(parseInt(idcmd));
                AffichageCommande();
                Refresh();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Commande Supprimé!");
                alert.showAndWait();
    }
    
     public ObservableList<Commande> CommandeList(){
        
        cnx = MyConnection.getInstance().getCnx();
        
        ObservableList<Commande> CommandeList = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM commande";
        
        try{
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            Commande commande;
            
            while(rs.next()){
                
                commande = new Commande(rs.getInt("numCmd"), rs.getFloat("total"),rs.getString("quantite"), rs.getInt("idU"),rs.getInt("idProduit"));   
                
                CommandeList.add(commande);
                
            }
            
        }catch(SQLException e){}
        
        return CommandeList;
        
    }
     
     public void AffichageCommande(){
        ObservableList<Commande> CommandeList = CommandeList();
        
        numCMD.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
        idP.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        
        
        AffichageCommande.setItems(CommandeList);
        
    }

   
      
       public void selectPublication(){
        
        Commande commande = AffichageCommande.getSelectionModel().getSelectedItem();
        
        int num = AffichageCommande.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        
        /*TextField_numCMD.setText(String.valueOf(commande.getNumCmd()));*/
        //DatePicker_Date_PUB.setText(String.(publication.getDate_Pub()));
        numCmdMod.setText(String.valueOf(commande.getNumCmd()));
        TextField_totalCMD.setText(String.valueOf(commande.getTotal()));
        TextField_QtiteCMD.setText(commande.getQuantite());
        TextField_idUcmd.setText(String.valueOf(commande.getIdU()));
        TextField_idproduit.setText(String.valueOf(commande.getIdProduit()));
        
        
      
        
    }
       
       public void Refresh() {

        numCmdMod.setText("");
        TextField_totalCMD.setText("");
        TextField_QtiteCMD.setText("");
        TextField_idUcmd.setText("");
        TextField_idproduit.setText("");
        
    }
     
     
}
