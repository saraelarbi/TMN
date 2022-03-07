/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
//stat
//notif

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int stock ; 


    public Produit(){}
     public Produit(String nom, float prix,String type, String image, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.image = image;
        this.stock = stock;
    }
 public Produit(int IdProduit, String nom, float prix,String type, String image, int stock) {
        this.idProduit = IdProduit;
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.image = image;
        this.stock = stock;
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
 public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", nom=" + nom + ", type=" + type + ", prix=" + prix + ", image=" + image + '}';
    }
    
    
    
   
}
