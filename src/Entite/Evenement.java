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
public class Evenement {
    private int Id_Evenement;
    private String Titre ;
    private String Categorie ;
    private String Description ;
    private String Image ; 
    private float Prix ; 
    private Etat Etat ; 
    private int Nbr_Place ;     
    private float Rate ;  

    public Evenement() {
    }

    public Evenement(String Titre, String Categorie, String Description, String Image, float Prix, Etat Etat, int Nbr_Place, float Rate) {
        this.Titre = Titre;
        this.Categorie = Categorie;
        this.Description = Description;
        this.Image = Image;
        this.Prix = Prix;
        this.Etat = Etat;
        this.Nbr_Place = Nbr_Place;
        this.Rate = Rate;
    }
        public Evenement(String Titre, String Categorie, String Description, String Image, float Prix, Etat Etat, int Nbr_Place) {
        this.Titre = Titre;
        this.Categorie = Categorie;
        this.Description = Description;
        this.Image = Image;
        this.Prix = Prix;
        this.Etat = Etat;
        this.Nbr_Place = Nbr_Place;
    }
    public Evenement(int Id_Evenement, String Titre, String Categorie, String Description, String Image, float Prix, Etat Etat, int Nbr_Place, float Rate) {
        this.Id_Evenement = Id_Evenement;
        this.Titre = Titre;
        this.Categorie = Categorie;
        this.Description = Description;
        this.Image = Image;
        this.Prix = Prix;
        this.Etat = Etat;
        this.Nbr_Place = Nbr_Place;
        this.Rate = Rate;
    }

    public int getId_Evenement() {
        return Id_Evenement;
    }

    public String getTitre() {
        return Titre;
    }

    public String getCategorie() {
        return Categorie;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public float getPrix() {
        return Prix;
    }

    public Etat getEtat() {
        return Etat;
    }

    public int getNbr_Place() {
        return Nbr_Place;
    }

    public float getRate() {
        return Rate;
    }

    public void setId_Evenement(int Id_Evenement) {
        this.Id_Evenement = Id_Evenement;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setEtat(Etat Etat) {
        this.Etat = Etat;
    }

    public void setNbr_Place(int Nbr_Place) {
        this.Nbr_Place = Nbr_Place;
    }

    public void setRate(float Rate) {
        this.Rate = Rate;
    }
}
