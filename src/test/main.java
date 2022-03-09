/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Publication;
import entities.Reclamation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServicePublication;
import services.ServiceReclamation;

/**
 *
 * @author ffsga
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        ServicePublication sPub = new ServicePublication();
        java.util.Date utilDate2 = new java.util.Date();
     
     
       SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        Date parsed;
        Date date2; 
      try {
          parsed = format.parse("20122012");
          date2 = format.parse("12122022");
          java.sql.Date sqlDate_Pub = new java.sql.Date(parsed.getTime());
        Publication pb = new Publication(11,sqlDate_Pub, "mathalan2", "katheee2", "page fb tuniscartage2", "sport2","C:\\Users");

        ServiceReclamation sRec = new ServiceReclamation();
        Reclamation re = new Reclamation(4,"test11",7,0,0);
       
        // Publication:
        //sPub.ajouterPB(pb); // ajouter une publication
         //sPub.modifierPB(pb);  // modifier une publication
        //sPub.supprimerPB(10);   // supprimer une publication par id
         System.out.println(sPub.afficherPB());   // afficher les publications
         
         // Reclamation
        // sRec.ajouterRE(re);
         //sRec.modifierRE(re);
        // sRec.supprimerRE(4);
         System.out.println(sRec.afficherRE()); 
          } catch (ParseException ex) {
          Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    }



    }
}    
