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
    private int quantite;
    private String methode_de_paiement;
    private String etat;
        private float total ; 

    private User idU;

    public Commande(){}
    public Commande(int numCmd, int quantite,  String methode_de_paiement, String etat, float total, User idU) {
        this.numCmd = numCmd;
        this.quantite = quantite;
        this.methode_de_paiement = methode_de_paiement;
        this.etat = etat;
        this.total = total;
        this.idU = idU;
    }
     public Commande(int numCmd, int quantite,  String methode_de_paiement, String etat, float total) {
        this.numCmd = numCmd;
        this.quantite = quantite;
        this.methode_de_paiement = methode_de_paiement;
        this.etat = etat;
        this.total = total;
    }
      public Commande(int quantite,  String methode_de_paiement, float total) {
        this.quantite = quantite;
        this.methode_de_paiement = methode_de_paiement;
        this.total = total;
    }
       public Commande(int quantite,  String methode_de_paiement, String etat) {
        this.quantite = quantite;
        this.methode_de_paiement = methode_de_paiement;
        this.etat = etat;
    }
        public Commande(int quantite, String methode_de_paiement) {
        this.quantite = quantite;
        this.methode_de_paiement = methode_de_paiement;
    }
         public Commande(int numCmd, int quantite, String methode_de_paiement, String etat) {
        this.numCmd = numCmd;
        this.quantite = quantite;
        this.methode_de_paiement = methode_de_paiement;
        this.etat = etat;
         }
    public int getNumCmd() {
        return numCmd;
    }

    public void setNumCmd(int numCmd) {
        this.numCmd = numCmd;
    }

   
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
   
    
    public String getMethode_de_paiement() {
        return methode_de_paiement;
    }
    
    public void setMethode_de_paiement(String methode_de_paiement) {
        this.methode_de_paiement = methode_de_paiement; 
    }

     public String getEtat() {
        return etat;
     }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public float getTotal() {
        return total;
    }

    public User getIdU() {
        return idU;
    }
     public void setTotal(float total) {
        this.total = total;
    }


    public void setIdU(User idU) {
        this.idU = idU;
    }

   

    @Override
    public String toString() {
        return "Commande{" + "numCmd=" + numCmd + ", quantite=" + quantite + ", methode_de_paiement=" + methode_de_paiement + ", etat=" + etat + ", idU=" + idU + '}';
    }
    
}
