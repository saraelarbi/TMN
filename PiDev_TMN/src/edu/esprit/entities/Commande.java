/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author ASUS CELERON
 */
public class Commande {
    private int numCmd;
    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;
    private float somme_dargent;
    private String email;
    private String idU;
    private String idProduit;

    public Commande(){}
    public Commande(int numCmd, String nom, String prenom, String adresse, int telephone, float somme_dargent, String email, String idU, String idProduit) {
        this.numCmd = numCmd;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.somme_dargent = somme_dargent;
        this.email = email;
        this.idU = idU;
        this.idProduit = idProduit;
    }

    public Commande(String nom, String prenom, String adresse, int telephone, float somme_dargent, String email, String idU, String idProduit) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.somme_dargent = somme_dargent;
        this.email = email;
        this.idU = idU;
        this.idProduit = idProduit;
    }

    public int getNumCmd() {
        return numCmd;
    }

    public void setNumCmd(int numCmd) {
        this.numCmd = numCmd;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public float getSomme_dargent() {
        return somme_dargent;
    }

    public void setSomme_dargent(float somme_dargent) {
        this.somme_dargent = somme_dargent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdU() {
        return idU;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Commande{" + "numCmd=" + numCmd + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", somme_dargent=" + somme_dargent + ", email=" + email + ", idU=" + idU + ", idProduit=" + idProduit + '}';
    }
    
}
