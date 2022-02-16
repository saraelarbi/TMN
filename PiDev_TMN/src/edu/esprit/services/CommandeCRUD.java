/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commande;
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
public class CommandeCRUD {
    Connection cnx2;
    Statement st;
    
    public CommandeCRUD(){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    
    
    public void ajouterCommande() {
        try {
            String requete = "INSERT INTO commande (numCmd,nom,prenom,adresse,telephone,somme_dargent,"
                    + "email,idU,idProduit)"
                    + "VALUES(444,'sara','elarbi','hhh',93743474,8888,'saraelarbi@esprit.tn','dddd','tt')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    }
    
    
    public void ajouterCommande2(Commande c){
        try {
            String requete2 = "INSERT INTO commande (numCmd,nom,prenom,adresse,telephone,somme_dargent,"
                    + "email,idU,idProduit)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, c.getNumCmd());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setString(4, c.getAdresse());
            pst.setInt(5, c.getTelephone());
            pst.setFloat(6, c.getSomme_dargent());
            pst.setString(7, c.getEmail());
            pst.setString(8, c.getIdU());
            pst.setString(9, c.getIdProduit());
            pst.executeUpdate();
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
    
    }
    
    public List<Commande> afficherCommande(){
                List<Commande> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM commande";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Commande c =new Commande();
               c.setNumCmd(rs.getInt("NumCmd"));
               c.setNom(rs.getString("nom"));
               c.setPrenom(rs.getString("Prenom"));
               c.setAdresse(rs.getString("Adresse"));
               c.setTelephone(rs.getInt("Telephone"));
               c.setSomme_dargent(rs.getFloat("Somme_dargent"));
               c.setEmail(rs.getString("Email"));
               c.setIdU(rs.getString("IdU"));
               c.setIdProduit(rs.getString("IdProduit"));
               myList.add(c);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

    
    }
    
   
    public void modifierCommande(Commande c) {
        try {
            String req = "update commande set nom = ? ,prenom = ? ,adresse = ? ,telephone = ?,somme_dargent = ?,"
                    + "email = ?,idU = ?,idProduit = ? "
                    + "where numCmd = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getAdresse());
            ps.setInt(4, c.getTelephone());
            ps.setFloat(5, c.getSomme_dargent());
            ps.setString(6, c.getEmail());
            ps.setString(7, c.getIdU());
            ps.setString(8, c.getIdProduit());
            ps.setInt(9, c.getNumCmd());
            ps.executeUpdate();
            System.out.println("Comande modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
        
    }

   
    public void supprimerCommande(int numCmd) {
        try {
            String requete5 = "delete from commande where numCmd = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setInt(1, numCmd);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
   
}