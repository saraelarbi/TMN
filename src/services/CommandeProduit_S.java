/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
 import entities.Commande;
import entities.Produit;


/**
 *
 * @author ASUS CELERON
 */
public class CommandeProduit_S {
    private Produit P ; 
    private Commande C ; 

    public CommandeProduit_S() {
    }

    public CommandeProduit_S(Produit P, Commande C) {
        this.P = P;
        this.C = C;
    }

    public Produit getP() {
        return P;
    }

    public Commande getC() {
        return C;
    }

    public void setP(Produit P) {
        this.P = P;
    }

    public void setC(Commande C) {
        this.C = C;
    }
    
    
}


