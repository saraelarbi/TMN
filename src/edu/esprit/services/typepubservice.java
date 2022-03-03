/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import java.util.List;

/**
 *
 * @author bolba
 */
public interface typepubservice<tp> {
    public void ajouterTypepub(tp tp);
    public void modifierTypepub(tp tp);
    public void supprimerTypepub(int id);
    public List<tp> afficherTypepub();
}
