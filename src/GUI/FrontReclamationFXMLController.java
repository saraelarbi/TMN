/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import services.mail;
import static services.mail.prepareMessage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ffsga
 */
public class FrontReclamationFXMLController implements Initializable {

    @FXML
    private Label description_PB;
    @FXML
    private Button Reclamerr_PB;
    @FXML
    private TextArea TextArea_Desc_PB;
    
    private Connection cnx;
    private PreparedStatement prepare;
    private Statement statement;
    private static int idPB ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public static int getIdPB(int id) {
        idPB = id;
        return idPB;
    }

    @FXML
    private void Reclamerr_PB(ActionEvent event) throws SQLException {
        cnx = MyDB.getInstance().getConnection();
//        I HAVE 5 COLUMNS
        String sql = "INSERT INTO reclamation (description,idPub,idPod,idBlog) VALUES (?,?,?,?)";
        if (TextArea_Desc_PB.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir la Description !");
            alert.showAndWait();

        } else {
                String textArea_DescPub = TextArea_Desc_PB.getText();
                prepare = cnx.prepareStatement(sql);
                prepare.setString(1, textArea_DescPub);
                prepare.setInt(2, idPB);
                prepare.setNull(3, Types.INTEGER);
                prepare.setNull(4, Types.INTEGER);
                prepare.executeUpdate();
                
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Success Reclamation Envoyé!");
            alert.showAndWait();
            
            String txt = "Bonjour  Vous avez eu une nouvelle Reclamation ";
            String sub = "Nouvelle Reclamation ajouté";
            String destinataire = "mohamedaziz.snoussi@esprit.tn";
            mail m = new mail(txt, sub, destinataire);
            Message msg = prepareMessage(m.getSession(), m.getMail(), destinataire, txt, sub);
            System.out.println(msg);
            try {
                Transport.send(msg);
            } catch (MessagingException ex) {
                // Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("MAIL ENVOYEE");
        }
    }
    
}
