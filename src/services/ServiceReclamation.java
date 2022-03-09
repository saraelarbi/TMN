/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
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
public class ServiceReclamation implements RecService<Reclamation>{
    Connection cnx;
    public ServiceReclamation() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterRE(Reclamation re) {
        try {
            String req = "insert into reclamation (description,idPub,idPod,idBlog) values"
                    + " ( '" + re.getDescription()+ "', '" + re.getIdPub()+ "','" + re.getIdPod()+ "','" + re.getIdBlog()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("Reclamation Ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierRE(Reclamation re) {
       try {
            String req = "update reclamation set  description = ? , idPub = ? , idPod = ? , idBlog = ? where idReclamation = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, re.getDescription());
            ps.setInt(2, re.getIdPub());
            ps.setInt(3, re.getIdPod());
            ps.setInt(4, re.getIdBlog());
            ps.setInt(5, re.getIdReclamation());
            ps.executeUpdate();
            System.out.println("Reclamation Modifié");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerRE(int id) {
       try {
            String req = "delete from reclamation where idReclamation = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
             System.out.println("Reclamation Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reclamation> afficherRE() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req ="select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation re = new Reclamation();
                re.setIdReclamation(rs.getInt(1));
                re.setDescription(rs.getString("description"));
                re.setIdPub(rs.getInt(1));
                re.setIdPod(rs.getInt(1));
                re.setIdBlog(rs.getInt(1));
                list.add(re);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
