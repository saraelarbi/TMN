/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Coadmin;
import edu.esprit.entities.User;
import edu.esprit.services.UserCRUD;
import edu.esprit.utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.sort;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ListUserController implements Initializable {

    @FXML
    private AnchorPane AnchorPane_Listuser;
    @FXML
    private Button ModifierUser;
    @FXML
    private Button SupprimerUser;
    @FXML
    private TableView<User> AffichageUsers;
    @FXML
    private TableColumn<User, Integer> afficheidU;
    @FXML
    private TableColumn<User, String> affichenom;
    @FXML
    private TableColumn<User, String> afficheprenom;
    @FXML
    private TableColumn<User, String> affichedatenaissance;
    @FXML
    private TableColumn<User, String> afficheemail;
    @FXML
    private TableColumn<User, String> afficheadresse;
    @FXML
    private TableColumn<User, Integer> affichenum;
    @FXML
    private TextField chercher;
    private Connection cnx;
    private User selecteduser;
    UserCRUD uu = new UserCRUD();
    int num = -1;
    FilteredList<User> filter = new FilteredList<>(UserList(), e -> true);
    SortedList<User> sort = new SortedList<>(filter);
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
       AffichageUsers();
    }
    public ObservableList<User> UserList(){
        
        cnx = MyConnection.getInstance().getCnx();
        
        ObservableList<User> UserList = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM user";
        
        try{
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            User user;
            
            while(rs.next()){
                
       
             user = new User(rs.getInt("idU"),rs.getString("nom"),rs.getString("prenom"), rs.getInt("numtel"), rs.getString("email"), rs.getString("adresse"), rs.getDate("Datenaissance"), rs.getString("password"));
             UserList.add(user);
                
            }
            
        }catch(SQLException e){}
        
        return UserList;
        
    }
    
   
    @FXML
    private void SupprimerUser(ActionEvent event) {
        User u = AffichageUsers.getSelectionModel().getSelectedItem();
        uu.supprimer(u.getIdU());
        Image img = new Image("/tmn2.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Suppression User")
                .text("User supprimé").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        AffichageUsers();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("User supprimé avec succes!");
        alert.showAndWait();
        AffichageUsers();
    }
    
    

    @FXML
    private void selectUser(MouseEvent event) {
      
        User user = AffichageUsers.getSelectionModel().getSelectedItem();
        this.selecteduser = user;
        int num = AffichageUsers.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        affichenom.getCellData(num);
        afficheemail.getCellData(num);
        afficheprenom.getCellData(num);
        afficheadresse.getCellData(num);
        affichenum.getCellData(num);
        user.getDatenaissance();
        user.getPassword();
        user.getIdU();
            
    }

    @FXML
    private void AffichageUsers() {
        ObservableList<User> UserList = UserList();
                AffichageUsers.setItems(UserList);
                
        afficheidU.setCellValueFactory(new PropertyValueFactory<>("idU"));
        affichenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        afficheprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        affichedatenaissance.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        afficheemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        afficheadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        affichenum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        
    }

    @FXML
    private void Chercher(ActionEvent event) {
        AffichageUsers();
    AffichageUsers.setItems(sort);
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
            sort.comparatorProperty().bind(AffichageUsers.comparatorProperty());
            AffichageUsers.setItems(sort);
        });
    }
    @FXML
    private void pdf (ActionEvent event) {
        try {
            User user = AffichageUsers.getSelectionModel().getSelectedItem();
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\PC\\Desktop\\TMN\\LISTUSERS.pdf"));
            document.open();

            document.open();

            Paragraph para = new Paragraph("Users :\n\t");
            document.add(para);

            //simple paragraph
            //add table
            PdfPTable pdfPTable = new PdfPTable(7);

            PdfPCell pdfCell1 = new PdfPCell(new Phrase("idU"));
            PdfPCell pdfCell2 = new PdfPCell(new Phrase("nom"));
            PdfPCell pdfCell3 = new PdfPCell(new Phrase("prenom"));
            PdfPCell pdfCell4 = new PdfPCell(new Phrase("datenaissance : "));
            PdfPCell pdfCell50 = new PdfPCell(new Phrase("email"));
            PdfPCell pdfCell5 = new PdfPCell(new Phrase("numtel :"));
            PdfPCell pdfCell555 = new PdfPCell(new Phrase("adreese :"));
            

            pdfPTable.addCell(pdfCell1);
            pdfPTable.addCell(pdfCell2);
            pdfPTable.addCell(pdfCell3);
            pdfPTable.addCell(pdfCell4);
            pdfPTable.addCell(pdfCell50);
            pdfPTable.addCell(pdfCell5);
            pdfPTable.addCell(pdfCell555);
            pdfPTable.addCell("" + user.getIdU() + "");
            pdfPTable.addCell("" + user.getNom() + "");
            pdfPTable.addCell(user.getPrenom());
            pdfPTable.addCell(user.getEmail());
            pdfPTable.addCell("" +user.getDatenaissance()+"");
            pdfPTable.addCell("" +user.getNumtel()+ "");
             pdfPTable.addCell(user.getAdresse());
            
            document.add(pdfPTable);
            document.close();

        } catch (DocumentException | FileNotFoundException Exception) {
            System.out.println(Exception);
        }
        
    }



}