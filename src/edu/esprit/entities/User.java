/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;



/**
 *
 * @author ASUS CELERON
 */
public class User {
    private int idU;
    private String nom ;
    private String prenom ; 
    private int numtel;
    private String email; 
    private String adresse; 
    private Date datenaissance;
    private String password;

    public User(){}
    public User(int idU, String nom, String prenom, int numtel, String email, String adresse, Date datenaissance, String password ) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
        this.adresse = adresse;
        this.datenaissance = datenaissance;
        this.password = password;
    }

    public User(String nom, String prenom, int numtel, String email, String adresse, Date datenaissance, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
        this.adresse = adresse;
        this.datenaissance = datenaissance;
        this.password = password;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
     public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" + "idU=" + idU+ ", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", email=" + email + ", adresse=" + adresse +", datenaissance=" + datenaissance +", password=" + password + '}';
    }
    
    
    
    
    
}