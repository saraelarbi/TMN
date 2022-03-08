/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author admin
 */
public class Rate_Evenement {
    private int Id_Evenement;
    private int Rate;

    public Rate_Evenement() {
    }

    public Rate_Evenement(int Rate) {
        this.Rate = Rate;
    }

    public Rate_Evenement(int Id_Evenement, int Rate) {
        this.Id_Evenement = Id_Evenement;
        this.Rate = Rate;
    }

    public int getId_Evenement() {
        return Id_Evenement;
    }

    public int getRate() {
        return Rate;
    }

    public void setId_Evenement(int Id_Evenement) {
        this.Id_Evenement = Id_Evenement;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }
    
    
}
