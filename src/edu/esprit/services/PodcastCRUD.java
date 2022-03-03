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
public class PodcastCRUD {
    Connection cnx;
    public PodcastCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterPod(Podcast p) {
       try {
            String req = "INSERT INTO podcast ( `id_coadmin` , `title`, `description`,`video`) VALUES ('"+p.getId_coadmin()+ "','"+p.getTitle()+ "','"+p.getDescription()+ "','"+p.getVideo()+""+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Podcast Ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierPod(Podcast p) {
        try {
            String req = "update Ccmmentaire set  id_coadmin = ? , title = ?, description = ?, video = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getId_coadmin());
            ps.setString(2, p.getTitle());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getVideo());
            ps.executeUpdate();
            
            System.out.println("Podcast Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(PodcastCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerPod(int id) {
        try {
            String req = "delete from podcast where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Podcast Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(PodcastCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Podcast> afficherPod() {
        List<Podcast> list = new ArrayList<>();
        try {
            String req ="select * from podcast";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Podcast p= new Podcast();
                p.setId(rs.getInt(1));
                p.setId_coadmin(rs.getInt("id_coadmin"));
                p.setTitle(rs.getString("title"));
                p.setDescription(rs.getString("description"));
                p.setVideo(rs.getString("video"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PodcastCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
