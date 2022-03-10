/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Blog;
import entities.Forum;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.BlogCRUD;
import services.ForumCrud;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS CELERON
 */
public class BlogController implements Initializable {

   
 
BlogCRUD cs = new BlogCRUD();
public ObservableList<Blog> list;
public ObservableList<Forum> list2;
     FilteredList<Blog> filter = new FilteredList<>(BlogList(), e -> true);
    SortedList<Blog> sort = new SortedList<>(filter);
    @FXML
    private AnchorPane bloganchor;
    @FXML
    private TextArea desc;
    @FXML
    private DatePicker date;
    @FXML
    private ImageView image;
     @FXML
    private Label file_path;
    @FXML
    private TextField categ;
    @FXML
    private TableView<Blog> tableview;
    @FXML
    private TableColumn<?, ?> idB;
    @FXML
    private TableColumn<?, ?> descri;
    @FXML
    private TableColumn<?, ?> datee;
    @FXML
    private TableColumn<Blog, String> imagee;
    @FXML
    private TableColumn<?, ?> cat;
    @FXML
    private Button ajout;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private TextField chercher;
    @FXML
    private AnchorPane anchor_forum;
    @FXML
    private TextField questions;
    @FXML
    private TextField etat;
    @FXML
    private TextField nbre_likes;
    @FXML
    private TableColumn<?, ?> idFf;
    @FXML
    private TableColumn<?, ?> quest;
    @FXML
    private TableColumn<?, ?> etatt;
    @FXML
    private TableColumn<?, ?> likes;
    @FXML
    private Button ajout_f;
    @FXML
    private Button supp_f;
    @FXML
    private Button modif_f;
    @FXML
    private Label idlabel;
    @FXML
    private Button confirmer;
    @FXML
    private TableView<Forum> tableview2;
    @FXML
    private TextField chercher2;
    @FXML
    private Label idlabel2;
    @FXML
    private Button confirmer2;
    @FXML
    private Button PDF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  afficher();

        
        ForumCrud css = new ForumCrud();
      //  BlogCRUD pss = new BlogCRUD();
         ArrayList<Forum> c2 = new ArrayList<>();
         
        ArrayList<Blog> c = new ArrayList<>();
       
      //  c = (ArrayList<Blog>) pss.Afficher();
        
        c2 = (ArrayList<Forum>) css.Afficher();
        
        
      /*  ObservableList<Blog> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);*/
        
         ObservableList<Forum> obs3 = FXCollections.observableArrayList(c2);
        tableview2.setItems(obs3);
        
       
 /*idB.setCellValueFactory(new PropertyValueFactory<>("idBlog"));
        descri.setCellValueFactory(new PropertyValueFactory<>("description"));
             datee.setCellValueFactory(new PropertyValueFactory<>("date_de_publication"));
        vd.setCellValueFactory(new PropertyValueFactory<>("video"));
               cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));*/

               
               
               
               
               
             
          /*  try {
            list = FXCollections.observableArrayList(
                    pss.Afficher()
            );        
           FilteredList<Blog> filteredData = new FilteredList<>(list, e -> true);
            chercher.setOnKeyReleased(e -> {
                chercher.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Blog>) Blogs -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Blogs.getCategorie().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Blog> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        
        
            */
            
