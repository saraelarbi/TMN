/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import java.util.List;

/**
 *
 * @author ghazi
 */
public interface fonctionservice<ft> {
    public void ajouterFonction(ft ft);
    public void modifierFonction(ft ft);
    public void supprimerFonction(int id);
    public List<ft> afficherFonction();
}
