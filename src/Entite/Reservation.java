/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Reservation {
    private int Id_Reservation ; 
    private Date Date ;
    private Evenement event ; 

    public Reservation(int Id_Reservation, Date Date, Evenement event) {
        this.Id_Reservation = Id_Reservation;
        this.Date = Date;
        this.event = event;
    }

    public Reservation(Date Date, Evenement event) {
        this.Date = Date;
        this.event = event;
    }
    

    public Evenement getIdevent() {
        return event;
    }

    public Reservation() {
    }

    public Reservation(Date Date) {
        this.Date = Date;
    }

    public Reservation(int Id_Reservation, Date Date) {
        this.Id_Reservation = Id_Reservation;
        this.Date = Date;
    }

    public void setId_Reservation(int Id_Reservation) {
        this.Id_Reservation = Id_Reservation;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getId_Reservation() {
        return Id_Reservation;
    }

    public Date getDate() {
        return Date;
    }

    
}
