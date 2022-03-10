/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Blog;
import java.security.NoSuchAlgorithmException;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
/**
 *
 * @author ASUS CELERON
 */
public class BlogCRUD  {
    Connection cnx2;
    Statement st;
    
    public BlogCRUD (){
    
        cnx2 = MyConnection.getInstance().getCnx();
    
    }
   
    
    
   
    public void Ajouter(Blog b) {
  try {
            String requete2 = "INSERT INTO blog (idBlog,description,date_de_publication,image,categorie)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, b.getIdBlog());
            pst.setString(2, b.getDescription());
            pst.setDate(3,(java.sql.Date) (Date) b.getDate_de_publication());
            pst.setString(4, b.getimage());
            pst.setString(5, b.getCategorie());
            pst.executeUpdate();
            System.out.println("Blog ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
        }
    

    
    
   /* public void ajouterProduit2(Produit p){
        try {
            String requete = "INSERT INTO produit (idProduit,nom,type,prix,image)"
                    + "VALUES('15ki','cadre','motivationnel',65,'tt')";
            Statement st= cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }*/
    
    
    
    
    
    
    
         public List<Blog> Afficher2() throws SQLException {

        List<Blog> blogg = new ArrayList<>();
        String req = "select * from blog ";
        Statement stm = cnx2.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Blog e = new Blog(rst.getInt("idBlog")
                    , rst.getString("description"), 
                   
                    rst.getDate("date_de_publication"),
                      rst.getString("image"),
                    rst.getString("categorie"));
            blogg.add(e);
        }
        return blogg;
    }
     
    
 
    public List<Blog> Afficher() {
        List<Blog> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM blog";
            Statement st = cnx2.createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               Blog b=new Blog();
               b.setIdBlog(rs.getInt("idBlog"));
               b.setDescription(rs.getString("description"));
               b.setDate_de_publication(rs.getDate("date_de_publication"));
               b.setimage(rs.getString("image"));
               b.setCategorie(rs.getString("categorie"));
               myList.add(b);
           
           
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());   
        }
         return myList;

        }
  
   
    public void Modifier(Blog p) {
 try {
            String req = "update blog set description = ? , date_de_publication = ? , image = ? , categorie = ? "
                    + "where idBlog = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setString(1, p.getDescription());
            ps.setDate(2, (java.sql.Date) p.getDate_de_publication());
            ps.setString(3, p.getimage());
            ps.setString(4, p.getCategorie());
            ps.setInt(5, p.getIdBlog());
            ps.executeUpdate();
            System.out.println("Blog modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
            }
  
  public void Modifier2(Blog e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE blog SET "
                + " description='"+e.getDescription()+"'"
                
                + ", date_de_publication  ='"+(java.sql.Date) (Date) e.getDate_de_publication()+"'"
                + ", image='"+e.getimage()+"'"
                + ", categorie='"+e.getCategorie()+"' where idBlog  = "+e.getIdBlog()+"";
        Statement stm = cnx2.createStatement();
        stm.executeUpdate(req);
    }
   
    public void Supprimer(int idBlog) {
try {
            String requete5 = "delete from Blog where idBlog = ?";
            PreparedStatement ps = cnx2.prepareStatement(requete5);
            ps.setInt(1, idBlog);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    }

    public void Supprimer2(Blog e) throws SQLException {

        String req = "DELETE FROM blog WHERE idBlog =?";
        try {
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1, e.getIdBlog());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
   
}