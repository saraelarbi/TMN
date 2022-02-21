/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Publication;
import entities.Reclamation;
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
        Publication pb = new Publication(10, "//image2", "mathalan2", "katheee2", "page fb tuniscartage2", "sport2");

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

    }

}
