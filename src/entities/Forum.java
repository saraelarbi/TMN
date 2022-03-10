/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
//notif
/**
 *
 * @author ASUS CELERON
 */
public class Forum {
    private int idForum;
    private String questions;
    private int etat;
    private int nbre_likes;

    public Forum() {
    }

    public Forum(int idForum, String questions, int etat, int nbre_likes) {
        this.idForum = idForum;
        this.questions = questions;
        this.etat = etat;
        this.nbre_likes = nbre_likes;
    }

    public Forum(String questions, int etat, int nbre_likes) {
        this.questions = questions;
        this.etat = etat;
        this.nbre_likes = nbre_likes;
    }

    public int getIdForum() {
        return idForum;
    }

    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNbre_likes() {
        return nbre_likes;
    }

    public void setNbre_likes(int nbre_likes) {
        this.nbre_likes = nbre_likes;
    }

  
}
