/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Publicite;
import edu.esprit.utils.MyConnection;
import static java.awt.PageAttributes.MediaType.C;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javax.print.attribute.standard.MediaSize.Engineering.C;
import static javax.print.attribute.standard.MediaSizeName.C;

/**
 * FXML Controller class
 *
 * @author bolba
 */
public class GestionPubliciteController implements Initializable {

    @FXML
    private AnchorPane AnchorPane_Publication;
    private TextField TextField_type;
    @FXML
    private DatePicker DatePicker_Date_PUB;
    @FXML
    private TextField TextField_SOURCE_PUB;
    @FXML
    private Button AjouterPublication;
    @FXML
    private Button ModifierPublication;
    @FXML
    private Button SupprimerPublication;
    @FXML
    private TableColumn<Publicite, Date> date_Pub;
    @FXML
    private TableColumn<Publicite, String> desc_Pub;
    @FXML
    private TextField TextField_DESC_PUB;
    @FXML
    private Button button_inserer_image;
    @FXML
    private ImageView imageview_Publication;
    @FXML
    private Label file_path;
    @FXML
    private TableColumn<Publicite, Integer> type_pub;
    @FXML
    private TableColumn<Publicite, String> domaine_Pub;
    @FXML
    private TableColumn<Publicite, String> image_Pub;
    @FXML
    private TableColumn<Publicite, String> lettre_Pub;
    @FXML
    private TextField TextField_DOMAINE_PUB;
    @FXML
    private TableView<Publicite> AffichagePublicite;
private Connection cnx;
    @FXML
    private ComboBox<?> Cbx_type;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField_type.setDisable (true);
//        AffichagePublicite();
    }    

    @FXML
    private void AjouterPublication(ActionEvent event) {
    }

    @FXML                       
    private void ModifierPublicite(ActionEvent event) {
    }

    @FXML
    private void SupprimerPublication(ActionEvent event) {
        
    }
    public ObservableList<Publicite> PubliciteList(){
        
        cnx = MyConnection.getInstance().getCnx();
        
        ObservableList<Publicite> PubliciteList = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM publicite";
        
        try{
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            Publicite publicite;
           
            while(rs.next()){
                
                publicite = new Publicite(rs.getInt("id_pub"),rs.getDate("date_creation"), rs.getString("domaine"), rs.getString("description"),rs.getString("lettre_motivation"),rs.getInt("id_typepub"),rs.getString("image"));   
                
                PubliciteList.add(publicite);
                
            }
            
        }catch(SQLException e){}
        
        return PubliciteList;
        
    }



    @FXML
    private void insertImage(ActionEvent event) {
    }

    @FXML
    private void AffichagePublicite() {
         ObservableList<Publicite> PubliciteList = PubliciteList();
        
        type_pub.setCellValueFactory(new PropertyValueFactory<>("id_typepub"));
        date_Pub.setCellValueFactory(new PropertyValueFactory<>("date_creation "));
        domaine_Pub.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        desc_Pub.setCellValueFactory(new PropertyValueFactory<>("description"));
        image_Pub.setCellValueFactory(new PropertyValueFactory<>("image"));
        lettre_Pub.setCellValueFactory(new PropertyValueFactory<>("lettre_motivation"));

        
        AffichagePublicite.setItems(PubliciteList);
    }

    @FXML
    private void selectPublicite(MouseEvent event) {
    }
    
}
