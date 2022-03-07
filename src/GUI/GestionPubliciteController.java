/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import edu.esprit.entities.Podcast;
import org.controlsfx.control.Notifications;
import edu.esprit.entities.Publicite;
import edu.esprit.entities.Typepub;
import edu.esprit.services.PubliciteCRUD;
import edu.esprit.services.TypepubCRUD;
import edu.esprit.utils.MyConnection;
import static java.awt.PageAttributes.MediaType.C;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.print.attribute.standard.MediaSize.Engineering.C;
import static javax.print.attribute.standard.MediaSizeName.C;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import static javafx.print.Paper.C;
import static javafx.scene.input.KeyCode.C;
import static javax.print.attribute.standard.MediaSize.Engineering.C;
import static javax.print.attribute.standard.MediaSizeName.C;
import static jdk.nashorn.internal.objects.NativeArray.filter;
import static sun.util.locale.LocaleMatcher.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author bolba
 */
public class GestionPubliciteController implements Initializable {
    
     String path = "C:\\Users\\bolba\\Documents\\NetBeansProjects\\Pidevconnection\\music\\rakabataya.mp3";
     Media media = new Media(new File(path).toURI().toString());
     MediaPlayer mediaPlayer = new MediaPlayer(media);
     FilteredList<Publicite> filters = new FilteredList<>(PubliciteList(), e -> true);
     SortedList<Publicite> sorta = new SortedList<>(filters);
     FilteredList<Typepub> filter = new FilteredList<>(TypepubList(), e -> true);
     SortedList<Typepub> sort = new SortedList<>(filter);
     
