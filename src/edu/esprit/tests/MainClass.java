/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Podcast;
import edu.esprit.entities.Typepub;
import edu.esprit.services.CommentaireCRUD;
import edu.esprit.services.PodcastCRUD;
import edu.esprit.services.TypepubCRUD;
import static edu.esprit.tests.MainClass.main;
import edu.esprit.utils.MyConnection;
import static java.sql.JDBCType.NULL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bolba
 */
public class MainClass {
    public static void main(String[] args){
      MyConnection mc = MyConnection.getInstance();
      MyConnection mc2 = MyConnection.getInstance();
    // System.out.println(mc.hashCode()+" - "+mc2.hashCode());
      CommentaireCRUD ccd = new CommentaireCRUD();
      TypepubCRUD tpc = new TypepubCRUD();
      PodcastCRUD pdc = new PodcastCRUD();
        java.util.Date utilDate2 = new java.util.Date();
        System.out.println(tpc.getid("publicite1"));
     
//       SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Date parsed;
//        Date date2; 
//      try {
//          
//           
//        
//          parsed = format.parse("20221212");
//          date2 = format.parse("20001219");
//          java.sql.Date sqlDate_Pub = new java.sql.Date(parsed.getTime());
//       //Commentaire c = new Commentaire(1,"this comment haha");
//       // Typepub tp = new Typepub(4,"founder type");
     //    Podcast p = new Podcast(2,"tomato","Asgagad","asf");
       // ServiceReclamation sRec = new ServiceReclamation();
        //Reclamation re = new Reclamation(4,"test11",7,0,0);
       
        // Commentaire:
  //      ccd.ajouterComm(c); // ajouter une Commentaire
         //ccd.modifierComm(c);  // modifier une Commentaire
        //ccd.supprimerComm(11);   // supprimer une Commentaire par id
        //tpc.ajouterTypepub(tp);
     // pdc.ajouterPod(p);
  //    System.out.println(ccd.afficherComm()); // afficher les Commentaire
         //System.out.println(tpc.afficherTypepub());//affchertypepub
        // System.out.println(pdc.afficherPod());
         
         
         // Reclamation
        // sRec.ajouterRE(re);
         //sRec.modifierRE(re);
        // sRec.supprimerRE(4);
        // System.out.println(sRec.afficherRE()); 
         // } catch (ParseException ex) {
         // Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
    }
            
    
    }

