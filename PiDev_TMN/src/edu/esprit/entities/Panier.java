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
public class Panier {
    private int idPanier;
    private float prix_des_produits;
    private int nbre_produit;
    private String idProduit;

    public Panier() {
    }

    public Panier(float prix_des_produits, int nbre_produit, String idProduit) {
        this.prix_des_produits = prix_des_produits;
        this.nbre_produit = nbre_produit;
        this.idProduit = idProduit;
    }

    public Panier(int idPanier, float prix_des_produits, int nbre_produit, String idProduit) {
        this.idPanier = idPanier;
        this.prix_des_produits = prix_des_produits;
        this.nbre_produit = nbre_produit;
        this.idProduit = idProduit;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public float getPrix_des_produits() {
        return prix_des_produits;
    }

    public void setPrix_des_produits(float prix_des_produits) {
        this.prix_des_produits = prix_des_produits;
    }

    public int getNbre_produit() {
        return nbre_produit;
    }

    public void setNbre_produit(int nbre_produit) {
        this.nbre_produit = nbre_produit;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Panier{" + "idPanier=" + idPanier + ", prix_des_produits=" + prix_des_produits + ", nbre_produit=" + nbre_produit + ", idProduit=" + idProduit + '}';
    }
    
    
    
}
