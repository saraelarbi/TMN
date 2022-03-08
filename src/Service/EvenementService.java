/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Etat;
import Entite.Evenement;
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
public class EvenementService implements IService<Evenement> {

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    /*
    private String Titre ;
    private String Categorie ;
    private String Description ;
    private String Image ; 
    private float Prix ; 
    private Etat Etat ; 
    private int Nbr_Place ;     
    private float Rate ;  
     */
    public EvenementService() {
        Cox = DataSource.getInstance().getConn();
    }

    
    /*
    
    */
    @Override
    public void Ajouter(Evenement E) {
        String Req = "insert into evenement (Titre,Categorie,Description,Image,Prix,Etat,nbreplace,Rate) values (?,?,?,?,?,?,?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setString(1, E.getTitre());
            Pst.setString(2, E.getCategorie());
            Pst.setString(3, E.getDescription());
            Pst.setString(4, E.getImage());
            Pst.setDouble(5, E.getPrix());
            Pst.setString(6, E.getEtat().name());
            Pst.setInt(7, E.getNbr_Place());
            Pst.setDouble(8, E.getRate());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public List<Evenement> Afficher() {
        String Req = "select * from evenement";
        List<Evenement> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Evenement(Rs.getInt("idEvent"), Rs.getString("Titre"),
                        Rs.getString("Categorie"), Rs.getString("Description"), Rs.getString("Image"),
                        (float) Rs.getDouble("Prix"), Etat.valueOf(Rs.getString("Etat")),
                        Rs.getInt("nbreplace"), (float) Rs.getDouble("Rate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Evenement TrouverById(int id) {
        Evenement E = null;
        String Req = "select * from Evenement where idEvent=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                E = new Evenement(Rs.getInt("idEvent"), Rs.getString("Titre"),
                        Rs.getString("Categorie"), Rs.getString("Description"), Rs.getString("Image"),
                        (float) Rs.getDouble("Prix"), Etat.valueOf(Rs.getString("Etat")),
                        Rs.getInt("nbreplace"), (float) Rs.getDouble("Rate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return E;
    }

    @Override
    public void Modifier(Evenement E) {
        String requeteUpdate = "UPDATE  `Evenement` set `Titre`='" + E.getTitre() + "',"
                + "`Categorie`='" + E.getCategorie() + "',`Description`='" + E.getDescription() + "',"
                + "`Image`='" + E.getImage() + "',`Prix`='" + E.getPrix() + "',`Etat`='" + E.getEtat() + "',"
                + "`nbreplace`='" + E.getNbr_Place() + "',`Rate`='" + E.getRate() + "' where `Evenement`.`idEvent`='" + E.getId_Evenement()+ "' ";
        try {
            Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void Supprimer(int id) {
        String Req = " delete from Evenement where idEvent=" + id + " ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }

    
    public List<Evenement> TrieA() {
        String Req = "select * from evenement order by idEvent ";
        List<Evenement> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Evenement(Rs.getInt("idEvent"), Rs.getString("Titre"),
                        Rs.getString("Categorie"), Rs.getString("Description"), Rs.getString("Image"),
                        (float) Rs.getDouble("Prix"), Etat.valueOf(Rs.getString("Etat")),
                        Rs.getInt("nbreplace"), (float) Rs.getDouble("Rate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public List<Evenement> TrieD() {
        String Req = "select * from evenement order by idEvent DESC ";
        List<Evenement> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Evenement(Rs.getInt("idEvent"), Rs.getString("Titre"),
                        Rs.getString("Categorie"), Rs.getString("Description"), Rs.getString("Image"),
                        (float) Rs.getDouble("Prix"), Etat.valueOf(Rs.getString("Etat")),
                        Rs.getInt("nbreplace"), (float) Rs.getDouble("Rate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
