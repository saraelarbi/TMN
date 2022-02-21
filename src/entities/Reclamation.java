/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ffsga
 */
public class Reclamation {
    private int idReclamation;
    private String description;
    private int idPub;
    private int idPod;
    private int idBlog;

    public Reclamation() {
    }
    
    

    public Reclamation(int idReclamation, String description, int idPub, int idPod, int idBlog) {
        this.idReclamation = idReclamation;
        this.description = description;
        this.idPub = idPub;
        this.idPod = idPod;
        this.idBlog = idBlog;
    }

    public Reclamation(String description, int idPub, int idPod, int idBlog) {
        this.description = description;
        this.idPub = idPub;
        this.idPod = idPod;
        this.idBlog = idBlog;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdPub() {
        return idPub;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }

    public int getIdPod() {
        return idPod;
    }

    public void setIdPod(int idPod) {
        this.idPod = idPod;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", description=" + description + ", idPub=" + idPub + ", idPod=" + idPod + ", idBlog=" + idBlog + '}';
    }
    
    
}
