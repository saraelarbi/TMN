/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author ASUS CELERON
 */
import entities.Panier;
import utils.MyConnection;
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
public class PanierCRUD implements Interface_Services<Panier> {
    Connection cnx2;
    Statement st;
    
    public PanierCRUD(){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    
    @Override
    public void Ajouter(Panier p) {
  try {
            String requete2 = "INSERT INTO panier (idPanier, prix_des_produits, nbre_produit, idProduit)"
                    + "VALUES(?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, p.getIdPanier());
            pst.setFloat(2, p.getPrix_des_produits());
            pst.setInt(3, p.getNbre_produit());
            pst.setString(4, p.getIdProduit());
            pst.executeUpdate();
            System.out.println("panier ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        }
  
    
    
    public void ajouterPanier2(Panier p){
        try {
            String requete = "INSERT INTO panier (idPanier, prix_des_produits, nbre_produit, idProduit)"
                    + "VALUES('1',55,1,'5hhh')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Panier ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    
    
    }
     @Override
    public List<Panier> Afficher() {
      List<Panier> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM panier";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Panier p =new Panier();
               p.setIdPanier(rs.getInt("IdPanier"));
               p.setPrix_des_produits(rs.getFloat("Prix_des_produits"));
               p.setNbre_produit(rs.getInt("Nbre_produit"));
               p.setIdProduit(rs.getString("idProduit"));
               myList.add(p);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

        }
    
   
 
    
     @Override
    public void Modifier(Panier p) {
 try {
            String req = "update panier set prix_des_produits = ? , nbre_produit = ? , idProduit = ?  "
                    + "where idPanier = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setFloat(1, p.getPrix_des_produits());
            ps.setInt(2, p.getNbre_produit());
            ps.setString(3, p.getIdProduit());
            ps.setInt(4, p.getIdPanier());
            ps.executeUpdate();
            System.out.println("Panier modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
            }
 
    @Override
    public void Supprimer(int idPanier) {
try {
            String requete5 = "delete from panier where idPanier = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setInt(1, idPanier);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    }
 /*   public void supprimerPanier(String idPanier) {
        try {
            String requete5 = "delete from panier where idPanier = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setString(1, idPanier);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }*/

    

    /* public void ajouterPanier() {
        try {
            String requete = "INSERT INTO panier (idPanier, prix_des_produits, nbre_produit, idProduit)"
                    + "VALUES('1',55,1,'5hhh')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Panier ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    }*/

    /*  public List<Panier> afficherPanier(){
                List<Panier> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM panier";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Panier p =new Panier();
               p.setIdPanier(rs.getString("IdPanier"));
               p.setPrix_des_produits(rs.getFloat("Prix_des_produits"));
               p.setNbre_produit(rs.getInt("Nbre_produit"));
               p.setIdProduit(rs.getString("idProduit"));
               myList.add(p);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

    
    }*/

     /*  public void modifierPanier(Panier p) {
        try {
            String req = "update panier set prix_des_produits = ? , nbre_produit = ? , idProduit = ?  "
                    + "where idPanier = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setFloat(1, p.getPrix_des_produits());
            ps.setInt(2, p.getNbre_produit());
            ps.setString(3, p.getIdProduit());
            ps.setString(4, p.getIdPanier());
            ps.executeUpdate();
            System.out.println("Panier modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
        
    }*/

   
   
}