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
public class Stock {
    private String idStock;
    private String quantite;
    private String idProduit;

    public Stock() {
    }

    public Stock(String quantite, String idProduit) {
        this.quantite = quantite;
        this.idProduit = idProduit;
    }

    public Stock(String idStock, String quantite, String idProduit) {
        this.idStock = idStock;
        this.quantite = quantite;
        this.idProduit = idProduit;
    }

    public String getIdStock() {
        return idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Stock{" + "idStock=" + idStock + ", quantite=" + quantite + ", idProduit=" + idProduit + '}';
    }
    
    
    
}
