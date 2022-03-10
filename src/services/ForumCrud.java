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
import entities.Blog;
import entities.Forum;
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
public class ForumCrud implements Interface_Services<Forum> {
    Connection cnx2;
    Statement st;
    
    public ForumCrud(){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    
    @Override
    public void Ajouter(Forum f) {
  try {
            String requete2 = "INSERT INTO forum (idForum,questions,etat,nbre_likes)"
                    + "VALUES(?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, f.getIdForum());
            pst.setString(2, f.getQuestions());
            pst.setInt(3, f.getEtat());
            pst.setInt(4, f.getNbre_likes());
            pst.executeUpdate();
            System.out.println("forum ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        }
  
    
    
    public void ajouterForum2(Forum f){
        try {
            String requete = "INSERT INTO panier (idForum, questions, etat, nbre_likes)"
                    + "VALUES('1',55,1,'5hhh')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Forum ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    
    
    }
     @Override
    public List<Forum> Afficher() {
      List<Forum> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM forum";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Forum p =new Forum();
               p.setIdForum(rs.getInt("idForum"));
               p.setQuestions(rs.getString("questions"));
               p.setEtat(rs.getInt("etat"));
               p.setNbre_likes(rs.getInt("nbre_likes"));
               myList.add(p);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

        }
    
   
 
    
     @Override
    public void Modifier(Forum f) {
 try {
            String req = "update forum set questions = ? , etat = ? , nbre_likes = ?  "
                    + "where idForum = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setString(1, f.getQuestions());
            ps.setInt(2, f.getEtat());
            ps.setInt(3, f.getNbre_likes());
            ps.setInt(4, f.getIdForum());
            ps.executeUpdate();
            System.out.println("Forum modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
            }
 
    @Override
    public void Supprimer(int idForum) {
try {
            String requete5 = "delete from forum where idForum = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setInt(1, idForum);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    }
    
    
    
      public void Supprimer2(Forum e) throws SQLException {

        String req = "DELETE FROM forum WHERE idForum =?";
        try {
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1, e.getIdForum());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
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