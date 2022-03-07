/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import entities.Produit;
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
public class CommandeCRUD implements Interface_Services<Commande> {
    Connection cnx2;
    Statement st;
    private ResultSet Rs;
    
    public CommandeCRUD (){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    @Override
    public void Ajouter(Commande c) {
  try {
            String requete2 = "INSERT INTO commande (numCmd,quantite,methode_de_paiement,etat,idU)"
                    + "VALUES(?,?,?,'en cours',?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, c.getNumCmd());
            pst.setInt(2, c.getQuantite());
            pst.setString(3, c.getMethode_de_paiement());
            pst.setString(4, c.getEtat());
            pst.setInt(5, c.getIdU().getIdU());
            pst.executeUpdate();
            System.out.println("commande ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
        }
    
public void Ajouter2(Commande T, int idProduit) {
        int max = 0 ; 
        String Req = "insert into commande (numCmd,quantite,methode_de_paiement,etat,total) values (?,?,?,'en cours',?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(Req);
            pst.setInt(1, T.getNumCmd());
            pst.setInt(2, T.getQuantite());
            pst.setString(3, T.getMethode_de_paiement());
            pst.setFloat(4, T.getTotal());
            
                        
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        max = BigId() ; 
      
        String Reqq = "insert into commande_produit (numCMD,idProduit) values (?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(Reqq);
            pst.setInt(1,max);
            pst.setInt(2,idProduit);            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int BigId()
    {    
        int max = 0 ; 
      String Select = "SELECT MAX(numCmd) FROM commande" ;
       try {
            st = cnx2.createStatement();
            Rs = st.executeQuery(Select);
            while (Rs.next()) {
               max = Rs.getInt("MAX(numCmd)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  System.out.println(max);
        return max ; 
    }
    
   /* public void ajouterProduit2(Produit p){
        try {
            String requete = "INSERT INTO produit (idProduit,nom,type,prix,image)"
                    + "VALUES('15ki','cadre','motivationnel',65,'tt')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }*/
    
    
    
    
    
    
    @Override
    public List<Commande> Afficher() {
        List<Commande> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM commande";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Commande c =new Commande();
               c.setNumCmd(rs.getInt("numCmd"));
               c.setQuantite(rs.getInt("Quantite"));
               c.setMethode_de_paiement(rs.getString("Methode_de_paiement"));
               c.setEtat(rs.getString("Etat"));
               c.setTotal(rs.getFloat("Quantite"));
              // c.setIdU(rs.getUser("idU"));
               myList.add(c);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

        }
  
     @Override
    public void Modifier(Commande T) {
         String requeteUpdate = "UPDATE  `commande` set `quantite`='" + T.getQuantite()+ "',`methode_de_paiement`='" + T.getMethode_de_paiement()+ "',`etat`='" + T.getEtat() + "' where `commande`.`numCmd`='" + T.getNumCmd()+ "' ";

        try {
            st = cnx2.createStatement();
             st.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
    }

    @Override
    public void Supprimer(int numCmd) {
try {
            String requete5 = "delete from commande where numCmd = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setInt(1, numCmd);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    }

   /* public void supprimerCommande(int numCmd) {
        try {
            String requete5 = "delete from commande where numCmd = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setInt(1, numCmd);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }*/

  
    /*public void modifierCommande(Commande c) {
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
        
    }*/

     /* public void ajouterCommande() {
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
    
    }*/

   
    /*public List<Commande> afficherCommande(){
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

    
    }*/
    public Commande TrouverById(int numCmd) {
        Commande C = null;
        String Req = "select * from commande where numCmd=" + numCmd + "";
        try {
            st = cnx2.createStatement();
            Rs = st.executeQuery(Req);
            while (Rs.next()) {
                C = new Commande(Rs.getInt("numCmd"), Rs.getInt("quantite"), Rs.getString("methode_de_paiement"), Rs.getString("etat"),Rs.getInt("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }

   
}