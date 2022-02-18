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
public class Produit {
    private int idProduit;
    private String nom ;
    private String type ; 
    private float prix;
    private String image; 

    public Produit(){}
    public Produit(int idProduit, String nom, String type, float prix, String image) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.image = image;
    }

    public Produit(String nom, String type, float prix, String image) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.image = image;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", nom=" + nom + ", type=" + type + ", prix=" + prix + ", image=" + image + '}';
    }
    
    
    
    
    
}
