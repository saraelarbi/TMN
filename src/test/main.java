/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.Produit;
import entities.Commande;
import entities.Panier;
import services.CommandeCRUD;
import services.ProduitCRUD;
import services.PanierCRUD;

import utils.MyConnection;
import javafx.event.ActionEvent;

/**
 *
 * @author ASUS CELERON
 */
public class main {
    public static void main(String[] args) {
      MyConnection mc = MyConnection.getInstance();
       MyConnection mc2 = MyConnection.getInstance();
        //System.out.println(mc.hashCode() + "-----" + mc2.hashCode());
      /* ProduitCRUD pcd= new ProduitCRUD();
      Produit p2 = new Produit("2", "jj", 5, "ll");
        pcd.Ajouter(p2);
       //  pcd.Supprimer(3);
      // pcd.Modifier(p2);
       System.out.println(pcd.Afficher());*/
       
     //PanierCRUD pncd= new PanierCRUD();
      // Panier pn2 = new Panier(2,100000, 8, "ll");
      // pncd.Modifier(pn2);
      // pncd.Ajouter(pn2);
    //  pncd.Supprimer(3);
       // System.out.println(pncd.Afficher());
       
     /*CommandeCRUD ccd= new CommandeCRUD();
      Commande c2 = new Commande();
      ccd.ajouterCommande2(c2);
        System.out.println(ccd.Afficher());*/
        
       // ccd.Supprimer("57");
       
    CommandeCRUD pccd= new CommandeCRUD();
      Commande p22 = new Commande(23, "j5555j", 5, 1);
        pccd.Ajouter(p22);
        System.out.println(pccd.Afficher());
       
        
    
       
    }

    
}
