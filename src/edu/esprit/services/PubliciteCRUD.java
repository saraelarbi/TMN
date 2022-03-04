/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Podcast;
import edu.esprit.entities.Publicite;
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
public class PubliciteCRUD {
    Connection cnx;
    public PubliciteCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterPublic(Publicite pb) {
       try {
            String req = "INSERT INTO publicite ( `id_typepub` , `Date_creation`, `domaine`, `description`, `image`, `lettre_motivation`) VALUES ('"+pb.getId_typepub()+"','"+pb.getDate_creation()+"','"+pb.getDomaine()+"','"+pb.getDescription()+"','"+pb.getImage()+"','"+pb.getLettre_motivation()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Publicite Ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierPublic(Publicite pb) {
        try {
            String req = "update publicite set  id_typepub = ? ,Date_creation = ? ,domaine = ? ,description = ? ,image = ? ,lettre_motivation = ? where id_pub =  "+pb.getId_pub();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, pb.getId_typepub());
            ps.setDate(2, (Date) pb.getDate_creation());
            ps.setString(3, pb.getDomaine());
            ps.setString(4, pb.getDescription());
            ps.setString(5, pb.getImage());
            ps.setString(6, pb.getLettre_motivation());
            
            ps.executeUpdate();
            
            System.out.println("Publicite Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerPublic(int id_pub) {
        try {
            String req = "delete from publicite where id_pub = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_pub);
            ps.executeUpdate();
            System.out.println("Publicite Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Publicite> afficherPublic() {
        List<Publicite> list = new ArrayList<>();
        try {
            String req ="select * from publicite";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Publicite pb = new Publicite();
                pb.setId_pub(rs.getInt(1));
                pb.setId_typepub(rs.getInt("id_typepub"));
                pb.setDate_creation(rs.getDate("date_creation"));
                pb.setDomaine(rs.getString("domaine"));
                pb.setDescription(rs.getString("description"));
                pb.setImage(rs.getString("image"));
                pb.setLettre_motivation(rs.getString("lettre_motivation"));
                
                list.add(pb);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
  }
