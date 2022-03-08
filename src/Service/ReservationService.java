/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Evenement;
import Entite.Reservation;
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
public class ReservationService implements IService<Reservation> {

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public ReservationService() {
        Cox = DataSource.getInstance().getConn();
    }


    
    public void Ajouter2(Reservation R, int Eventid) {
 String Req = "insert into Reservation (Date) values (?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setDate(1, R.getDate());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }              
        String Reqq = "insert into Reservation_Evenement (idReservation,Evenement) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Reqq);
            Pst.setInt(1,R.getId_Reservation());
            Pst.setInt(2,Eventid);            
            Pst.executeUpdate();
        } catch (SQLException ex) { 
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void Ajouter(Reservation R) {
        String Req = "insert into Reservation (date,idevent) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setDate(1, R.getDate());
            Pst.setInt(2,R.getIdevent().getId_Evenement());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    public void Ajouter3(Reservation R,Evenement E) {
        String Req = "insert into Reservation (date,idevent) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setDate(1, R.getDate());
            Pst.setInt(2,R.getIdevent().getId_Evenement());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reservation> Afficher() {
        Service.EvenementService es = new EvenementService();
        Evenement e = new Evenement();
        String Req = "select * from Reservation";
        List<Reservation> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                
                e= es.TrouverById(Rs.getInt("idEvent"));
                
                list.add(new Reservation(Rs.getInt("idReservation"), Rs.getDate("Date"),e));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Reservation TrouverById(int id) {
        Reservation R = null;
        String Req = "select * from Reservation where id_Reservation=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                R = new Reservation(Rs.getInt("idReservation"), Rs.getDate("Date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }

    @Override
    public void Modifier(Reservation R) {
        String requeteUpdate = "UPDATE  `Reservation` set `Date`='" + R.getDate() + "' where `Reservation`.`idReservation`='" + R.getId_Reservation() + "' ";
        try {
            Ste = Cox.createStatement();
            Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Supprimer(int id) {
        String Req = " delete from Reservation where idReservation=" + id + " ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
