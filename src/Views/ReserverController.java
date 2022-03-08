/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Evenement;
import Entite.Reservation;
import Service.EvenementService;
import Service.Mailling;
import Service.ReservationService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReserverController implements Initializable {
    private static int idd;
    private Evenement EEE;
    public static float prix ; 
    ReservationService ps = new ReservationService() ;
     EvenementService Es = new EvenementService() ;
     
    public static int getIdd(int id) {
        idd = id;
        return idd;
    }
    @FXML
    private Button ReserverBB;
    @FXML
    private DatePicker dateeee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ReserverB(ActionEvent event) {
         Evenement E = Es.TrouverById(idd);
        Reservation R1= new Reservation(Date.valueOf(dateeee.getValue()),E) ;
    
        ps.Ajouter(R1); 
        System.out.println(idd);
        Mailling M = new Mailling() ; 
        M.sendEmail("imennkhaldi@gmail.com",E.getTitre(), "le 04 mars 2022\n" +
"Chère Madame Imen khaldi  ,\n" +
"\n" +
"Le "+ R1.getDate() +" dernier, nous avons organisé cette événement à laquelle vous nous avez honoré de votre présence.\n" +
"\n" +
"Je tenais à vous remercier personnellement pour avoir assisté à cet événement qui nous tenait à cœur. Grâce à votre présence, de nombreux dons ont été récoltés / nous avons bénéficié d'une formidable couverture médiatique / les participants se sont sentis soutenus comme jamais...\n" +
"\n" +
"Outre le plaisir de vous rencontrer et de vous avoir à nos côtés ce jour-là, votre présence a été un encouragement et une véritable récompense.\n" +
"\n" +
"Avec encore une fois ma plus vive reconnaissance, je vous prie de croire, chère Madame sarra laarbi , en l'expression de mes sentiments les plus dévoués.\n" +
"merci .");
    }
    
}