     ///////////////Forum       
            idFf.setCellValueFactory(new PropertyValueFactory<>("idForum"));
        quest.setCellValueFactory(new PropertyValueFactory<>("questions"));
             etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));
        likes.setCellValueFactory(new PropertyValueFactory<>("nbre_likes"));
             
        
            try {
            list2 = FXCollections.observableArrayList(
                    css.Afficher()
            );        
           FilteredList<Forum> filteredData = new FilteredList<>(list2, e -> true);
            chercher2.setOnKeyReleased(e -> {
                chercher2.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Forum>) Forums -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Forums.getQuestions().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Forum> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview2.comparatorProperty());
                tableview2.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
      
    }
     @FXML
    private void podcast(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void blog(ActionEvent event) throws IOException {
      
    }
      @FXML
    private void news_f(ActionEvent event) throws IOException {
       }
     @FXML
    private void evenement_f(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void shop_f(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void forum_f(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void podcast_f(ActionEvent event) throws IOException {
      
    }
     @FXML
    private void blog_f(ActionEvent event) throws IOException {
      
    }
      

    @FXML
    private void select_blog(MouseEvent event) {
         Blog blog = tableview.getSelectionModel().getSelectedItem();

        int num = tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        date.getEditor().setText(String.valueOf(blog.getDate_de_publication()));
        desc.setText(blog.getDescription());
        
        categ.setText(blog.getCategorie());

        String picture = "file:" + blog.getimage();

        Image imagee = new Image(picture, 110, 110, false, true);

        image.setImage(imagee);

        String path = blog.getimage();

        file_path.setText(path);

    }
        
      public void resetTableDataForum() throws SQLDataException, SQLException {
          ForumCrud m = new ForumCrud();
        List<Forum> forum = new ArrayList<>();
        forum = m.Afficher();
        ObservableList<Forum> data = FXCollections.observableArrayList(forum);
        tableview2.setItems(data);
    }    
      Connection cnx2;
      public ObservableList<Blog> BlogList(){
        cnx2 = MyConnection.getInstance().getCnx();
        ObservableList<Blog> BlogList = FXCollections.observableArrayList();
        String req = "SELECT * FROM blog";
        try{
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            Blog blog;
            while(rs.next()){
                blog = new Blog(rs.getInt("idBlog"), rs.getString("description"),rs.getDate("date_de_publication"),rs.getString("image") ,rs.getString("categorie"));   
                BlogList.add(blog);
            }
        }catch(SQLException e){}
        return BlogList;
    }
    @FXML
    private void afficher() {
         ObservableList<Blog> BlogList = BlogList();
        idB.setCellValueFactory(new PropertyValueFactory<>("idBlog"));
        descri.setCellValueFactory(new PropertyValueFactory<>("description"));
        datee.setCellValueFactory(new PropertyValueFactory<>("date_de_publication"));
        imagee.setCellValueFactory(new PropertyValueFactory<>("imagee"));
        cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
        tableview.setItems(BlogList);
    }
    @FXML
    private void Ajouter_blog(ActionEvent event) throws IOException, SQLException {
            
        if (date.getEditor().getText().isEmpty()
                | desc.getText().isEmpty()
                | categ.getText().isEmpty()
                | image.getImage() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
            String str2 = date.getValue().toString();

            Date datepub1 = Date.valueOf(str2);
            BlogCRUD sPub = new BlogCRUD();
            Blog pb = new Blog(  desc.getText(),datepub1 , file_path.getText(), categ.getText());
            sPub.Ajouter(pb);

           
            afficher();
        }
        
      
           try {
                                   sendMail("fares.moalla@esprit.tn");
                               
                                   
                                
                                } catch (MessagingException ex) {
                                }  
       
        
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Ajouté un nouveau Blog");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        
    }

    @FXML
    private void supprimer_blog(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            Blog e = new Blog();
            e.setIdBlog(tableview.getSelectionModel().getSelectedItem().getIdBlog());  
            BlogCRUD cs = new BlogCRUD();
            cs.Supprimer2(e);
            afficher();  
        
               TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Supprimé un evenement");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        
    }
        
        
    }

    @FXML
    private void modif_blog(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        
         if (date.getEditor().getText().isEmpty()
                | desc.getText().isEmpty()
                | categ.getText().isEmpty()
               
                | image.getImage() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous Modifier cette publication ?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {
                BlogCRUD sPub = new BlogCRUD();
                String str2 = date.getValue().toString();
                Date datepub1 = Date.valueOf(str2);

                Blog pb = new Blog(desc.getText(),datepub1, file_path.getText(), categ.getText());
                sPub.Modifier(pb);
                afficher();
            }

        }

        
        
        
        
        
        
        
        
        BlogCRUD sPub = new BlogCRUD();
                String str2 = date.getValue().toString();
                Date datepub1 = Date.valueOf(str2);

                Blog pb = new Blog(desc.getText(),datepub1 ,file_path.getText(), categ.getText());
                sPub.Modifier(pb);

                afficher();
        /* BlogCRUD prod = new BlogCRUD();
         java.sql.Date r;*/
       /* r = new java.sql.Date(c.getDate_de_publication().getTime());
        LocalDate date = r.toLocalDate();*/
       /*  String str2 = date.getValue().toString();
         Date date1 = Date.valueOf(str2);
            Blog pd = new Blog(desc.getText(),date1,  file_path.getText(),categ.getText());
            prod.Modifier(pd);
            afficher();*/
           
              
    }
          
        
    
     @FXML
    private void insert_image(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage)bloganchor.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file != null){
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\");
            file_path.setText(path);
            Image imagee = new Image(file.toURI().toString(), 110, 110, false, true);
            image.setImage(imagee);
        }else{
            System.out.println("NO DATA EXIST!");
        }
    }
    
       @FXML
    private void confirmer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        /* BlogCRUD ps = new BlogCRUD();
     java.util.Date date2
                = java.util.Date.from(this.date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                   
                 
        Blog c = new Blog(Integer.parseInt(idlabel.getText()),desc.getText(),sqlDate2,vid.getText(),   
               categ.getText()
                );
                    
        
        ps.Modifier2(c);
       
  
        resetTableData();*/
        
    }


    @FXML
    private void Chercher(ActionEvent event) {
         afficher();
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sort);
        });
    }

    @FXML
    private void select_forum(MouseEvent event) {
    }

  

    @FXML
    private void Ajouter_forum(ActionEvent event) throws SQLException {
          ForumCrud productService = new ForumCrud();

        Forum c = new Forum(this.questions.getText(),Integer.parseInt(this.etat.getText()),   
               Integer.parseInt(nbre_likes.getText())
                );
                    
        
        productService.Ajouter(c);
       
  
        resetTableDataForum();

           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Ajouté un nouveau Forum");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        
        
        
    }

    @FXML
    private void supprimer_forum(ActionEvent event) throws SQLException {
            if (event.getSource() == supp_f) {
            Forum e = new Forum();
            e.setIdForum(tableview2.getSelectionModel().getSelectedItem().getIdForum());  
            ForumCrud cs = new ForumCrud();
            cs.Supprimer2(e);
            resetTableDataForum();  
        
               TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Supprimé un Forum");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        
    }
        
        
    }

    @FXML
    private void modifier_f(ActionEvent event) {
           ForumCrud ps = new ForumCrud();
        Forum c = new Forum(tableview2.getSelectionModel().getSelectedItem().getIdForum(),
                tableview2.getSelectionModel().getSelectedItem().getQuestions(),
             
                 tableview2.getSelectionModel().getSelectedItem().getEtat(),
                tableview2.getSelectionModel().getSelectedItem().getNbre_likes());

         
           questions.setText(c.getQuestions());
           
         idlabel2.setText(Integer.toString(c.getIdForum()));
          etat.setText(Integer.toString(c.getEtat()));
               
                
                 
       nbre_likes.setText(Integer.toString(c.getNbre_likes()));
       
        confirmer2.setVisible(true); 
        
        
        
    }

    @FXML
    private void confirmer2(ActionEvent event) throws SQLException {
      ForumCrud productService = new ForumCrud();

        Forum c = new Forum(Integer.parseInt(idlabel2.getText()),questions.getText(),Integer.parseInt(this.etat.getText()),   
               Integer.parseInt(nbre_likes.getText())
                );
                    
        
        productService.Modifier(c);
       
  
        resetTableDataForum();

           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Modifié un Forum");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));   
        
        
    }

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, IOException {
          if (event.getSource() == PDF) {
            
             printPDF();
            
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("PDF");
            tray.setMessage("PDF");
            tray.setNotificationType(NotificationType.INFORMATION);//
            tray.showAndDismiss(Duration.millis(3000));
        } 
        
        
    }

 

   private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        ;
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("ListQuestions.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Questions"));
        
        PdfPTable pTable = new PdfPTable(1);
       
     //   pTable.addCell("NomEvent");
     
        
        tableview2.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getQuestions()));
         
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                System.out.println(ex);
            }
        });
        
        
        d.close();
        Desktop.getDesktop().open(new File("ListQuestions.pdf"));

    } 
   
   
  public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
         String myAccountEmail = "pialgo11@gmail.com";
        String password = "pialgo5683@";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Vous Avez Ajouté un nouveau Blog");
            message.setText("Vous Avez Ajouté un nouveau Blog");
            return message;
        } catch (MessagingException ex) {
            
        }
        return null;
    }     
   
   
   
   

    
   
    
}
