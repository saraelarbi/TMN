/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Produit;
import edu.esprit.services.ProduitCRUD;
import edu.esprit.utils.MyConnection;

/**
 *
 * @author ASUS CELERON
 */
public class mainClass {
    public static void main(String[] args) {
      //  MyConnection mc = MyConnection.getInstance();
        //MyConnection mc2 = MyConnection.getInstance();
        //System.out.println(mc.hashCode() + "-----" + mc2.hashCode());
       ProduitCRUD pcd= new ProduitCRUD();
       Produit p2 = new Produit("hhhh","hh", "jj", 5, "ll");
        pcd.supprimerProduit(p2);
      
      // pcd.ajouterProduit2(p2);
        //System.out.println(pcd.afficherPersonne());
       
    }

    
}
