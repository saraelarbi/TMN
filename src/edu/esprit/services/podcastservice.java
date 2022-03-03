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
public interface podcastservice<p> {
    public void ajouterPod(p p);
    public void modifierPod(p p);
    public void supprimerPod(int id);
    public List<p> afficherPod();
}
