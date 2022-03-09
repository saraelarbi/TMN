package edu.esprit.services;


import java.util.List;

/**
 *
 * @author bolba
 */
public interface coadminservice<c> {
    public void ajouterCoadmin(c c);
    public void modifierCoadmin(c c);
    public void supprimerCoadmin(int id);
    public List<c> afficherPublic();
}
