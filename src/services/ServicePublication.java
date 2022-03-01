/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Publication;
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
import utils.MyDB;

/**
 *
 * @author ffsga
 */
public class ServicePublication implements PubService<Publication>{
    
   
    Connection cnx;
    public ServicePublication() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouterPB(Publication pb) {
       try {
            String req = "insert into publication (date_Pub,titre_Pub,desc_Pub,source_Pub,categorie_Pub,image_Pub) values"
                    + " ( '" + pb.getDate_Pub()+ "', '" + pb.getTitre_Pub()+ "','" + pb.getDesc_Pub()+ "','" + pb.getSource_Pub()+ "','" + pb.getCategorie_Pub()+ "','" + pb.getImage_Pub()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Publication Ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierPB(Publication pb) {
        try {
            String req = "update publication set  date_Pub = ? , titre_Pub = ? , desc_Pub = ? , source_Pub = ? , categorie_Pub = ? , image_Pub = ? where idPub = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, (Date) pb.getDate_Pub());
            ps.setString(2, pb.getTitre_Pub());
            ps.setString(3, pb.getDesc_Pub());
            ps.setString(4, pb.getSource_Pub());
            ps.setString(5, pb.getCategorie_Pub());
            ps.setString(6, pb.getImage_Pub());
            ps.setInt(7, pb.getIdPub());
            ps.executeUpdate();
            
            System.out.println("Publication Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerPB(int id) {
        try {
            String req = "delete from publication where idPub = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Publication Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Publication> afficherPB() {
        List<Publication> list = new ArrayList<>();
        try {
            String req ="select * from publication";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Publication pb = new Publication();
                pb.setIdPub(rs.getInt(1));
                pb.setDate_Pub(rs.getDate("date_Pub"));
                pb.setTitre_Pub(rs.getString("titre_Pub"));
                pb.setDesc_Pub(rs.getString("desc_Pub"));
                pb.setSource_Pub(rs.getString("source_Pub"));
                pb.setCategorie_Pub(rs.getString("categorie_Pub"));
                pb.setImage_Pub(rs.getString("image_Pub"));
                list.add(pb);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
