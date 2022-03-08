/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entite.Evenement;
import Entite.Rate_Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author admin
 */
public class Rate_EvenementService implements IService<Rate_Evenement>  {
    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public Rate_EvenementService() {
        Cox = DataSource.getInstance().getConn();
    }
  @Override
    public void Ajouter(Rate_Evenement R) {
        String Req = "insert into rate (idEvent,Rate) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1, R.getId_Evenement());
            Pst.setInt(2, R.getRate());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rate_EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Rate_Evenement> Afficher() {
        String Req = "select * from rate";
        List<Rate_Evenement> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Rate_Evenement(Rs.getInt("idEvent"), Rs.getInt("Rate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rate_EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Rate_Evenement TrouverById(int id) {
        Rate_Evenement R = null;
        String Req = "select * from rate where idEvent=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                R = new Rate_Evenement(Rs.getInt("idEvent"),Rs.getInt("Rate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rate_EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }

    @Override
    public void Modifier(Rate_Evenement R) {
        String requeteUpdate = "UPDATE  `rate` set `Rate`='" + R.getRate()+ "' where `rate`.`idEvent`='" + R.getId_Evenement() + "' ";
        try {
            Ste = Cox.createStatement();
            Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(Rate_EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Supprimer(int id) {
        String Req = " delete from rate where idEvent=" + id + " ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(Rate_EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
