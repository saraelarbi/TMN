/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Produit;
import edu.esprit.entities.Commande;
import edu.esprit.entities.Panier;
import edu.esprit.services.CommandeCRUD;
import edu.esprit.services.ProduitCRUD;
import edu.esprit.services.PanierCRUD;

import edu.esprit.utils.MyConnection;

/**
 *
 * @author ASUS CELERON
 */
public class mainClass {
    public static void main(String[] args) {
      MyConnection mc = MyConnection.getInstance();
       MyConnection mc2 = MyConnection.getInstance();
        //System.out.println(mc.hashCode() + "-----" + mc2.hashCode());
       //ProduitCRUD pcd= new ProduitCRUD();
       //Produit p2 = new Produit("2", "jj", 5, "ll");
        // pcd.Ajouter(p2);
       //  pcd.Supprimer(3);
      // pcd.Modifier(p2);
       // System.out.println(pcd.Afficher());
       
     PanierCRUD pncd= new PanierCRUD();
       Panier pn2 = new Panier(2,100000, 8, "ll");
       pncd.Modifier(pn2);
      // pncd.Ajouter(pn2);
    //  pncd.Supprimer(3);
        System.out.println(pncd.Afficher());
       
    /* CommandeCRUD ccd= new CommandeCRUD();
     // Commande c2 = new Commande("57","hh555555","jj","hh",5,55,"ll","jj","jjk");
      // ccd.Ajouter(c2);
        System.out.println(ccd.Afficher());
        
        ccd.Supprimer("57");
       */
   
       
        
      
       
      
       
    
    
       
    }

    
}
