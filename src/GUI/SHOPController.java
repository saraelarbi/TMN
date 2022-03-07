/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import com.lowagie.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import entities.Commande;
import entities.Panier;
import entities.Produit;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import services.CommandeCRUD;
import services.PanierCRUD;
import services.ProduitCRUD;
import utils.MyConnection; 
import org.controlsfx.control.Notifications; 
import javafx.scene.control.TextArea; 
import static sun.awt.image.ImagingLib.filter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
//import javax.swing.text.Document;

//import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class SHOPController implements Initializable {
    FilteredList<Produit> filter = new FilteredList<>(ProduitList(), e -> true);
    SortedList<Produit> sort = new SortedList<>(filter);
    @FXML
    private TextField idProduitMod;
    @FXML
    private AnchorPane produitanchor;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField prix;
    @FXML
    private Label file_path;
    @FXML
    private TextField stock;
    @FXML
    private Button AjouterProd;
    @FXML
    private Button SupprimerProd;
    @FXML
    private Button button_inserer_image;
    @FXML
    private Button ModifierProd;
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
    private TableColumn<Produit, Integer> stockk;
    @FXML
    private ImageView ImageViewer_Prod;
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
   /* FilteredList<Commande> filter_cmd = new FilteredList<>(CommandeList(), e -> true);
    SortedList<Commande> sortcmd = new SortedList<>(filter_cmd);*/
    @FXML
    private TextField chercher_cmd;
    @FXML
    private AnchorPane AnchorPane_Commande;
   
   
    @FXML
    private Button ModifierCommande;
    @FXML
    private Button SupprimerCommande;
    @FXML
    private TableView<Commande> AffichageCommande;
    @FXML
    private TableColumn<Commande, Integer> numCMD;
    @FXML
    private TableColumn<Commande, Integer> Quantite;
    @FXML
    private TableColumn<Commande, String> methode;
    @FXML
    private TableColumn<Commande, String> etat;
    @FXML
    private TableColumn<?, ?> total;
    //@FXML
    //private TableColumn<Commande, Integer> idU;
    @FXML
    private ComboBox<String> EtatComande;
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private TextField chercher_panier;
    @FXML
    private AnchorPane AnchorPane_Panier;
    @FXML
    private TextField prix_des_prods;
    @FXML 
    private TextField nbre_produit;
    @FXML 
    private TextField idProduit_panier;
    @FXML
    private TextField IdPanierMod;
    @FXML
    private Button AjouterPanier;
    @FXML
    private Button SupprimerPanier;
    @FXML
    private Button ModiferPanier;
    @FXML
    private TableView<Panier> AffichagePanier;
    @FXML
    private TableColumn<Panier, Integer> idPanier;
    @FXML
    private TableColumn<Panier, Float> prixProds;
    @FXML
    private TableColumn<Panier, Integer> nbreProds;
    @FXML
    private TableColumn<Panier, Integer> idprod_panier;
    /////////////////////////////////////////////////////////////////////////////////////////////////////
     @FXML
    private AnchorPane anchor_sms;
    @FXML 
    private TextField to;
    @FXML 
    private TextArea msg;
    @FXML
    private Button envoyer;
    ////////////////////////////
    @FXML 
    private TextField chercher;
    //////////////////////////////////////////////////////
    @FXML
    private Button pdf;
    private Connection cnx2;  
    Produit p;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EtatComande.getItems().addAll("Acceptée", "Refusée" , "Livrée") ;   
      
        try {
            AffichageCommande();
        } catch (SQLException ex) {
            Logger.getLogger(SHOPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        etat.setCellFactory(TextFieldTableCell.forTableColumn());
      AfficherProd();
      AffichagePanier();
      

    }    
    
    
    
    //////////////////////////////////////// PRODUIT /////////////////////////////////////////////////////
    
    
    
    
 public ObservableList<Produit> ProduitList(){
        cnx2 = MyConnection.getInstance().getCnx();
        ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
        String req = "SELECT * FROM produit";
        try{
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            Produit produit;
            while(rs.next()){
                produit = new Produit(rs.getInt("idProduit"), rs.getString("nom"),rs.getFloat("prix"),rs.getString("type") ,rs.getString("image"),rs.getInt("stock"));   
                ProduitList.add(produit);
            }
        }catch(SQLException e){}
        return ProduitList;
    }

    @FXML
    private void AjouterProd(ActionEvent event) {
         if (
                  nom.getText().isEmpty()
                | type.getText().isEmpty()
                | prix.getText().isEmpty()
                | ImageViewer_Prod.getImage()== null
                | stock.getText().isEmpty())

                {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Remplissez tous les champs");
            alert.showAndWait();

        } else {

            ProduitCRUD prod = new ProduitCRUD();
            Float prixx = Float.parseFloat(prix.getText());
            int stockk = Integer.parseInt(stock.getText());
            
            Produit pd = new Produit(nom.getText(),prixx,type.getText(), file_path.getText(),stockk);
            prod.Ajouter(pd);
            AfficherProd();
            //Refresh_produit();
            
          /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Produit Ajouté!");
                alert.showAndWait();*/
           Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajout Produit")
                    .text("Produit Ajouté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();

            
    }
    }
    

    @FXML
    private void SupprimerProd(ActionEvent event) {
        ProduitCRUD prod = new ProduitCRUD();
                String idpd = idProduitMod.getText();
                prod.Supprimer(parseInt(idpd));
                AfficherProd();
                //Refresh_produit();
                 Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Suppression Produit")
                    .text("Produit Supprimé").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Produit Supprimé!");
                alert.showAndWait();*/
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
        stock.setText(String.valueOf(produit.getStock()));
        
    }

    @FXML
    private void AfficherProd() {
         ObservableList<Produit> ProduitList = ProduitList();
        idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        nom2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix2.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image2.setCellValueFactory(new PropertyValueFactory<>("image"));
        stockk.setCellValueFactory(new PropertyValueFactory<>("stock"));
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
         if (
                  nom.getText().isEmpty()
                | type.getText().isEmpty()
                | prix.getText().isEmpty()
                | ImageViewer_Prod.getImage()== null
                |stock.getText().isEmpty())
                {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Remplissez tous les champs");
            alert.showAndWait();

        } else {

         String str1= idProduitMod.getText();
        int idpd =parseInt(str1);
        
         ProduitCRUD prod = new ProduitCRUD();
            Produit pd = new Produit(idpd,nom.getText(), Float.parseFloat(prix.getText()), type.getText(), file_path.getText(),Integer.parseInt(stock.getText()));
            prod.Modifier(pd);
            AfficherProd();
            //Refresh_produit();
            Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Modification Produit")
                    .text("Produit Modifié").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
              
    }
    }
    /* public void Refresh_produit() {
       // idProduitMod.setText("");
        nom.setText("");
        type.setText("");
        prix.setText("");
        ImageViewer_Prod.setImage(null);
        file_path.setText("");
    
} */
   
     @FXML
    private void Chercher(ActionEvent event) {
        
        AfficherProd();
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(AfficherProd.comparatorProperty());
            AfficherProd.setItems(sort);
        });
    }
     
    @FXML
    private void pdf(ActionEvent event)
  {
      try {
            Produit prod = AfficherProd.getSelectionModel().getSelectedItem();
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/ASUS CELERON/Desktop/Pdf/produit.pdf"));
            document.open();

            document.open();

            Paragraph para = new Paragraph("Liste des produits :\n\t");
            document.add(para);
            PdfPTable pdfPTable = new PdfPTable(5);

            PdfPCell pdfCell1 = new PdfPCell(new Phrase("idProduit"));

            PdfPCell pdfCell2 = new PdfPCell(new Phrase("nom"));
            PdfPCell pdfCell3 = new PdfPCell(new Phrase("type"));
            PdfPCell pdfCell4 = new PdfPCell(new Phrase("prix"));
            PdfPCell pdfCell50 = new PdfPCell(new Phrase("image"));
            
            pdfPTable.addCell(pdfCell1);
            pdfPTable.addCell(pdfCell2);
            pdfPTable.addCell(pdfCell3);
            pdfPTable.addCell(pdfCell4);
            pdfPTable.addCell(pdfCell50);
           
            pdfPTable.addCell("" + prod.getIdProduit()+ "");
            pdfPTable.addCell("" + prod.getNom()+ "");
            pdfPTable.addCell(prod.getType());
            pdfPTable.addCell(prod.getImage());
            pdfPTable.addCell("" + prod.getPrix()+ "");
          
            document.add(pdfPTable);
            document.close();

        } catch (DocumentException | FileNotFoundException Exception) {
            System.out.println(Exception);
        }
      
  }
    
