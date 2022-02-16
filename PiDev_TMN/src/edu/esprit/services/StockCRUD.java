/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

/**
 *
 * @author ASUS CELERON
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS CELERON
 */
import edu.esprit.entities.Stock;
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
public class StockCRUD {
    Connection cnx2;
    Statement st;
    
    public StockCRUD(){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    
    
    public void ajouterStock() {
        try {
            String requete = "INSERT INTO stock (idStock, quantite, idProduit)"
                    + "VALUES('1','55','5hhh')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Stock ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    }
    
    
    public void ajouterStock2(Stock s){
        try {
            String requete2 = "INSERT INTO stock (idStock, quantite, idProduit)"
                    + "VALUES(?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, s.getIdStock());
            pst.setString(2, s.getQuantite());
            pst.setString(3, s.getIdProduit());
            pst.executeUpdate();
            System.out.println("Stock ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
    
    }
    
    public List<Stock> afficherStock(){
                List<Stock> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM stock";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Stock s =new Stock();
               s.setIdStock(rs.getString("IdStock"));
               s.setQuantite(rs.getString("Quantite"));
               s.setIdProduit(rs.getString("idProduit"));
               myList.add(s);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

    
    }
    
    
    public void modifierStock(Stock s) {
        try {
            String req = "update stock set quantite = ? , idProduit = ?  "
                    + "where idStock = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setString(1, s.getQuantite());
            ps.setString(2, s.getIdProduit());
            ps.setString(3, s.getIdStock());
            ps.executeUpdate();
            System.out.println("Stock modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
        
    }

   
    public void supprimerStock(String idStock) {
        try {
            String requete5 = "delete from stock where idStock = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setString(1, idStock);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
   
   
}
