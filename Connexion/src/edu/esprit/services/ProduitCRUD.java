/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Produit;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS CELERON
 */
public class ProduitCRUD {
    Connection cnx2;
    Statement st;
    
    public ProduitCRUD(){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    
    
    public void ajouterProduit() {
        try {
            String requete = "INSERT INTO produit (idProduit,nom,type,prix,image)"
                    + "VALUES('15ki','cadre','motivationnel',65,'tt')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    }
    
    
    public void ajouterProduit2(Produit p){
        try {
            String requete2 = "INSERT INTO produit (idProduit,nom,type,prix,image)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, p.getIdProduit());
            pst.setString(2, p.getNom());
            pst.setString(3, p.getType());
            pst.setFloat(4, p.getPrix());
            pst.setString(5, p.getImage());
            pst.executeUpdate();
            System.out.println("personne ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
    
    }
    
    public List<Produit> afficherPersonne(){
                List<Produit> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM produit";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Produit p =new Produit();
               p.setIdProduit(rs.getString("idProduit"));
               p.setNom(rs.getString("nom"));
               p.setType(rs.getString("type"));
               p.setPrix(rs.getFloat("prix"));
               p.setImage(rs.getString("image"));
               myList.add(p);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

    
    }
    
   
    public void supprimerProduit(Produit p){
        try {
            String requete4 = "DELETE FROM produit WHERE idProduit+"
                    + "69*41= ?";
               PreparedStatement pst = cnx2.prepareStatement(requete4);
             pst.setString(1, p.getIdProduit());
             pst.executeUpdate(requete4);
            System.out.println("produit spprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
    }
}