////////////////////////////////// COMMANDE //////////////////////////////////////////////////////////////
    
    public ObservableList<Commande> getlist() throws SQLException {
        CommandeCRUD Ps = new CommandeCRUD();
        ObservableList<Commande> listproduit = FXCollections.observableArrayList(Ps.Afficher());
        return listproduit;
    }
    
   

    @FXML
    private void ModifierCommande(ActionEvent event) throws SQLException {
        CommandeCRUD Ps = new CommandeCRUD();
          Commande C = Ps.TrouverById(AffichageCommande.getSelectionModel().getSelectedItem().getNumCmd()) ; 
          C.setEtat(EtatComande.getValue());
          Ps.Modifier(C);     
          AffichageCommande();
    }
    @FXML
    private void SupprimerCommande(ActionEvent event) throws SQLException {
         CommandeCRUD Ps = new CommandeCRUD();
          Ps.Supprimer(AffichageCommande.getSelectionModel().getSelectedItem().getNumCmd());
          JOptionPane.showMessageDialog(null, "Are you sure ? :(");
          AffichageCommande();
               Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Suppression Commande")
                    .text("Commande Supprimée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
              
    }

    
    @FXML
    private void AffichageCommande()throws SQLException {
         ObservableList<Commande> list = getlist();
        AffichageCommande.setItems(list);
        numCMD.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
        Quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        methode.setCellValueFactory(new PropertyValueFactory<>("methode_de_paiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
       // idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
    }
   
     /*public void Refresh_commande() {

        TextField_totalCMD.setText("");
        TextField_QtiteCMD.setText("");
        TextField_idUcmd.setText("");
        TextField_idproduit.setText("");
        numCmdMod.setText("");
    
} */
    @FXML
    private void Chercher_cmd(ActionEvent event) {
        /*chercher_cmd.setOnKeyReleased(e -> {
            chercher_cmd.textProperty().addListener((observable, oldValue, newValue) -> {
                filter_cmd.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getQuantite().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else { 
                        return false;
                    }
                });

            });
            sortcmd.comparatorProperty().bind(AffichageCommande.comparatorProperty());
            AffichageCommande.setItems(sortcmd);
        });*/
    }
    
    
    
    ////////////////////////////// PANIER ///////////////////////////////////////////////////////////////
    
    
    
    public ObservableList<Panier> PanierList(){
        cnx2 = MyConnection.getInstance().getCnx();
        ObservableList<Panier> PanierList = FXCollections.observableArrayList();
        String req = "SELECT * FROM panier";
        try{
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            Panier panier;
            while(rs.next()){
                panier = new Panier(rs.getInt("idPanier"), rs.getFloat("prix_des_produits"),
                        rs.getInt("nbre_produit"), rs.getInt("idProduit"));   
                PanierList.add(panier);
            }
        }catch(SQLException e){}
        return PanierList;
        
    }
    @FXML
    private void AjouterPanier(ActionEvent event){
        if (
                  prix_des_prods.getText().isEmpty()
                | nbre_produit.getText().isEmpty()
                | idProduit_panier.getText().isEmpty())
                {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Remplissez tous les champs");
            alert.showAndWait();

        } else {

            PanierCRUD pn = new PanierCRUD();
            Float prix_des_prods = Float.parseFloat(this.prix_des_prods.getText());
            int nbre_prods = Integer.parseInt(nbre_produit.getText());
            int idP_PA = Integer.parseInt(idProduit_panier.getText());
            Panier pnn = new Panier(prix_des_prods, nbre_prods,idP_PA );
            pn.Ajouter(pnn);
            AffichagePanier();
            
            
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Panier Ajouté!");
                alert.showAndWait();*/

            
       Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajout Panier")
                    .text("Panier Ajouté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();}
    }
    @FXML
    private void ModifierPanier(ActionEvent event){
         if (
                  prix_des_prods.getText().isEmpty()
                | nbre_produit.getText().isEmpty()
                | idProduit_panier.getText().isEmpty())
                {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Remplissez tous les champs");
            alert.showAndWait();

        } else {
        String str1= IdPanierMod.getText();
        int idpn =parseInt(str1);
        
         PanierCRUD pn = new PanierCRUD();
            Panier pnn = new Panier(idpn,Float.parseFloat(prix_des_prods.getText()), Integer.parseInt(nbre_produit.getText()),Integer.parseInt(idProduit_panier.getText()));
            pn.Modifier(pnn);
            AffichagePanier();
           // Refresh_panier();

             /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText(" Panier Modifié!");
                alert.showAndWait();}*/
          Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Modification Panier")
                    .text("Panier Modifié").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();}}
    
    @FXML
    private void SupprimerPanier(ActionEvent event){
     PanierCRUD pd = new PanierCRUD(); 
                String idpn = IdPanierMod.getText();
                pd.Supprimer(parseInt(idpn));
                AffichagePanier();
               // Refresh_panier();
                Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Suppression Panier")
                    .text("Panier Supprimé").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();}
    
    @FXML
    private void AffichagePanier(){
     ObservableList<Panier> PanierList = PanierList();
        
        idPanier.setCellValueFactory(new PropertyValueFactory<>("idPanier"));
        prixProds.setCellValueFactory(new PropertyValueFactory<>("prix_des_produits"));
        nbreProds.setCellValueFactory(new PropertyValueFactory<>("nbre_produit"));
        idprod_panier.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
       
        
        AffichagePanier.setItems(PanierList);} 
    
    @FXML
    private void select_panier(MouseEvent event) {
         Panier panier = AffichagePanier.getSelectionModel().getSelectedItem();
        int num = AffichagePanier.getSelectionModel().getSelectedIndex();
        if((num-1) < -1)
            return;
        IdPanierMod.setText(String.valueOf(panier.getIdPanier()));
        prix_des_prods.setText(String.valueOf(panier.getPrix_des_produits()));
        nbre_produit.setText(String.valueOf(panier.getNbre_produit()));
        idProduit_panier.setText(String.valueOf(panier.getIdProduit()));
       
        
    }
  /*  public void Refresh_panier() {

        prix_des_prods.setText("");
        nbre_produit.setText("");
        idProduit_panier.setText("");
        IdPanierMod.setText("");
       
    
} 
    */
   
           
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
   @FXML
    private void envoyer_sms(ActionEvent event){
   
    }
}