    @FXML
    private AnchorPane AnchorPane_Publication;
    static Publicite selectionedPublicite;
    @FXML
    private DatePicker DatePicker_Date_PUB;
    
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
    private ComboBox<String> Cbx_type;
    @FXML
    private Button ModifierPublicite;
    @FXML
    private TextField TextField_Lettres_PUB;
    @FXML
    private Button AjouterPublicite;
    @FXML
    private Button SupprimerPublicite;
    @FXML
    private TableColumn<Publicite, Integer> id_pub1;
    @FXML
    private Label id;
    @FXML
    private TableColumn<Typepub, String> type_pub1;
    @FXML
    private Label id1;
    @FXML
    private TextField TextField_type_pub;
    @FXML
    private Button Ajoutertypepub1;
    @FXML
    private Button Modifiertypepub1;
    @FXML
    private Button Supprimertypepub1;
    @FXML
    private Button play;
    @FXML
    private AnchorPane AnchorPane_typepub11;
    @FXML
    private TableView<Typepub> Affichagetypepub1;
    @FXML
    private TableColumn<Typepub, Integer> id_typepub1;
    @FXML
    private Button play1;
    @FXML
    private TextField chercher;
    @FXML
    private TextField chercher2;
    @FXML
    private Label IdTypePub1;
    @FXML
    private Button play2;
    @FXML
    private AnchorPane AnchorPane_Podcast;
    @FXML
    private ComboBox<?> Cbx_coadmin;
    @FXML
    private TextField TextField_TITLE;
    @FXML
    private Button AjouterPodcast;
    @FXML
    private Button ModifierPodcast;
    @FXML
    private Button SupprimerPodcast;
    @FXML
    private TableView<Podcast> AffichagePodcast;
    @FXML
    private TableColumn<Podcast, Integer> coadmin_podcast;
    @FXML
    private TableColumn<Podcast,String> podcast_title;
    @FXML
    private TableColumn<Podcast, String> desc_podcast;
    @FXML
    private TableColumn<Podcast, String> video_podcast;
    @FXML
    private TextField TextField_DESC_POD;
    @FXML
    private Button button_inserer_video;
    @FXML
    private Label id2;
    @FXML
    private ImageView imageview_Publication1;
    @FXML
    private TextField chercher3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AffichagePublicite();
        AffichageTypepub();
        AffichagePodcast();
        
        
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
            
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        return PubliciteList;
        
    }
    public ObservableList<Typepub> TypepubList(){
        
        cnx = MyConnection.getInstance().getCnx();
        
        ObservableList<Typepub> TypepubList = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM typepub";
        
        try{
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            Typepub typepub;
           
            while(rs.next()){
                
                typepub = new Typepub(rs.getInt("id"),rs.getString("cat"));   
                
                TypepubList.add(typepub);
                
            }
            
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        return TypepubList;
        
    }


    @FXML
    private void insertImage(ActionEvent event) {
        FileChooser open = new FileChooser();
        
        Stage stage = (Stage)AnchorPane_Publication.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
         

            Image image = new Image(file.toURI().toString(), 317, 188, false, true);
            
            imageview_Publication.setImage(image);
            button_inserer_image.setText(path);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }
    }

    @FXML
    private void AffichagePublicite() {
         ObservableList<Publicite> PubliciteList = PubliciteList();
        String type[] = {"publicite1","type2moda","collosal type","founder type","type"}; 
        Cbx_type.setItems(FXCollections.observableArrayList(type));
        id_pub1.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        date_Pub.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        domaine_Pub.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        desc_Pub.setCellValueFactory(new PropertyValueFactory<>("description"));
        lettre_Pub.setCellValueFactory(new PropertyValueFactory<>("lettre_motivation")); 
        type_pub.setCellValueFactory(new PropertyValueFactory<>("id_typepub"));
        image_Pub.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        AffichagePublicite.setItems(PubliciteList);
    }

    @FXML
    private void selectPublicite(MouseEvent event) {
        Publicite publicite = AffichagePublicite.getSelectionModel().getSelectedItem();
        
        int num = AffichagePublicite.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        
        TypepubCRUD tp = new TypepubCRUD();
        id.setText(String.valueOf(publicite.getId_pub()));
        Cbx_type.setValue(tp.gettype(publicite.getId_typepub()));
//        DatePicker_Date_PUB.setValue(publicite.getDate_creation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        TextField_DOMAINE_PUB.setText(publicite.getDomaine());
        TextField_DESC_PUB.setText(publicite.getDescription());
        TextField_Lettres_PUB.setText(publicite.getLettre_motivation());
        button_inserer_image.setText(publicite.getImage());
        
        
        
        String picture ="file:" +  publicite.getImage();
        
        Image image = new Image(picture, 317, 188, false, true);
        
        imageview_Publication.setImage(image);
        
        String path = publicite.getImage();
        
        
        
    }

    @FXML
    private void AjouterPublicite(ActionEvent event) {
        Publicite p = new Publicite();
        TypepubCRUD tp = new TypepubCRUD();
        PubliciteCRUD pc = new PubliciteCRUD();
        p.setId_typepub(tp.getid(Cbx_type.getValue()));
        p.setDate_creation(java.sql.Date.valueOf(DatePicker_Date_PUB.getValue()));
        p.setDomaine(TextField_DOMAINE_PUB.getText());
        p.setDescription(TextField_DESC_PUB.getText());
        p.setLettre_motivation(TextField_Lettres_PUB.getText());
        p.setImage(button_inserer_image.getText());
        pc.ajouterPublic(p);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Publicite ajoutée");
        AffichagePublicite();
        Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Gestion Publicite")
                    .text("        Publicite Ajouté").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        
            
            }

    @FXML
    private void ModifierPublicite(ActionEvent event) {
        Publicite p = new Publicite();
        TypepubCRUD tp = new TypepubCRUD();
        PubliciteCRUD pc = new PubliciteCRUD();
        p.setId_pub(Integer. parseInt(id.getText()));
        p.setId_typepub(tp.getid(Cbx_type.getValue()));
        p.setDate_creation(java.sql.Date.valueOf(DatePicker_Date_PUB.getValue()));
        p.setDomaine(TextField_DOMAINE_PUB.getText());
        p.setDescription(TextField_DESC_PUB.getText());
        p.setLettre_motivation(TextField_Lettres_PUB.getText());
        p.setImage(button_inserer_image.getText());
        pc.modifierPublic(p);
        
        
        AffichagePublicite();
        Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Gestion Publicite")
                    .text("        Publicite Modifié").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
    }

    @FXML
    private void SupprimerPublicite(ActionEvent event) {
         PubliciteCRUD pc = new PubliciteCRUD();
         pc.supprimerPublic(Integer. parseInt(id.getText()));
         AffichagePublicite();
         Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Gestion Publicite")
                    .text("        Publicite Supprimé").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
         
        
    }

    @FXML
    private void AjouterTypepub(ActionEvent event) {
        Typepub t = new Typepub();
        TypepubCRUD tp = new TypepubCRUD();
        t.setCat(TextField_type_pub.getText());
        tp.ajouterTypepub(t);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Type pub ajoutée");
        AffichageTypepub();
        
    }
    @FXML
    private void ModifierTypepub(ActionEvent event) {
        Typepub t = new Typepub();
        TypepubCRUD tp = new TypepubCRUD();
        t.setId(Integer. parseInt(id.getText()));
        t.setCat(TextField_type_pub.getText());
        tp.modifierTypepub(t);
        
        
        AffichageTypepub();
    }

    @FXML
    private void SupprimerTypepub(ActionEvent event) {
        Typepub typepub = Affichagetypepub1.getSelectionModel().getSelectedItem();
        TypepubCRUD tp = new TypepubCRUD();
        tp.supprimerTypepub(typepub.getId());
        AffichageTypepub();
    }

    @FXML
    private void play(ActionEvent event) {
        mediaPlayer.play();
        Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Musique")
                    .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        
    }

    @FXML
    private void AffichageTypepub() {
        ObservableList<Typepub> Typepublist = TypepubList(); 
        id_typepub1.setCellValueFactory(new PropertyValueFactory<>("id"));
        type_pub1.setCellValueFactory(new PropertyValueFactory<>("cat"));
        
        Affichagetypepub1.setItems(Typepublist);
    }

    @FXML
    private void pause(ActionEvent event) {
                mediaPlayer.pause();
                Image img = new Image("/tmn2.jpg");
            Notifications notificationBuilder = Notifications.create()
                    .title("Musique")
                    .text("      Musique Arrêtée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
    }
    private void selectTypepub(MouseEvent event) {
        Typepub type = Affichagetypepub1.getSelectionModel().getSelectedItem();
        
        int num = Affichagetypepub1.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        
        TypepubCRUD tp = new TypepubCRUD();
        IdTypePub1.setText(String.valueOf(type.getId()));
        TextField_type_pub.setText(type.getCat());
        
        
        
        
        
      
        
        
        
    }
    @FXML
    private void Chercher(ActionEvent event) {
        
        AffichagePublicite();
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filters.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getDomaine().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sorta.comparatorProperty().bind(AffichagePublicite.comparatorProperty());
            AffichagePublicite.setItems(sorta);
        });
    }
    @FXML
    private void Chercher2(ActionEvent event) {
        
        AffichageTypepub();
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getCat().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(Affichagetypepub1.comparatorProperty());
            Affichagetypepub1.setItems(sort);
        });
    }
    public ObservableList<Podcast> PodcastList(){
        
        cnx = MyConnection.getInstance().getCnx();
        
        ObservableList<Podcast> PodcastList = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM podcast";
        
        try{
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            Podcast podcast;
           
            while(rs.next()){
                
                podcast = new Podcast(rs.getInt("id_coadmin"), rs.getString("title"), rs.getString("description"),rs.getString("video"));   
                
                PodcastList.add(podcast);
                
            }
            
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        return PodcastList;
        
    }
    @FXML
    private void AjouterPodcast(ActionEvent event) {
    }

    @FXML
    private void ModifierPodcast(ActionEvent event) {
    }

    @FXML
    private void SupprimerPodcast(ActionEvent event) {
    }

    @FXML
    private void AffichagePodcast() {
        ObservableList<Podcast> PodcastList = PodcastList();
//        String coadmin[] = {"coadminnume1", "asfasfasf"}; 
//        Cbx_coadmin.setItems(FXCollections.observableArrayList(coadmin));
        coadmin_podcast.setCellValueFactory(new PropertyValueFactory<>("id_coadmin"));
        podcast_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_podcast.setCellValueFactory(new PropertyValueFactory<>("description"));
        video_podcast.setCellValueFactory(new PropertyValueFactory<>("video")); 
        
        AffichagePodcast.setItems(PodcastList);
    }

    @FXML
    private void insertVideo(ActionEvent event) {
    }

    @FXML
    private void Chercher3(ActionEvent event) {
    }
   
    }

    

    
    

