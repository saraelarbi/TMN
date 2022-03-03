/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Podcast;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class CommentaireCRUD {
    
   
    Connection cnx;
    public CommentaireCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterComm(Commentaire c) {
       try {
            String req = "INSERT INTO commentaire ( `id_podcast` , `description`) VALUES ('"+c.getId_podcast()+"','"+c.getDescription()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Commentaire Ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierComm(Commentaire c) {
        try {
            String req = "update Ccmmentaire set  id_podcast = ? ,description = ? where id_commentaire = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getId_podcast());
            ps.setString(2, c.getDescription());
            ps.executeUpdate();
            
            System.out.println("Commentaire Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerComm(int id) {
        try {
            String req = "delete from commentaire where id_commentaire = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Commentaire> afficherComm() {
        List<Commentaire> list = new ArrayList<>();
        try {
            String req ="select * from commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Commentaire c = new Commentaire();
                c.setId_commentaire(rs.getInt(1));
                c.setId_podcast(rs.getInt("id_podcast"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
