/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
//PDF
//mailing

import java.util.Date;

//sms
/**
 *
 * @author ASUS CELERON
 */
public class Blog {
    private int idBlog;
    private String description;
    private Date date_de_publication;
    private String image;
    private String categorie ; 

    public Blog() {
    }

    public Blog(int idBlog, String description, Date date_de_publication, String image, String categorie) {
        this.idBlog = idBlog;
        this.description = description;
        this.date_de_publication = date_de_publication;
        this.image = image;
        this.categorie = categorie;
    }

    public Blog(String description, Date date_de_publication, String image, String categorie) {
        this.description = description;
        this.date_de_publication = date_de_publication;
        this.image = image;
        this.categorie = categorie;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_de_publication() {
        return date_de_publication;
    }

    public void setDate_de_publication(Date date_de_publication) {
        this.date_de_publication = date_de_publication;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

 
    
}
