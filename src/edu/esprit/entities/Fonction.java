/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author ghazi
 */
public class Fonction {
    private int id;
    private String fonction;
    
     
    public Fonction(){
    }
    public Fonction(int id, String fonction) {
        this.id = id;
        this.fonction = fonction;
    }

    public Fonction( String fonction) {
        this.fonction = fonction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public String getFonction() {
        return fonction;
    }

    @Override
    public String toString() {
        return "Fonction{" + "id=" + id + ", fonction=" + fonction + '}';
    }
    
}
