/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.User;
import edu.esprit.entities.Coadmin;
import edu.esprit.entities.Fonction;
import edu.esprit.services.CoadminCRUD;
import edu.esprit.services.UserCRUD;
import edu.esprit.utils.MyConnection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author ASUS CELERON
 */
public class mainClass {
    public static void main(String[] args) throws SQLException {
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode() + "-----" + mc2.hashCode());
       UserCRUD pcd= new UserCRUD();
       CoadminCRUD pcd2= new CoadminCRUD();
       String str="2000-11-30";
       Date datenaissance=Date.valueOf(str);
      // User U2 = new User("test","YEs",99759551,"test@esprit.tn","sousse",datenaissance,"abcdefgh");
      // Coadmin C2 = new Coadmin("Boulbeba","abdeljawed","boulbeba.abdeljawed@esprit.tn","Tunis",datenaissance,"JOURNALIST","hhhh");
       //pcd.ajouterUser2(U2);
       //pcd2.ajouterCoadmin2(C2);
       System.out.println(pcd2.afficherCoadmin());
    }
}