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
 * @author GHAZI
 */
public class Coadmin {
   
    private int idC;
    private int id_fonction;
    private String nom ;
    private String prenom ;
    private String email; 
    private String adresse; 
    private Date datenaissance;
    private String password;

    public Coadmin() {
    }

    public Coadmin(int idC,int id_fonction, String nom, String prenom, String email, String adresse, Date datenaissance, String password) {
        this.idC = idC;
        this.id_fonction = id_fonction;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.datenaissance = datenaissance;
        this.password = password;
    }

    public Coadmin(String nom, String prenom, String email, String adresse, Date datenaissance, int id_fonction, String password) {
        this.id_fonction = id_fonction;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.datenaissance = datenaissance;
        this.password = password;
    }

    public int getIdC() {
        return idC;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public int getId_fonction() {
        return id_fonction;
    }

    public String getPassword() {
        return password;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setId_fonction(int id_fonction) {
        this.id_fonction = id_fonction;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Coadmin{" + "idC=" + idC + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + ", datenaissance=" + datenaissance + ", id_fonction=" + id_fonction + ", password=" + password + '}';
    }

   
    
    
    
    
}

