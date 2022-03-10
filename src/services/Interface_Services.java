/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author ASUS CELERON
 * @param <T>
 */
public interface Interface_Services<T> {
    public void Ajouter(T t);
    public void Modifier(T t);
    public void Supprimer(int id);
    public List<T> Afficher();
}
