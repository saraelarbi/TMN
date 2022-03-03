package edu.esprit.services;


import java.util.List;

/**
 *
 * @author bolba
 */
public interface publiciteservice<pb> {
    public void ajouterPublic(pb pb);
    public void modifierPublic(pb pb);
    public void supprimerPublic(int id);
    public List<pb> afficherPublic();
}
