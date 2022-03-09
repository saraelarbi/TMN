/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Coadmin;
import edu.esprit.entities.Fonction;
import edu.esprit.utils.MyConnection;
import edu.esprit.utils.mdpCrypt;
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
public class CoadminCRUD {
    Connection cnx;
    
    public CoadminCRUD(){
    
        cnx = MyConnection.getInstance().getCnx();
    
    }
    
    
    public void ajouterCoadmin() {
        try {
            String requete = "INSERT INTO coadmin (id_fonction,nom,prenom,email,adresse,datenaissance,fonction,password)"
                    + "VALUES('11','benjemaa','ghazi','ghazi@esprit.tn','manzah','30/11/2000','fonc','abcdef')";
            Statement st= cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Coadmin ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    }
    
    
    public void ajouterCoadmin2(Coadmin C){
        try {
            String requete2 = "INSERT INTO coadmin (id_fonction,nom,prenom,email,adresse,datenaissance,password)"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, C.getId_fonction());
            pst.setString(2, C.getNom());
            pst.setString(3, C.getPrenom());
            pst.setString(4, C.getEmail());
            pst.setString(5, C.getAdresse());
            pst.setDate(6, C.getDatenaissance());
          pst.setString(7, C.getPassword());
          
            pst.executeUpdate();
            System.out.println("Coadmin ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
    
    }
    
    public List<Coadmin> afficherCoadmin(){
                List<Coadmin> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM coadmin";
            Statement st = cnx.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Coadmin C =new Coadmin();
               C.setIdC(rs.getInt("idC"));
               C.setNom(rs.getString("nom"));
               C.setPrenom(rs.getString("prenom"));
               C.setEmail(rs.getString("email"));
               C.setAdresse(rs.getString("adresse"));
               C.setDatenaissance(rs.getDate("Datenaissance"));
               C.setId_fonction(rs.getInt("id_fonction"));
               C.setPassword(rs.getString("password"));
               myList.add(C);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

    
    }
    public void modifier(Coadmin C) {
        try {
            String req = "update coadmin set nom = ? , prenom = ?  , email = ? , adresse = ?, datenaissance = ? , id_fonction = ?, password=? where idC = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, C.getNom());
            ps.setString(2, C.getPrenom());
            ps.setString(3, C.getEmail());
            ps.setString(4, C.getAdresse());
            ps.setDate(5, C.getDatenaissance());
            ps.setInt(6, C.getId_fonction());
            ps.setString(7, C.getPassword());
            ps.setInt(8 , C.getIdC());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Coadmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void supprimer(int id) {
        try {
            String req = "delete from coadmin where idC = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Coadmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public Coadmin getCoadminById(int id) throws SQLException {
        Coadmin c = null;
        String req = "SELECT * FROM coadmin WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c = new Coadmin();
            c.setIdC(rs.getInt("idC"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setId_fonction(rs.getInt("id_fonction"));
            c.setEmail(rs.getString("email"));
            c.setAdresse(rs.getString("adresse"));
            c.setDatenaissance(rs.getDate("datenaissance"));
            c.setPassword(rs.getString("password"));
       
        }
        return c;
    }
    public Coadmin searchByPseudoPassU(String email, String password) throws SQLException {
        Coadmin c = null;
        String req = "SELECT * FROM coadmin WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c = new Coadmin();
            c = CoadminCRUD.this.getCoadminById(rs.getInt("idC"));
            if (mdpCrypt.checkpw(c.getPassword(), mdpCrypt.hashpw(password, mdpCrypt.gensalt()))) {
                return c;
            }
        }
        return c;
    }
        public void loggedIn(Coadmin c) {
//        String hachedMdp = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO logged (idC,nom,prenom,email,adresse,datenaissance,fonction,password)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getIdC());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setString(4, c.getEmail());
            pst.setString(5, c.getPassword());
            pst.setInt(6, c.getId_fonction());
            pst.setDate(7, c.getDatenaissance());
            pst.setString(8, c.getAdresse());
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Coadmin logged in");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public String getnomC(int id) { 
        try {
            String sql ="SELECT nom from coadmin WHERE idC="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoadminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    public int getidC(String type) { 
        try {
            String sql ="SELECT idC from coadmin WHERE nom like '%"+type+"%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                int i = rs.getInt("idC");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoadminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
        
}
   
    
}