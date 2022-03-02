/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
//PDF
//mailing
//sms
/**
 *
 * @author ASUS CELERON
 */
public class Commande {
    private int numCmd;
    private float total;
    private String quantite;
    private int idU;
    private int idProduit;

    public Commande(){}
    public Commande(int numCmd, float total, String quantite, int idU, int idProduit) {
        this.numCmd = numCmd;
        this.total = total;
        this.quantite = quantite;
        this.idU = idU;
        this.idProduit = idProduit;
    }

    public Commande( float total, String quantite, int idU, int idProduit) {
        this.total = total;
        this.quantite = quantite;
        this.idU = idU;
        this.idProduit = idProduit;
    }

    public int getNumCmd() {
        return numCmd;
    }

    public void setNumCmd(int numCmd) {
        this.numCmd = numCmd;
    }

   
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Commande{" + "numCmd=" + numCmd + ", total=" + total + ", quantite=" + quantite + ", idU=" + idU + ", idProduit=" + idProduit + '}';
    }
    
}
