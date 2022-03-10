/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author bolba
 */
public class Commentaire {
    private int id_commentaire;
    private int id_user;
    private int id_podcast;
    private String description;
    
     
    public Commentaire(){
    }
    public Commentaire(int id_commentaire, int id_user, int id_podcast, String description) {
        this.id_commentaire = id_commentaire;
        this.id_user = id_user;
        this.id_podcast = id_podcast;
        this.description = description;
    }

    public Commentaire(int id_user, int id_podcast, String description) {
        this.id_user = id_user;
        this.id_podcast = id_podcast;
        this.description = description;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public int getId_podcast() {
        return id_podcast;
    }

    public void setId_podcast(int id_podcast) {
        this.id_podcast = id_podcast;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", id_podcast=" + id_podcast + ", description=" + description + '}';
    }

}

