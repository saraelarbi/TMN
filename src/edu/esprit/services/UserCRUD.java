/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.User;
import edu.esprit.utils.mdpCrypt;
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
public class UserCRUD {
    Connection cnx2;
    
    public UserCRUD(){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
    
    
    public void ajouterUser() {
        try {
            String requete = "INSERT INTO user (idU,nom,prenom,numtel,email,adresse,datenaissance,password)"
                    + "VALUES('1u','mohamed','hama',99419180,'hama@esprit.tn','manzah','01/01/2000',)";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("User ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    
    }
    
    
    public void ajouterUser2(User U){
       //       String hachedMdp = mdpCrypt.hashpw(U.getPassword(), mdpCrypt.gensalt());
        try {
            String requete2 = "INSERT INTO user (idU,nom,prenom,numtel,email,adresse,datenaissance,password)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, U.getIdU());
            pst.setString(2, U.getNom());
            pst.setString(3, U.getPrenom());
            pst.setInt(4, U.getNumtel());
            pst.setString(5, U.getEmail());
            pst.setString(6, U.getAdresse());
            pst.setDate(7, U.getDatenaissance());
            pst.setString(8,U.getPassword());
            pst.executeUpdate();
            System.out.println("User ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
    
    }
    
    public List<User> afficherUser(){
                List<User> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM user";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               User U =new User();
               U.setIdU(rs.getInt("idU"));
               U.setNom(rs.getString("nom"));
               U.setPrenom(rs.getString("prenom"));
               U.setNumtel(rs.getInt("numtel"));
               U.setEmail(rs.getString("email"));
               U.setAdresse(rs.getString("adresse"));
               U.setDatenaissance(rs.getDate("Datenaissance"));
               U.setPassword(rs.getString("password"));
               myList.add(U);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

    
    }
    public void modifier(User U) {
        try {
            String req = "update user set nom = ? , prenom = ? ,numtel = ? , email = ? , adresse = ?, datenaissance = ?, password= ?  where idU = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            
            ps.setString(1, U.getNom());
            ps.setString(2, U.getPrenom()); 
            ps.setInt(3, U.getNumtel());
            ps.setString(4, U.getEmail());
            ps.setString(5, U.getAdresse());
            ps.setDate(6, U.getDatenaissance());
            ps.setString(7, U.getPassword());
            ps.setInt(8, U.getIdU());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void supprimer(int id) {
        try {
            String req = "delete  from user where idU = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public User getUserById(int id) throws SQLException {
        User U = null;
        String req = "SELECT * FROM user WHERE idU = ?";
        PreparedStatement pst = cnx2.prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            U = new User();
            U.setIdU(rs.getInt("idU"));
            U.setNom(rs.getString("nom"));
            U.setPrenom(rs.getString("prenom"));
            U.setNumtel(rs.getInt("numtel"));
            U.setEmail(rs.getString("email"));
            U.setAdresse(rs.getString("adresse"));
            U.setDatenaissance(rs.getDate("datenaissance"));
            U.setPassword(rs.getString("password"));
       
        }
        return U;
    }
     public User TrouverUserParEmail(String email) throws SQLException {
        User U = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = cnx2.prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            U = new User();

            U.setIdU(rs.getInt("idU"));
            U.setNom(rs.getString("nom"));
            U.setPrenom(rs.getString("prenom"));
            U.setNumtel(rs.getInt("numtel"));
            U.setEmail(rs.getString("email"));
            U.setAdresse(rs.getString("adresse"));
            U.setPassword(rs.getString("password"));
            U.setDatenaissance(rs.getDate("datenaissance"));

            System.out.println("Utilisateur trouvé !");
            System.out.println(U);

        }
        return U;
    }
     public User searchByPseudoPassU(String email, String password) throws SQLException {
        User u = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = cnx2.prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new User();
            u = UserCRUD.this.getUserById(rs.getInt("idU"));
            if (mdpCrypt.checkpw(u.getPassword(), mdpCrypt.hashpw(password, mdpCrypt.gensalt()))) {
                return u;
            }
        }
        return u;
    }
        public void loggedIn(User u) {
//        String hachedMdp = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO logged (idU,nom,prenom,email,password,numtel,datenaissance,adresse)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst.setInt(1, u.getIdU());
            pst.setString(2, u.getNom());
            pst.setString(3, u.getPrenom());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getPassword());
            pst.setInt(6, u.getNumtel());
            pst.setDate(7, u.getDatenaissance());
            pst.setString(8, u.getAdresse());
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User logged in");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public void changePassword(String mdp, String email) throws SQLException {

//        String hachedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());
        String req = "UPDATE user SET password = ?  WHERE email = ?";
        PreparedStatement pst = cnx2.prepareStatement(req);
        pst.setString(1, mdp);
        pst.setString(2, email);
        int rowUpdated = pst.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Mdp modifié");
        } else {
            System.out.println("ERR");
        }
    }
         public User getUserlogged() throws SQLException {

        User u = null;
        String req = "SELECT * FROM logged ";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            u = new User();

            u.setIdU(rs.getInt("idU"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setNumtel(rs.getInt("numtel"));
            u.setAdresse(rs.getString("adresse"));
            u.setDatenaissance(rs.getDate("datenaissance"));
            System.out.println("Utilisateur trouvé !");
            System.out.println(u);
        }
        return u;
    }

    public void loggedOut() {
        try {
            String req = "DELETE FROM logged ";
            PreparedStatement pst = cnx2.prepareStatement(req);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User logged out");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void modifier2(User U) {
        try {
            String req = "update user set  nom = ? , prenom = ? ,numtel = ? , email = ? , adresse = ?, datenaissance = ?, password= ? where idU = ? ";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1, U.getNumtel());
            ps.setString(2, U.getNom());
            ps.setString(3, U.getPrenom());
            ps.setInt(4, U.getNumtel());
            ps.setString(5, U.getEmail());
            ps.setString(6, U.getAdresse());
            ps.setDate(7, U.getDatenaissance());
            ps.setString(8, U.getPassword());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
    
    
    
    
    
