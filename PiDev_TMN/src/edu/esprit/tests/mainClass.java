/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Produit;
import edu.esprit.entities.Commande;
import edu.esprit.entities.Panier;
import edu.esprit.entities.Stock;
import edu.esprit.services.CommandeCRUD;
import edu.esprit.services.ProduitCRUD;
import edu.esprit.services.PanierCRUD;
import edu.esprit.services.StockCRUD;

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
      /* ProduitCRUD pcd= new ProduitCRUD();
       Produit p2 = new Produit("5hhh","888", "jj", 5, "ll");*/
        // pcd.ajouterProduit2(p2);
        //System.out.println(pcd.afficherProduit());
       
      // PanierCRUD pncd= new PanierCRUD();
       //Panier pn2 = new Panier("1",55, 8, "ll");
       // pncd.modifierPanier(pn2);
       // pncd.ajouterPanier();
       
     /* CommandeCRUD ccd= new CommandeCRUD();
       Commande c2 = new Commande(57,"hh555555","jj","hh",5,55,"ll","jj","jjk");
        //System.out.println(ccd.afficherCommande());
        ccd.supprimerCommande(57);*/
       
    /*  StockCRUD scd= new StockCRUD();
       Stock s2 = new Stock("2","5555555555", "5hhh");
       
       scd.supprimerStock("2");*/
      // scd.ajouterStock2(s2);
      
       
      
       
       // CommandeCRUD ccd= new CommandeCRUD();
      // Commande c2 = new Commande(58,"hh","jj","hh",5,55,"ll","jj","jjk");
      // ccd.ajouterCommande();
      
    
       
    }

    
}
