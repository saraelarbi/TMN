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
public class Podcast {
     private int id;
      int id_coadmin;
    private String description;
    private String title;
    private String video;
     
    public Podcast(){
    }
    public Podcast(int id, int id_coadmin, String description, String title, String video) {
        this.id = id;
        this.id_coadmin = id_coadmin;
        this.description = description;
        this.title = title;
        this.video = video;
    }

    public Podcast(int id_coadmin, String description, String title, String video) {
        this.id_coadmin = id_coadmin;
        this.description = description;
        this.title = title;
        this.video = video;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_coadmin() {
        return id_coadmin;
    }
    public void setId_coadmin(int id_coadmin) {
        this.id_coadmin = id_coadmin;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
      

    @Override
    public String toString() {
        return "Podcasts{" + "id=" + id +", id_coadmin=" + id_coadmin + ",description=" + description + ", title=" + title +", video=" + video + ", id_coadmin=" + id_coadmin + '}';
    }
    
    
}
