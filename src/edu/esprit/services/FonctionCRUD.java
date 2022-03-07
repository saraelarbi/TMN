/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Coadmin;
import edu.esprit.entities.Fonction;
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
 * @author bolba
 */
public class FonctionCRUD {
    Connection cnx;
    public FonctionCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterFonction(Fonction f) {
       try {
            String req = "insert into fonction (fonction) values"
                    + " ( '" + f.getFonction()+  "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Fonction Ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierFonction(Fonction f) {
        try {
            String req = "update fonction set  fonction = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(   1, f.getFonction());
            ps.setInt(2 , f.getId());
            ps.executeUpdate();
            
            System.out.println("Fonction Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerFonction(int id) {
        try {
            String req = "delete from fonction where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Fonction Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(FonctionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Fonction> afficherFonction() {
        List<Fonction> list = new ArrayList<>();
        try {
            String req ="select * from fonction";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Fonction tp = new Fonction();
                tp.setId(rs.getInt(1));
                tp.setFonction(rs.getString("fonction"));
                list.add(tp);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String gettype(int id) { 
        try {
            String sql ="SELECT fonction from fonction WHERE id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return rs.getString("fonction");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FonctionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    public int getid(String fonction) { 
        try {
            String sql ="SELECT id from fonction WHERE fonction like '%"+fonction+"%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                int i = rs.getInt("id");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FonctionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
        
}
}
