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
public interface commentaireservice<c> {
    public void ajouterComm(c c);
    public void modifierComm(c c);
    public void supprimerComm(int id);
    public List<c> afficherComm();
    
}