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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CommandeCRUD;
import services.ProduitCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class SHOPController implements Initializable {

    @FXML
    private AnchorPane produitanchor;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField prix;
    @FXML
    private Button AjouterProd;
    @FXML
    private Button SupprimerProd;
   @FXML
    private TableView<Produit> AfficherProd;
    @FXML
    private TableColumn<Produit, Integer> idProduit;
    @FXML
    private TableColumn<Produit, String> nom2;
    @FXML
    private TableColumn<Produit, String> type2;
    @FXML
    private TableColumn<Produit, Float> prix2;
    @FXML
    private TableColumn<Produit, String> image2;
    @FXML
    private Button button_inserer_image;
    @FXML
    private Button ModifierProd;
    @FXML
    private ImageView ImageViewer_Prod;
    @FXML
    private Label file_path;
    @FXML
    private TextField idProduitMod;
    @FXML
    private AnchorPane AnchorPane_Commande;
    @FXML
    private TextField TextField_totalCMD;
    @FXML
    private TextField TextField_QtiteCMD;
    @FXML
    private TextField TextField_idUcmd;
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
    @FXML
    private Label file_path1;
    @FXML
    private TextField TextField_idproduit;
    @FXML
    private TextField numCmdMod;
    private Connection cnx;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      AffichageCommande();
      AfficherProd();
    }    
 public ObservableList<Produit> ProduitList(){
        cnx = MyConnection.getInstance().getCnx();
        ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
        String req = "SELECT * FROM produit";
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            Produit produit;
            while(rs.next()){
                produit = new Produit(rs.getInt("idProduit"), rs.getString("nom"),rs.getString("type"), rs.getFloat("prix"),rs.getString("image"));   
                ProduitList.add(produit);
            }
        }catch(SQLException e){}
        return ProduitList;
    }

    @FXML
    private void AjouterProd(ActionEvent event) {
        /* ProduitCRUD prod = new ProduitCRUD();
            Produit pd = new Produit(nom.getText(), type.getText(), Float.parseFloat(prix.getText()), file_path.getText());
            prod.Ajouter(pd);
            AfficherProd();
            Refresh();

            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Success Produit Ajouté!");
                alert.showAndWait();*/
    }

    @FXML
    private void SupprimerProd(ActionEvent event) {
        ProduitCRUD prod = new ProduitCRUD();
                String idpd = idProduitMod.getText();
                prod.Supprimer(parseInt(idpd));
                AfficherProd();
                Refresh();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Produit Supprimé!");
                alert.showAndWait();
    }

    @FXML
    private void selectProduit(MouseEvent event) {
         Produit produit = AfficherProd.getSelectionModel().getSelectedItem();
        int num = AfficherProd.getSelectionModel().getSelectedIndex();
        if((num-1) < -1)
            return;
        idProduitMod.setText(String.valueOf(produit.getIdProduit()));
        nom.setText(produit.getNom());
        type.setText(produit.getType());
        prix.setText(String.valueOf(produit.getPrix()));
        String picture ="file:" +  produit.getImage();
        Image image = new Image(picture, 110, 110, false, true);
        ImageViewer_Prod.setImage(image);
        String path = produit.getImage();
        file_path.setText(path);
        
    }

    @FXML
    private void AfficherProd() {
         ObservableList<Produit> ProduitList = ProduitList();
        idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        nom2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix2.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image2.setCellValueFactory(new PropertyValueFactory<>("image"));
        AfficherProd.setItems(ProduitList);
    }

    @FXML
    private void insert_image(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage)produitanchor.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file != null){
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\");
            file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            ImageViewer_Prod.setImage(image);
        }else{
            System.out.println("NO DATA EXIST!");
        }
    }

    @FXML
    private void ModifierProd(ActionEvent event) {
         String str1= idProduitMod.getText();
        int idpd =parseInt(str1);
        
         ProduitCRUD prod = new ProduitCRUD();
            Produit pd = new Produit(idpd,nom.getText(), type.getText(), Float.parseFloat(prix.getText()), file_path.getText());
            prod.Modifier(pd);
            AfficherProd();
            Refresh();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Produit Modifié!");
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
                alert.setContentText(" Commande Ajouté!");
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

    @FXML
    private void select_Commande(MouseEvent event) {
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

    @FXML
    private void AffichageCommande() {
         ObservableList<Commande> CommandeList = CommandeList();
        
        numCMD.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
        idP.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        
        
        AffichageCommande.setItems(CommandeList);
    }
     public void Refresh() {

        nom.setText("");
        type.setText("");
        prix.setText("");
        ImageViewer_Prod.setImage(null);
        file_path.setText("");
    
}
    
}
