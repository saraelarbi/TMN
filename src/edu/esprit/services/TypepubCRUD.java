/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Typepub;
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
public class TypepubCRUD {
    Connection cnx;
    public TypepubCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterTypepub(Typepub tp) {
       try {
            String req = "insert into typepub (cat) values"
                    + " ( '" + tp.getCat()+  "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Typepub Ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierTypepub(Typepub tp) {
        try {
            String req = "update typepub set  cat = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, tp.getCat());
            ps.executeUpdate();
            
            System.out.println("Typepub Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(TypepubCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerTypepub(int id) {
        try {
            String req = "delete from typepub where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Typepub Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(TypepubCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Typepub> afficherTypepub() {
        List<Typepub> list = new ArrayList<>();
        try {
            String req ="select * from typepub";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Typepub tp = new Typepub();
                tp.setId(rs.getInt(1));
                tp.setCat(rs.getString("cat"));
                list.add(tp);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TypepubCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String gettype(int id) { 
        try {
            String sql ="SELECT cat from typepub WHERE id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return rs.getString("cat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypepubCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    public int getid(String type) { 
        try {
            String sql ="SELECT id from typepub WHERE cat like '%"+type+"%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                int i = rs.getInt("id");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypepubCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
        
}
}
