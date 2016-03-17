/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import dao.CategorieDAO;
import dao.CoursDAO;
import entities.Categories;
import entities.Cours;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Anouar
 */
public class Anwer {
    public static void main(String[] args) {
        Categories c = new Categories(1,"html");
        List<Cours> l = new ArrayList<Cours>();
//        CategorieDAO cat = new CategorieDAO();
//        l=cat.findAll();
//        l.stream().forEach(e->System.out.println(e));
//        System.out.println(l.size());
Cours cr = new Cours(1, "introduction","bbbbbbbbbb", Date.valueOf("2010-05-26"), Date.valueOf("2008-05-26"), "déficile", c);
        CoursDAO crdao = new CoursDAO();
      l= crdao.findAll();
        l.stream().forEach(e->System.out.println(e));
    //    crdao.;
        
    }
    
}
